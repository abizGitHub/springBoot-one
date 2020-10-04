package abiz.ir.crwlr;

public class App {

    public static void main(String[] args) throws Exception {
        QueueManager queueManager = new QueueManager(10,"https://jadi.ir/" );
        queueManager.start();
    }
}
