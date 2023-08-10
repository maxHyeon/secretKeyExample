package com.example.secretkeyexample;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.secretkeyexample.configuration.EncryptKeyConfiguration.ENCRYPTION_KEY;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class EncryptKeyConfigurationTest {

    @Test
    public void testGetEncryptionKey() {
        assertEquals(ENCRYPTION_KEY, "ThisIsEncryptedKey");
    }
}
