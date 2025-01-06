package org.webpki.wallet_core;

import static org.webpki.wallet_core.MessageCommon.*;

public class UnencryptedData extends TableExecutor {

    static final String PAYMENT_REQUEST_NAME  = "paymentRequest";
    static final String PROVIDER_INFO_NAME    = "providerInfo";
    static final String PAYEE_HOST_NAME       = "payeeHost";
    static final String TIME_STAMP_NAME       = "timeStamp";

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

            .add(TIME_STAMP_LBL, TIME_STAMP_NAME, Types.TSTR,
                "ISO date-time string [${href.rfc3339}] " +
                "using UTC (T) or local time (Z) format." +
                "<div style='padding-top:0.5em'>" +
                "The purpose of the <kbd>" + TIME_STAMP_NAME + "</kbd> attribute is to provide " +
                "a means for an <code class='entity'>Issuer</code> to verify " +
                "the &quot;freshness&quot; of a received ${href.authorization-response}. " +
                "The recommended method is using a cache holding a hash of the " +
                "associated ${href.signed-authorization} " +
                "and its <kbd>" + TIME_STAMP_NAME + "</kbd>, " +
                "where the latter is used to automatically remove a cache entry when the " +
                "authorization is considered to be expired. " +
                "This arrangement is either used for protection against replay, " +
                "or for supporting <i>idempotent</i> operation.</div>" +
                "<div style='padding-top:0.5em'>" +
                "Note that authorizations that already have expired or are too new <b>must</b> " +
                "be rejected.</div>" +
                "<div style='padding-top:0.5em'>" +
                "<i>Tentative</i> lower limit: " +
                "<kbd>" + TIME_STAMP_NAME + "</kbd><code> &gt; currentTime - 600s</code><br>" +
                "<i>Tentative</i> higher limit: " +
                "<kbd>" + TIME_STAMP_NAME + "</kbd><code> &lt; currentTime + 60s</code></div>")

            .getTableString();
    }

    @Override
    String getTitle() {
        return "Unencrypted Data";
    }

}
