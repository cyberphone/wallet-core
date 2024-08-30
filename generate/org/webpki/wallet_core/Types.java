package org.webpki.wallet_core;

enum Types {

    ARRAY ("array"), 
    MAP   ("map"), 
    TSTR  ("tstr"), 
    BSTR  ("bstr"), 
    ANY   ("&quot;</code><i>Any</i><code>&quot;"), 
    INT   ("int");

    String html;

    Types(String html) {
        this.html = html;
    }

    String getHTML() {
        return html;
    }
}
