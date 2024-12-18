package org.webpki.wallet_core;

import static org.webpki.wallet_core.MessageCommon.*;

public class ProviderInfo extends TableExecutor {

    static final String NETWORK_ID_NAME      = "networkId";
    static final String SERVICE_LOCATOR_NAME = "serviceLocator";

    @Override
    String getTableString() {
        return new Table()

            .add(NETWORK_ID_LBL, NETWORK_ID_NAME, Types.TSTR,
                CreateDocument.COPY_ATTRIBUTE)

            .add(SERVICE_LOCATOR_LBL, SERVICE_LOCATOR_NAME, Types.TSTR,
                CreateDocument.COPY_ATTRIBUTE)

            .getTableString();
    }

    @Override
    String getTitle() {
        return "Provider Info";
    }

}
