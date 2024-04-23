package model;

public class Will {
    private Integer code;
    private String epitaph;
    private String funeralsType;

    public Will(Integer code, String epitaph, String funeralsType) {
        this.code = code;
        this.epitaph = epitaph;
        setFuneralsType(funeralsType);
    }
    public Will(String epitaph){
        this(null,epitaph,null);
    }

    public Integer getCode() {
        return code;
    }
    public String getEpitaph() {
        return epitaph;
    }
    public String getFuneralsType() {
        return funeralsType;
    }

    public void setFuneralsType(String funeralsType) {
        this.funeralsType = funeralsType;
    }
}
