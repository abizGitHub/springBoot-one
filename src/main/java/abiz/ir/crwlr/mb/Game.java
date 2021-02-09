package abiz.ir.crwlr.mb;

public class Game implements GameMBean {
    private String playerName;
    public String publicName;

    @Override
    public void playFootball(String clubName) {
        System.out.println(
                this.playerName + " playing football for " + clubName);
    }

    @Override
    public String getPlayerName() {
        System.out.println("Return playerName " + this.playerName);
        return playerName;
    }

    @Override
    public void setPlayerName(String playerName) {
        System.out.println("Set playerName to value " + playerName);
        this.playerName = playerName;
    }

    @Override
    public void m1() {
        publicName = Thread.currentThread().toString();
        System.out.println("m1:" + Thread.currentThread());
        System.out.println("------------------");
        //Arrays.stream(Thread.currentThread().getStackTrace()).forEach(System.out::println);
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1:" + Thread.currentThread() + "  DONE");
        System.out.println("------------------");
    }

    public void m2() {
        System.out.println("m2:" + Thread.currentThread());
    }

}
