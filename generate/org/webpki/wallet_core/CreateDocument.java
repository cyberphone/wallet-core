package org.webpki.wallet_core;

import static org.webpki.wallet_core.MessageCommon.*;

import java.io.File;
import java.io.IOException;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import java.util.ArrayList;

import org.webpki.cbor.CBORArray;
import org.webpki.cbor.CBORAsymKeyDecrypter;
import org.webpki.cbor.CBORAsymKeyEncrypter;
import org.webpki.cbor.CBORAsymKeySigner;
import org.webpki.cbor.CBORAsymKeyValidator;
import org.webpki.cbor.CBORCryptoUtils;
import org.webpki.cbor.CBORDecoder;
import org.webpki.cbor.CBORDiagnosticNotation;
import org.webpki.cbor.CBORFloat;
import org.webpki.cbor.CBORKeyPair;
import org.webpki.cbor.CBORMap;
import org.webpki.cbor.CBORObject;
import org.webpki.cbor.CBORString;
import org.webpki.cbor.CBORTag;

import org.webpki.crypto.AsymSignatureAlgorithms;
import org.webpki.crypto.ContentEncryptionAlgorithms;
import org.webpki.crypto.CryptoException;
import org.webpki.crypto.KeyEncryptionAlgorithms;

import org.webpki.jose.JOSEKeyWords;
import org.webpki.json.JSONObjectReader;
import org.webpki.json.JSONParser;

import org.webpki.util.IO;
import org.webpki.util.UTF8;

public class CreateDocument {

    static final String BANKNET2 = "https://banknet2.org"; 

    static final String CREDENTIAL_VERSION = "https://saturn.standard/cred/v1";

    static final String PAYEE_HOST = "spaceshop.com";

    static final String TIME_STAMP = "2024-12-10T13:28:02-01:00";

    static final String PAYER_ACCOUNT = "FR7630002111110020050014382";

    static final String SERIAL_NUMBER = "010049255";

    static final String REFERENCE_ID = "20241210.00079";

    static final String COPY_ATTRIBUTE =
        "Copy of the same attribute of the selected payment credential." +
        "<div style='padding-top:0.5em'>See also ${href.credential-database}.</div>";

    static final ContentEncryptionAlgorithms ENC_CONTENT = ContentEncryptionAlgorithms.A256GCM;
    static final KeyEncryptionAlgorithms ENC_KEY = KeyEncryptionAlgorithms.ECDH_ES_A128KW;

    static final String AUTH_RESP_FILE = "authz-res.txt";

    KeyPair authorizationKey;
    KeyPair encryptionKey;

    String template;
    String docgenDirectory;

    int outerCount = 0;
    int innerCount = 0;

    static class TOCEntry {
        String name;
        String rawName;
        String id;
        String rawId;
        boolean indent;
    }

    ArrayList<TOCEntry> tocEntries = new ArrayList<>();

    void addTocEntry(String name, String optional) {
        TOCEntry tocEntry = new TOCEntry();
        String prefix;
        int h;
        if (innerCount == 0) {
            prefix = String.valueOf(++outerCount);
            h = 3;
        } else {
            prefix = String.valueOf(outerCount) + "." + String.valueOf(innerCount++);
            h = 5;
            tocEntry.indent = true;
        }
        tocEntry.rawName = name.replace(" ", "&nbsp;");
        tocEntry.name = prefix + ".&nbsp; " + tocEntry.rawName;
        tocEntry.rawId = name.toLowerCase()
                        .replace(' ', '-')
                        .replace("&quot;", "");
        tocEntry.id = prefix + "." + tocEntry.rawId;
        updateTemplate(tocEntry.rawId, 
                       "<h" + h + " id='" + tocEntry.id + "'>" + tocEntry.name + "</h" + h + ">" +
                       (optional == null ? "" : optional));
        tocEntries.add(tocEntry);
    }

    void addTocEntry(String name) {
        addTocEntry(name, null);
    }

    KeyPair getKeyPair(String holder, String jwkFile) {
        JSONObjectReader jwk = JSONParser.parse(IO.readFile(jwkFile));
        jwk.removeProperty(JOSEKeyWords.KID_JSON);
        codeTable(holder + ".jwk", htmlize(jwk.toString()));
        KeyPair keyPair = jwk.getKeyPair();
        codeTable(holder + ".txt", CBORKeyPair.convert(keyPair));
        return keyPair;
    }

    void updateTemplate(String holder, String replacement) {
        String oldTemplate = template;
        template = template.replace("${" + holder + "}", replacement);
        if (oldTemplate.length() == template.length()) {
            throw new RuntimeException("Did not find: " + holder);
        }        
    }

