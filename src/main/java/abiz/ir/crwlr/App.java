package abiz.ir.crwlr;

public class App {

    public static void main(String[] args) throws Exception {
        QueueManager queueManager = new QueueManager(10000, "https://www.theguardian.com/international");
        queueManager
                .ignoreRepetitiveUrl()
                .ignoreUrl("https://www.theguardian.com")
                .start();
    }

}
