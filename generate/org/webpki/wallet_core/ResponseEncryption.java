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
                "<div style='padding-top:0.5em'>" +
                "The length of the <kbd>" + INSTANCE_KEY_NAME + 
                "</kbd> attribute <b>must</b> match the <kbd>" + ALGORITHM_NAME + "</kbd>.</div>")

            .getTableString();
    }

    @Override
    String getTitle() {
        return "Response Encryption";
    }

    @Override
    String getBeforeText() {
        return 
            "The " + getTitle() + " object provides a means for an " +
            "<code class='entity'>Issuer</code> to return " +
            "messages to the <code class='entity'>Payer</code> " +
            "like &quot;Out&nbsp;of&nbsp;funds&quot; through " +
            "the regular transaction channel, and that " +
            "without revealing any personal information to intermediaries. " +
            "In addition, " + getTitle()  + " also serves as a strong <i>nonce</i>, " +
            "ensuring that ${href.signed-authorization} objects become unique, irrespective " +
            "of ${href.authorization-request} data and time-stamps." +
            "<div style='padding-top:0.5em'>" +
            "This concept can also be used to force the <code class='entity'>Payer</code> " +
            "to provide additional information which could be needed for high-value " +
            "or &quot;suspicious&quot; transaction requests.</div>";
    }

}
