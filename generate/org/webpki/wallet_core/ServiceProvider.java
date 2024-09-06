package org.webpki.wallet_core;

import static org.webpki.wallet_core.MessageCommon.*;

public class ServiceProvider extends TableExecutor {

    @Override
    String getTableString() {
        return new Table()
            .add(PAYMENT_NETWORK_LABEL, "paymentNetwork", Types.TSTR,
                "Copy of the same attribute in the selected " +
                "virtual card (see " +
                "<a href='#payment-credentials'>Payment&nbsp;Credentials</a>).")

            .add(PAYMENT_SERVICE_LABEL, "paymentService", Types.TSTR,
                "Copy of the same attribute in the selected " +
                "virtual card (see " +
                "<a href='#payment-credentials'>Payment&nbsp;Credentials</a>).")
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
