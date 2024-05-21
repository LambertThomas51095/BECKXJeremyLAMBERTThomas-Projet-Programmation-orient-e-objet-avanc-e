package model;

import exception.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if(funeralsType != null && funeralsType.length() <= FUNERALS_TYPE_LENGTH) {
            Pattern pattern = Pattern.compile(RegularExpression.NO_SPACE_PATTERN.toString());
            Matcher matcher = pattern.matcher(funeralsType);
            if (!matcher.find()) {
                this.funeralsType = funeralsType;
            } else {
                throw new WillException("Vous devez entrer obligatoirement un type de funérail !");
            }
        }
        else if(funeralsType != null){
            throw new WillException("Le type de funérail entré est trop long !\nMaximum " + FUNERALS_TYPE_LENGTH + " charactères");
        }
    }
    public void setEpitaph(String epitaph) throws WillException{
        if(epitaph.length() <= EPITAPH_LENGTH){
            Pattern pattern = Pattern.compile(RegularExpression.NO_SPACE_PATTERN.toString());
            Matcher matcher = pattern.matcher(epitaph);
            if(!matcher.find()){
                this.epitaph = epitaph;
            }else{
                throw new WillException("Vous devez entrer obligatoirement un épitaphe !");
            }
        }
        else{
            throw new WillException("L'épitaph entré est trop long !\nMaximum " + EPITAPH_LENGTH + " charactères");
        }
    }
}