package abiz.ir.crwlr;

import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {


        QueueManager queueManager = new QueueManager(10000, "https://infra-status.gentoo.org/");
        queueManager
                .retry(3)
                .ignoreRepetitiveUrl()
                .ignoreInAnyPartUrl()
                .ignoreUrl("https://planet.gentoo.org/")
                .ignoreUrl("https://wiki.creativecommons.org")
                .ignoreUrl("https://docs.djangoproject.com")
                .ignoreUrl("github.")
                .start();

       //singlePageRead("https://www.ampr.org/grants/");
    }

    private static void singlePageRead(String url) {
        PageReader reader = new PageReader(url);
        try {
            List<String> list = reader.call();
            list.stream().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
