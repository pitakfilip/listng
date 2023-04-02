package sk.fmfi.listng.domain.utils;

import io.jsonwebtoken.Jwts;

public class JWT {

    public static void main(String[] args) {
        String token = Jwts.builder()
                .setSubject("pitak4@uniba.sk")
                .compact();

        System.out.println(token);
    }
}
