package abiz.ir.saga;

public class OneState {

    private String serviceId;
    private Boolean isNasimReplyed;
    private Boolean isUmsReplyed;
    private Boolean isSimaReplyed;
    private Integer nasimResponseCode;
    private Integer umsResponseCode;
    private Integer simaResponseCode;

    public OneState(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public Boolean getNasimReplyed() {
        return isNasimReplyed;
    }

    public void setNasimReplyed(Boolean nasimReplyed) {
        isNasimReplyed = nasimReplyed;
    }

    public Boolean getUmsReplyed() {
        return isUmsReplyed;
    }

    public void setUmsReplyed(Boolean umsReplyed) {
        isUmsReplyed = umsReplyed;
    }

    public Boolean getSimaReplyed() {
        return isSimaReplyed;
    }

    public void setSimaReplyed(Boolean simaReplyed) {
        isSimaReplyed = simaReplyed;
    }

    public Integer getNasimResponseCode() {
        return nasimResponseCode;
    }

    public void setNasimResponseCode(Integer nasimResponseCode) {
        this.nasimResponseCode = nasimResponseCode;
    }

    public Integer getUmsResponseCode() {
        return umsResponseCode;
    }

    public void setUmsResponseCode(Integer umsResponseCode) {
        this.umsResponseCode = umsResponseCode;
    }

    public Integer getSimaResponseCode() {
        return simaResponseCode;
    }

    public void setSimaResponseCode(Integer simaResponseCode) {
        this.simaResponseCode = simaResponseCode;
    }

    public Boolean completed() {
        return isNasimReplyed && isSimaReplyed; // and ....
    }
}
