package com.github.iceant.point.assetshub.webui.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IoUtil {
    public static String readAsString(InputStream is, int bufferSize, String charset){
        if(is==null) return null;
        byte[] buffer = new byte[bufferSize<0?1024:bufferSize];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int count =  0;
        try {
            while ((count = is.read(buffer, 0, bufferSize)) != -1) {
                baos.write(buffer, 0, count);
            }
            return baos.toString(charset);
        }catch (Exception err){
            throw new RuntimeException(err);
        }finally {
            try {
                baos.close();
            } catch (IOException e) {
            }
            baos = null;
        }
    }
}
