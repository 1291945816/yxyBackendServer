package ink.kilig.yxy.controller;

import ink.kilig.yxy.domain.PictureVO;
import ink.kilig.yxy.domain.UploadPictureInfo;
import ink.kilig.yxy.domain.Result;
import ink.kilig.yxy.service.YxyPictureService;
import ink.kilig.yxy.utils.JwtTokenUtils;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
     *返回图片
     * @param pictureId 图片id
     * @return
     */
    @GetMapping(value = "",produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE})
    public byte[] getPicture(@RequestParam Long pictureId){
        return yxyPictureService.getPictureByid(pictureId);
    }

    /**
     * 返回图片详情集合
     */
    @GetMapping("/pictures")
    public Result<List<PictureVO>> getPictures(@RequestBody Map<String,String> map,HttpServletRequest request){
//        String token=request.getHeader("token");
//        String username = jwtTokenUtils.getUsernameFromToken(token);
        String pageNum = map.get("pageNum");
        String size = map.get("size");
        if(pageNum == null || pageNum.equals("") || !pageNum.matches("^[0-9]*$") || size == null
        ||size.equals("") || !size.matches("^[0-9]*$")) return Result.falure("抱歉，你的输入格式不对，请重试!");
        return yxyPictureService.getPublishPicture("1800300916",Long.valueOf(pageNum),Long.valueOf(size));
    }

}
