package com.sulcacorp.lissa.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public final class LogPrint {

    public static void logInicio(Object myClass, String nameMethod) {
        log.info("Inicio ".concat(myClass.getClass().getName()).concat(" ").concat(nameMethod));
    }

    public static void logError(Object myClass, String nameMethod, Exception exception) {
        log.info("Error ".concat(myClass.getClass().getName()).concat(" ").concat(nameMethod), exception.fillInStackTrace());
    }

}
