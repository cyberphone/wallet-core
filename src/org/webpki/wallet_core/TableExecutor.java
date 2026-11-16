package org.webpki.wallet_core;

import java.util.regex.Pattern;

import org.webpki.cbor.CBORInt;
import org.webpki.cbor.CBORMap;

abstract class TableExecutor {

    abstract String getTableString();

    abstract String getTitle();

    String getBeforeText() {
        return null;
    }

    String getAfterText() {
        return null;
    }

    CBORMap mapObject;

    static final int  MAX_ERROR_MESSAGE       = 100;

    static void walletError(String format, Object... args) {
        String error = String.format(format, args);
        if (error.length() > MAX_ERROR_MESSAGE) {
            error = error.substring(0, MAX_ERROR_MESSAGE - 3) + " ...";
        }
        throw new WalletException(error);
    }

    String getString(CBORInt key, Pattern pattern) {
        String string = mapObject.get(key).getString();
        if (!pattern.matcher(string).matches()) {
            walletError("\"" + string + "\" does not match: " + pattern.toString());
        }
        return string;
    }
}