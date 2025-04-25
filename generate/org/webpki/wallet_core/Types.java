package org.webpki.wallet_core;

enum Types {

    ARRAY ("[]"), 
    MAP   ("{}"), 
    TSTR  ("tstr"), 
    BSTR  ("bstr"), 
    ANY   ("any"), 
    PS    ("&quot;ps&quot;"),
    COTX  ("cotx"),
    INT   ("int");

    String html;

    Types(String html) {
        this.html = html;
    }

    String getHTML() {
        return html;
    }
}
