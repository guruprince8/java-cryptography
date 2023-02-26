package org.example.cryptography;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Base64;

public class CryptoOperations {

    public void executeCryptoAlgorithm(String algorithm,String transformation){
        try {
            KeyPairGenerator keyPairGenerator= KeyPairGenerator.getInstance(algorithm);
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            System.out.println("Public Key is"+publicKey);
            PrivateKey privateKey = keyPair.getPrivate();
            //ByteBuffer byteBuffer = new ByteBuffer();
            System.out.println("Private Key is"+ Base64.getEncoder().encodeToString(privateKey.getEncoded()));

            // start encryption
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE,publicKey);
            byte[] input = "hello".getBytes();
            cipher.update(input);
            byte [] cipherBytes = cipher.doFinal();
            System.out.println("Encrypted Bytes "+new String(cipherBytes,"UTF-8"));

            // start decryption
            cipher.init(Cipher.DECRYPT_MODE,privateKey);
            byte [] decryptedMessage = cipher.doFinal(cipherBytes);
            System.out.println("Decrypted Message "+new String(decryptedMessage));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static  void main(String args[]){
        CryptoOperations rsaAlgorithm = new CryptoOperations();
        rsaAlgorithm.executeCryptoAlgorithm("RSA","RSA/ECB/PKCS1Padding");
    }
}
