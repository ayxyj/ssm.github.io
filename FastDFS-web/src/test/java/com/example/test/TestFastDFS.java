package com.example.test;

import com.example.fastdfs.controller.FileController;
import com.example.fastdfs.pojo.FastDFSFile;
import com.example.fastdfs.utils.FastDFSClient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class TestFastDFS {
    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    /**
     * 不同次数测试
     */
    @Test
    public void testUploadCount(){
        for (int j = 1; j <= 10; j++) {
            long startTime = System.currentTimeMillis();
            int i;
            for (i = 1; i <= j; i++) {
                String path = testFileUpload("D:\\test\\5.pdf");
                //logger.info("上传成功：" + path);
            }
            logger.info("上传轮次："+ (i-1) +" 上传总时间："+ (System.currentTimeMillis() - startTime) / 1000.00 + "秒");
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
                String fdfsPath = testFileUpload(fullPath);
                logger.info("上传时间："+ (System.currentTimeMillis() - startTime) / 1000.00 + "秒");
                System.out.println(fullPath);
                System.out.println(fdfsPath);
            }
        }

        //String s = ("H:\\\\迅雷下载\\\\215672_7808_PG444_Win10_WHQL_KBL292_DAX2_ADCTL_YOGA5.zip");
        //System.out.println(s);
    }

    /**
     * 文件下载
     */
    public void testFileDownload(String ext, String filePath) {
        //保存的文件路径
        String file = "D:/fastdfs/" + UUID.randomUUID() + ext;
        String groupName = "group1";
        String fileName = filePath;
        InputStream inputStream = FastDFSClient.downloadFile(groupName, fileName);
        OutputStream outputStream = null;
        try {
            //文件输出流
            outputStream = new FileOutputStream(file);
            //字节数组
            byte[] buffer = new byte[1024];
            int bytesRead;
            //read from is to buffer
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 文件上传
     */
    public String testFileUpload(String filePath) {
        Path paths = Paths.get(filePath);
        File file = paths.toFile();
        String fileName = file.getName();
        String ext = fileName.substring(fileName.indexOf(".") + 1);
        String[] fileAbsolutePath = {};

        byte[] bytes = fileToByte(file);

        FastDFSFile fastDFSFile = new FastDFSFile(fileName, bytes, ext);
        fileAbsolutePath = FastDFSClient.upload(fastDFSFile);
        if (fileAbsolutePath == null) {
            logger.error("上传失败");
        }
        String path = null;
        try {
            path = FastDFSClient.getTrackerUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * 文件File类型转byte[]
     *
     * @param filePath 文件路径
     * @return
     */
    public static byte[] file2Byte(String filePath) {
        byte[] fileBytes = null;
        FileInputStream fis = null;
        try {
            File file = new File(filePath);
            fis = new FileInputStream(file);
            fileBytes = new byte[(int) file.length()];
            fis.read(fileBytes);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileBytes;
    }

    /**
     * 文件File类型转byte[]
     *
     * @param file 文件对象
     * @return
     */
    public static byte[] fileToByte(File file) {
        byte[] fileBytes = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            fileBytes = new byte[(int) file.length()];
            fis.read(fileBytes);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileBytes;
    }

    /**
     * 下载
     *
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public InputStream downloadFile(String groupName, String remoteFileName) {

        InputStream inputStream = FastDFSClient.downloadFile(groupName, remoteFileName);
        return inputStream;
    }

}
