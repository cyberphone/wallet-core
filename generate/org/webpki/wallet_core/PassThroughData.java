package org.webpki.wallet_core;

import static org.webpki.wallet_core.Common.*;

public class PassThroughData extends TableExecutor {

    static final String PAYMENT_REQUEST_NAME = "paymentRequest";
    static final String SERVICE_PROVIDER_NAME    = "serviceProvider";

    @Override
    String getTableString() {
        return new Table()
            .add(PAYMENT_REQUEST_LABEL, PAYMENT_REQUEST_NAME, Types.MAP,
                "By including a copy of the " +
                new PaymentRequest().getHref() +
                " in the user authorization, this object remains <i>authoritative</i> " +
                "throughout the entire payment process.")

            .add(SERVICE_PROVIDER_LABEL, SERVICE_PROVIDER_NAME, Types.MAP,
                "Holds the " +
                new ServiceProvider().getHref() +
                " required by the Payee (merchant) to initiate " +
                "a payment transaction.")

            .toString();
    }

    @Override
    String getTitle() {
        return "&quot;Pass Through&quot; Data";
    }

    @Override
    String getLink() {
        return "pass-through";
    }
}
