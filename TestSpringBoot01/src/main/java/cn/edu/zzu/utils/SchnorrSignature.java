package cn.edu.zzu.utils;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: mulming
 * @ClassName: SchnorrSignature
 * @date: 2020年6月16日 下午9:25:09
 * @Description:schnorr签名
 */
public class SchnorrSignature {
	private static final Logger LOG = LoggerFactory.getLogger(SchnorrSignature.class);
	// 路径前缀
	private static final String PERFIX_PATH = "E:" + "/para/";
	// 存放公共参数
	private static final String PARAM_PATH = PERFIX_PATH + "initParams.properties";
	// 存放公钥的路径
	private static final String PUBLIC_KEY_PATH = PERFIX_PATH + "publicKey.properties";


	
	/**
	 * @Author: mulming
	 * @Description: 系统初始化阶段，把初始化的密码公共参数存放到文件中去
	 * @param blq:选择的q的bit长度
	 * @Date:下午9:28:20
	 */
	public static void initPara(int blq) {
		File  file = new File(PARAM_PATH);
		if(file.exists()) {
			LOG.info("已经存在初始化参数，为不影响已经颁发的证书，如果你强制要重新产生参数，请备份所有文件到其他路径下，并重新生产密钥对并重新签名");
		}else {
			LOG.info("系统初始化中，生产公共参数... ...");
			BigInteger one = new BigInteger("1");
			BigInteger two = new BigInteger("2");
			BigInteger q, qp, p, a, g;
			int certainty = 100;
			SecureRandom sr = new SecureRandom();
			// blq长度的q， q是p-1的素因子 
			//生成BigInteger伪随机数，它可能是（概率不小于1 - 1/2certainty）一个具有指定 bitLength 的素数
			q = new BigInteger(blq, certainty, sr);
			qp = BigInteger.ONE;
			do { // 选择一个素数 p 
				p = q.multiply(qp).multiply(two).add(one);
				if(p.isProbablePrime(certainty))
					break;
				qp = qp.add(BigInteger.ONE);
			} while(true);
			
			while(true) {
				a = (two.add(new BigInteger(blq, 100, sr))).mod(p);// (2+x) mod p
				BigInteger ga = (p.subtract(BigInteger.ONE)).divide(q);// (p-1)/q
				g = a.modPow(ga, p); // a^ga mod p = 1 
				if(g.compareTo(BigInteger.ONE) != 0) // g!=1
					break;
			}
			// 存放公共参数
			List<String> transArryToLi = KeyPairOperate.transArryToLi(new String[] {"blq=" + blq,"q=" + q, "p=" + p, "g=" + g});
			KeyPairOperate.writePublicKeyToFile(PARAM_PATH, transArryToLi, false);
			LOG.debug("... \n 初始化完成！");
		}
	}
	
	/**
	 * @Author: mulming
	 * @Description: 为用户生成公私钥对
	 * @param user：传入用户的身份
	 * @param userAuthPswd: 用户的一个认证密钥
	 * @Return:void
	 * @Date:上午9:32:18
	 */
	public static void generateKeyForUser(String user, String userAuthPswd) {
		File  file = new File(PERFIX_PATH + user + ".properties");
		if(file.exists()) {
			LOG.info(user + "已经颁发了密钥，请备份所有文件到其他路径下，并重新签名");
		}else {
			LOG.info("密钥颁发中，请稍后 \n ... ...");
			BigInteger sk,pk;// 私钥和公钥
			int blq = Integer.parseInt(KeyPairOperate.getDataFromFile(PARAM_PATH, "blq"));
			
			Random rd = new Random(userAuthPswd.hashCode());
			// 根据用户的系统中的认证私钥，产生签名的私钥
			sk = new BigInteger(blq, rd);
			
			// 私钥的话名字命名
			List<String> toLiSK = KeyPairOperate.transArryToLi(new String[] {"sk=" + sk});
			KeyPairOperate.writePublicKeyToFile(PERFIX_PATH + user + ".properties", toLiSK, false);
			
			BigInteger g = new BigInteger(KeyPairOperate.getDataFromFile(PARAM_PATH, "g"));
			BigInteger p = new BigInteger(KeyPairOperate.getDataFromFile(PARAM_PATH, "p"));
			// 公钥
			pk = g.modPow(sk, p);// g^w mod p  -- 注意这儿是正，所以下面验证的时候是用的负
			List<String> toLiPK = KeyPairOperate.transArryToLi(new String[] {user + "=" + pk});
			KeyPairOperate.writePublicKeyToFile(PUBLIC_KEY_PATH, toLiPK, true);
			LOG.info(user + " 密钥颁发完成");
		}
	}
	
