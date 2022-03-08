package io.ipfs.api;

import io.ipfs.multiaddr.MultiAddress;
import io.ipfs.multihash.Multihash;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 注意：查询和下载可以是其他节点数据，添加删除必须是本节点数据
 */
public class ApiTest01 {
    //实例化ipfs节点对象
    private final IPFS ipfs = new IPFS(new MultiAddress("/ip4/127.0.0.1/tcp/5001"));
    static final Map<String, String> cids = new LinkedHashMap<>();

    static {
        cids.put("index.html", "QmVts3YjmhsCSqMv8Thk1CCy1nnpCbqEFjbkjS7PEzthZE");
        cids.put("html", "QmfPRsChuVCsnN4PGLaDCngvjqCoc9KGkxea4hgiq6qitk");
    }

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    /**
     * 不同次数测试
     */
    @Test
    public void testUploadCount(){
        for (int j = 1; j <= 10; j++) {
            long startTime = System.currentTimeMillis();
            int i;
            for (i = 1; i <= j; i++) {
                try {
                    add("D:\\test\\11.pdf");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("上传轮次："+ (i-1) +" 上传总时间："+ (System.currentTimeMillis() - startTime) / 1000.00 + "秒");
        }
    }
    /**
     * 不同大小测试
     */
    @Test
    public void testuploadSize() {

        File dir = new File("D:\\test");
        String[] children = dir.list();
        if (children == null) {
            System.out.println("目录不存在或它不是一个目录");
        } else {
            for (int i = 0; i < children.length; i++) {
                String filename = children[i];
                String fullPath = dir + "\\" + filename;
                long startTime = System.currentTimeMillis();
                try {
                    add(fullPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("上传时间："+ (System.currentTimeMillis() - startTime) / 1000.00 + "秒");
                System.out.println(fullPath);
            }
        }
    }

    /**
     * 上传文件夹
     *
     * @throws Exception
     */
    @Test
    public void testRecursiveOnlyHash() throws Exception {

        Path path = Paths.get("src/test/resources/html");
        NamedStreamable file = new NamedStreamable.FileWrapper(path.toFile());
        printTime("开始上传文件夹");
        List<MerkleNode> tree = ipfs.add(file, false, true);
        printTree(tree);
        printTime("结束上传文件夹");

        /**
         * 断言
         */
        Assert.assertEquals(14, tree.size());
        Assert.assertEquals("html/css", tree.get(7).name.get());
        Assert.assertEquals(cids.get("html"), tree.get(7).hash.toBase58());
    }

    /**
     * 获取节点文件夹中文件列表
     */
    @Test
    public void getfiles() throws IOException {
        //文件夹hash
        String hash = "QmPoGcqRwnf9x3LWXGDtaiwqNgz7ou8yQz7Mqur3DVmP3S";
        printTime("开始查询文件夹");
        Multihash filePointer = Multihash.fromBase58(hash);
        List<MerkleNode> ls = ipfs.ls(filePointer);
        for (MerkleNode node : ls) {
            System.out.println(node.toString());
        }
        printTime("结束查询文件夹");
    }

    /**
     * 添加文件
     *
     * @throws IOException
     */
    public void add(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        //上传文件
        NamedStreamable.FileWrapper savefile = new NamedStreamable.FileWrapper(path.toFile());
        List<MerkleNode> tree = ipfs.add(savefile);
        printTree(tree);
    }

    /**
     * 文件下载并保存
     *
     * @throws IOException
     */
    @Test
    public void download() throws IOException {
        printTime("开始下载文件-11M");
        //文件hash  CID
        String hash = "QmdypZDpwWnkAT52DwwCywaMFPARDa7RtZzc4wEsYCsutH";
        //保存的文件路径
        String filename = "D:/123123123.pdf";

        Multihash filePointer = Multihash.fromBase58(hash);

        byte[] data = ipfs.cat(filePointer);
        if (data != null) {
            File file = new File(filename);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data, 0, data.length);
            fos.flush();
            fos.close();
        }
        printTime("结束下载文件-5M");
    }

    /**
     * 文件删除
     *
     * @throws IOException
     */
    @Test
    public void delete() throws IOException {
        //文件hash
        String hash = "QmfMTNdRjruNFg7G7856XYxGAQPY91oiCSYajhTQqVgjEL";
        Multihash filePointer = Multihash.fromBase58(hash);
        List<Multihash> rm = ipfs.pin.rm(filePointer);
        System.out.println(rm.get(0));//返回结果文件内容的 hash
    }

    /**
     * 添加新节点
     */
    @Test
    public void addPeers() throws IOException {
        MultiAddress multiAddress = new MultiAddress("/ip4/47.93.7.196/udp/4001/quic/p2p/12D3KooWBgyvtfosb96uJBSQkpeaHRJCbRrc2jxYfBHY6L1xPwmZ");
        List<MultiAddress> peers = ipfs.bootstrap.add(multiAddress);
        System.out.println(peers.get(0).toString());
    }

    /**
     * 删除节点
     *
     * @throws IOException
     */
    @Test
    public void rmPeers() throws IOException {
        MultiAddress multiAddress = new MultiAddress("/ip4/47.93.7.196/udp/4001/quic/p2p/12D3KooWBgyvtfosb96uJBSQkpeaHRJCbRrc2jxYfBHY6L1xPwmZ");
        List<MultiAddress> rm = ipfs.bootstrap.rm(multiAddress);
        System.out.println(rm.get(0).toString());
    }

    /**
     * 输出Tree
     *
     * @param tree
     */
    public void printTree(List<MerkleNode> tree) {
        for (MerkleNode merkleNode : tree) {
            System.out.println(merkleNode);
        }
    }

    /**
     * 时间戳
     */
    public void printTime(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        String time = sdf.format(new Date());
        System.out.print("+++++++++++" + str + "+++++++++++   ");
        System.out.println("日期：" + time + "毫秒数：" + currentTimeMillis);
    }

}