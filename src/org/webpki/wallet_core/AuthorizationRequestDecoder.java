package org.webpki.wallet_core;

import static org.webpki.wallet_core.MessageCommon.*;

import org.webpki.cbor.CBORMap;
import org.webpki.cbor.CBORObject;

public class AuthorizationRequestDecoder {

    public AuthorizationRequestDecoder(CBORObject authorizationRequest) {
        CBORMap cborMap = authorizationRequest.getMap();
        paymentRequest = new PaymentRequest(cborMap.get(PAYMENT_REQUEST_LBL));
        optionalReceiptUrl = cborMap.containsKey(RECEIPT_URL_LBL) ?
            checkString(cborMap.get(RECEIPT_URL_LBL).getString(), URL_PATTERN) : null;
    }

    final PaymentRequest paymentRequest;

    String[] supportedNetworks;

    final String optionalReceiptUrl;
}
