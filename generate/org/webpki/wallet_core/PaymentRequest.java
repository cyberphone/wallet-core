package org.webpki.wallet_core;

import static org.webpki.wallet_core.MessageCommon.*;

public class PaymentRequest extends TableExecutor {

    static final String AMOUNT_NAME      = "amount";
    static final String COMMON_NAME_NAME = "commonName";
    static final String CURRENCY_NAME    = "currency";
    static final String INSTANCE_ID_NAME = "instanceId";
    static final String NON_DIRECT_NAME  = "nonDirect";

    @Override
    String getTableString() {
        return new Table()
            .add(AMOUNT_LABEL, AMOUNT_NAME, Types.TSTR,
                "Monetary amount.")

            .add(CURRENCY_LABEL, CURRENCY_NAME, Types.TSTR,
                "Currency.")

            .add(COMMON_NAME_LABEL, COMMON_NAME_NAME, Types.TSTR,
                "Payee (merchant) common name to be shown in UIs.")
    
            .add(INSTANCE_ID_LABEL, INSTANCE_ID_NAME, Types.TSTR,
                "Payee (merchant) transaction Id.")

            .add(NON_DIRECT_LABEL, NON_DIRECT_NAME, Types.MAP,
                "<i>Optional</i>: Non-direct payment request.")

           .toString();
    }

    @Override
    String getTitle() {
        return "Payment Request";
    }
}
