package com.pacilnugas.account.security;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import java.security.spec.KeySpec;

public class PasswordCrypter {
    private static final String UNICODE_FORMAT = "UTF8";
    public static String DESEDE_ENCRYPTION_SCHEME;
    private KeySpec ks;
    private SecretKeyFactory skf;
    private Cipher cipher;
    byte[] arrayBytes;
    private String myEncryptionKey;
    private String myEncryptionScheme;
    SecretKey key;

    /**
     * Constructor for a password crypter used for storing and retrieving password safely.
     */
    public PasswordCrypter(String encryptionScheme) throws Exception {
        DESEDE_ENCRYPTION_SCHEME = encryptionScheme;
        myEncryptionKey = "ThisIsSpartaThisIsSparta";
        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        ks = new DESedeKeySpec(arrayBytes);
        skf = SecretKeyFactory.getInstance(myEncryptionScheme);
        cipher = Cipher.getInstance(myEncryptionScheme);
        key = skf.generateSecret(ks);
    }

    /**
     * Password encryption that is used for storing password and retrieving password.
     */
    public String encrypt(String unencryptedString, boolean emulateError) {
        String encryptedString = null;
        try {
            if (emulateError) {
                throw new Exception();
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, key);
                byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
                byte[] encryptedText = cipher.doFinal(plainText);
                encryptedString = new String(Base64.encodeBase64(encryptedText));
            }
        } catch (Exception e) {
            encryptedString = "";
        }
        return encryptedString;
    }
}
