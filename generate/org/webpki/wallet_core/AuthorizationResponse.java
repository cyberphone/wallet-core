package org.webpki.wallet_core;

import static org.webpki.cbor.CBORCryptoConstants.*;

public class AuthorizationResponse extends TableExecutor {

    static final String CUSTOM_DATA_NAME    = "customData";

    @Override
    String getTableString() {
        return new Table()
            .add(CUSTOM_DATA_LABEL, CUSTOM_DATA_NAME, Types.MAP,
                            "Holds " +
                            getCEFLink() +
                            " custom (<i>unencrypted</i>) data in the form of a copy of the " +
                            new PassThroughData().getHref() +
                            " object.")

            .toString();
    }

    @Override
    String getTitle() {
        return "Authorization Response";
    }
}
