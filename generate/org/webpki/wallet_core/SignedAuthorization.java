package org.webpki.wallet_core;

import static org.webpki.wallet_core.MessageCommon.*;

public class SignedAuthorization extends TableExecutor {

    static final String UNENCRYPTED_DATA_NAME     = "unencryptedData";
    static final String RESPONSE_ENCRYPTION__NAME = "responseEncryption";
    static final String ACCOUNT_ID_NAME           = "accountId";
    static final String SERIAL_NUMBER_NAME        = "serialNumber";
    static final String PLATFORM_DATA_NAME        = "platformData";
    static final String WALLET_DATA_NAME          = "walletData";
    static final String LOCATION_NAME             = "location";
    static final String AUTHZ_SIGNATURE_NAME      = "authzSignature";

    @Override
    public String getTableString() {
        return new Table()

            .add(UNENCRYPTED_DATA_LBL, UNENCRYPTED_DATA_NAME, Types.MAP,
                "Holds the ${href.unencrypted-data} object.")

            .add(RESPONSE_ENCRYPTION_LBL, RESPONSE_ENCRYPTION__NAME, Types.MAP,
                "Holds the ${href.response-encryption} object.")

            .add(ACCOUNT_ID_LBL, ACCOUNT_ID_NAME, Types.TSTR,
                CreateDocument.COPY_ATTRIBUTE)

            .add(SERIAL_NUMBER_LBL, SERIAL_NUMBER_NAME, Types.TSTR,
                CreateDocument.COPY_ATTRIBUTE)

            .add(PLATFORM_DATA_LBL, PLATFORM_DATA_NAME, Types.ARRAY,
                "Array holding the name and version of the operating system in " +
                "<code>[0]</code> and <code>[1]</code> respectively, expressed as " +
                "CBOR strings (tstr).")

            .add(WALLET_DATA_LBL, WALLET_DATA_NAME, Types.ARRAY,
                "Array holding the name and version of the <code class='entity'>Wallet</code> " +
                "software in <code>[0]</code> and <code>[1]</code> respectively, expressed as " +
                "CBOR strings (tstr).")

            .add(LOCATION_LBL, LOCATION_NAME, Types.ARRAY,
                "<i>Optional</i>: Array holding the current latitude <code>[0]</code> " +
                "and longitude <code>[1]</code> of the <code class='entity'>Wallet</code> " +
                "device, expressed as CBOR floating point values." +
                "<div style='padding-top:0.5em'>" +
                "This option depends on <code class='entity'>Payer</code> " +
                "privacy settings.</div>")

            .add(AUTHZ_SIGNATURE_LBL, AUTHZ_SIGNATURE_NAME, Types.MAP,
                "Authorization signature using a ${href.csf} object.")
    
            .getTableString();
    }

    @Override
    String getTitle() {
        return "Signed Authorization";
    }


    @Override
    String getBeforeText() {
        return "An " + getTitle() + " consists of a CBOR map wrapped in " +
            "a ${href.cotx} container as follows:" +
            "<div class='webpkifloat'><div style='padding:1em 2em'>" +
            "<code>1010([&quot;" + MessageCommon.SIGNED_AUTHZ_ID + "&quot;,&nbsp;{<br></code>" +
            "<div style='padding:1em 0 1em 2em'><i>CBOR map...</i></div>" +
            "<code>}])</code>" +
            "</div></div>" +
            "Note that the COTX wrapper is included in the signature process as well." +
            "<div style='padding-top:0.5em'>" +
            "The CBOR <code>map</code> keys are as follows:</div>";
    }

    @Override
    String getAfterText() {
        return "For an example, see ${href.signature-validation}." +
            "<p>Note that <code>&quot;" + MessageCommon.SIGNED_AUTHZ_ID + "&quot;</code> " +
            "represents a temporary name allocation.</p>";
    }
}
