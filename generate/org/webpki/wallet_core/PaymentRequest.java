package org.webpki.wallet_core;

import static org.webpki.wallet_core.MessageCommon.*;

public class PaymentRequest extends TableExecutor {

    static final String AMOUNT_NAME       = "amount";
    static final String COMMON_NAME_NAME  = "commonName";
    static final String CURRENCY_NAME     = "currency";
    static final String REFERENCE_ID_NAME = "referenceId";
    static final String NON_DIRECT_NAME   = "nonDirect";

    @Override
    String getTableString() {
        return new Table()
            .add(AMOUNT_LABEL, AMOUNT_NAME, Types.TSTR,
                "Monetary amount compatible with the regular expression: " +
                "<code style='white-space:nowrap'>^(0|[1-9][0-9]*)(\\.[0-9]+)?$</code>. " +
                "<div style='padding-top:0.5em'>" +
                "Amounts <b>must&nbsp;not</b> use more decimals than is " +
                "custom for prices for the specific <kbd>currency</kbd>.</div>")

            .add(CURRENCY_LABEL, CURRENCY_NAME, Types.TSTR,
                "Currency expressed in the ${href.iso4217} <i>alphabetical</i> format.")

            .add(COMMON_NAME_LABEL, COMMON_NAME_NAME, Types.TSTR,
                "<code class='entity'>Payee</code> common name to be shown in UIs.")
    
            .add(REFERENCE_ID_LABEL, REFERENCE_ID_NAME, Types.TSTR,
                "<code class='entity'>Payee</code> reference Id. " +
                "<div style='padding-top:0.5em'>" +
                "Reference Ids <b>must</b> be unique with respect to the " +
                "<code class='entity'>Payee</code>.</div>")

            .add(NON_DIRECT_LABEL, NON_DIRECT_NAME, Types.MAP,
                "<i>Optional</i>: Non-direct payment request. TBD.")

           .toString();
    }

    @Override
    String getTitle() {
        return "Payment Request";
    }
}
