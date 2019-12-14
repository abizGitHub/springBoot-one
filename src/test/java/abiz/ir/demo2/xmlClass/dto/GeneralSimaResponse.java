package abiz.ir.demo2.xmlClass.dto;

import abiz.ir.demo2.xmlClass.AckCodeType;

import java.util.List;

public abstract class GeneralSimaResponse {

    private AckCodeType ack;
    private String correlationId;
    private List<ErrorType> errors;

    public abstract SimaOperationType getSimaOperationType();

    public AckCodeType getAck() {
        return ack;
    }

    public void setAck(AckCodeType ack) {
        this.ack = ack;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public List<ErrorType> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorType> errors) {
        this.errors = errors;
    }
}
