package cn.edu.zzu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
public class FileController {
    @RequestMapping("/upload1")
    public String upload(@RequestParam("file") CommonsMultipartFile commonsMultipartFile, HttpServletRequest request) throws IOException {
        //获取文件名称
        String originalFilename = commonsMultipartFile.getOriginalFilename();

        //判断名称是否为空
        if ("".equals(originalFilename)) {
            return "redirece:/index.jsp";
        }
        System.out.println("文件名称" + originalFilename);

        //文件路径servlet-api 3.x之前
        String realPath1 = request.getSession().getServletContext().getRealPath("/upload");
        //servlet-api 3.x之后
        String path = request.getServletContext().getRealPath("/upload");

        //创建路径
        File realPath = new File(path);
        if (!realPath.exists()) {
            realPath.mkdir();
        }
        System.out.println("上传保存路径：" + realPath);

        InputStream inputStream = commonsMultipartFile.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(new File(realPath, originalFilename));

        //读写
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = inputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, len);
            fileOutputStream.flush();
        }
        fileOutputStream.close();
        inputStream.close();

        return "redirect:/index.jsp";
    }

    @RequestMapping("/upload")
    public String upload2(@RequestParam("file") CommonsMultipartFile commonsMultipartFile, HttpServletRequest request) throws IOException {
        String originalFilename = commonsMultipartFile.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        String fileName = uuid +"_"+ originalFilename;

        //上传路径保存设置
        String path = request.getServletContext().getRealPath("/upload");

        //日期目录
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy/MM/dd");
        String datePath = simpleDateFormat.format(date);
        System.out.println(datePath);

        String savePath = path +"/"+ datePath;

        File realPath = new File(savePath);
        if (!realPath.exists()) {
            realPath.mkdirs();
        }
        //上传文件地址
        System.out.println("上传文件保存地址：" + realPath);


        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        commonsMultipartFile.transferTo(new File(realPath + "/" + fileName));

        return "redirect:/index.jsp";
    }

    @RequestMapping("/download")
    public String download(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String realPath = request.getServletContext().getRealPath("/upload");
        String fileName = "酷狗写真.jpg";
        //设置响应头
        //1、设置response 响应头
        response.reset(); //设置页面不缓存,清空buffer
        response.setCharacterEncoding("UTF-8"); //字符编码
        response.setContentType("multipart/form-data"); //二进制传输数据
        //设置响应头
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));

        File file = new File(realPath, fileName);
        //2、 读取文件--输入流
        FileInputStream fileInputStream = new FileInputStream(file);
        //3、 写出文件--输出流
        ServletOutputStream outputStream = response.getOutputStream();
        //4、执行 写出操作
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
            outputStream.flush();
        }

        outputStream.close();
        fileInputStream.close();
        return "redirect:/index.jsp";
    }
}
