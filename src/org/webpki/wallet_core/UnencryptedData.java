package org.webpki.wallet_core;

import static org.webpki.wallet_core.MessageCommon.*;

public class UnencryptedData extends TableExecutor {

    static final String PAYMENT_REQUEST_NAME  = "paymentRequest";
    static final String PROVIDER_INFO_NAME    = "providerInfo";
    static final String LANGUAGE_NAME         = "language";
    static final String PAYEE_HOST_NAME       = "payeeHost";

    @Override
    String getTableString() {
        return new Table()

            .add(PAYMENT_REQUEST_LBL, PAYMENT_REQUEST_NAME, Types.MAP,
                "Through the inclusion of a copy of the ${href.payment-request} " +
                "in the <code class='entity'>Payer</code> authorization, " +
                "this object remains <i>authoritative</i> " +
                "throughout the payment process (except for interbank operations).")

            .add(PROVIDER_INFO_LBL, PROVIDER_INFO_NAME, Types.MAP,
                "Holds the ${href.provider-info} " +
                "required by the <code class='entity'>Payee</code> for deriving " +
                "which payment network " +
                "to use and how to initiate a compatible payment transaction request.")

           .add(LANGUAGE_LBL, LANGUAGE_NAME, Types.TSTR,
                "A 2-or-3-character code that defines the basic language, all " +
                "written in <i>lowercase</i>. For example, the language code for English is " +
                "<code>en</code>, and the code for Badeshi is <code>bdz</code>.")

            .add(PAYEE_HOST_LBL, PAYEE_HOST_NAME, Types.TSTR,
                "Host name or IP address of the invoking <code class='entity'>Payee</code>, " +
                "derived from the URL obtained in step #1 in the sequence diagram." +
                "<div style='padding-top:0.5em'>" +
                "The purpose of the <kbd>" + PAYEE_HOST_NAME + "</kbd> attribute is to provide " +
                "a means for a <code class='entity'>Payment Network</code> " +
                "to verify that the origin of a received ${href.authorization-response} " +
                "matches that of the <code class='entity'>Payee</code>. " +
                "This is essentially an inverted version of the phishing " +
                "protection method used by WebAuthn [${href.webauthn}].</div>" +
                "<div style='padding-top:0.5em'>" +
                "The security of this arrangement also depends on that forwarded " + 
                "<code class='entity'>Payee</code> requests are properly authenticated.</div>")

            .getTableString();
    }

    @Override
    String getBeforeText() {
        return "The " + getTitle() + " structure holds the elements of a " +
            "${href.authorization-response} that are provided in clear. " +
            "<div style='padding-top:0.5em'>" +
            "Note that the <code>&quot;" +
             MessageCommon.AUTHZ_RESPONSE_ID + "&quot;</code> ${href.cotx} tag " +
            "also belongs to unencrypted data.</div>";
    }

    @Override
    String getTitle() {
        return "Unencrypted Data";
    }

}
