package com.yeahmobi.loadbalance_manager.api;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.adclear.dbunit.json.annotations.JsonData;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.reflectionassert.ReflectionComparatorMode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.ImmutableMap;
import com.yeahmobi.loadbalance_manager.api.AbstractController.JsonResult;
import com.yeahmobi.loadbalance_manager.common.Constants;
import com.yeahmobi.loadbalance_manager.helper.MapWrapper;
import com.yeahmobi.loadbalance_manager.model.Global;

@SuppressWarnings("unchecked")
public class GlobalControllerTest extends ControllerBaseTest {

    @Autowired
    private GlobalController globalController;

    @Test
    @JsonData(fileName = "initGlobal.json")
    public void testList() throws Exception {
        // String json = this.globalController.list();
        // JsonResult actualResult = JSON.parseObject(json, JsonResult.class);
        //
        // JsonResult expectedResult = createSuccessJsonResult();
        // Global global = createGlobal();
        // expectedResult.setResult(global);
        //
        // assertResult(expectedResult, actualResult);

        String json = this.globalController.list();
        JsonResult<Global> actualResult = JSON.parseObject(json, new TypeReference<JsonResult<Global>>() {
        });

        JsonResult expectedResult = createSuccessJsonResult();
        Global global = createGlobal();
        expectedResult.setResult(global);

        assertJsonResult(expectedResult, actualResult);
    }

    @Test
    @JsonData(fileName = "initGlobal.json")
    public void testGet() throws Exception {
        String[] keys = { "user", "tcpNodelay" };
        String json = this.globalController.get(keys);
        JsonResult<Map<String, Object>> actualResult = JSON.parseObject(json,
                                                                        new TypeReference<JsonResult<Map<String, Object>>>() {
                                                                        });

        Map keyValues = ImmutableMap.of("user", "ops", "tcpNodelay", Boolean.FALSE);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("version", 1);
        map.put("gmtModified", "2014-12-16 08:59:09");
        map.put("keyValues", new JSONObject(keyValues));
        JsonResult expectedResult = createSuccessJsonResult();
        expectedResult.setResult(map);

        assertJsonResult(expectedResult, actualResult);

    }

    @Test
    @JsonData(fileName = "initGlobal.json")
    public void testSet() throws Exception {
        // save 'user'
        {
            Map<String, String> map = new HashMap<String, String>();
            map.put("user", "atell");
            MapWrapper requestMap = new MapWrapper(map);
            this.globalController.set(requestMap);
        }
        // test 'user'
        {
            String[] keys = { "user" };
            String json = this.globalController.get(keys);
            JsonResult<Map<String, Object>> actualResult = JSON.parseObject(json,
                                                                            new TypeReference<JsonResult<Map<String, Object>>>() {
                                                                            });

            Map keyValues = ImmutableMap.of("user", "atell");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("version", 2);
            map.put("gmtModified", actualResult.getResult().get("gmtModified"));// ignore times
            map.put("keyValues", new JSONObject(keyValues));
            JsonResult expectedResult = createSuccessJsonResult();
            expectedResult.setResult(map);

            assertJsonResult(expectedResult, actualResult);
        }
    }

    @Test
    @JsonData(fileName = "initGlobal.json")
    public void testInfo() throws Exception {
        // save & configGen will auto insert
        {
            Map<String, String> map = new HashMap<String, String>();
            map.put("user", "atell");
            MapWrapper requestMap = new MapWrapper(map);
            this.globalController.set(requestMap);
        }

        String json = this.globalController.info();
        JsonResult<List<Map<String, Object>>> actualResult = JSON.parseObject(json,
                                                                              new TypeReference<JsonResult<List<Map<String, Object>>>>() {
                                                                              });

        Assert.assertEquals(JsonResult.CODE_SUCCESS, actualResult.getErrorCode());
        List<Map<String, Object>> list = actualResult.getResult();

        String[] modules = GlobalController.modules;
        assertInfo(modules, list);
    }

    @Test
    @JsonData(fileName = "initGlobal.json")
    public void testDownload() throws Exception {
        // save 'user'
        {
            Map<String, String> map = new HashMap<String, String>();
            map.put("user", "atell");
            MapWrapper requestMap = new MapWrapper(map);
            this.globalController.set(requestMap);
        }

        HttpEntity<String> entity = (HttpEntity<String>) this.globalController.download(1L);
        Assert.assertEquals(StringUtils.trim(IOUtils.toString(GlobalControllerTest.class.getResourceAsStream("/download/global.conf"))),
                            StringUtils.trim(entity.getBody()));
    }

    private void assertJsonResult(JsonResult expectedResult, JsonResult actualResult) {
        Assert.assertEquals(expectedResult.getErrorCode(), actualResult.getErrorCode());
        Assert.assertEquals(expectedResult.getMsg(), actualResult.getMsg());

        ReflectionAssert.assertReflectionEquals(expectedResult.getResult(), actualResult.getResult(),
                                                ReflectionComparatorMode.LENIENT_DATES);
    }

    private void assertInfo(String[] modules, List<Map<String, Object>> list) throws ParseException {
        Assert.assertEquals(modules.length, list.size());
        for (int i = 0; i < modules.length; i++) {
            String module = modules[i];
            Map<String, Object> map = list.get(i);
            // module
            Assert.assertEquals(module, map.get("module"));
            // gmtModified
            TestUtils.generallyEquals(new Date(),
                                      DateUtils.parseDate((String) map.get("gmtModified"), Constants.DATE_PATTERNS));
            // url
            Assert.assertEquals("/global/download?id=" + map.get("version"), map.get("url"));
        }
    }

    protected JsonResult createSuccessJsonResult() {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setErrorCode(JsonResult.CODE_SUCCESS);
        jsonResult.setMsg("success");
        return jsonResult;
    }

    protected Global createGlobal() throws ParseException {
        Global global = new Global();
        global.setSendfile(true);
        global.setServerNamesHashBucketSize(10);
        global.setSslSessionCache("shared:SSL:90m");
        global.setSslSessionTimeout("100m");
        global.setTcpNodelay(false);
        global.setTcpNopush(true);
        global.setUlimit(90);
        global.setUseEpoll(false);
        global.setUser("ops");
        global.setVersion(1L);
        global.setWorkerConnections(90);
        global.setWorkerProcesses(90);
        global.setWorkerRlimitNofile(90);
        global.setGmtCreate(DateUtils.parseDate("2014-12-15 08:59:09", Constants.DATE_PATTERNS));
        global.setGmtModified(DateUtils.parseDate("2014-12-16 08:59:09", Constants.DATE_PATTERNS));
        global.setCustomHttp("");
        global.setCustomMimetypes("");
        global.setCustomProxy("");
        global.setCustomServer("");
        return global;
    }

}
