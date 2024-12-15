package org.webpki.wallet_core;

import static org.webpki.wallet_core.MessageCommon.*;

public class ResponseEncryption extends TableExecutor {

    static final String ALGORITHM_NAME    = "algorithm";
    static final String INSTANCE_KEY_NAME = "instanceKey";

    @Override
    String getTableString() {
        return new Table()
            .add(ALGORITHM_LBL, ALGORITHM_NAME, Types.INT,
                KeyEncryption.ENCRYPTION_ALGORITHM_COPY)

            .add(INSTANCE_KEY_LBL, INSTANCE_KEY_NAME, Types.BSTR,
                "Random encryption key, initialized for each " +
                "authorization request." +
                "<div style='padding-top:0.5em'>The <kbd>" + INSTANCE_KEY_NAME + "</kbd> " +   
                "is used by the <code class='entity'>Issuer</code> " +
                "for returning encrypted messages to the <code class='entity'>Payer</code> " +
                "like &quot;Out&nbsp;of&nbsp;funds&quot; through " +
                "the regular transaction channel " +
                "without revealing any personal information to intermediaries.</div>" +
                "<div style='padding-top:0.5em'>" +
                "The length of the <kbd>" + INSTANCE_KEY_NAME + 
                "</kbd> attribute <b>must</b> match the <kbd>" + ALGORITHM_NAME + "</kbd>.</div>")

            .getTableString();
    }

    @Override
    String getTitle() {
        return "Response Encryption";
    }

}
