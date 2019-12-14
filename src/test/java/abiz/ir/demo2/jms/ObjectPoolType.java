package abiz.ir.demo2.jms;

import abiz.ir.demo2.xmlClass.AbstractResponseType;

public class ObjectPoolType {

    private Object obj;
    private boolean hasResponse;
    private AbstractResponseType abstractResponseType;

    public AbstractResponseType getAbstractResponseType() {
        return abstractResponseType;
    }

    public void setAbstractResponseType(AbstractResponseType abstractResponseType) {
        this.abstractResponseType = abstractResponseType;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public boolean isHasResponse() {
        return hasResponse;
    }

    public void setHasResponse(boolean hasResponse) {
        this.hasResponse = hasResponse;
    }
}
