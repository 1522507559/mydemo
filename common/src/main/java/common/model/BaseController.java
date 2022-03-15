package common.model;


import common.enums.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;

@Controller
public class BaseController {

    private static String SUCCESS = ResultCodeEnum.CODE_0.value();
    private static String ERROR = ResultCodeEnum.CODE_1001.value();
    private static String COMPLETE = ResultCodeEnum.CODE_2001.value();

    /*@Autowired
    protected RedisTemplate<String, Object> redisTemplate;*/

    /**
     * 请求成功
     *
     * @return result
     */
    protected JsonResult renderSuccess() {
        JsonResult result = new JsonResult();
        result.setCode(SUCCESS);
        return result;
    }
    protected JsonResult renderSuccess(String message) {
        JsonResult result = renderSuccess();
        result.setMsg(message);
        return result;
    }
    protected JsonResult renderSuccess(Object object) {
        JsonResult result = renderSuccess();
        result.setData(object);
        return result;
    }
    protected JsonResult renderSuccess(HttpServletResponse response, HttpStatus status) {
        JsonResult result = renderSuccess();
        response.setStatus(status.value());
        result.setMsg(status.getReasonPhrase());
        return result;
    }

    protected JsonResult renderSuccess(Object data, String message){
        JsonResult result = renderSuccess();
        result.setCode(SUCCESS);
        result.setMsg(message);
        result.setData(data);
        return result;
    }

    /**
     * 请求失败
     *
     * @return result
     */
    protected JsonResult renderError() {
        JsonResult result = new JsonResult();
        result.setCode(ERROR);
        return result;
    }
    protected JsonResult renderError(String message) {
        JsonResult result = renderError();
        result.setMsg(message);
        return result;
    }
    protected JsonResult renderError(Object object) {
        JsonResult result = renderError();
        result.setData(object);
        return result;
    }
    protected JsonResult renderError(HttpServletResponse response, HttpStatus status) {
        JsonResult result = renderError();
        response.setStatus(status.value());
        result.setMsg(status.getReasonPhrase());
        return result;
    }

    /**
     * 请求完成：用于异步的数据处理
     *
     * @return result
     */
    protected JsonResult renderComplete() {
        JsonResult result = new JsonResult();
        result.setCode(COMPLETE);
        return result;
    }
    protected JsonResult renderComplete(String message) {
        JsonResult result = renderComplete();
        result.setMsg(message);
        return result;
    }
    protected JsonResult renderComplete(Object object) {
        JsonResult result = renderComplete();
        result.setData(object);
        return result;
    }
    protected JsonResult renderComplete(HttpServletResponse response, HttpStatus status) {
        JsonResult result = renderComplete();
        response.setStatus(status.value());
        result.setMsg(status.getReasonPhrase());
        return result;
    }

}
