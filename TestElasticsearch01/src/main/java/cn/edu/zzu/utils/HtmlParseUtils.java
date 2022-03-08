package cn.edu.zzu.utils;

import cn.edu.zzu.entity.Content;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class HtmlParseUtils {

    private static final String URL_PATH = "https://search.jd.com/Search?keyword=";

    public static void main(String[] args) {
        try {
            new HtmlParseUtils().parseKey("java").forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Content> parseKey(String keyword) throws IOException {
        Document document = Jsoup.parse(new URL(URL_PATH + keyword), 3000);
        Element j_goodsList = document.getElementById("J_goodsList");
        Elements li = j_goodsList.getElementsByTag("li");
        ArrayList<Content> contents = new ArrayList<>();
        for (Element element : li) {
            String name = element.getElementsByClass("p-name").eq(0).text();
            String img = element.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = element.getElementsByClass("p-price").eq(0).text();
            String shopnum = element.getElementsByClass("p-shopnum").eq(0).text();

            //Elements links = element.select("a[href]"); // 具有 href 属性的链接
            //Elements pngs = element.select("img[src$=.png]");// 所有引用 png 图片的元素

/*            System.out.println(img);
            System.out.println(price);
            System.out.println(name);
            System.out.println(shopnum);
            System.out.println("========================");*/
            contents.add(new Content(name, img, price, shopnum));
        }
        return contents;
    }
}
