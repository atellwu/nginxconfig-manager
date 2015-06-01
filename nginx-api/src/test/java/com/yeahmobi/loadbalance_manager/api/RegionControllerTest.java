package com.yeahmobi.loadbalance_manager.api;

import java.text.ParseException;
import java.util.List;

import junit.framework.Assert;

import org.adclear.dbunit.json.annotations.JsonData;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.reflectionassert.ReflectionComparatorMode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.yeahmobi.loadbalance_manager.api.AbstractController.JsonResult;
import com.yeahmobi.loadbalance_manager.common.Constants;
import com.yeahmobi.loadbalance_manager.model.Region;

@SuppressWarnings("unchecked")
public class RegionControllerTest extends ControllerBaseTest {

    @Autowired
    private RegionController regionController;

    @Test
    @JsonData(fileName = "initRegion.json")
    public void testList() throws Exception {
        String json = this.regionController.list();
        JsonResult<List<Region>> actualResult = JSON.parseObject(json, new TypeReference<JsonResult<List<Region>>>() {
        });

        JsonResult expectedResult = createSuccessJsonResult();
        Region regionA = createRegion("testRegionA");
        Region regionB = createRegion("testRegionB");
        expectedResult.setResult(Lists.newArrayList(regionA, regionB));

        ReflectionAssert.assertReflectionEquals(expectedResult, actualResult, ReflectionComparatorMode.LENIENT_DATES);
    }

    @Test
    @JsonData(fileName = "initRegion.json")
    public void testDel() throws Exception {
        Region region = createRegion("testRegionC");
        region.setVersion(null);
        this.regionController.save(region);

        {
            String[] name = { "testRegionC" };
            String json = this.regionController.del(name);
            JsonResult<String> actualResult = JSON.parseObject(json, new TypeReference<JsonResult<String>>() {
            });

            JsonResult expectedResult = createSuccessJsonResult();
            expectedResult.setResult("");

            assertJsonResult(expectedResult, actualResult);
        }

        String json = this.regionController.list();
        JsonResult<List<Region>> actualResult = JSON.parseObject(json, new TypeReference<JsonResult<List<Region>>>() {
        });

        JsonResult expectedResult = createSuccessJsonResult();
        Region regionA = createRegion("testRegionA");
        Region regionB = createRegion("testRegionB");
        expectedResult.setResult(Lists.newArrayList(regionA, regionB));

        ReflectionAssert.assertReflectionEquals(expectedResult, actualResult, ReflectionComparatorMode.LENIENT_DATES);
    }

    @Test
    @JsonData(fileName = "initRegion.json")
    public void testSetAsInsert() throws Exception {
        Region region = createRegion("testRegionC");
        region.setVersion(null);
        this.regionController.save(region);

        String json = this.regionController.list();
        JsonResult<List<Region>> actualResult = JSON.parseObject(json, new TypeReference<JsonResult<List<Region>>>() {
        });

        JsonResult expectedResult = createSuccessJsonResult();
        expectedResult.setResult(Lists.newArrayList(createRegion("testRegionA"), createRegion("testRegionB"),
                                                    createRegion("testRegionC")));

        ReflectionAssert.assertReflectionEquals(expectedResult, actualResult, ReflectionComparatorMode.LENIENT_DATES);
    }

    @Test
    @JsonData(fileName = "initRegion.json")
    public void testSetAsUpdate() throws Exception {
        Region region = createRegion("testRegionA");
        region.setDesc("desc");
        this.regionController.save(region);

        String json = this.regionController.list();
        JsonResult<List<Region>> actualResult = JSON.parseObject(json, new TypeReference<JsonResult<List<Region>>>() {
        });

        System.out.println(json);

        JsonResult expectedResult = createSuccessJsonResult();
        expectedResult.setResult(Lists.newArrayList(region, createRegion("testRegionB")));

        ReflectionAssert.assertReflectionEquals(expectedResult, actualResult, ReflectionComparatorMode.LENIENT_DATES);
    }

    private void assertJsonResult(JsonResult expectedResult, JsonResult actualResult) {
        Assert.assertEquals(expectedResult.getErrorCode(), actualResult.getErrorCode());
        Assert.assertEquals(expectedResult.getMsg(), actualResult.getMsg());

        ReflectionAssert.assertReflectionEquals(expectedResult.getResult(), actualResult.getResult(),
                                                ReflectionComparatorMode.LENIENT_DATES);
    }

    protected JsonResult createSuccessJsonResult() {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setErrorCode(JsonResult.CODE_SUCCESS);
        jsonResult.setMsg("success");
        return jsonResult;
    }

    protected Region createRegion(String name) throws ParseException {
        Region region = new Region();
        region.setName(name);
        region.setVersion(1L);
        region.setDesc("");
        region.setGmtCreate(DateUtils.parseDate("2014-12-15 08:59:09", Constants.DATE_PATTERNS));
        region.setGmtModified(DateUtils.parseDate("2014-12-16 08:59:09", Constants.DATE_PATTERNS));
        return region;
    }

}
