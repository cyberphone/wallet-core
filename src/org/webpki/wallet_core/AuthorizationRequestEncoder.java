package org.webpki.wallet_core;

import static org.webpki.wallet_core.MessageCommon.*;

import org.webpki.cbor.CBORArray;
import org.webpki.cbor.CBORMap;
import org.webpki.cbor.CBORObject;
import org.webpki.cbor.CBORString;
import org.webpki.cbor.CBORTag;

public class AuthorizationRequestEncoder {

    final CBORObject authorizationRequest;
    
    public AuthorizationRequestEncoder(PaymentRequest paymentRequest,
                                       String[] supportedNetworks,
                                       String optionalReceiptUrl) {
        CBORMap cborMap = new CBORMap();
        cborMap.set(PAYMENT_REQUEST_LBL, paymentRequest.mapObject);
        if (supportedNetworks.length == 0 || supportedNetworks.length > MAX_SUPPORTED_NETWORKS) {
            walletError("\"supportedNetworks\" array size out of range: %d",
                        supportedNetworks.length);
        }
        CBORArray array = new CBORArray();
        for (String network : supportedNetworks) {
            array.add(new CBORString(checkString(network, ANY_STRING_PATTERN)));
        }
        cborMap.set(SUPPORTED_NETWORKS_LBL, array);
        if (optionalReceiptUrl != null) {
            cborMap.set(RECEIPT_URL_LBL, 
                        new CBORString(checkString(optionalReceiptUrl, URL_PATH_PATTERN)));
        }
        authorizationRequest = new CBORTag(AUTHZ_REQUEST_ID, cborMap);
    }

    public byte[] encode() {
        return authorizationRequest.encode();
    }
}
