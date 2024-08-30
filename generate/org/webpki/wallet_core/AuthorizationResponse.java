package org.webpki.wallet_core;

import static org.webpki.wallet_core.Common.*;

public class AuthorizationResponse implements TableExecutor {
/*
    static final CBORInt PAYMENT_HOST_LABEL    = new CBORInt(3);
    static final CBORInt ACCOUNT_ID_LABEL      = new CBORInt(4);
    static final CBORInt CREDENTIAL_ID_LABEL   = new CBORInt(5);
    static final CBORInt PLATFORM_DATA_LABEL   = new CBORInt(6);
    static final CBORInt LOCATION_LABEL        = new CBORInt(7);
    static final CBORInt TIME_STAMP_LABEL      = new CBORInt(8);
 */
    @Override
    public String getTableString() {
        return new Table()
            .add(PAYMENT_REQUEST_LABEL, "paymentRequest", Types.MAP,
                "Holds <a href='#payment-request'>Payment Request</a> object.")
            .add(PAYMENT_INFO_LABEL, "paymentInfo", Types.MAP,
                "Holds <a href='#payment-information'>Payment Information</a> object.")
            .add(PAYMENT_HOST_LABEL, "paymentHost", Types.TSTR,
                "comment.")
            .add(ACCOUNT_ID_LABEL, "accountId", Types.TSTR,
                "comment.")
            .add(CREDENTIAL_ID_LABEL, "credentialId", Types.TSTR,
                "comment.")
            .add(PLATFORM_DATA_LABEL, "platformData", Types.TSTR,
                "comment.")
            .add(LOCATION_LABEL, "location", Types.TSTR,
                "comment.")
            .add(TIME_STAMP_LABEL, "timeStamp", Types.TSTR,
                "comment.")
             .add(SIGNATURE_LABEL, "signature", Types.MAP,
                "Authorization signature.")
            .toString();
    }

    @Override
    public String getTitle() {
        return "Authorization Response";
    }
}
