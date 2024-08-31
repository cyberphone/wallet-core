package org.webpki.wallet_core;

import org.webpki.cbor.CBORInt;

public class Common {

    static final CBORInt PAYMENT_REQUEST_LABEL = new CBORInt(1); 

    static final CBORInt COMMON_NAME_LABEL     = new CBORInt(1); 
    static final CBORInt INSTANCE_ID_LABEL     = new CBORInt(2);
    static final CBORInt AMOUNT_LABEL          = new CBORInt(3); 
    static final CBORInt CURRENCY_LABEL        = new CBORInt(4); 
    static final CBORInt NON_DIRECT_LABEL      = new CBORInt(5); 

    static final CBORInt PAYMENT_INFO_LABEL    = new CBORInt(2);

    static final CBORInt PAYMENT_NETWORK_LABEL = new CBORInt(1); 
    static final CBORInt PAYMENT_SERVICE_LABEL = new CBORInt(2);
    static final CBORInt PAYEE_HOST_LABEL      = new CBORInt(3);
    static final CBORInt ACCOUNT_ID_LABEL      = new CBORInt(4);
    static final CBORInt SERIAL_NUMBER_LABEL   = new CBORInt(5);
    static final CBORInt PLATFORM_DATA_LABEL   = new CBORInt(6);
    static final CBORInt WALLET_SOFTWARE_LABEL = new CBORInt(7);
    static final CBORInt LOCATION_LABEL        = new CBORInt(8);
    static final CBORInt TIME_STAMP_LABEL      = new CBORInt(9);

    static final CBORInt SIGNATURE_LABEL       = new CBORInt(-1); 

}
