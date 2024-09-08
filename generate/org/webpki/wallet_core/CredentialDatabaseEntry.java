package org.webpki.wallet_core;

public class CredentialDatabaseEntry {

    static final String VERSION_NAME           = "version";
    static final String CARD_IMAGE_NAME        = "cardImage";
    static final String AUTHZ_KEY_HANDLE_NAME  = "authzKeyHandle";
    static final String AUTHZ_ALG_NAME         = "authzAlg";
    static final String AUTHZ_PUBLIC_KEY_NAME  = "authzPublicKey";
    static final String ENC_CONTENT_ALG_NAME   = "encContentAlg";
    static final String ENC_KEY_ALG_NAME       = "encKeyAlg";
    static final String ENC_PUBLIC_KEY_NAME    = "encPublicKey";
    static final String ENC_KEY_ID_NAME        = "encKeyId";

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

        add(VERSION_NAME, Types.TSTR,
            "Since credential data may evolve over time, versioning is necessary. " +
            "This specification covers version: " +
            "<code style='white-space:nowrap'>" + CreateDocument.CREDENTIAL_VERSION + "</code>.");

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

        add(AUTHZ_ALG_NAME, Types.INT,
            "COSE signature algorithm to use for creating " +
            "${href.signed-authorization} objects.");

        add(AUTHZ_KEY_HANDLE_NAME, Types.PS,
            "Local handle to the private key to use for creating " +
            "${href.signed-authorization} objects.");

        add(AUTHZ_PUBLIC_KEY_NAME, Types.PS,
            "Authorization public key for inclusion in " +
            "${href.signed-authorization} objects.");

        add(ENC_CONTENT_ALG_NAME, Types.INT,
            "COSE content encryption algorithm to use for creating " +
            "${href.authorization-response} objects.");

        add(ENC_KEY_ALG_NAME, Types.INT,
            "COSE key encryption algorithm to use for creating " +
            "${href.authorization-response} objects.");

        add(ENC_PUBLIC_KEY_NAME, Types.PS,
            "Encryption public key to use for creating " +
            "${href.authorization-response} objects.");
        
        add(ENC_KEY_ID_NAME, Types.ANY,
            "<i>Optional</i>: if the <kbd>" +
            ENC_KEY_ID_NAME +
            "</kbd> attribute is defined, it <b>must</b> be " +
            "featured in ${href.authorization-response} objects instead of <kbd>" +
            ENC_PUBLIC_KEY_NAME + "</kbd>.");
 
        return table.append("</table></div>").toString();
    }

}
