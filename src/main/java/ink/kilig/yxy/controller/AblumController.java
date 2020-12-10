package ink.kilig.yxy.controller;

import ink.kilig.yxy.domain.*;
import ink.kilig.yxy.service.YxyUserAblumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author: Hps
 * @date: 2020/10/24 3:55
 * @description:
 */
@RestController
@RequestMapping("/ablum")
public class AblumController {
    private YxyUserAblumService yxyUserAblumService;

    @Autowired
    public AblumController(YxyUserAblumService yxyUserAblumService) {
        this.yxyUserAblumService = yxyUserAblumService;
    }

    /**
     * 增加相册
     * @param ablumInfo
     * @param request
     * @return
     */
    @PostMapping("/add")
    public Result<String> add(@RequestBody Map<String,String> ablumInfo, HttpServletRequest request){
       return yxyUserAblumService.insertAblum(ablumInfo,request);
    }

    /**
     * 删除相册（连同相册里面的图片
     * @param ablumInfo 相册相关信息的键值对集合
     * @return
     */
    @PostMapping("/delete")
    public Result<String> delete(@RequestBody Map<String,String> ablumInfo){
        return yxyUserAblumService.deleteAblum(ablumInfo);
    }

    /**
     * 修改相册名称
     * @return
     */
    @PostMapping("/change")
    public Result<String> change(@RequestBody Map<String,String> ablumInfo){
        return yxyUserAblumService.changeAblumName(ablumInfo);
    }


    @GetMapping("/info")
    public Result<List<Ablum>> info(HttpServletRequest request){
        return yxyUserAblumService.getAblumInfo(request);
    }
    @GetMapping("pictures/{ablumId}")
    public Result<List<PrivatePicture>> pictures(@PathVariable String ablumId){
            return yxyUserAblumService.getPictures(ablumId);
    }


    /**
     * 获取图片点赞详情
     */
    @GetMapping("/starInfo")
    public Result<List<YxyUser>> getStaredInfo(@RequestParam String pictureId ){
        return yxyUserAblumService.getStaredInfo(pictureId);
    }

    /**
     * 更新图片的发布状态
     * @param requestData
     * @return
     */
    @PostMapping("/changePublishStatus")
    public Result<String> updatePublishStatus(@RequestBody Map<String,String> requestData){
        String pictureId = requestData.get("pictureId");
        boolean publish=Boolean.parseBoolean(requestData.get("publish"));
        if (pictureId== null || pictureId.isEmpty()){
            return Result.falure("获取失败，你输入的值不对");
        }else {
            return yxyUserAblumService.changePublishStatus(publish,pictureId);
        }
    }

    @PostMapping("/deletePicture/{pictureId}")
    public Result<String> deletePicture(@PathVariable String pictureId){
        if ( pictureId == null||pictureId.isEmpty() )
        {
            return Result.falure("输入的相片ID不正确");
        }else
            return yxyUserAblumService.deletePicture(pictureId);
    }

}
