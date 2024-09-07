package org.webpki.wallet_core;

public class PaymentCredential {

    static final String CARD_IMAGE_NAME = "cardImage";

    StringBuilder table = new StringBuilder("<div class='webpkifloat'>" +
             "<table class='webpkitable' style='width:52em'>" +
                "<tr><th>Name</th><th>Type</th>" +
                    "<th style='width:100%'>Description</th></tr>");

    void add(String name, Types type, String description) {
        table.append("<tr><td><kbd>")
            .append(name)
            .append("</kbd></td><td style='text-align:center'><code>")
            .append(type.getHTML())
            .append("</code></td><td>")
            .append(description)
        .append("</td></tr>");
    }

    String getTableString() {

        add(ServiceProvider.PAYMENT_NETWORK_NAME, Types.TSTR,
            "Payment network/method identifier. " +
            "Since payment networks are likely to continue having unique message " +
            "solutions, the <code style='color:darkgreen'>Payee</code> needs to " +
            "identify the specific network before making a transation request." +
            "<div style='padding-top:0.5em'>" +
            "Payment network identifiers may be expressed as URLs or as " +
            "simple names like \"VISA\".  Note that this concept does not " +
            "make a distinction between payment methods or \"schemes\".</div>" +
            "<div style='padding-top:0.5em'>Also see ${href.service-provider}.</div>");

        add(ServiceProvider.PAYMENT_SERVICE_NAME, Types.TSTR,
            "Payment service URL or host name. " +
            "This attribute enables the <code style='color:darkgreen'>Payee</code> " +
            "to find the end-point of the specific payment service (like a bank), " +
            "associated with the payment credential." +
            "<div style='padding-top:0.5em'>" +
            "How to interprete this attribute is dictated by the <kbd>" +
            ServiceProvider.PAYMENT_NETWORK_NAME + "</kbd> identifier. If <kbd>" + 
            ServiceProvider.PAYMENT_SERVICE_NAME + "</kbd> is expressed as a host-name only, " +
            "a <code style='white-space:nowrap'>&quot;.well-known&quot;</code> " +
            "[${href.rfc8615}] extension would typically be used.</div>" +
            "<div style='padding-top:0.5em'>Also see ${href.service-provider}.</div>");

        add(SignedAuthorization.ACCOUNT_ID_NAME, Types.TSTR,
            "Account identifier associated with the virtual card." +
            "<div style='padding-top:0.5em'>Also see ${href.signed-authorization}.</div>");

        add(SignedAuthorization.SERIAL_NUMBER_NAME, Types.TSTR,
            "Serial number of the virtual card." +
            "<div style='padding-top:0.5em'>Also see ${href.signed-authorization}.</div>");

        add(CARD_IMAGE_NAME, Types.BSTR,
            "Virtual card image. Card images are used for " +
            "<code style='color:darkgreen'>Payer</code> administration of " +
            "wallet credentials as well as displayed in payment UIs " +
            "(${href.payer-authorization}). " +
            "Card images <b>must</b> be in ${href.svg} format and tentatively having " +
            "a size of <code>300&times;180</code> pixels.");
/* 
        add(ServiceProvider.PAYMENT_SERVICE_NAME, Types.TSTR,
            "Payment service URL or host name. This attribute enables the <code>Payee</code> " +
            "to find the end-point of the specific payment service (like a bank)," +
            " assocated with the payment credential. " +
            "How to interprete this attribute is dictated by the <kbd>" +
            ServiceProvider.PAYMENT_NETWORK_NAME + "</kbd> identifier. If <kbd>" + 
            ServiceProvider.PAYMENT_SERVICE_NAME + "</kbd> is expressed as a host-name only, " +
            "a <code style='white-space:nowrap'>&quot;.well-known&quot;</code> " +
            "[${href.rfc8615}] extension would typically be used.");
*/    
        return table.append("</table></div>").toString();
    }

}
