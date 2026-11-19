package org.webpki.wallet_core;

import java.util.ArrayList;

import static org.webpki.wallet_core.MessageCommon.*;

import org.webpki.cbor.CBORMap;
import org.webpki.cbor.CBORObject;
import org.webpki.cbor.CBORTag;

public class AuthorizationRequestDecoder {

    final PaymentRequest paymentRequest;

    final String[] supportedNetworks;

    final String optionalReceiptUrl;

    public AuthorizationRequestDecoder(CBORObject authorizationRequest) {
        CBORTag.COTX cotx = authorizationRequest.getTag().getCOTX();
        if (!cotx.id.equals(AUTHZ_REQUEST_ID)) {
            walletError("Unexpected COTX id: %s", cotx.id);
        }
        CBORMap cborMap = cotx.getObject().getMap();
        paymentRequest = new PaymentRequest(cborMap.get(PAYMENT_REQUEST_LBL));
        ArrayList<CBORObject> array =
            getArray(cborMap.get(SUPPORTED_NETWORKS_LBL), -MAX_SUPPORTED_NETWORKS);
        supportedNetworks = new String[array.size()];
        if (supportedNetworks.length == 0 || supportedNetworks.length > MAX_SUPPORTED_NETWORKS) {
            walletError("Array size out of range: %d", supportedNetworks.length);           
        }
        for (int i = 0; i < array.size(); i++) {
            supportedNetworks[i] = checkString(array.get(i).getString(), ANY_STRING_PATTERN);
        }
        optionalReceiptUrl = cborMap.containsKey(RECEIPT_URL_LBL) ?
            checkString(cborMap.get(RECEIPT_URL_LBL).getString(), URL_PATH_PATTERN) : null;
        authorizationRequest.checkForUnread();
    }
}
