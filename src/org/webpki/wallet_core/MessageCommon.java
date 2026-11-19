package org.webpki.wallet_core;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.webpki.cbor.CBORArray;
import org.webpki.cbor.CBORInt;
import org.webpki.cbor.CBORObject;

class MessageCommon {

    // COTX Identifiers
    static final String AUTHZ_REQUEST_ID         = "https://cyberphone.github.io/saturn/areq/v1";
    static final String AUTHZ_RESPONSE_ID        = "https://cyberphone.github.io/saturn/ares/v1";
    static final String AUTHZ_RESPONSE_SIG_ID    = AUTHZ_RESPONSE_ID + "#sig";
  
    static final String NDP_GAS_ID               = "https://cyberphone.github.io/saturn/ndp/gas";

    // /.well-known/ extension
    static final String SATURM_CONFIGURATION     = "saturn-configuration";

    // Authorization Request
    static final CBORInt PAYMENT_REQUEST_LBL     = new CBORInt(1); 
    static final CBORInt SUPPORTED_NETWORKS_LBL  = new CBORInt(2);
    static final CBORInt RECEIPT_URL_LBL         = new CBORInt(3);

    static final CBORInt COMMON_NAME_LBL         = new CBORInt(1); 
    static final CBORInt AMOUNT_LBL              = new CBORInt(2); 
    static final CBORInt CURRENCY_LBL            = new CBORInt(3);
    static final CBORInt REFERENCE_ID_LBL        = new CBORInt(4);
    static final CBORInt NON_DIRECT_PAYMENT_LBL  = new CBORInt(5); 
 
    // Unencryptef Data
    // PAYMENT_REQUEST_LBL
    static final CBORInt PROVIDER_INFO_LBL       = new CBORInt(2);
    static final CBORInt LANGUAGE_LBL            = new CBORInt(3);
    static final CBORInt PAYEE_HOST_LBL          = new CBORInt(4);

    static final CBORInt NETWORK_ID_LBL          = new CBORInt(1); 
    static final CBORInt SERVICE_LOCATOR_LBL     = new CBORInt(2);

    // Signed Authorization
    static final CBORInt UNENCRYPTED_DATA_LBL    = new CBORInt(1);
    static final CBORInt RESPONSE_ENCRYPTION_LBL = new CBORInt(2);
    static final CBORInt ACCOUNT_ID_LBL          = new CBORInt(3);
    static final CBORInt SERIAL_NUMBER_LBL       = new CBORInt(4);
    static final CBORInt TIME_STAMP_LBL          = new CBORInt(5);
    static final CBORInt PLATFORM_DATA_LBL       = new CBORInt(6);   
    static final CBORInt WALLET_DATA_LBL         = new CBORInt(7);
    static final CBORInt LOCATION_LBL            = new CBORInt(8);

    // Response Encryption
    static final CBORInt ALGORITHM_LBL           = new CBORInt(1);
    static final CBORInt INSTANCE_KEY_LBL        = new CBORInt(2);

    static final Pattern URL_PATH_PATTERN        =  Pattern.compile("^https:\\/\\/\\S+\\/\\S+$");
    static final Pattern ANY_STRING_PATTERN      =  Pattern.compile("^\\S+$");

    static final int MAX_SUPPORTED_NETWORKS      = 20;

    enum SIGNED_AUTHZ {
        unencryptedData (UNENCRYPTED_DATA_LBL);

        CBORInt label;

        SIGNED_AUTHZ(CBORInt label) {
            this.label = label;
        }
    }

    static final int  MAX_ERROR_MESSAGE       = 100;

    static void walletError(String format, Object... args) {
        String error = String.format(format, args);
        if (error.length() > MAX_ERROR_MESSAGE) {
            error = error.substring(0, MAX_ERROR_MESSAGE - 3) + " ...";
        }
        throw new WalletException(error);
    }

    static String checkString(String string, Pattern pattern) {
        if (!pattern.matcher(string).matches()) {
            MessageCommon.walletError("\"%s\" does not match: %s", string, pattern.toString());
        }
        return string;
    }

    static ArrayList<CBORObject> getArray(CBORObject cborObject, int expectedElements) {
        CBORArray array = cborObject.getArray();
        if (array.size() == 0 || expectedElements < 0 ?
                     array.size() > -expectedElements : array.size() != expectedElements) {
            walletError("Array size out of range: %d", array.size());
        }
        return array.toArray();
    }

}
