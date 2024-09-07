package org.webpki.wallet_core;

import static org.webpki.wallet_core.MessageCommon.*;

public class ServiceProvider extends TableExecutor {

    static final String PAYMENT_NETWORK_NAME = "paymentNetwork";
    static final String PAYMENT_SERVICE_NAME = "paymentService";

    @Override
    String getTableString() {
        return new Table()
            .add(PAYMENT_NETWORK_LABEL, PAYMENT_NETWORK_NAME, Types.TSTR,
                CreateDocument.COPY_ATTRIBUTE)

            .add(PAYMENT_SERVICE_LABEL, PAYMENT_SERVICE_NAME, Types.TSTR,
                CreateDocument.COPY_ATTRIBUTE)

            .toString();
    }

    @Override
    String getTitle() {
        return "Service Provider";
    }

}
