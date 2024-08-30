package org.webpki.wallet_core;

import static org.webpki.wallet_core.Common.*;

public class PaymentRequest implements TableExecutor {

    @Override
    public String getTableString() {
        return new Table()
            .add(AMOUNT_LABEL, "amount", Types.TSTR,
                "Monetary amount.")
            .add(INSTANCE_LABEL, "instanceId", Types.TSTR,
                "Merchant transaction ID")
            .toString();
    }

    @Override
    public String getTitle() {
        return "Payment Request";
    }
}
