package ink.kilig.yxy.service.impl;

import ink.kilig.yxy.domain.UploadPictureInfo;
import ink.kilig.yxy.domain.Result;
import ink.kilig.yxy.mapper.YxyPictureMapper;
import ink.kilig.yxy.po.PictureInfoPO;
import ink.kilig.yxy.service.YxyPictureService;
import ink.kilig.yxy.utils.JwtTokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

/**
 * @author: Hps
 * @date: 2020/10/26 20:35
 * @description:
 */
@Service
public class YxyPictureServiceImpl implements YxyPictureService {
    private YxyPictureMapper yxyPictureMapper;
    private JwtTokenUtils jwtTokenUtils;
    private String fileRootPath;
    Logger logger= LoggerFactory.getLogger(YxyPictureServiceImpl.class);

    @Autowired
    public YxyPictureServiceImpl(YxyPictureMapper yxyPictureMapper, JwtTokenUtils jwtTokenUtils, @Value("${file.sharePicture}") String fileRootPath) {
        this.yxyPictureMapper = yxyPictureMapper;
        this.jwtTokenUtils = jwtTokenUtils;
        this.fileRootPath = fileRootPath;
    }


    @Override
    public Result<String> uploadPicture(UploadPictureInfo uploadPictureInfo) {
        PictureInfoPO infoPO = new PictureInfoPO();
        MultipartFile file = uploadPictureInfo.getFile();
        infoPO.setAblumId(Long.parseLong(uploadPictureInfo.getAblumId())); //设置所位于的相册
        StringBuffer path=new StringBuffer(fileRootPath);

        if(file != null){
            final String filePath = path.append(infoPO.getAblumId()).append("-").append(file.getOriginalFilename()).toString();
            File file1 = new File(filePath);
            if (!file1.exists())
                file1.mkdirs();
            //保存文件
            try {
                file.transferTo(file1);
            } catch (IOException e) {
                logger.info(e.toString());
                return Result.falure("上传图片失败!");
            }
            infoPO.setPicturePath(filePath); //设置路径
            infoPO.setPictureCreateTime(String.valueOf(Calendar.getInstance().getTimeInMillis())); //设置创建时间
            infoPO.setPictureName(uploadPictureInfo.getPittureName()); //设置照片名字
            infoPO.setPictureInfo(uploadPictureInfo.getPictureInfo()); //设置照片的描述信息
            infoPO.setPublishVisiable(uploadPictureInfo.isPublishVisiable()); //设置是否公布到广场
            boolean isUpload = yxyPictureMapper.upload(infoPO);
            if (isUpload)
                return Result.success("上传图片成功");
            else
                return Result.falure("由于未知的原因，上传图片失败");
        }
        return Result.falure("上传失败，可能你没有上传图片，请重试!");


    }

    @Override
    public byte[] getPictureByid(Long pictureId) {
        FileInputStream stream =null;
        byte[] bytes =null;
        if (pictureId!= null){
            String picturePath = yxyPictureMapper.getPicturePath(pictureId);
            if (picturePath == null) return null;
            try {
                stream=new FileInputStream(new File(picturePath));
                try {
                    bytes = new byte[stream.available()];
                    stream.read(bytes,0,stream.available());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }
}
