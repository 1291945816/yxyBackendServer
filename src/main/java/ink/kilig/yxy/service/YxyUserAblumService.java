package ink.kilig.yxy.service;

import ink.kilig.yxy.domain.Ablum;
import ink.kilig.yxy.domain.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author: Hps
 * @date: 2020/10/24 4:05
 * @description:
 */
public interface YxyUserAblumService {
    Result<String> insertAblum(Map<String,String> map, HttpServletRequest request);
    Result<String> deleteAblum(Map<String,String> map);
    Result<String> changeAblumName(Map<String,String> map);
    Result<List<Ablum>> getAblumInfo(HttpServletRequest request);
}
