package org.webpki.wallet_core;

import org.webpki.cbor.CBORCryptoConstants;

public class KeyEncryption extends TableExecutor {

    static final String KEY_ID_NAME        = "keyId";
    static final String PUBLIC_KEY_NAME    = "publicKey";
    static final String EPHEMERAL_KEY_NAME = "ephemeralKey";

    @Override
    String getTableString() {
        return new Table()
            .add(CBORCryptoConstants.ALGORITHM_LABEL, 
                 AuthorizationResponse.ALGORITHM_NAME,
                 Types.INT,
                "Copy of the <kbd>" +
                CredentialDatabaseEntry.ENC_KEY_ALG_NAME +
                "</kbd> attribute of the selected payment credential in the " +
                "${href.credential-database}.")

            .add(CBORCryptoConstants.EPHEMERAL_KEY_LABEL, EPHEMERAL_KEY_NAME, Types.MAP,
                "Ephemeral ECDH public key.")

            .add(CBORCryptoConstants.KEY_ID_LABEL, KEY_ID_NAME, Types.ANY,
                "<i>Optional</i>: Copy of the <kbd>" + CredentialDatabaseEntry.ENC_KEY_ID_NAME +
                "</kbd> attribute of the selected payment credential in the " +
                "${href.credential-database}.")

            .add(CBORCryptoConstants.PUBLIC_KEY_LABEL, PUBLIC_KEY_NAME, Types.MAP,
                "<i>Optional</i>: Copy of the <kbd>" + CredentialDatabaseEntry.ENC_PUBLIC_KEY_NAME +
                "</kbd> object of the selected payment credential in the " +
                "${href.credential-database}." +
                "<div style='padding-top:0.5em'>" +
                "Note that <kbd>" + KEY_ID_NAME + "</kbd> and <kbd>" + PUBLIC_KEY_NAME +
                "</kbd> are <i>mutually exclusive</i>.</div>")

            .add(CBORCryptoConstants.CIPHER_TEXT_LABEL, 
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
