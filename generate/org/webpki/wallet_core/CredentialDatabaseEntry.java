package org.webpki.wallet_core;

public class CredentialDatabaseEntry {

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

        add(ProviderData.NETWORK_ID_NAME, Types.TSTR,
            "Payment network/method identifier. " +
            "Since payment networks are likely to continue having unique message " +
            "solutions, the <code class='entity'>Payee</code> needs to " +
            "identify the specific network before making a transaction request." +
            "<div style='padding-top:0.5em'>" +
            "Payment network identifiers may be expressed as URLs or as " +
            "simple names like \"VISA\".  Note that this concept does not " +
            "make a distinction between payment methods or \"schemes\".</div>" +
            "<div style='padding-top:0.5em'>Also see ${href.provider-data}.</div>");

        add(ProviderData.SERVICE_LOCATOR_NAME, Types.TSTR,
            "Payment service URL or host name. " +
            "This attribute enables the <code class='entity'>Payee</code> " +
            "to find the end-point of the specific payment service (like a bank), " +
            "associated with the payment credential." +
            "<div style='padding-top:0.5em'>" +
            "How to interpret this attribute is dictated by the <kbd>" +
            ProviderData.NETWORK_ID_NAME + "</kbd> identifier. If <kbd>" + 
            ProviderData.SERVICE_LOCATOR_NAME + "</kbd> is expressed as a host-name only, " +
            "a <code style='white-space:nowrap'>&quot;.well-known&quot;</code> " +
            "[${href.rfc8615}] extension would typically be used.</div>" +
            "<div style='padding-top:0.5em'>Also see ${href.provider-data}.</div>");

        add(SignedAuthorization.ACCOUNT_ID_NAME, Types.TSTR,
            "Account identifier associated with the virtual card." +
            "<div style='padding-top:0.5em'>Also see ${href.signed-authorization}.</div>");

        add(SignedAuthorization.SERIAL_NUMBER_NAME, Types.TSTR,
            "Serial number of the virtual card." +
            "<div style='padding-top:0.5em'>Also see ${href.signed-authorization}.</div>");

        add(CARD_IMAGE_NAME, Types.BSTR,
            "Virtual card image. Card images are used for " +
            "<code class='entity'>Payer</code> administration of " +
            "<code class='entity'>Wallet</code> credentials as well as displayed in the " +
            "${href.wallet-request-ui}. " +
            "<div style='padding-top:0.5em'>" +
            "Card images <b>must</b> be in ${href.svg} format and tentatively having " +
            "a size of <code>300&times;180</code> pixels.</div>");
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
