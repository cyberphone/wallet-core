package org.webpki.wallet_core;

import static org.webpki.wallet_core.MessageCommon.*;

public class UnencryptedData extends TableExecutor {

    static final String PAYMENT_REQUEST_NAME  = "paymentRequest";
    static final String PROVIDER_INFO_NAME    = "providerInfo";

    @Override
    String getTableString() {
        return new Table()
            .add(PAYMENT_REQUEST_LBL, PAYMENT_REQUEST_NAME, Types.MAP,
                "Through the inclusion of a copy of the ${href.payment-request} " +
                "in the <code class='entity'>Payer</code> authorization, " +
                "this object remains <i>authoritative</i> " +
                "throughout the payment process (except for interbank operations).")

            .add(PROVIDER_INFO_LBL, PROVIDER_INFO_NAME, Types.MAP,
                "Holds the ${href.provider-info} " +
                "required by the <code class='entity'>Payee</code> for deriving " +
                "which payment network " +
                "to use and how to initiate a compatible payment transaction request.")

            .getTableString();
    }

    @Override
    String getTitle() {
        return "Unencrypted Data";
    }

}
