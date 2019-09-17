package itsurena.ir.saga.command;

public class CallSimaCommand implements Command{

    private String msgId;

    public CallSimaCommand(String msgId) {
        this.msgId = msgId;
    }

    @Override
    public String getMsgId() {
        return msgId;
    }

}
