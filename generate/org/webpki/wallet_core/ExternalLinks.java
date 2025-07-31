package org.webpki.wallet_core;

enum ExternalLinks {

    EMV ("https://www.emvco.com/"),

    CSF ("https://cyberphone.github.io/javaapi/org/webpki/cbor/doc-files/signatures.html"),

    CEF   ("https://cyberphone.github.io/javaapi/org/webpki/cbor/doc-files/encryption.html"),

    COSE  ("https://www.rfc-editor.org/rfc/rfc9052.html"),

    COTX  ("https://www.ietf.org/archive/id/draft-rundgren-cotx-04.html"),

    CORE  ("https://datatracker.ietf.org/doc/draft-rundgren-cbor-core/", "CBOR::Core"),

    RFC8615 ("https://www.rfc-editor.org/rfc/rfc8615"),

    SVG ("https://www.w3.org/TR/SVG11/"),

    RFC3339 ("https://www.rfc-editor.org/rfc/rfc3339"),

    ISO4217 ("https://www.iso.org/iso-4217-currency-codes.html"),

    RECEIPTS ("https://cyberphone.github.io/doc/defensive-publications/signed-e-receipts.pdf", "e-Receipts"),

    RFC8610 ("https://www.rfc-editor.org/rfc/rfc8610.html"),

    RFC8949 ("https://www.rfc-editor.org/rfc/rfc8949.html"),

    WEBAUTHN ("https://www.w3.org/TR/webauthn/"),

    PAYREQ ("https://www.w3.org/TR/payment-request/", "Payment Request"),

    LICENSE ("https://github.com/cyberphone/wallet-core/blob/main/LICENSE", "MIT license"),

    WOMAN ("https://commons.wikimedia.org/wiki/File:Crystal_Clear_kdm_user_female.svg", "wikimedia.org"),

    PLAYGROUND ("https://cyberphone.github.io/CBOR.js/doc/playground.html", "CBOR Playground"),

    EUDIW ("https://ec.europa.eu/digital-building-blocks/sites/display/EUDIGITALIDENTITYWALLET/EU+Digital+Identity+Wallet+Home");

    String link;
    String actualName;

    ExternalLinks(String link, String actualName) {
        this.link = link;
        this.actualName = actualName;
    }

    ExternalLinks(String link) {
        this(link, null);
    }

    String getHolder() {
        return "${href." + toString().toLowerCase() + "}";
    }

    String getHtml() {
        String name = actualName == null? toString() : actualName;
        return "<a href='" + 
               link +
               "' style='white-space:nowrap'>" + name +
               "<img src='xtl.svg' alt='" +
               toString().toLowerCase() + "'></a>";
    }
}
