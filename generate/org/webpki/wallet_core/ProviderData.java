package org.webpki.wallet_core;

import static org.webpki.wallet_core.MessageCommon.*;

public class ProviderData extends TableExecutor {

    static final String NETWORK_ID_NAME      = "networkId";
    static final String SERVICE_LOCATOR_NAME = "serviceLocator";

    @Override
    String getTableString() {
        return new Table()
            .add(NETWORK_ID_LABEL, NETWORK_ID_NAME, Types.TSTR,
                CreateDocument.COPY_ATTRIBUTE)

            .add(SERVICE_LOCATOR_LABEL, SERVICE_LOCATOR_NAME, Types.TSTR,
                CreateDocument.COPY_ATTRIBUTE)

            .toString();
    }

    @Override
    String getTitle() {
        return "Provider Data";
    }

}
