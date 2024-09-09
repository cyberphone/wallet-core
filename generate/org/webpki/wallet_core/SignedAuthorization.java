package org.webpki.wallet_core;

import static org.webpki.wallet_core.MessageCommon.*;

public class SignedAuthorization extends TableExecutor {

    static final String PASS_THROUGH_NAME    = "passThrough";
    static final String PAYEE_HOST_NAME      = "payeeHost";
    static final String ACCOUNT_ID_NAME      = "accountId";
    static final String SERIAL_NUMBER_NAME   = "serialNumber";
    static final String PLATFORM_DATA_NAME   = "platformData";
    static final String WALLET_DATA_NAME     = "walletData";
    static final String LOCATION_NAME        = "location";
    static final String TIME_STAMP_NAME      = "timeStamp";
    static final String SIGNATURE_NAME       = "signature";

    @Override
    public String getTableString() {
        return new Table()

            .add(PASS_THROUGH_LABEL, PASS_THROUGH_NAME, Types.MAP,
                "Holds the ${href.pass-through-data} object.")

            .add(PAYEE_HOST_LABEL, PAYEE_HOST_NAME, Types.TSTR,
                "Host name or IP address of the invoking <code class='entity'>Payee</code>, " +
                "derived from step #1 in the sequence diagram.")

            .add(ACCOUNT_ID_LABEL, ACCOUNT_ID_NAME, Types.TSTR,
                CreateDocument.COPY_ATTRIBUTE)

            .add(SERIAL_NUMBER_LABEL, SERIAL_NUMBER_NAME, Types.TSTR,
                CreateDocument.COPY_ATTRIBUTE)

            .add(PLATFORM_DATA_LABEL, PLATFORM_DATA_NAME, Types.ARRAY,
                "Array holding the name and version of the operating system in " +
                "<code>[0]</code> and <code>[1]</code> respectively, expressed as " +
                "CBOR strings (tstr).")

            .add(WALLET_DATA_LABEL, WALLET_DATA_NAME, Types.ARRAY,
                "Array holding the name and version of the <code class='entity'>Wallet</code> " +
                "software in <code>[0]</code> and <code>[1]</code> respectively, expressed as " +
                "CBOR strings (tstr).")

            .add(LOCATION_LABEL, LOCATION_NAME, Types.ARRAY,
                "<i>Optional</i>: Array holding latitude <code>[0]</code> " +
                "and longitude <code>[1]</code>, expressed as CBOR floating point values.")

            .add(TIME_STAMP_LABEL, TIME_STAMP_NAME, Types.TSTR,
                "ISO date-time string [${href.rfc3339}] " +
                "using UTC (T) or local time (Z) format.")

            .add(SIGNATURE_LABEL, SIGNATURE_NAME, Types.MAP,
                "Authorization signature using a ${href.csf} object.")
    
            .toString();
    }

    @Override
    String getTitle() {
        return "Signed Authorization";
    }

    @Override
    String getAfterText() {
        return "For an example, see ${href.signature-validation}.";
    }
}
