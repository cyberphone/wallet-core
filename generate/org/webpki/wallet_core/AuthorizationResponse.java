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

            .add(CBORCryptoConstants.CXF_CUSTOM_DATA_LBL, CUSTOM_DATA_NAME, Types.MAP,
                "CEF custom (<i>unencrypted</i>) data in the form of a copy of the " +
                "${href.signed-authorization} object where all <code>map</code> objects " +
                "except for the ${href.unencrypted-data} object have been removed." +
                "<div style='padding-top:0.5em'>Also see ${href.decryption}.</div>")

            .add(CBORCryptoConstants.CXF_ALGORITHM_LBL, ALGORITHM_NAME, Types.INT,
                "Copy of the <kbd>" +
                CredentialDatabaseEntry.ENC_CONTENT_ALG_NAME +
                "</kbd> attribute of the selected payment credential in the " +
                "${href.credential-database}.")

            .add(CBORCryptoConstants.CEF_IV_LBL, IV_NAME, Types.BSTR,
                "Encryption algorithm initialization vector (IV).")

            .add(CBORCryptoConstants.CEF_TAG_LBL, TAG_NAME, Types.BSTR,
                "Encryption algorithm tag.")

            .add(CBORCryptoConstants.CEF_KEY_ENCRYPTION_LBL, KEY_ENCRYPTION_NAME, Types.MAP,
                "Holds the CEF ${href.key-encryption} object.")

            .add(CBORCryptoConstants.CEF_CIPHER_TEXT_LBL, CIPHER_TEXT_NAME, Types.BSTR,
                "Encrypted version of the outermost <code>map</code> object " +
                "of the ${href.signed-authorization} object " +
                "where the ${href.unencrypted-data} object has been removed." +
                "<div style='padding-top:0.5em'>Also see ${href.decryption}.</div>")

            .getTableString();
    }

    @Override
    String getTitle() {
        return "Authorization Response";
    }

    @Override
    String getBeforeText() {
        return "An " + getTitle() + " consists of a single ${href.cef} object, where the " +
            "outermost element is a ${href.cotx} wrapper as follows:" +
            "<div class='webpkifloat'><div style='padding:1em 2em'>" +
            "<code>1010([&quot;" + MessageCommon.ENCRYPTED_AUTHZ_ID + "&quot;,&nbsp;{<br></code>" +
            "<div style='padding:1em 0 1em 2em'><i>CEF container...</i></div>" +
            "<code>}])</code>" +
            "</div></div>" +
            "Note that the COTX wrapper is included in the encryption process by " +
            "constituting a part of the Additional Authentication Data (AAD)." +
            "<div style='padding-top:0.5em'>" +
            "The CEF container <code>map</code> keys are as follows:</div>";
    }

    @Override
    String getAfterText() {
        return "See also ${href.authorization-processing}."+
        "<p>Note that <code>&quot;" + MessageCommon.ENCRYPTED_AUTHZ_ID + "&quot;</code> " +
        "represents a temporary name allocation.</p>";
    }
}
