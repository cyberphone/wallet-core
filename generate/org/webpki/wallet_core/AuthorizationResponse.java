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
                new PassThroughData().getHref() +
                " object.")

            .add(CIPHER_TEXT_LABEL, CIPHER_TEXT_NAME, Types.BSTR,
                "CEF: " +
                "<i>encrypted</i> " +
                new SignedAuthorization().getHref() +
                " having the " +
                new PassThroughData().getHref() +
                " object being removed <i>after</i> signing. " +
                "Note that the modified " + 
                new SignedAuthorization().getHref() +
                " <code>map</code> object <b>must</b> be updated to refect " +
                "the removal of the " + 
                new PassThroughData().getHref() +
                " object.")

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
