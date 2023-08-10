package com.example.secretkeyexample.configuration;

import com.example.secretkeyexample.aws.EncryptedSDK;
import com.example.secretkeyexample.aws.SecretManager;

public class EncryptKeyConfiguration {
    public static final String ENCRYPTION_KEY = EncryptedSDK.decryptWithEncryptSDK(SecretManager.getSecret());

}
