package org.webpki.wallet_core;

import static org.webpki.wallet_core.Common.*;

public class ServiceProvider extends TableExecutor {

    @Override
    String getTableString() {
        return new Table()
            .add(PAYMENT_NETWORK_LABEL, "paymentNetwork", Types.TSTR,
                "Payment network/method identifier.")
            .add(PAYMENT_SERVICE_LABEL, "paymentService", Types.TSTR,
                "Payment service URL or host name.")
            .toString();
    }

    @Override
    String getTitle() {
        return "Service Provider Information";
    }

    @Override
    String getLink() {
        return "service-provider";
    }
}
