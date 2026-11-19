package org.webpki.wallet_core;

import static org.webpki.wallet_core.MessageCommon.*;

import java.util.regex.Pattern;

import org.webpki.cbor.CBORMap;
import org.webpki.cbor.CBORObject;
import org.webpki.cbor.CBORString;
import org.webpki.cbor.CBORTag;

public class PaymentRequest extends MapCommon {

    static final String AMOUNT_NAME             = "amount";
    static final String COMMON_NAME_NAME        = "commonName";
    static final String CURRENCY_NAME           = "currency";
    static final String REFERENCE_ID_NAME       = "referenceId";
    static final String NON_DIRECT_PAYMENT_NAME = "nonDirectPayment";

    static final Pattern COMMON_NAME_PATTERN    = Pattern.compile("^\\S+(\\s?\\S)*$");
    static final Pattern AMOUNT_PATTERN         = Pattern.compile("^(0|[1-9]\\d{0,17})\\.\\d{2}$");
    static final Pattern CURRENCY_PATTERN       = Pattern.compile("^[A-Z]{3}$");
    static final Pattern REFERENCE_ID_PATTERN   = Pattern.compile("^\\S{6,}$");

    public final String commonName;

    public final String amount;

    public final String currency;

    public final String referenceId;

    public final CBORTag nonDirectPayment;

    PaymentRequest() { // For documentation purposes only
        this("N/A", "0.00", "USD", "333333334333", null);
    }

    public PaymentRequest(String commonName,
                          String amount,
                          String currency,
                          String referenceId,
                          CBORObject nonDirectPayment) {
                              this(new CBORMap()
                                  .set(COMMON_NAME_LBL, new CBORString(commonName))
                                  .set(AMOUNT_LBL, new CBORString(amount))
                                  .set(CURRENCY_LBL, new CBORString(currency))
                                  .set(REFERENCE_ID_LBL, new CBORString(referenceId))
                                  .setDynamic((wr) -> nonDirectPayment == null ? 
                                      wr : wr.set(NON_DIRECT_PAYMENT_LBL, nonDirectPayment)));
    }

    public PaymentRequest(CBORObject paymentRequest) {
        mapObject = paymentRequest.getMap();
        commonName = getString(COMMON_NAME_LBL, COMMON_NAME_PATTERN);
        amount = getString(AMOUNT_LBL, AMOUNT_PATTERN);
        currency = getString(CURRENCY_LBL, CURRENCY_PATTERN);
        referenceId = getString(REFERENCE_ID_LBL, REFERENCE_ID_PATTERN);
        CBORObject ndp = mapObject.getConditionally(NON_DIRECT_PAYMENT_LBL, null);
        nonDirectPayment = ndp == null ? null : ndp.getTag();
    }

    @Override
    String getTableString() {
        return new Table()

            .add(COMMON_NAME_LBL, COMMON_NAME_NAME, Types.TSTR,
                "<code class='entity'>Payee</code> common name to be shown in the " +
                "${href.wallet-request-ui}" +
                "<div style='padding-top:0.5em'>" +
                "Note that common and <i>legal</i> names often differ.</div>")

            .add(AMOUNT_LBL, AMOUNT_NAME, Types.TSTR,
                "Monetary amount compatible with the regular expression: " +
                "<code style='white-space:nowrap'>^(0|[1-9][0-9]*)(\\.[0-9]+)?$</code>. " +
                "<div style='padding-top:0.5em'>" +
                "Amounts <b>must&nbsp;not</b> use more decimals than is " +
                "custom for prices for the specific <kbd>currency</kbd>.</div>")

            .add(CURRENCY_LBL, CURRENCY_NAME, Types.TSTR,
                "Currency expressed in the ${href.iso4217} <i>alphabetical</i> format." +
                "<div style='padding-top:0.5em'>" +
                "See also <kbd>" + CredentialDatabaseEntry.CURRENCIES + "</kbd> in the " +
                "${href.credential-database}.</div>")
    
            .add(REFERENCE_ID_LBL, REFERENCE_ID_NAME, Types.TSTR,
                "<code class='entity'>Payee</code> reference Id. " +
                "<div style='padding-top:0.5em'>" +
                "Reference Ids <b>must</b> be unique with respect to the " +
                "<code class='entity'>Payee</code>.</div>")

            .add(NON_DIRECT_PAYMENT_LBL, NON_DIRECT_PAYMENT_NAME, Types.COTX,
                "<i>Optional</i>: See also ${href.non-direct-payments-(ndp)}.")

           .getTableString();
    }

    @Override
    String getTitle() {
        return "Payment Request";
    }
}
