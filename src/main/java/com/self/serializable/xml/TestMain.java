package com.self.serializable.xml;

/**
 * @author shichen
 * @create 2018/5/4
 * @desc
 */
public class TestMain {

    public static void main(String[] args) {
        Operate<Article> operate = new Operate<>();

        Article article = new Article();
        article.setId(1);
        article.setTitle("test");
        article.setAge(10);
        Article.count = 20;

        operate.serializable(article);

        Article _article = operate.deSerializable();
        System.out.println(_article);
    }

}