	/**
	 * @Author: mulming
	 * @Description: 产生签名
	 * @param text ： 待签名文件路径
	 * @param userAuthPswd：用户的一个认证密钥身份
	 */
	public static void makeSign(String text, String user,String userAuthPswd) {
		LOG.info("文件签名开始 \n ... ...");
		BigInteger q = new BigInteger(KeyPairOperate.getDataFromFile(PARAM_PATH, "q")); // 素数 q
		BigInteger p = new BigInteger(KeyPairOperate.getDataFromFile(PARAM_PATH, "p")); // 素数 p
		BigInteger g = new BigInteger(KeyPairOperate.getDataFromFile(PARAM_PATH, "g")); // q的原根 a
		
		int blq = Integer.parseInt(KeyPairOperate.getDataFromFile(PARAM_PATH, "blq"));
		
		// 私钥
		//BigInteger sk = new BigInteger(KeyPairOperate.getDataFromFile(PERFIX_PATH + user + ".properties", "sk")); // 私钥
		Random rd = new Random(userAuthPswd.hashCode());
		// 根据用户的系统中的认证私钥，产生签名的私钥
		BigInteger sk = new BigInteger(blq, rd);
		
		
		SecureRandom sr = new SecureRandom();
		BigInteger r, x, e, y; 
		r = new BigInteger(q.bitLength(), sr); // 随机数
		x = g.modPow(r, p); // g^r mod p
		// e=H(M||x)
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(text.getBytes());
			md5.update(x.toString().getBytes());
			byte[] digest = md5.digest();
			// e 将BigInteger的符号大小表示法转换成一个BigInteger值
			e = new BigInteger(1, digest); 
			// y s2 = r
			y = (r.subtract(sk.multiply(e))).mod(q);
			List<String> transArryToLi = KeyPairOperate.transArryToLi(new String[] {"e="+e,"y="+y});
			String fileName =PERFIX_PATH + user + "_sign_" + text.hashCode() + ".properties";
			KeyPairOperate.writePublicKeyToFile(fileName, transArryToLi, false);
			LOG.info("文件" + fileName + "签名成功 !");
		} catch (Exception e1) {
			LOG.info(e1.toString());
		}
	}
	
	/**
	 * @Author: mulming
	 * @Description: 验证签名
	 * @param sourceText : 原文件路径
	 * @param user ： 用户名
	 * @Return:void
	 * @Date:上午11:07:04
	 */
	public static void checkSign(String sourceText, String user) {
		LOG.info("验证签名");
		
		BigInteger p = new BigInteger(KeyPairOperate.getDataFromFile(PARAM_PATH, "p")); // 素数 p
		BigInteger g = new BigInteger(KeyPairOperate.getDataFromFile(PARAM_PATH, "g")); // q的原根 a
		
		BigInteger pk = new BigInteger(KeyPairOperate.getDataFromFile(PUBLIC_KEY_PATH, user));//  公钥
		
		String fileName =PERFIX_PATH + user + "_sign_" + sourceText.hashCode() + ".properties"; 
		
		BigInteger e = new BigInteger(KeyPairOperate.getDataFromFile(fileName, "e")); // e 签名信息1： 产生的签名信息
		BigInteger y = new BigInteger(KeyPairOperate.getDataFromFile(fileName, "y"));; // y 签名信息2: 加密后的消息
		
		// 计算的 x'
		BigInteger x1 = g.modPow(y, p); // g^y mod p -- y
		BigInteger x2 = (pk.modPow(e, p)).mod(p); // pk^e mod p 
		BigInteger x = x1.multiply(x2).mod(p); // x1*x2 mod p = (g^y)*(pk^e)mod p
		
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(sourceText.getBytes());
			md5.update(x.toString().getBytes());
			byte[] digest = md5.digest();
			BigInteger h = new BigInteger(1, digest);
			System.out.println("... ...");
			if(e.equals(h))
				LOG.info(user+ "的文件" + sourceText.hashCode() + "验证通过 !");
			else
				LOG.info(user+ "的文件" + sourceText.hashCode() + "验证失败 !");
		} catch (Exception e1) {
			LOG.info(e1.toString());
		}
	}
}