package org.webpki.wallet_core;

import org.webpki.cbor.CBORCryptoConstants;

public class AuthorizationResponse extends TableExecutor {

    static final String CUSTOM_DATA_NAME    = "customData";
    static final String ALGORITHM_NAME      = "algorithm";
    static final String CIPHER_TEXT_NAME    = "cipherText";
    static final String IV_NAME             = "iv";
    static final String TAG_NAME            = "tag";
    static final String KEY_ENCRYPTION_NAME = "keyEncryption";

    @Override
    String getTableString() {
        return new Table()
            .add(CBORCryptoConstants.CUSTOM_DATA_LABEL, CUSTOM_DATA_NAME, Types.MAP,
                "CEF custom (<i>unencrypted</i>) data in the form of a copy of the " +
                "${href.pass-through-data} object fetched from " +
                "${href.signed-authorization} object.")

            .add(CBORCryptoConstants.ALGORITHM_LABEL, ALGORITHM_NAME, Types.INT,
                "The CEF <kbd>" +  ALGORITHM_NAME + "</kbd> attribute is a copy of the <kbd>" +
                CredentialDatabaseEntry.ENC_CONTENT_ALG_NAME +
                "</kbd> of the selected payment credential in the " +
                "${href.credential-database}.")

            .add(CBORCryptoConstants.IV_LABEL, IV_NAME, Types.BSTR,
                "CEF encryption algorithm initialization vector (IV).")

            .add(CBORCryptoConstants.TAG_LABEL, TAG_NAME, Types.BSTR,
                "CEF encryption algorithm tag.")

            .add(CBORCryptoConstants.KEY_ENCRYPTION_LABEL, KEY_ENCRYPTION_NAME, Types.MAP,
                "Holds a CEF key encryption object.")

            .add(CBORCryptoConstants.CIPHER_TEXT_LABEL, CIPHER_TEXT_NAME, Types.BSTR,
                "Encrypted CEF data containing a version of the ${href.signed-authorization} object " +
                "where the ${href.pass-through-data} object has been removed <i>after</i> the " +
                "completed authorization signature process." +
                "<div style='padding-top:0.5em'>" +
                "Note that the modified ${href.signed-authorization} " +
                "<code>map</code> object <b>must</b> be updated (<i>before</i> " +
                "being encrypted), to reflect the removal of the " +
                "${href.pass-through-data} object.</div>")

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

    @Override
    String getAfterText() {
        return "See also ${href.authorization-processing}.";
    }
}
