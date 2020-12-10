package ink.kilig.yxy.service.impl;

import ink.kilig.yxy.domain.*;
import ink.kilig.yxy.mapper.YxyPictureMapper;
import ink.kilig.yxy.po.CommentPO;
import ink.kilig.yxy.po.PictureInfoPO;
import ink.kilig.yxy.service.YxyPictureService;
import ink.kilig.yxy.utils.JwtTokenUtils;
import ink.kilig.yxy.utils.MinIOUtils;
import ink.kilig.yxy.vo.PictureVO;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: Hps
 * @date: 2020/10/26 20:35
 * @description:
 */
@Service
public class YxyPictureServiceImpl implements YxyPictureService {
    private final String PATH="picture?pictureId=";
    private final String tPATH="/thumbnail?pictureId=";
    private YxyPictureMapper yxyPictureMapper;
    private JwtTokenUtils jwtTokenUtils;
    private String fileRootPath;
    Logger logger= LoggerFactory.getLogger(YxyPictureServiceImpl.class);
    private MinIOUtils minIOUtils;

    @Autowired
    public YxyPictureServiceImpl(YxyPictureMapper yxyPictureMapper,
                                 MinIOUtils minIOUtils,
                                 JwtTokenUtils jwtTokenUtils,
                                 @Value("${file.sharePicture}") String fileRootPath) {
        this.minIOUtils=minIOUtils;
        this.yxyPictureMapper = yxyPictureMapper;
        this.jwtTokenUtils = jwtTokenUtils;
        this.fileRootPath = fileRootPath;
    }


    @Override
    public Result<String> uploadPicture(UploadPictureInfo uploadPictureInfo) {
        PictureInfoPO infoPO = new PictureInfoPO();
        MultipartFile file = uploadPictureInfo.getFile();

        infoPO.setAblumId(Long.parseLong(uploadPictureInfo.getAblumId())); //设置所位于的相册
        if(file != null){

            String string = DigestUtils.md5Hex(uploadPictureInfo.getPictureName() + "&" + Calendar.getInstance().getTimeInMillis()).toString();
            String filePath = minIOUtils.uploadPicture(file,string, infoPO.getAblumId()+"");
            infoPO.setPicturePath(filePath); //设置路径

            InputStream stream = getThumbnail(file);
            String thumbnailUrl = minIOUtils.uploadPicture(file, string, "thumbnail", stream);
            //设置缩略图路径
            infoPO.setPictureCreateTime(String.valueOf(Calendar.getInstance().getTimeInMillis())); //设置创建时间
            infoPO.setPictureName(uploadPictureInfo.getPictureName()); //设置照片名字
            infoPO.setPictureInfo(uploadPictureInfo.getPictureInfo()); //设置照片的描述信息
            infoPO.setPublishVisiable(uploadPictureInfo.isPublishVisiable()); //设置是否公布到广场
            infoPO.setThumbnailPath(thumbnailUrl); //缩略图
            boolean isUpload = yxyPictureMapper.upload(infoPO);
            if (isUpload)
                return Result.success("上传图片成功");
            else
                return Result.falure("由于未知的原因，上传图片失败");
        }
        return Result.falure("上传失败，可能你没有上传图片，请重试!");


    }

