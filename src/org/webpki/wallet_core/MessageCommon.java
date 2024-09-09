package org.webpki.wallet_core;

import org.webpki.cbor.CBORInt;

public class MessageCommon {

    // Authorization Request
    static final CBORInt PAYMENT_REQUEST_LABEL    = new CBORInt(1); 
    static final CBORInt SUPPORTED_NETWORKS_LABEL = new CBORInt(2);
    static final CBORInt RECEIPT_URL_LABEL        = new CBORInt(3);

    static final CBORInt COMMON_NAME_LABEL        = new CBORInt(1); 
    static final CBORInt INSTANCE_ID_LABEL        = new CBORInt(2);
    static final CBORInt AMOUNT_LABEL             = new CBORInt(3); 
    static final CBORInt CURRENCY_LABEL           = new CBORInt(4); 
    static final CBORInt NON_DIRECT_LABEL         = new CBORInt(5); 
 
    static final CBORInt PROVIDER_DATA_LABEL      = new CBORInt(2);

    static final CBORInt NETWORK_ID_LABEL         = new CBORInt(1); 
    static final CBORInt SERVICE_LOCATOR_LABEL    = new CBORInt(2);

    // Signed Authorization
    static final CBORInt PASS_THROUGH_DATA_LABEL  = new CBORInt(1);
    static final CBORInt PAYEE_HOST_LABEL         = new CBORInt(2);
    static final CBORInt ACCOUNT_ID_LABEL         = new CBORInt(3);
    static final CBORInt SERIAL_NUMBER_LABEL      = new CBORInt(4);
    static final CBORInt PLATFORM_DATA_LABEL      = new CBORInt(5);   
    static final CBORInt WALLET_DATA_LABEL        = new CBORInt(6);
    static final CBORInt LOCATION_LABEL           = new CBORInt(7);
    static final CBORInt TIME_STAMP_LABEL         = new CBORInt(8);

    static final CBORInt AUTHZ_SIGNATURE_LABEL    = new CBORInt(-1); 

}
