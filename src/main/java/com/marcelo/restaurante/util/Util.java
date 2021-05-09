package com.marcelo.restaurante.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.springframework.stereotype.Service;

@Service
public class Util {
    public static String formatarBigDecimalParaMoeda(BigDecimal valor) {
        DecimalFormat fmt = new DecimalFormat("#,###,##0.00");
        return fmt.format(valor);
    }
}
