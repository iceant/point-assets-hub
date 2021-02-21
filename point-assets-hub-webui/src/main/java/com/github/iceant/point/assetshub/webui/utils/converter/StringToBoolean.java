package com.github.iceant.point.assetshub.webui.utils.converter;

public class StringToBoolean implements IConverter<String, Boolean>{
    @Override
    public Boolean convertFromAToB(String s, Boolean def) {
        if(s==null) return def;
        try{
            return Boolean.valueOf(s);
        }catch (Exception err){}
        return def;
    }
}