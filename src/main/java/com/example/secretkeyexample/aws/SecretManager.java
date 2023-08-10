package com.example.secretkeyexample.aws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

@Component
public class SecretManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecretManager.class);

    private static String secretId;

    @Value("${aws.secretsmanager.secretId}")
    public void setSecretName(String value){
        secretId=value;
    }

    public static SdkBytes getSecret() {
//        String secretName = "dev/EncryptKey";
//        Region region = Region.of("ap-northeast-2");

        // Create a Secrets Manager client
        SecretsManagerClient client = SecretsManagerClient.builder()
//                .region(region)
                .build();

        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretId)
                .build();

        GetSecretValueResponse getSecretValueResponse;

        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);

        } catch (Exception e) {
            throw e;
        }
        SdkBytes secret = SdkBytes.fromByteArray(getSecretValueResponse.secretBinary().asByteArray());
//        LOGGER.info(Kms.decryptWithEncryptSDK("arn:aws:kms:ap-northeast-2:014104314849:alias/encryptKey",secret));
        return secret;
    }
}