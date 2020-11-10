package ink.kilig.yxy.utils;



import ink.kilig.yxy.config.MinioParamConfig;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author: Hps
 * @date: 2020/11/10 14:15
 * @description:
 */
@Component
public class MinIOUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(MinIOUtils.class) ;

    @Resource
    private MinioParamConfig minioParamConfig;

    private MinioClient minioClient;

    @PostConstruct
    private void init(){
        minioClient = MinioClient.builder().endpoint(minioParamConfig.getEndpoint())
                .credentials(minioParamConfig.getAccessKey(), minioParamConfig.getSecretKey()).build();
    }
    /**
     *
     * @param file 头像文件
     * @param fileName 修饰过后的文件名
     * @return 可访问的头像链接
     */
    public String uploadAvatar(MultipartFile file,String fileName){
        BucketExistsArgs build = BucketExistsArgs.builder().bucket(minioParamConfig.getBucketNameAvatar()).build();
        try {
            boolean exists = minioClient.bucketExists(build);
            if (!exists){
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioParamConfig.getBucketNameAvatar()).build());
            }
            PutObjectArgs args = PutObjectArgs.builder().contentType(file.getContentType())
                        .stream(file.getInputStream(), file.getSize(), PutObjectArgs.MIN_MULTIPART_SIZE)
                        .bucket(minioParamConfig.getBucketNameAvatar()).object(fileName).build();
            minioClient.putObject(args);
        }catch (Exception e){
            e.printStackTrace();
        }
        return minioParamConfig.getEndpoint()+"/"+minioParamConfig.getBucketNameAvatar()+"/"+ UriUtils.encode(fileName, StandardCharsets.UTF_8);
    }

    /**
     * 上传照片
     * @param file 照片文件
     * @param fileName 文件名称
     * @return 存储的文件路径
     */
    public String uploadPicture(MultipartFile file,String fileName,String floder){
        BucketExistsArgs build = BucketExistsArgs.builder().bucket(minioParamConfig.getBucketNameYxyImages()).build();
        try {
            boolean exists = minioClient.bucketExists(build);
            if (!exists){
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioParamConfig.getBucketNameYxyImages()).build());
            }
            PutObjectArgs args = PutObjectArgs.builder().contentType(file.getContentType())
                        .stream(file.getInputStream(), file.getSize(), PutObjectArgs.MIN_MULTIPART_SIZE)
                        .bucket(minioParamConfig.getBucketNameYxyImages()).object(floder+"/"+fileName).build();
                minioClient.putObject(args);
                return minioParamConfig.getEndpoint()+"/"+minioParamConfig.getBucketNameYxyImages()+"/"+floder+"/"+UriUtils.encode(fileName,StandardCharsets.UTF_8);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }


}
