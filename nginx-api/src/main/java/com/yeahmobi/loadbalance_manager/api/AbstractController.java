package com.yeahmobi.loadbalance_manager.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author atell.wu
 */
@SuppressWarnings("unchecked")
public class AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractController.class);

    protected String json(Handler handler) {
        JsonResult result = new JsonResult();

        try {
            handler.handle(result);

            result.setErrorCode(JsonResult.CODE_SUCCESS);
            result.setMsg("success");
            if (result.getResult() == null) {
                result.setResult("");
            }
        } catch (IllegalArgumentException e) {
            throw e;// 交给Validate框架去处理
        } catch (Exception e) {
            result.setErrorCode(JsonResult.CODE_SERVER_ERROR);
            result.setMsg(e.getMessage());
            result.setResult("");
            LOGGER.error(e.getMessage(), e);
        }
        return JSON.toJSONString(result, SerializerFeature.WriteNullListAsEmpty,
                                 SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    public static class JsonResult<T> {

        public static final String ERROR_CODE        = "errorCode";
        public static final int    CODE_SUCCESS      = 0;
        public static final int    CODE_PARAM_ERROR  = 1;
        public static final int    CODE_SERVER_ERROR = 2;

        public static final String MSG               = "msg";

        private int                errorCode;
        private String             msg;
        private T                  result;

        public int getErrorCode() {
            return this.errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public String getMsg() {
            return this.msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public T getResult() {
            return this.result;
        }

        public void setResult(T result) {
            this.result = result;
        }

    }

}
