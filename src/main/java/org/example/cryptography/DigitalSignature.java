package org.example.cryptography;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Base64;

public class DigitalSignature {
    public static void main(String args[]){
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            // start signing the message
            Signature signature = Signature.getInstance("SHA256withDSA");
            signature.initSign(privateKey);
            byte[] msg = "msg".getBytes();
            signature.update(msg);
            byte[] sign = signature.sign();
            System.out.println("Digital signature for given text: "+new String(sign, "UTF8"));
            System.out.println(Base64.getEncoder().encodeToString(sign));

            // verifying the signature
            Signature verifySign = Signature.getInstance("SHA256withDSA");
            verifySign.initVerify(publicKey);
            verifySign.update("msg".getBytes());
            boolean signVerified = verifySign.verify(sign);
            if(signVerified){
                System.out.println("verified");
            } else {
                System.out.println("not verified");
            }

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
