package com.github.iceant.point.assetshub.webui.utils.converter;

public interface IConverter<A, B> {
    public B convertFromAToB(A a, B def);
}