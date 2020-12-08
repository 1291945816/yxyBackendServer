package ink.kilig.yxy.controller;

import ink.kilig.yxy.domain.*;
import ink.kilig.yxy.service.YxyPictureService;
import ink.kilig.yxy.utils.JwtTokenUtils;
import ink.kilig.yxy.vo.PictureVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author: Hps
 * @date: 2020/10/26 20:34
 * @description:
 */
@RestController
@RequestMapping("/picture")
public class PictureController {
    private YxyPictureService yxyPictureService;
    private JwtTokenUtils jwtTokenUtils;
    Logger logger=LoggerFactory.getLogger(PictureController.class);


    @Autowired
    public PictureController(YxyPictureService yxyPictureService, JwtTokenUtils jwtTokenUtils) {
        this.yxyPictureService = yxyPictureService;
        this.jwtTokenUtils = jwtTokenUtils;

    }

    /**
     *上传用户的图片的信息
     * @param uploadPictureInfo
     * @return
     */
    @PostMapping("/uploadPicture")
    public Result<String> upload( UploadPictureInfo uploadPictureInfo){
        logger.info(uploadPictureInfo.toString());
        return yxyPictureService.uploadPicture(uploadPictureInfo);
    }


    /**
     * 返回图片详情集合
     */
    @GetMapping("/pictures")
    public Result<List<PictureVO>> getPictures(@RequestParam Map<String,String> map, HttpServletRequest request){
        String token=request.getHeader("token");
        String username = jwtTokenUtils.getUsernameFromToken(token);
        String pageNum = map.get("pageNum");
        String size = map.get("size");
        if(pageNum == null || pageNum.equals("") || !pageNum.matches("^[0-9]*$") || size == null
        ||size.equals("") || !size.matches("^[0-9]*$")) return Result.falure("抱歉，你的输入格式不对，请重试!");
        return yxyPictureService.getPublishPicture(username,Long.valueOf(pageNum),Long.valueOf(size));
    }

    /**
     * 获取评论
     */
    @GetMapping("/comment")
    public Result<List<CommentInfo>> getCommentInfo(@RequestParam("pictureId") long pictureId){
        return yxyPictureService.getComments(String.valueOf(pictureId));

    }

    /**
     * 添加评论
     */
    @PostMapping("/comment")
    public Result<String> comment(@RequestBody Map<String,String> map,HttpServletRequest request){
        String token=request.getHeader("token");
        String username = jwtTokenUtils.getUsernameFromToken(token);
        String comment = map.get("comment"); //获取评论内容
        String pictureId = map.get("pictureId");
        if(comment != null && !comment.equals("") && pictureId != null && !pictureId.equals("")){
            return  yxyPictureService.commentPicture(pictureId,comment,username);
        }
        return Result.falure("评论失败，您未作有效的评论!");
    }

    /*
     *点赞
     */
    @PostMapping("/star")
    public Result<String> star(@RequestBody Map<String,String> map,HttpServletRequest request){
        return yxyPictureService.star(map,request);
    }
    /**
     * 下载 用于download ++
     */
    @PostMapping("/download_count")
    public Result<String> download_count(@RequestBody Map<String,String> map){
        //获取图片id
        String pictureId = map.get("pictureId");
        if (pictureId != null && !pictureId.equals("")){
            return yxyPictureService.downloadCount(pictureId);
        }
        return Result.falure("输入的图片id不存在或表示错误，请重新输入");
    }










}
