package ink.kilig.yxy.controller;

import ink.kilig.yxy.domain.UploadPictureInfo;
import ink.kilig.yxy.domain.Result;
import ink.kilig.yxy.service.YxyPictureService;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    Logger logger=LoggerFactory.getLogger(PictureController.class);



    @Autowired
    public PictureController(YxyPictureService yxyPictureService) {
        this.yxyPictureService = yxyPictureService;
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

}
