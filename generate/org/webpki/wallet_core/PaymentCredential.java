package org.webpki.wallet_core;

import static org.webpki.wallet_core.MessageCommon.*;

public class PaymentCredential extends TableExecutor {

    static final String PAYMENT_NETWORK_NAME = "paymentNetwork";
    static final String PAYMENT_SERVICE_NAME = "paymentService";

    @Override
    String getTableString() {
        return new Table()
            .add(PAYMENT_NETWORK_LABEL, PAYMENT_NETWORK_NAME, Types.TSTR,
                "Payment network/method identifier. " +
                "Since payment networks are likely to continue having unique message " +
                "solutions, the Payee needs to identify the specific network " +
                "before making a transation request. " +
                "Payment network identifiers may be expressed as URLs or as " +
                "simple names like \"VISA\".  Note that this concept does not " +
                "make a distinction between payment methods or \"schemes\"." )

            .add(PAYMENT_SERVICE_LABEL, PAYMENT_SERVICE_NAME, Types.TSTR,
                "Payment service URL or host name. This attribute enables the Payee " +
                "to find the end-point of the specific payment service (like a bank)," +
                " assocated with the payment credential. " +
                "How to interprete this attribute is dictated by the <kbd>" +
                PAYMENT_NETWORK_NAME + "</kbd> identifier. If <kbd>" + 
                PAYMENT_SERVICE_NAME + "</kbd> is expressed as a host-name only, " +
                "a <code style='white-space:nowrap'>&quot;.well-known&quot;</code> " +
                "[${href.rfc8615}] extension would typically be used.")
    
            .toString();
    }

    @Override
    String getTitle() {
        return "Payment Credential";
    }
}
