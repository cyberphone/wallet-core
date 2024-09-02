package org.webpki.wallet_core;

import static org.webpki.wallet_core.Common.*;

public class AuthorizationRequest extends TableExecutor {

    static final String PAYMENT_NETWORKS_NAME = "paymentNetworks";

    @Override
    public String getTableString() {
        return new Table()

            .add(PAYMENT_REQUEST_LABEL, PassThroughData.PAYMENT_REQUEST_NAME, Types.MAP,
                "Holds the " +
                new PaymentRequest().getHref() +
                " object.")

            .add(PAYMENT_NETWORKS_LABEL, PAYMENT_NETWORKS_NAME, Types.ARRAY,
                "Holds a non-empty list of payment network/method identifiers that the Payee " +
                "(merchant) supports.  Identifiers are expressed as CBOR strings (tstr).")

            .toString();
    }

    @Override
    String getTitle() {
        return "Authorization Request";
    }
}
