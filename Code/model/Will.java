package model;

public class Will {
    private Integer code;
    private String epitaph;
    private String funeralsType;

    public Will(Integer code, String epitaph, String funeralsType) {
        this.code = code;
        this.epitaph = epitaph;
        this.funeralsType = funeralsType;
    }

    public Integer getCode() {
        return code;
    }
}
