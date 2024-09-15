package org.webpki.wallet_core;

class GasStationPaymentsProfile extends NonDirectPaymentProfile {

    static final String TIME_OUT_NAME = "timeOut";

    GasStationPaymentsProfile() {
        super(NDPGasStation.GAS_STATION_1_ID);
        add(NDPGasStation.TIME_OUT_LABEL, TIME_OUT_NAME, Types.INT,
            "Number of hours (1-24) the reservation will remain valid before " +
            "being automatically revoked by the account-holding entity. " +
            "It is recommended to have a margin of at least 15 minutes.");
    }

    String getHtml() {
        return
"""
A gas station payment consists of two operations: 1) Reservation of a <i>maximum
amount</i> of money to be used. 2) Resolving the reservation by debiting the 
<code class='entity'>Payer</code> for the actual cost of the fill-up.
Note that a valid receipt (step #7 in the ${href.sequence-diagram}) can only
be made available after the second phase has been executed.
""" +
"<p style='padding-bottom:0; margin-bottom:0'>" +
"All non-direct payment profiles <b>must</b> feature a <kbd>" +
NON_DIR_PAY_ID_NAME +
"</kbd> entry (label <code>1</code>) holding a unique identifier in the form of a URL. " +
"This entry may be followed by other, profile-specific elements required for " +
"describing the operation at hand. " +
"Below is the gas station payment profile:" +
"</p>"
+ getTableString() +
"""
Since non-direct payments differ from one-off payments, the 
<code class='entity'>Wallet</code> UI should also reflect such requests in
a <i>meaningful</i> way.
<p style='padding-bottom:0; margin-bottom:0'>
For gas station payments, the following appears like
a suitable solution:
</p>
<div class='webpkifloat'><img src='gas-payment-ui.svg' style='padding:0;width:28em' 
class='webpkibox'  title='Partial Wallet UI'></div>
""";
    }

}
