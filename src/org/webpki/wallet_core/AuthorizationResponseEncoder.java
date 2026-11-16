package org.webpki.wallet_core;

import org.webpki.cbor.CBORInt;

public class AuthorizationResponseEncoder {
    public AuthorizationResponseEncoder() {}

    AuthorizationResponseEncoder setPaymentRequest(PaymentRequest paymentRequest) {
        return this;
    }
}
