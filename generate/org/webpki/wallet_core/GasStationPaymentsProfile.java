package org.webpki.wallet_core;

class GasStationPaymentsProfile {

    String getHtml() {
        return
"A gas station payment consists of two operations: 1) Reservation of a <i>maximum " +
"amount</i> of money to be used. 2) Resolving the reservation by debiting the " +
"<code class='entity'>Payer</code> for the actual cost of the fill-up. " +
"Note that a valid receipt (step #7 in the ${href.sequence-diagram}) can only " +
"be made available after the second phase has been executed. " +
"<p>" +
"For gas station payments, <kbd>ndpObjectId</kbd> <b>must</b> be set to: <code>&quot;" +
MessageCommon.NDP_GAS_ID +
"&quot;</code>, " +
"while the <i>Additional&nbsp;Parameters</i> consist of single CBOR " +
"integer (<code>int</code>) holding the " +
"number of hours (1-24) the reservation will remain " +
"valid before being automatically revoked by the account-holding entity. " +
"It is recommended to have a margin of at least 15 minutes." +
"</p>" +
"The CBOR object " +
"<div class='webpkifloat'><div style='padding:1em 2em'>" +
"<code>1010([&quot;" + MessageCommon.NDP_GAS_ID  + "&quot;, 2])</code></div></div>" +
"thus represents an argument to a ${href.payment-request}, " +
"for a gas station payment with a validity of 2 hours." +
"<p>" +
"Note that <code>&quot;" + MessageCommon.NDP_GAS_ID + "&quot;</code> " +
"represents a temporary name allocation." +
"</p>" +
"<p>" +
"Since non-direct payments differ from one-off payments, the " +
"<code class='entity'>Wallet</code> UI should also reflect such requests in " +
"a <i>meaningful</i> way." +
"<p style='padding-bottom:0; margin-bottom:0'>" +
"For gas station payments, the following appears like a suitable solution:" +
"</p> " +
"<div class='webpkifloat'><img src='gas-payment-ui.svg' style='padding:0;width:28em' " + 
"class='webpkibox' title='Partial Wallet UI'></div>";
    }

}
