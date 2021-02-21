package com.github.iceant.point.assetshub.webui.utils.validate.functions;

import java.math.BigDecimal;

public class NumberUtil {
    public static int compareTo(Number n1, Number n2) {
        // ignoring null handling
        BigDecimal b1 = new BigDecimal(n1.doubleValue());
        BigDecimal b2 = new BigDecimal(n2.doubleValue());
        return b1.compareTo(b2);
    }
}
