package org.webpki.wallet_core;

import java.util.regex.Pattern;

import org.webpki.cbor.CBORInt;
import org.webpki.cbor.CBORMap;
import org.webpki.cbor.CBORObject;

abstract class MapCommon extends TableExecutor {

    CBORMap mapObject;

    MapCommon(CBORObject cborObject) {
        mapObject = cborObject.getMap();
    }

    public byte[] encode() {
        return mapObject.encode();
    }

    String getMapString(CBORInt key, Pattern pattern) {
        return MessageCommon.checkString(mapObject.get(key).getString(), pattern);
    }
}
