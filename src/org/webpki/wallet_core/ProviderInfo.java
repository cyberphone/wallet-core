package org.webpki.wallet_core;

import static org.webpki.wallet_core.MessageCommon.*;

import org.webpki.cbor.CBORMap;
import org.webpki.cbor.CBORObject;
import org.webpki.cbor.CBORString;

public class ProviderInfo extends MapCommon {

    static final String NETWORK_ID_NAME      = "networkId";
    static final String SERVICE_LOCATOR_NAME = "serviceLocator";

    public final String networkId;

    public final String serviceLocator;

    ProviderInfo() { // For documentation purposes only
        this("any", "any");
    }

    public ProviderInfo(String networkId, String serviceLocator) {
        this(new CBORMap()
                .set(NETWORK_ID_LBL, new CBORString(networkId))
                .set(SERVICE_LOCATOR_LBL, new CBORString(serviceLocator)));
    }

    public ProviderInfo(CBORObject providerInfo) {
        super(providerInfo);
        networkId = getMapString(NETWORK_ID_LBL, ANY_STRING_PATTERN);
        serviceLocator = getMapString(SERVICE_LOCATOR_LBL, ANY_STRING_PATTERN);
    }

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
