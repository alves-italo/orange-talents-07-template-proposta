package com.zupacademy.italo.propostas.utilidades.criptografia;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Codificador {
    private String chave;
    private String salt;

    public Codificador() {
        this.chave = System.getenv().get("CODIFICADOR_CHAVE");
        this.chave = System.getenv().get("CODIFICADOR_SALT");
    }

    public static Codificador getCodificador() {
        return new Codificador();
    }

    public String codificar(String dado) {
        return this.encryptor().encrypt(dado);
    }

    public String decodificar(String dado) {
        return this.encryptor().decrypt(dado);
    }

    public String hash(String dado) {
        MessageDigest messageDigest;

        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return new BigInteger(1, messageDigest.digest(dado.getBytes())).toString(16);
    }

    public TextEncryptor encryptor() {
        return Encryptors.text(this.chave, this.salt);
    }
}
