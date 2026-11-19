package org.webpki.wallet_core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

import org.webpki.cbor.CBORDiagnosticNotation;

public class TestWallet {

    void checkException(Exception e, String format, Object... args) {
        assertFalse("Format==null", format == null);
        String m = e.getMessage();
        String full = m;
        String compareMessage;
        if (args.length > 0) {
            compareMessage = String.format(format, args);
        } else {
            compareMessage = format;
            int i = compareMessage.indexOf('%');
            if (i > 0) {
                compareMessage = compareMessage.substring(0, i);
            }
        }
        if (compareMessage.length() < m.length()) {
            m = m.substring(0, compareMessage.length());
        }
        if (!m.equals(compareMessage)) {
            fail("Exception: " + full + "\ncompare: " + compareMessage);
        }
    }

    @Test
    public void createRequest() {

    }

    void decodeRequest(String cborText, String failTextOrNull) {
        try {
            new AuthorizationRequestDecoder(CBORDiagnosticNotation.convert(cborText));
            assertTrue("Must not", failTextOrNull == null);
        } catch (Exception e) {
            checkException(e, failTextOrNull);
        }
    }

    @Test
    public void decodeRequestTest() {
        decodeRequest("""
        1010(["https://cyberphone.github.io/saturn/areq/v1", {
            1: {
                1: "Space Shop",
                2: "600.00",
                3: "EUR",
                4: "20261113.00079"
            },
            2: ["https://cardnetwork.com", "https://banknet2.org"],
            3: "https://spaceshop.com/receipts/20261113.00079.MNloPyPahXxr43flXzufdQ"
            }])
            """, null);

       decodeRequest("""
        1010(["https://cyberphone.github.io/saturn/areq/v1", {
            1: {
                1: "Space Shop",
                2: "600.00",
                3: "EUR",
                4: "20261113.00079"
            },
            2: ["https://cardnetwork.com", "https://banknet2.org"]
            }])
            """, null);

       decodeRequest("""
        1010(["https://cyberphone.github.io/saturn/areq/v1", {
            1: {
                1: "Space Shop",
                2: "600.00",
                3: "EUR",
                4: "20261113.00079"
            },
            2: []
            }])
            """, "Array size out of range: 0");

        decodeRequest("""
        1010(["https://cyberphone.github.io/saturn/areq/v1", {
            1: {
                1: "Space Shop ",
                2: "600.00",
                3: "EUR",
                4: "20261113.00079"
            },
            2: ["https://cardnetwork.com", "https://banknet2.org"],
            3: "https://spaceshop.com/receipts/20261113.00079.MNloPyPahXxr43flXzufdQ"
            }])
            """, "\"Space Shop \" does not match");

        decodeRequest("""
        1010(["https://cyberphone.github.io/saturn/areq/v1", {
            1: {
                1: "Space Shop",
     #           2: "600.00",
                3: "EUR",
                4: "20261113.00079"
            },
            2: ["https://cardnetwork.com", "https://banknet2.org"],
            3: "https://spaceshop.com/receipts/20261113.00079.MNloPyPahXxr43flXzufdQ"
            }])
            """, "Missing key: 2");

        decodeRequest("""
        1010(["https://cyberpho", {
            1: {
                1: "Space Shop",
                2: "600.00",
                3: "EUR",
                4: "20261113.00079"
            },
            2: ["https://cardnetwork.com", "https://banknet2.org"],
            3: "https://spaceshop.com/receipts/20261113.00079.MNloPyPahXxr43flXzufdQ"
            }])
            """, "Unexpected COTX id: https://cyberpho");

        decodeRequest("""
        1010(["https://cyberphone.github.io/saturn/areq/v1", {
            1: {
                1: "Space Shop",
                2: "600.00",
                3: "EUR",
                4: "20261113.00079",
                5: "here there should be an NDP"
            },
            2: ["https://cardnetwork.com", "https://banknet2.org"],
            3: "https://spaceshop.com/receipts/20261113.00079.MNloPyPahXxr43flXzufdQ"
            }])
            """, "Is type: CBORString, requested: CBORTag");
    }

}
