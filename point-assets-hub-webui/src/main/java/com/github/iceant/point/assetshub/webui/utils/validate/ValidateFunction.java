package com.github.iceant.point.assetshub.webui.utils.validate;

@FunctionalInterface
public interface ValidateFunction<V> {
    ValidateResult apply(ValidateRule rule, V value);
}
