package org.webpki.wallet_core;

enum ExternalLinks {

    EMV ("https://www.emvco.com/"),

    CSF ("https://cyberphone.github.io/javaapi/org/webpki/cbor/doc-files/signatures.html"),

    CEF   ("https://cyberphone.github.io/javaapi/org/webpki/cbor/doc-files/encryption.html"),

    COSE  ("https://www.rfc-editor.org/rfc/rfc9052.html"),

    COTX  ("https://www.ietf.org/archive/id/draft-rundgren-cotx-04.html"),

    CDE  ("https://datatracker.ietf.org/doc/draft-ietf-cbor-cde/"),

    RFC8615 ("https://www.rfc-editor.org/rfc/rfc8615"),

    SVG ("https://www.w3.org/TR/SVG11/"),

    RFC3339 ("https://www.rfc-editor.org/rfc/rfc3339"),

    ISO4217 ("https://www.iso.org/iso-4217-currency-codes.html"),

    RECEIPTS ("https://cyberphone.github.io/doc/defensive-publications/e-receipts.pdf"),

    RFC8949 ("https://www.rfc-editor.org/rfc/rfc8949.html"),

    WEBAUTHN ("https://www.w3.org/TR/webauthn/"),

    EUDIW ("https://ec.europa.eu/digital-building-blocks/sites/display/EUDIGITALIDENTITYWALLET/EU+Digital+Identity+Wallet+Home");

    String link;

    ExternalLinks(String link) {
        this.link = link;
    }

    String getHolder() {
        return "${href." + toString().toLowerCase() + "}";
    }

    String getHtml() {
        return "<a href='" + 
               link +
               "' style='white-space:nowrap'>" +
               toString() +
               "<img src='xtl.svg' alt='" +
               toString().toLowerCase() + "'></a>";
    }
}
