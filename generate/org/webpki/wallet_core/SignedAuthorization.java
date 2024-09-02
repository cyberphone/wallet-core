package org.webpki.wallet_core;

import static org.webpki.wallet_core.Common.*;

public class SignedAuthorization extends TableExecutor {

    static final String PASS_THROUGH_NAME    = "passThrough";
    static final String PAYEE_HOST_NAME      = "payeeHost";
    static final String ACCOUNT_ID_NAME      = "accountId";
    static final String SERIAL_NUMBER_NAME   = "serialNumber";
    static final String PLATFORM_DATA_NAME   = "platformData";
    static final String WALLET_SOFTWARE_NAME = "walletSoftware";
    static final String LOCATION_NAME        = "location";
    static final String TIME_STAMP_NAME      = "timeStamp";
    static final String SIGNATURE_NAME       = "signature";

    @Override
    public String getTableString() {
        return new Table()

            .add(PASS_THROUGH_LABEL, PASS_THROUGH_NAME, Types.MAP,
                "Holds the " +
                new PassThroughData().getHref() +
                " object.")

            .add(PAYEE_HOST_LABEL, PAYEE_HOST_NAME, Types.TSTR,
                "Host name or IP address of invoking Payee (merchant).")

            .add(ACCOUNT_ID_LABEL, ACCOUNT_ID_NAME, Types.TSTR,
                "Account identifier associated with the virtual card.")

            .add(SERIAL_NUMBER_LABEL, SERIAL_NUMBER_NAME, Types.TSTR,
                "Serial number of the virtual card.")

            .add(PLATFORM_DATA_LABEL, PLATFORM_DATA_NAME, Types.MAP,
                "Hardware and operating system.")

            .add(WALLET_SOFTWARE_LABEL, WALLET_SOFTWARE_NAME, Types.MAP,
                "Wallet software and version.")

            .add(LOCATION_LABEL, LOCATION_NAME, Types.ARRAY,
                            "<i>Optional</i>: Array holding longitude <code>[0]</code> " +
                            "and latitude <code>[1]</code>, expressed as CBOR floats.")

            .add(TIME_STAMP_LABEL, TIME_STAMP_NAME, Types.TSTR,
                            "ISO date-time string using UTC (T) or " +
                            "local time (Z) format.")

            .add(SIGNATURE_LABEL, SIGNATURE_NAME, Types.MAP,
                "Authorization signature.")
    
            .toString();
    }

    @Override
    String getTitle() {
        return "Signed Authorization";
    }
}
