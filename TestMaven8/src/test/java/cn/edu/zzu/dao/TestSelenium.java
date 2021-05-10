package cn.edu.zzu.dao;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSelenium {
    public static void main(String[] args)  throws Exception{
//设置驱动
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
        //创建ChromeDriver对象
        ChromeDriver driver = new ChromeDriver();
        //创建窗口最大化
        driver.manage().window().maximize();
        //打开网页
        driver.get("http:\\www.baidu.com");

        //1、ID定位，因为id是唯一的能用id不用其他
        //用ID定位输入框
        WebElement id = driver.findElement(By.id("kw"));
        //输入Python
        id.sendKeys("python");
        //用ID定位
        WebElement ById = driver.findElementById("su");
        //点击百度一下
        ById.click();

        //2、name定位
        //用name定位输入框
        WebElement Name = driver.findElementByName("wd");
        //输入奇艺科技
        Name.sendKeys("小强");
        //强制等待
        Thread.sleep(1000);
        //用class定位元素
        driver.findElementByClassName("s_btn").click();
        //关闭网页
        driver.close();

        //3、class定位
        //className定位输入框
        WebElement name = driver.findElementByClassName("s_ipt");
        //输入吾爱破解
        name.sendKeys("吾爱破解");
        //定位点击按钮
        driver.findElementByClassName("s_btn").click();
        //关闭网页
        driver.close();

        //4、超链接定位
        //用linkText等位超链接
        WebElement  xinwen= driver.findElementByLinkText("新闻");
        //点击新闻
        xinwen.click();
        //设置强行等待时间
        Thread.sleep(3000);
        //关闭网页
        driver.close();

        //部分超链接
        driver.get("https://www.baidu.com");
        //用partialLinkText  定位部分超链接
        WebElement tieba = driver.findElementByPartialLinkText("贴");
        //点击贴吧
        tieba.click();
        //设置强制等待
        Thread.sleep(3000);
        //关闭网页
        driver.close();


    }
}
