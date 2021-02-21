package com.github.iceant.point.assetshub.webui.utils.converter;

public class StringToFloat implements IConverter<String, Float>{
    @Override
    public Float convertFromAToB(String s, Float def) {
        if(s==null) return def;
        try{
            return Float.valueOf(s);
        }catch (Exception err){}
        return def;
    }
}