package org.webpki.wallet_core;

import static org.webpki.wallet_core.MessageCommon.*;

public class AuthorizationRequest extends TableExecutor {

    static final String SUPPORTED_NETWORKS_NAME = "supportedNetworks";
    static final String RECEIPT_URL_NAME        = "receiptUrl";

    @Override
    public String getTableString() {
        return new Table()

            .add(PAYMENT_REQUEST_LBL, UnencryptedData.PAYMENT_REQUEST_NAME, Types.MAP,
                "${href.payment-request} is the core " +
                "<code class='entity'>Payee</code> request object.")

            .add(SUPPORTED_NETWORKS_LBL, SUPPORTED_NETWORKS_NAME, Types.ARRAY,
                "Non-empty list of payment network/method identifiers " +
                "that the <code class='entity'>Payee</code> supports. " +
                "Network identifiers are expressed as CBOR strings (<code>tstr</code>)." +
                "<div style='padding-top:0.5em'>" +
                "See also <kbd>" + ProviderInfo.NETWORK_ID_NAME + "</kbd> in the " +
                "${href.credential-database}.</div>")

            .add(RECEIPT_URL_LBL, RECEIPT_URL_NAME, Types.TSTR,
                "<i>Optional</i>: URL to a <code class='entity'>Payee</code> " +
                "receipt service." +
                "<div style='padding-top:0.5em'>See also ${href.receipts}.</div>")

            .getTableString();
    }

    @Override
    String getTitle() {
        return "Authorization Request";
    }

    @Override
    String getBeforeText() {
        return "An " + getTitle() + " consists of a CBOR <code>map</code> object, " +
            "embedded by a ${href.cotx} wrapper:" +
            "<div class='webpkifloat'><div style='padding:1em 2em'>" +
            "<code>1010([&quot;" + MessageCommon.AUTHZ_REQUEST_ID + "&quot;,&nbsp;{<br></code>" +
            "<div style='padding:1em 0 1em 2em'><i>CBOR map...</i></div>" +
            "<code>}])</code>" +
            "</div></div>";
    }
 
    @Override
    String getAfterText() {
        return "The " + getTitle() + " represents the primary " +
            "<code class='entity'>Payee</code> to <code class='entity'>Wallet</code> message.";
    }
}
