package org.webpki.wallet_core;

import static org.webpki.wallet_core.Common.*;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.webpki.cbor.CBORArray;
import org.webpki.cbor.CBORAsymKeyDecrypter;
import org.webpki.cbor.CBORAsymKeyEncrypter;
import org.webpki.cbor.CBORAsymKeySigner;
import org.webpki.cbor.CBORAsymKeyValidator;
import org.webpki.cbor.CBORCryptoUtils;
import org.webpki.cbor.CBORDecoder;
import org.webpki.cbor.CBORKeyPair;
import org.webpki.cbor.CBORMap;
import org.webpki.cbor.CBORObject;
import org.webpki.cbor.CBORString;
import org.webpki.cbor.CBORTag;

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

    static final String OBJECT_ID = "https://saturn.standard/v4";

    static final String TIME_STAMP = "2024-09-01T13:28:02-02:00";

    KeyPair authorizationKey;
    KeyPair encryptionKey;

    String template;
    String docgenDirectory;

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
        updateTemplate(executor.getLink(),
            "<h5 id='" +
            executor.getLink() +
            "'>" +
            executor.getTitle() +
            "</h5>" +
            executor.getTableString());
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
            "<div class='webpkifloat'><div class='webpkibox' style='width:50em'>" +
            cleanText +
            "</div></div>");
    }

    void codeTable(String holder, CBORObject object) {
        codeTable(holder, htmlize(object.toString()));
    }

    CreateDocument(String templateFileName, 
                   String documentFileName,
                   String docgenDirectory,
                   String authKeyFile,
                   String encKeyFile) {
        this.docgenDirectory = docgenDirectory;
        template = UTF8.decode(IO.readFile(templateFileName));
        authorizationKey = getKeyPair("authorization-key", authKeyFile);
        encryptionKey = getKeyPair("encryption-key", encKeyFile);

        replace(new AuthorizationRequest());
        replace(new PaymentRequest());
        replace(new SignedAuthorization());
        replace(new AuthorizationResponse());
        replace(new PassThroughData());
        replace(new ServiceProvider());

        // Create AuthorizationRequest

        CBORMap paymentRequest = new CBORMap()
            .set(AMOUNT_LABEL, new CBORString("600.00"))
            .set(CURRENCY_LABEL, new CBORString("EUR"))
            .set(INSTANCE_ID_LABEL, new CBORString("722385402"))
            .set(COMMON_NAME_LABEL, new CBORString("Space Shop"));

        CBORMap serviceProvider = new CBORMap()
            .set(PAYMENT_NETWORK_LABEL, new CBORString(BANKNET2))
            .set(SERVICE_PROVIDER_LABEL, new CBORString("mybank.com"));

        CBORMap authorizationRequest = new CBORMap()
            .set(PAYMENT_REQUEST_LABEL, paymentRequest)
            .set(PAYMENT_NETWORKS_LABEL, new CBORArray()
                .add(new CBORString("https://cardnetwork.com"))
                .add(new CBORString(BANKNET2)));
        codeTable("authz-req.txt", authorizationRequest);

        // Create a singned authorization response

        CBORMap passThrough = new CBORMap()
            .set(PAYMENT_REQUEST_LABEL, paymentRequest)
            .set(SERVICE_PROVIDER_LABEL, serviceProvider);

        CBORMap signedAuthorization = new CBORMap()
            .set(PASS_THROUGH_LABEL, passThrough)
            .set(TIME_STAMP_LABEL, new CBORString(TIME_STAMP));
        new CBORAsymKeySigner(authorizationKey.getPrivate())
            .setPublicKey(authorizationKey.getPublic())
            .sign(SIGNATURE_LABEL, signedAuthorization);
        codeTable("signed-authz.txt", signedAuthorization);

        // Create the actual (encrypted) AuthorizationResponse

        signedAuthorization.remove(PASS_THROUGH_LABEL);
        byte[] cbor = new CBORAsymKeyEncrypter(encryptionKey.getPublic(), 
                                               KeyEncryptionAlgorithms.ECDH_ES_A128KW,
                                               ContentEncryptionAlgorithms.A256GCM)
            .setIntercepter(new CBORCryptoUtils.Intercepter() {
                @Override
                public CBORObject getCustomData() {
                    return passThrough;
                }
                @Override
                public CBORObject wrap(CBORMap map) {
                    return new CBORTag(OBJECT_ID, map);
                }          
            })
            .setPublicKeyOption(true)
            .encrypt(signedAuthorization.encode()).encode();
        CBORTag encrypted = CBORDecoder.decode(cbor).getTag();
        codeTable("authz-res.txt", encrypted);

        // Now, decode/decrypt/verify AuthorizationResponse
        // Note: this is performed by the Issuer!

        final CBORMap saveCustomData[] = new CBORMap[1];
        cbor = new CBORAsymKeyDecrypter(new CBORAsymKeyDecrypter.KeyLocator() {

            @Override
            public PrivateKey locate(PublicKey optionalPublicKey,
                                     CBORObject optionalKeyId,
                                     KeyEncryptionAlgorithms keyEncryptionAlgorithm,
                                     ContentEncryptionAlgorithms contentEncryptionAlgorithm) {
                // Simplistic key data base...
                if (!encryptionKey.getPublic().equals(optionalPublicKey)) {
                    throw new CryptoException("pubkey mismatch");
                }
                return encryptionKey.getPrivate();
            }
    
        }).setTagPolicy(CBORCryptoUtils.POLICY.MANDATORY, new CBORCryptoUtils.Collector() {

            @Override
            public void foundData(CBORObject object) {
                CBORTag cborTag = object.getTag();
                if (cborTag.getTagNumber() != CBORTag.RESERVED_TAG_COTX ||
                    !cborTag.getTaggedObject().getArray().get(0).getString().equals(OBJECT_ID)) {
                        throw new CryptoException("Un known tag:" + cborTag);
                    }
            }

        }).setCustomDataPolicy(CBORCryptoUtils.POLICY.MANDATORY, new CBORCryptoUtils.Collector() {

            @Override
            public void foundData(CBORObject customData) {
                saveCustomData[0] = customData.getMap();
            }
                                
        }).decrypt(encrypted);

        // Restore message and verify signature

        CBORMap restored = CBORDecoder.decode(cbor).getMap();
        restored.set(PASS_THROUGH_LABEL, saveCustomData[0]);

        new CBORAsymKeyValidator(authorizationKey.getPublic())
            .validate(SIGNATURE_LABEL, restored);
 
        IO.writeFile(documentFileName, template);
    }
  
    public static void main(String[] args) {
        new CreateDocument(args[0], args[1], args[2], args[3], args[4]);
    }
}