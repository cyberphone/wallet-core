package org.webpki.wallet_core;

import org.webpki.cbor.CBORInt;

public class AuthorizationRequestEncoder {
    public AuthorizationRequestEncoder() {}

    AuthorizationRequestEncoder setPaymentRequest(PaymentRequest paymentRequest) {
        return this;
    }
}
