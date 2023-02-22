package sk.fmfi.listng.domain.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionTest {

    @Test
    void encrypt() throws Exception {
        String pass1 = "Lorem Ipsum is simply dummy text of the printing and typesetting industry";
        String pass2 = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s";

        String encrypted_pass1 = Encryption.encrypt(pass1);
        String encrypted_pass2 = Encryption.encrypt(pass2);

        assertNotEquals(encrypted_pass1, encrypted_pass2);
        assertEquals(encrypted_pass1, encrypted_pass1);
    }

    @Test
    void validate() throws Exception {
        String pass1 = "Lorem Ipsum is simply dummy text of the printing and typesetting industry";
        String pass2 = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s";

        String encrypted_pass1 = Encryption.encrypt(pass1);

        assertFalse(Encryption.validate(pass2, encrypted_pass1));
        assertTrue(Encryption.validate(pass1, encrypted_pass1));
    }
}
