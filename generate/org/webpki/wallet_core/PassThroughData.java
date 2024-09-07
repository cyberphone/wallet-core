package org.webpki.wallet_core;

import static org.webpki.wallet_core.MessageCommon.*;

public class PassThroughData extends TableExecutor {

    static final String PAYMENT_REQUEST_NAME  = "paymentRequest";
    static final String SERVICE_PROVIDER_NAME = "serviceProvider";

    @Override
    String getTableString() {
        return new Table()
            .add(PAYMENT_REQUEST_LABEL, PAYMENT_REQUEST_NAME, Types.MAP,
                "By including a copy of the ${href.payment-request} " +
                "in the <code style='color:darkgreen'>Payer</code> authorization, " +
                "this object remains <i>authoritative</i> " +
                "throughout the payment process (except for interbank operations).")

            .add(SERVICE_PROVIDER_LABEL, SERVICE_PROVIDER_NAME, Types.MAP,
                "Holds the ${href.service-provider} " +
                "required by the Payee (merchant) for deriving which payment network " +
                "to use and initiating a compatible payment transaction request.")

            .toString();
    }

    @Override
    String getTitle() {
        return "&quot;Pass Through&quot; Data";
    }

}
