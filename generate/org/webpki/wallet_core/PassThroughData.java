package org.webpki.wallet_core;

import static org.webpki.wallet_core.MessageCommon.*;

public class PassThroughData extends TableExecutor {

    static final String PAYMENT_REQUEST_NAME  = "paymentRequest";
    static final String PROVIDER_DATA_NAME    = "providerData";

    @Override
    String getTableString() {
        return new Table()
            .add(PAYMENT_REQUEST_LABEL, PAYMENT_REQUEST_NAME, Types.MAP,
                "Through the inclusion of a copy of the ${href.payment-request} " +
                "in the <code class='entity'>Payer</code> authorization, " +
                "this object remains <i>authoritative</i> " +
                "throughout the payment process (except for interbank operations).")

            .add(PROVIDER_DATA_LABEL, PROVIDER_DATA_NAME, Types.MAP,
                "Holds the ${href.provider-data} " +
                "required by the <code class='entity'>Payee</code> for deriving " +
                "which payment network " +
                "to use and how to initiate a compatible payment transaction request.")

            .toString();
    }

    @Override
    String getTitle() {
        return "&quot;Pass Through&quot; Data";
    }

}
