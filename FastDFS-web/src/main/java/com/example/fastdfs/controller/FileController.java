package com.example.fastdfs.controller;

import com.example.fastdfs.pojo.FastDFSFile;
import com.example.fastdfs.utils.FastDFSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * 文件上传controller
 */
@Controller
public class FileController {

    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    /**
     * 文件下载
     */
    @PostMapping("/download")
    public void  fileDownload(@RequestParam("groupName") String groupName, @RequestParam("fileName") String fileName
            , RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response
    ) {
        System.out.println(groupName + "/" + fileName);
        if (groupName.isEmpty() && fileName.isEmpty()) {
            return;
        }
        OutputStream outputStream = null;
        BufferedInputStream bis = null;
        InputStream inputStream = null;
        try {
            //设置响应头
            //1、设置response 响应头
            response.reset(); //设置页面不缓存,清空buffer
            response.setCharacterEncoding("UTF-8"); //字符编码
            response.setContentType("multipart/form-data"); //二进制传输数据
            //设置响应头
            response.setHeader("Content-Disposition", "attachment;fileName=" + UUID.randomUUID().toString() + fileName.substring(fileName.indexOf('.')));
            //response.setContentType("application/force-download");

            //2、文件流
            inputStream = downloadFile(groupName, fileName);
            //3、写出文件--输出流
            outputStream = response.getOutputStream();

            bis = new BufferedInputStream(inputStream);
            //4、执行 写出操作
            byte[] buff = new byte[1024];
            int read = bis.read(buff);

            //通过while循环写出
            while (read != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                read = bis.read(buff);
            }
            //http://47.93.7.196:8888/group1/M00/00/00/rBkudWInJSiAYVyTFOpOrmQChTE013.zip
            //http://47.93.7.196:8888/group1/M00/00/00/rBkudWInI8qAFG0zAABUjHt9Znw892.zip
            //http://47.93.7.196:8888/group1/M00/00/00/rBkudWInGK6AKDDRAALi70teDbY288.jpg
            //http://47.93.7.196:8888/group1/M00/00/00/rBkudWInC_GAKH2lAALi70teDbY526.jpg

            redirectAttributes.addFlashAttribute("message", "下载成功");
            redirectAttributes.addFlashAttribute("path", groupName + "/" + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
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
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 上传文件
     *
     * @param file
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "请选择一个文件上传");
            return "redirect:uploadStatus";
        }
        try {
            //上传文件，拿到返回的文件路径
            String path = saveFile(file);
            redirectAttributes.addFlashAttribute("message", "上传成功"
                    + file.getOriginalFilename());

            //打印路径
            redirectAttributes.addFlashAttribute("path", "路径：" + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:uploadStatus";
    }

    /**
     * 页面跳转
     *
     * @return
     */
    @GetMapping("/downloadStatus")
    public String downloadStatus() {
        return "downloadStatus";
    }

    /**
     * 页面跳转
     *
     * @return
     */
    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    /**
     * 页面跳转
     *
     * @return
     */
    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    /**
     * 页面跳转
     *
     * @return
     */
    @GetMapping("/download")
    public String download() {
        return "download";
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

    /**
     * 上传方法
     *
     * @param multipartFile
     * @return
     * @throws Exception
     */
    public String saveFile(MultipartFile multipartFile) throws Exception {
        String[] fileAbsolutePath = {};
        String fileName = multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream = multipartFile.getInputStream();
        if (inputStream != null) {
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
        fileAbsolutePath = FastDFSClient.upload(file);
        if (fileAbsolutePath == null) {
            logger.error("上传失败");
        }
        String path = FastDFSClient.getTrackerUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
        return path;
    }
}
