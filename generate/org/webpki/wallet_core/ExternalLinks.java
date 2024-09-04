package org.webpki.wallet_core;

enum ExternalLinks {

    EMV ("https://www.emvco.com/"),

    CSF ("https://cyberphone.github.io/javaapi/org/webpki/cbor/doc-files/signatures.html"),

    CEF   ("https://cyberphone.github.io/javaapi/org/webpki/cbor/doc-files/encryption.html"),

    COSE  ("https://www.rfc-editor.org/rfc/rfc9052.html"),

    CDE  ("https://datatracker.ietf.org/doc/draft-ietf-cbor-cde/"),

    RFC8949 ("https://www.rfc-editor.org/rfc/rfc8949.html");

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
               "'>" +
               toString() +
               "<img src='xtl.svg' alt='" +
               toString().toLowerCase() + "'></a>";
    }
}