    /**
     * 获取缩略图
     * @param file
     * @return
     */
    public InputStream getThumbnail(MultipartFile file){
        InputStream stream=null;
        logger.info(file.getSize()+" "+file.getOriginalFilename());
        File file1=new File(fileRootPath+file.getOriginalFilename());
        if (!file1.exists()){
            file1.getParentFile().mkdir();
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Thumbnails.of(file.getInputStream())
                    .width(800)
                    .outputQuality(0.8f)
                    .toFile(file1);
             stream = new FileInputStream(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  stream;
    }


    @Override
    public Result<List<PictureVO>> getPublishPicture(String username, Long pageNum, Long size) {
        List<PictureInfoPO> pictures = yxyPictureMapper.getPulishPicture(pageNum*size, size); //获取到照片集合
        List<String> staredPicture = yxyPictureMapper.getStaredPictureByusername(username); //用户的点赞集合
        logger.info(pictures.toString());
        logger.info(staredPicture.toString());
        List<PictureVO> result=new ArrayList<>();

        for (PictureInfoPO pictureInfoPO:pictures){
            PictureVO pictureVO=new PictureVO();
            pictureVO.setImgID(String.valueOf(pictureInfoPO.getPictureId())); //设置图片id
            if (staredPicture.size() != 0 && staredPicture.contains(String.valueOf(pictureInfoPO.getPictureId()))){

                pictureVO.setStared(true);
            }
            pictureVO.setAuthorAvatar(pictureInfoPO.getYxyUserAvatar());
            pictureVO.setAuthorId(pictureInfoPO.getYxyUserName());
            pictureVO.setAuthorName(pictureInfoPO.getYxyNickName());
            pictureVO.setDisplayImgName(pictureInfoPO.getPictureName());
            pictureVO.setDownloadNum(pictureInfoPO.getDownloadSum());
            pictureVO.setStarNum(pictureInfoPO.getStarNum());
            pictureVO.setDisplayImgUrl(pictureInfoPO.getPicturePath());
            pictureVO.setThumbnailUrl(pictureInfoPO.getThumbnailPath());
            result.add(pictureVO);
        }
        return Result.success(result,"获取成功");
    }

    @Override
    public Result<String> star(Map<String, String> map, HttpServletRequest request) {
        String token=request.getHeader("token");
        String username = jwtTokenUtils.getUsernameFromToken(token);
        String pictureId = map.get("pictureId");
        Result<String> result=new Result<String>();
        result.setCode("200");
        long starNum;
        if(pictureId == null || pictureId.equals("")) return Result.falure("抱歉，图片Id不可以为空");
        try {
            boolean isStar = yxyPictureMapper.isPictureExist(username, pictureId);
            yxyPictureMapper.updatStar(username,pictureId,!isStar); //点赞的相反操作
            starNum = yxyPictureMapper.getStarNum(pictureId);
            if (isStar) {
               starNum--;
               result.setMessage("取消点赞");
               yxyPictureMapper.updateStarNum(starNum,pictureId);
            }else
            {
                starNum++;
                result.setMessage("点赞成功");
                yxyPictureMapper.updateStarNum(starNum,pictureId);
            }


        }catch (BindingException bindException){
            //这里是抛空异常的时候
            yxyPictureMapper.insertStar(username,pictureId); //这里是第一次点赞
             starNum = yxyPictureMapper.getStarNum(pictureId);
            starNum ++; // 点赞数量
            result.setMessage("点赞成功");
            yxyPictureMapper.updateStarNum(starNum,pictureId); //记录点赞数量
        }
        return result;
    }

    @Override
    public Result<String> downloadCount(String pictureId) {
        PictureInfoPO pictureInfo = yxyPictureMapper.getPictureInfo(pictureId);
        if(pictureInfo != null){
            int downloadSum = pictureInfo.getDownloadSum();
            yxyPictureMapper.updateDownloadSum(++downloadSum,pictureId);
            return Result.success("下载成功+1");
        }
        return Result.falure("由于未知的原因，计数失败");
    }

    /**
     *
     * @param pictureId
     * @param comment
     * @param username
     * @return
     */
    @Override
    public Result<String> commentPicture(String pictureId, String comment, String username) {

        CommentPO commentPO=new CommentPO();
        commentPO.setComment(comment);
        commentPO.setPictureId(pictureId);
        commentPO.setYxyUserName(username);
        commentPO.setCommentTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())); //创建时间
        try {
             yxyPictureMapper.isPictureExist(username, pictureId);//判断一下 是否在那个数据库表上面存在
            //能进行下一步表示可以
            yxyPictureMapper.updateComment(commentPO); //更新评论信息
            return Result.success("评论成功");
        }catch (BindingException bindException){
            //这里是抛空异常的时候
            try {
                yxyPictureMapper.insertComment(commentPO);
            }catch (Exception e){
                return Result.falure("评论失败，图片不存在!");
            }
            return Result.success("评论成功");
        }
    }

    @Override
    public Result<List<CommentInfo>> getComments(String pictureId) {
        List<CommentInfo> infos = yxyPictureMapper.getCommentInfo(pictureId);
        return Result.success(infos,"获取成功");

    }

    @Override
    public Result<PictureInfoPO> getPictureDetail(String pictureId) {
        PictureInfoPO pictureDetail = yxyPictureMapper.getPictureDetail(pictureId);
        return Result.success(pictureDetail,"获取图片详情成功");
    }

}
