package org.webpki.wallet_core;

import static org.webpki.cbor.CBORCryptoConstants.*;

public class AuthorizationResponse extends TableExecutor {

    static final String CUSTOM_DATA_NAME    = "customData";
    static final String CIPHER_TEXT_NAME    = "cipherText";

    @Override
    String getTableString() {
        return new Table()
            .add(CUSTOM_DATA_LABEL, CUSTOM_DATA_NAME, Types.MAP,
                "CEF: custom (<i>unencrypted</i>) data in the form of a copy of the " +
                "${href.pass-through-data} object.")

            .add(CIPHER_TEXT_LABEL, CIPHER_TEXT_NAME, Types.BSTR,
                "CEF: <i>encrypted</i> ${href.signed-authorization} where the " +
                "${href.pass-through-data} object was removed <i>after</i> the " +
                "authorization signature process. " +
                "<div style='padding-top:0.5em'>" +
                "Note that the modified ${href.signed-authorization} " +
                "<code>map</code> object <b>must</b> be updated (<i>before</i> being " +
                "encrypted), to refect the removal of the ${href.pass-through-data} object.</div>")

            .toString();
    }

    @Override
    String getTitle() {
        return "Authorization Response";
    }

    @Override
    String getBeforeText() {
        return "An " + getTitle() + " consists of a single ${href.cef} object, where the " +
            "outermost element is a ${href.cotx} wrapper with ID=<code>" + 
            CreateDocument.OBJECT_ID + "</code>.  The CEF inner elements are as follows:";
    }
}
