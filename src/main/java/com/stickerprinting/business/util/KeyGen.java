package com.stickerprinting.business.util;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Base64;

public class KeyGen {
    public static void main(String[] args) {

        SecretKey key = Jwts.SIG.HS256.key().build();

        String encoded = Base64.getEncoder()
                .encodeToString(key.getEncoded());

        System.out.println(encoded);
    }
}