package org.webpki.wallet_core;

abstract class TableExecutor {

    abstract String getTableString();

    abstract String getTitle();

    String getLink() {
        return getTitle().toLowerCase().replace(' ', '-');
    }

    String getHref() {
        return "<a href='#" + getLink() + 
               "'>" +
               getTitle().replace(" ", "&nbsp;") +
               "</a>";
    }

    static String getCEFLink() {
        return "<a href='" +
            "https://cyberphone.github.io/javaapi/org/webpki/cbor/doc-files/encryption.html' " +
            "title='CBOR Encryption Format (CEF)'>CEF<img src='xtl.svg' alt='CEF'></a>";
    }

}