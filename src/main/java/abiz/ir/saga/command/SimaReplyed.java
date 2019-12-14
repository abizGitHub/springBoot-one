package abiz.ir.saga.command;

public class SimaReplyed implements Reply {

    private String msgId;

    public SimaReplyed(String msgId) {
        this.msgId = msgId;
    }

    @Override
    public String getMsgId() {
        return msgId;
    }

}
