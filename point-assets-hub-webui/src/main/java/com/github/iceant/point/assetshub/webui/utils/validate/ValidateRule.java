package com.github.iceant.point.assetshub.webui.utils.validate;

public interface ValidateRule {
    int getErrorCode();

    String getErrorMessage();

    ValidateFunction getValidateFunction();
}
