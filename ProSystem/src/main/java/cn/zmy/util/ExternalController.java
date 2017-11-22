package cn.zmy.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import com.alibaba.fastjson.JSONObject;

@RestController
public class ExternalController {

    private static Logger logger = LoggerFactory.getLogger(ExternalController.class);

    /**
     * 后台管理接口入口。
     *
     * @param request  request对象
     * @param response response对象
     * @return 调用结果
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/**")
	@CrossOrigin(allowCredentials="false")
    public String executeExternal(@RequestBody(required = false) String params, HttpServletRequest request, HttpServletResponse response) throws Exception {
        long timeStart = DateUtil.getUTCMillis();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        String uuid = StringUtil.getUUIDString();
        // 参数
        logger.info("request({}):path={}, params={}", uuid, path, params);

        ResponseDto rspDto = new ResponseDto();
        try {
            JSONObject paramsObj = CheckUtil.parseObject(params);
            // 请求的token带上appKey时，把appKey去掉
            if (paramsObj != null) {
                String token = paramsObj.getString("token");
                if (!StringUtils.isEmpty(StringUtils.trim(token))) {
                	String[] strArr = token.split("_");
                	paramsObj.put("token", strArr[0]);
                }
            }
            IBaseService baseService = (IBaseService) PathFactoryUtil.getBean(path);
            // 路径不正确
            if (baseService == null) {
                return "";
            }
            rspDto = baseService.process(paramsObj, request, response);
        } catch (Throwable e) {
            logger.error("executeExternal error:", e);
        } finally {
            long timeEnd = DateUtil.getUTCMillis();
            logger.info("totalExeTime:{} response({}):{}", timeEnd - timeStart, uuid, rspDto.toString());
        }
        return rspDto.toString();
    }
}
