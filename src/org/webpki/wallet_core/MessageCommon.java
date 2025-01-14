package org.webpki.wallet_core;

import org.webpki.cbor.CBORInt;

public class MessageCommon {

    // COTX Identifiers
    public static final String AUTHZ_RESPONSE_ID        = "https://cyberphone.github.io/saturn/ares/v1";
    public static final String AUTHZ_REQUEST_ID         = "https://cyberphone.github.io/saturn/areq/v1";
    public static final String SIGNED_AUTHZ_ID          = "https://cyberphone.github.io/saturn/sig/v1";

    public static final String NDP_GAS_ID               = "https://cyberphone.github.io/saturn/ndp/gas";

    // Authorization Request
    public static final CBORInt PAYMENT_REQUEST_LBL     = new CBORInt(1); 
    public static final CBORInt SUPPORTED_NETWORKS_LBL  = new CBORInt(2);
    public static final CBORInt RECEIPT_URL_LBL         = new CBORInt(3);

    public static final CBORInt COMMON_NAME_LBL         = new CBORInt(1); 
    public static final CBORInt AMOUNT_LBL              = new CBORInt(2); 
    public static final CBORInt CURRENCY_LBL            = new CBORInt(3);
    public static final CBORInt REFERENCE_ID_LBL        = new CBORInt(4);
    public static final CBORInt NON_DIRECT_PAYMENT_LBL  = new CBORInt(5); 
 
    // Unencryptef Data
    // PAYMENT_REQUEST_LBL
    public static final CBORInt PROVIDER_INFO_LBL       = new CBORInt(2);
    public static final CBORInt PAYEE_HOST_LBL          = new CBORInt(3);
    public static final CBORInt TIME_STAMP_LBL          = new CBORInt(4);

    public static final CBORInt NETWORK_ID_LBL          = new CBORInt(1); 
    public static final CBORInt SERVICE_LOCATOR_LBL     = new CBORInt(2);

    // Signed Authorization
    public static final CBORInt UNENCRYPTED_DATA_LBL    = new CBORInt(1);
    public static final CBORInt RESPONSE_ENCRYPTION_LBL = new CBORInt(2);
    public static final CBORInt ACCOUNT_ID_LBL          = new CBORInt(3);
    public static final CBORInt SERIAL_NUMBER_LBL       = new CBORInt(4);
    public static final CBORInt PLATFORM_DATA_LBL       = new CBORInt(5);   
    public static final CBORInt WALLET_DATA_LBL         = new CBORInt(6);
    public static final CBORInt LOCATION_LBL            = new CBORInt(7);

    public static final CBORInt AUTHZ_SIGNATURE_LBL     = new CBORInt(-1); 

    // Response Encryption
    public static final CBORInt ALGORITHM_LBL           = new CBORInt(1);
    public static final CBORInt INSTANCE_KEY_LBL        = new CBORInt(2);



}