    void replace(TableExecutor executor) {
        addTocEntry(executor.getTitle(),
            (executor.getBeforeText() == null ? "" : executor.getBeforeText()) +
            executor.getTableString() +
            (executor.getAfterText() == null ? "" : executor.getAfterText()));
    }

    String htmlize(String text) {
        return text.replace("&", "&amp;")
                   .replace("\"", "&quot;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace(" ", "&nbsp;")
                   .replace("\n", "<br>");
    }

    void codeTable(String holder, String cleanText) {
        updateTemplate(holder,
            "<div class='webpkifloat'><div class='webpkibox' id='" +
            holder +
            "' style='width:50em'>" +
            cleanText +
            "</div></div>");
    }

    void codeTable(String holder, CBORObject object) {
        codeTable(holder, htmlize(object.toString()));
    }
  
    CBORObject createAuthz() {

        // Create AuthorizationRequest

        CBORMap paymentRequest = new CBORMap()
            .set(AMOUNT_LBL, new CBORString("600.00"))
            .set(CURRENCY_LBL, new CBORString("EUR"))
            .set(REFERENCE_ID_LBL, new CBORString(REFERENCE_ID))
            .set(COMMON_NAME_LBL, new CBORString("Space Shop"));

        CBORMap serviceProvider = new CBORMap()
            .set(NETWORK_ID_LBL, new CBORString(BANKNET2))
            .set(PROVIDER_INFO_LBL, new CBORString("mybank.com"));

        CBORMap authorizationRequest = new CBORMap()
            .set(PAYMENT_REQUEST_LBL, paymentRequest)
            .set(SUPPORTED_NETWORKS_LBL, new CBORArray()
                .add(new CBORString("https://cardnetwork.com"))
                .add(new CBORString(BANKNET2)))
            .set(RECEIPT_URL_LBL, new CBORString(
                "https://" + PAYEE_HOST + 
                "/receipts/" + REFERENCE_ID + ".MNloPyPahXxr43flXzufdQ"));
        codeTable("authz-req.txt", authorizationRequest);

        // Create a signed authorization response

        CBORMap unencryptedData = new CBORMap()
            .set(PAYMENT_REQUEST_LBL, paymentRequest)
            .set(PROVIDER_INFO_LBL, serviceProvider)
            .set(PAYEE_HOST_LBL, new CBORString(PAYEE_HOST))
            .set(TIME_STAMP_LBL, new CBORString(TIME_STAMP));

        CBORArray platformData = new CBORArray()
            .add(new CBORString("Android"))
            .add(new CBORString("14.1"));

        CBORArray walletData = new CBORArray()
            .add(new CBORString("Saturn"))
            .add(new CBORString("1.0.0"));
        
        CBORArray location = new CBORArray()
            .add(new CBORFloat(38.88820))
            .add(new CBORFloat(-77.01988));

            CBORObject signedAuthorization = new CBORAsymKeySigner(authorizationKey.getPrivate())
            .setPublicKey(authorizationKey.getPublic())
            .setIntercepter(new CBORCryptoUtils.Intercepter() {
                @Override
                public CBORObject wrap(CBORMap map) {
                    return new CBORTag(SIGNED_AUTHZ_ID, map);
                }         
            })
            .sign(AUTHZ_SIGNATURE_LBL, new CBORMap()
                .set(UNENCRYPTED_DATA_LBL, unencryptedData)
                .set(ACCOUNT_ID_LBL, new CBORString(PAYER_ACCOUNT))
                .set(SERIAL_NUMBER_LBL, new CBORString(SERIAL_NUMBER))
                .set(PLATFORM_DATA_LBL, platformData)
                .set(LOCATION_LBL, location)
                .set(WALLET_DATA_LBL, walletData));

        codeTable("signed-authz.txt", signedAuthorization);

        // Create the actual (encrypted) AuthorizationResponse

        CBORTag signatureTag = signedAuthorization.getTag();
        CBORMap dataToBeEncrypted = signatureTag.get().getArray().get(1).getMap();
        signatureTag.get().getArray().update(1, dataToBeEncrypted.remove(UNENCRYPTED_DATA_LBL));
 
        byte[] cbor = new CBORAsymKeyEncrypter(encryptionKey.getPublic(), 
                                               ENC_KEY,
                                               ENC_CONTENT)
            .setIntercepter(new CBORCryptoUtils.Intercepter() {
                @Override
                public CBORObject getCustomData() {
                    return signatureTag;
                }
                @Override
                public CBORObject wrap(CBORMap map) {
                    return new CBORTag(ENCRYPTED_AUTHZ_ID, map);
                }          
            })
            .setPublicKeyOption(true)
            .encrypt(dataToBeEncrypted.encode()).encode();
        return CBORDecoder.decode(cbor);
    }

    CBORTag issuerDecrypt(CBORObject authorizationResponse, boolean update) {
        final CBORTag saveCustomData[] = new CBORTag[1];
        byte[] cbor = new CBORAsymKeyDecrypter(new CBORAsymKeyDecrypter.KeyLocator() {

            @Override
            public PrivateKey locate(PublicKey optionalPublicKey,
                                     CBORObject optionalKeyId,
                                     KeyEncryptionAlgorithms keyEncryptionAlgorithm,
                                     ContentEncryptionAlgorithms contentEncryptionAlgorithm) {
                // Simplistic key data base...
                if (!encryptionKey.getPublic().equals(optionalPublicKey)) {
                    throw new CryptoException("pubkey mismatch");
                }
                if (keyEncryptionAlgorithm != ENC_KEY || 
                    contentEncryptionAlgorithm != ENC_CONTENT) {
                    throw new CryptoException("alg mismatch");
                }
                return encryptionKey.getPrivate();
            }
    
        }).setTagPolicy(CBORCryptoUtils.POLICY.MANDATORY, new CBORCryptoUtils.Collector() {

            @Override
            public void foundData(CBORObject object) {
                CBORTag cborTag = object.getTag();
                if (cborTag.getTagNumber() != CBORTag.RESERVED_TAG_COTX ||
                    !cborTag.get().getArray().get(0).getString().equals(ENCRYPTED_AUTHZ_ID)) {
                        throw new CryptoException("Unknown tag:" + cborTag);
                }
            }

        }).setCustomDataPolicy(CBORCryptoUtils.POLICY.MANDATORY, new CBORCryptoUtils.Collector() {

            @Override
            public void foundData(CBORObject customData) {
                saveCustomData[0] = customData.clone().getTag();
            }
                                
        }).decrypt(authorizationResponse);
        if (update) {
            codeTable("unencrypted-data.txt", saveCustomData[0]);
        }

        // Restore signed message

        CBORMap decryptedData = CBORDecoder.decode(cbor).getMap();
        if (update) {
            codeTable("restored.txt", decryptedData);
        }

        // It helps having a potent CBOR implementation...
        
        CBORTag signedData = saveCustomData[0];
        CBORArray object = signedData.get().getArray();
        object.update(1, decryptedData.set(UNENCRYPTED_DATA_LBL, object.get(1)));
        return signedData;
    }

    CBORObject verifyAuthz(CBORObject authorizationResponse, boolean update) {

        // Now, decode/decrypt/verify AuthorizationResponse
        // Note: this is performed by the Issuer!


        // We want to 1) enforce public key 2) check key for trust after validation
        PublicKey[] suppliedPublicKey = new PublicKey[1];
        CBORObject retVal = new CBORAsymKeyValidator(new CBORAsymKeyValidator.KeyLocator() {

            @Override
            public PublicKey locate(PublicKey optionalPublicKey, 
                                    CBORObject optionalKeyId, 
                                    AsymSignatureAlgorithms asymSignatureAlgorithm) {
                if (optionalPublicKey == null) {
                    throw new CryptoException("Missing public key");
                }
                if (asymSignatureAlgorithm != AsymSignatureAlgorithms.ED25519) {
                    throw new CryptoException("Invalid algorithm: " + asymSignatureAlgorithm);
                }
                suppliedPublicKey[0] = optionalPublicKey;
                return optionalPublicKey;
            }

        })
        .setTagPolicy(CBORCryptoUtils.POLICY.MANDATORY, new CBORCryptoUtils.Collector() {

            @Override
            public void foundData(CBORObject object) {
                CBORTag cborTag = object.getTag();
                if (cborTag.getTagNumber() != CBORTag.RESERVED_TAG_COTX ||
                    !cborTag.get().getArray().get(0).getString().equals(SIGNED_AUTHZ_ID)) {
                        throw new CryptoException("Unknown tag:" + cborTag);
                }
            }
        })
        .validate(AUTHZ_SIGNATURE_LBL, issuerDecrypt(authorizationResponse, update));
        if (!suppliedPublicKey[0].equals(authorizationKey.getPublic())) {
            throw new CryptoException("Unknown public key");
        }
        return retVal;
    }

    CreateDocument(String templateFileName, 
                   String documentFileName,
                   String docgenDir,
                   String authKeyFile,
                   String encKeyFile) {
        docgenDirectory = docgenDir + File.separator;
        template = UTF8.decode(IO.readFile(docgenDirectory + templateFileName));
        authorizationKey = getKeyPair("authorization-key", authKeyFile);
        encryptionKey = getKeyPair("encryption-key", encKeyFile);

        addTocEntry("Introduction");
        addTocEntry("Detailed Operation");
 
        innerCount = 1;
        addTocEntry("Sequence Diagram");
        addTocEntry("Wallet Initiation");
        addTocEntry("Wallet Request UI");
        addTocEntry("Payer Authorization");
        addTocEntry("Calling the Payment Network");
        addTocEntry("Wallet Termination");
 
        innerCount = 0;
        addTocEntry("Message Reference");
  
        innerCount = 1;
        replace(new AuthorizationRequest());
        replace(new PaymentRequest());
        replace(new AuthorizationResponse());
        replace(new KeyEncryption());
        replace(new UnencryptedData());
        replace(new ProviderInfo());
        replace(new SignedAuthorization());

        innerCount = 0;
        addTocEntry("Credential Database");
        updateTemplate("credential-database-entry", 
                       new CredentialDatabaseEntry().getHtml());
        addTocEntry("Credential Enrollment");
        addTocEntry("Authorization Processing");

        innerCount = 1;
        addTocEntry("Decryption");
        addTocEntry("Signature Validation");

        innerCount = 0;
        addTocEntry("Non-direct Payments (NDP)");
        innerCount = 1;
        addTocEntry("NDP Objects");
        addTocEntry("Gas Station Payments");
        updateTemplate("gas-station-payments-profile", 
                       new GasStationPaymentsProfile().getHtml());
        innerCount = 0;
        addTocEntry("Algorithm Support");
        addTocEntry("Security Considerations");
        addTocEntry("Test Vectors");
        addTocEntry("Version");

        CBORObject authz = createAuthz();

/* test verifying that the unencrypted data is protected by AEAD
        authz.getTag().getTaggedObject().getArray().get(1).getMap()
            .get(org.webpki.cbor.CBORCryptoConstants.CUSTOM_DATA_LABEL).getMap()
            .get(PAYMENT_REQUEST_LABEL).getMap()
            .remove(ACCOUNT_ID_LABEL);
*/
        // To avoid updating index.html each time we run the doc builder
        // we keep a reference to a previous build.
        String refFile = docgenDirectory + AUTH_RESP_FILE;
        try {
            CBORObject refAuthz = CBORDiagnosticNotation.convert(UTF8.decode(IO.readFile(refFile)));
            if (verifyAuthz(authz, true).equals(verifyAuthz(refAuthz, false))) {
                authz = refAuthz;
            } else {
                throw new IOException("changed");
            }
        } catch (Exception e) {
            IO.writeFile(refFile, authz.toString());
            System.out.println("*** WROTE ***=" + e.getMessage());
        }
        codeTable(AUTH_RESP_FILE, authz);

        // Fill in external links
        for (ExternalLinks link : ExternalLinks.values()) {
            template = template.replace(link.getHolder(), link.getHtml());
        }

        StringBuilder s = new StringBuilder();
        int length = tocEntries.size();    
        for (int i = 0; i < length; i++) {
            TOCEntry tocEntry = tocEntries.get(i);
            boolean next = tocEntries.get((i < length - 1) ? i + 1 : i).indent;
            String image = "empty.svg' style='height:1em;margin-right:1em";
            if (next && !tocEntry.indent) {
              image = "closed.svg' onclick='tocSwitch(this)' style='height:1em;margin-right:1em;cursor:pointer";
            }
            template = template.replace("${href." + tocEntry.rawId + "}", 
                                        "<a href='#" + tocEntry.id +
                                            "'>" + 
                                            tocEntry.rawName + "</a>");
            s.append("<div style='padding: 0 0 0.4em ")
             .append(tocEntry.indent ? 4 : 2)
             .append("em'><img alt='n/a' src='")                   
             .append(image)
             .append("'><a href='#")
             .append(tocEntry.id)
             .append("'>")
             .append(tocEntry.name)
             .append("</a></div>");
            if (next && !tocEntry.indent) {
                s.append("<div style='display:none'>");
            } else if (!next && tocEntry.indent) {
                s.append("</div>");                
            }
            s.append('\n');
        }
        updateTemplate("toc", s.toString());
        
        int macroPosition = template.indexOf("${");
        if (macroPosition > 0) {
            throw new RuntimeException("unresolved macro: " + 
                template.substring(macroPosition, macroPosition + 20));
        }
 
        IO.writeFile(documentFileName, template);
    }
  
    public static void main(String[] args) {
        new CreateDocument(args[0], args[1], args[2], args[3], args[4]);
    }
}