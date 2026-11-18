package org.webpki.wallet_core;

import java.util.regex.Pattern;

import org.webpki.cbor.CBORInt;
import org.webpki.cbor.CBORMap;

abstract class MapCommon extends TableExecutor {

    CBORMap mapObject;

    String getString(CBORInt key, Pattern pattern) {
        return MessageCommon.checkString(mapObject.get(key).getString(), pattern);
    }
}
