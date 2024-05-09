package model;

import exception.*;

public class Will {
    private Integer code;
    private String epitaph;
    private static Integer EPITAPH_LENGTH = 75;
    private String funeralsType;
    private static Integer FUNERALS_TYPE_LENGTH = 20;

    public Will(Integer code, String epitaph, String funeralsType) throws WillException{
        setCode(code);
        setEpitaph(epitaph);
        setFuneralsType(funeralsType);
    }
    public Will(String epitaph) throws WillException{
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

    public void setCode(Integer code) {
        this.code = code;
    }
    public void setFuneralsType(String funeralsType) throws WillException{
        if(funeralsType.length() <= FUNERALS_TYPE_LENGTH){
            this.funeralsType = funeralsType;
        }
        else{
            throw new WillException("Le type de funérail entré est trop long !\nMaximum " + FUNERALS_TYPE_LENGTH + " charactères");
        }
    }
    public void setEpitaph(String epitaph) throws WillException{
        if(epitaph.length() <= EPITAPH_LENGTH){
            this.epitaph = epitaph;
        }
        else{
            throw new WillException("L'épitaph entré est trop long !\nMaximum " + EPITAPH_LENGTH + " charactères");
        }
    }
}
