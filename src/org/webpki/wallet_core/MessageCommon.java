package org.webpki.wallet_core;

import org.webpki.cbor.CBORInt;

public class MessageCommon {

    // Authorization Request
    static final CBORInt PAYMENT_REQUEST_LBL    = new CBORInt(1); 
    static final CBORInt SUPPORTED_NETWORKS_LBL = new CBORInt(2);
    static final CBORInt RECEIPT_URL_LBL        = new CBORInt(3);

    static final CBORInt COMMON_NAME_LBL        = new CBORInt(1); 
    static final CBORInt REFERENCE_ID_LBL       = new CBORInt(2);
    static final CBORInt AMOUNT_LBL             = new CBORInt(3); 
    static final CBORInt CURRENCY_LBL           = new CBORInt(4); 
    static final CBORInt NON_DIRECT_LBL         = new CBORInt(5); 
 
    static final CBORInt PROVIDER_DATA_LBL      = new CBORInt(2);

    static final CBORInt NETWORK_ID_LBL         = new CBORInt(1); 
    static final CBORInt SERVICE_LOCATOR_LBL    = new CBORInt(2);

    // Signed Authorization
    static final CBORInt PASS_THROUGH_DATA_LBL  = new CBORInt(1);
    static final CBORInt PAYEE_HOST_LBL         = new CBORInt(2);
    static final CBORInt ACCOUNT_ID_LBL         = new CBORInt(3);
    static final CBORInt SERIAL_NUMBER_LBL      = new CBORInt(4);
    static final CBORInt PLATFORM_DATA_LBL      = new CBORInt(5);   
    static final CBORInt WALLET_DATA_LBL        = new CBORInt(6);
    static final CBORInt LOCATION_LBL           = new CBORInt(7);
    static final CBORInt TIME_STAMP_LBL         = new CBORInt(8);

    static final CBORInt NON_DIR_PAY_ID_LBL     = new CBORInt(1);

    static final CBORInt AUTHZ_SIGNATURE_LBL    = new CBORInt(-1); 

}
