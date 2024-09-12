package org.webpki.wallet_core;

public class NonDirectPaymentProfile extends Table {

    static final String NON_DIR_PAY_ID_NAME = "nonDirPayId";

    NonDirectPaymentProfile(String id) {
        super();
        add(MessageCommon.NON_DIR_PAY_ID_LABEL, 
            NON_DIR_PAY_ID_NAME,
            Types.TSTR,
            "Unique Id: <code>" + id + "</code>"); 
    }
}
