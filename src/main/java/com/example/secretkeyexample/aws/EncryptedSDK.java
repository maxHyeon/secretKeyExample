package com.example.secretkeyexample.aws;

import com.amazonaws.encryptionsdk.AwsCrypto;
import com.amazonaws.encryptionsdk.CommitmentPolicy;
import com.amazonaws.encryptionsdk.CryptoResult;
import com.amazonaws.encryptionsdk.kmssdkv2.KmsMasterKey;
import com.amazonaws.encryptionsdk.kmssdkv2.KmsMasterKeyProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.SdkBytes;

import java.nio.charset.StandardCharsets;
@Component
public class EncryptedSDK {
    private static String keyArn;

    @Value("${aws.encryptedSDK.keyArn}")
    public void setKeyArn(String value){
        keyArn=value;
    }
    public static String decryptWithEncryptSDK(SdkBytes cipherfile) {

        final AwsCrypto crypto = AwsCrypto.builder()
                .withCommitmentPolicy(CommitmentPolicy.RequireEncryptRequireDecrypt)
                .build();
        final KmsMasterKeyProvider keyProvider = KmsMasterKeyProvider.builder().buildStrict(keyArn);
        final CryptoResult<byte[], KmsMasterKey> decryptResult = crypto.decryptData(keyProvider, cipherfile.asByteArray());
        return new String(decryptResult.getResult(), StandardCharsets.UTF_8).trim();
    }
}

