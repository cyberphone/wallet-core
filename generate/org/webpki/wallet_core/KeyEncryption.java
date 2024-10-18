package org.webpki.wallet_core;

import org.webpki.cbor.CBORCryptoConstants;

public class KeyEncryption extends TableExecutor {

    static final String KEY_ID_NAME        = "keyId";
    static final String PUBLIC_KEY_NAME    = "publicKey";
    static final String EPHEMERAL_KEY_NAME = "ephemeralKey";

    @Override
    String getTableString() {
        return new Table()
            .add(CBORCryptoConstants.CXF_ALGORITHM_LBL, 
                 AuthorizationResponse.ALGORITHM_NAME,
                 Types.INT,
                "Copy of the <kbd>" +
                CredentialDatabaseEntry.ENC_KEY_ALG_NAME +
                "</kbd> attribute of the selected payment credential in the " +
                "${href.credential-database}.")

            .add(CBORCryptoConstants.CEF_EPHEMERAL_KEY_LBL, EPHEMERAL_KEY_NAME, Types.MAP,
                "Ephemeral ECDH public key.")

            .add(CBORCryptoConstants.CXF_KEY_ID_LBL, KEY_ID_NAME, Types.ANY,
                "<i>Optional</i>: Copy of the <kbd>" + CredentialDatabaseEntry.ENC_KEY_ID_NAME +
                "</kbd> attribute of the selected payment credential in the " +
                "${href.credential-database}.")

            .add(CBORCryptoConstants.CXF_PUBLIC_KEY_LBL, PUBLIC_KEY_NAME, Types.MAP,
                "<i>Optional</i>: Copy of the <kbd>" + CredentialDatabaseEntry.ENC_PUBLIC_KEY_NAME +
                "</kbd> object of the selected payment credential in the " +
                "${href.credential-database}." +
                "<div style='padding-top:0.5em'>" +
                "Note that <kbd>" + KEY_ID_NAME + "</kbd> and <kbd>" + PUBLIC_KEY_NAME +
                "</kbd> are <i>mutually exclusive</i>.</div>")

            .add(CBORCryptoConstants.CEF_CIPHER_TEXT_LBL, 
                 AuthorizationResponse.CIPHER_TEXT_NAME, 
                 Types.BSTR,
                "<i>Optional</i>: Encrypted key for <i>key&nbsp;wrapping</i> algorithms.")

            .getTableString();
    }

    @Override
    String getTitle() {
        return "Key Encryption";
    }

}
