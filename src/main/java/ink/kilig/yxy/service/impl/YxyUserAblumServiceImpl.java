package ink.kilig.yxy.service.impl;

import ink.kilig.yxy.domain.Ablum;
import ink.kilig.yxy.domain.Result;
import ink.kilig.yxy.mapper.YxyUserAblumMapper;
import ink.kilig.yxy.service.YxyUserAblumService;
import ink.kilig.yxy.utils.JwtTokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author: Hps
 * @date: 2020/10/24 4:05
 * @description:
 */
@Service
public class YxyUserAblumServiceImpl implements YxyUserAblumService {
    private Logger logger=LoggerFactory.getLogger(YxyUserServiceImpl.class);
    private YxyUserAblumMapper yxyUserAblumMapper;
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    public YxyUserAblumServiceImpl(YxyUserAblumMapper yxyUserAblumMapper,JwtTokenUtils jwtTokenUtils) {
        this.yxyUserAblumMapper = yxyUserAblumMapper;
        this.jwtTokenUtils=jwtTokenUtils;
    }

    @Override
    public Result<String> insertAblum(Map<String,String> map, HttpServletRequest request) {
        String token = request.getHeader("token");
        String username = jwtTokenUtils.getUsernameFromToken(token);
        //获取相册名称
        String ablumName = map.get("ablumName");
        if (ablumName != null && !ablumName.equals("")){
            String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            yxyUserAblumMapper.insertAblum(ablumName,createTime,username);
            return Result.success(null,"创建相册成功!");
        }
        return Result.falure("由于你的输入存在问题，创建相册失败");
    }

    @Override
    public Result<String> deleteAblum(Map<String, String> map, HttpServletRequest request) {




        return null;
    }
}