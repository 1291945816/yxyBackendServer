package ink.kilig.yxy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: Hps
 * @date: 2020/11/10 14:11
 * @description:
 */
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioParamConfig {
    private String endpoint ;
    private String accessKey ;
    private String secretKey ;
    private String bucketNameAvatar;
    private String bucketNameYxyImages;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucketNameAvatar() {
        return bucketNameAvatar;
    }

    public void setBucketNameAvatar(String bucketNameAvatar) {
        this.bucketNameAvatar = bucketNameAvatar;
    }

    public String getBucketNameYxyImages() {
        return bucketNameYxyImages;
    }

    public void setBucketNameYxyImages(String bucketNameYxyImages) {
        this.bucketNameYxyImages = bucketNameYxyImages;
    }
}
