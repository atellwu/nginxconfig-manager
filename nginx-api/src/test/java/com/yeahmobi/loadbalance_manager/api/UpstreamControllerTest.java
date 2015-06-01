package com.yeahmobi.loadbalance_manager.api;

import java.text.ParseException;
import java.util.ArrayList;
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
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.yeahmobi.loadbalance_manager.api.AbstractController.JsonResult;
import com.yeahmobi.loadbalance_manager.common.Constants;
import com.yeahmobi.loadbalance_manager.model.Member;
import com.yeahmobi.loadbalance_manager.model.Upstream;

@SuppressWarnings("unchecked")
public class UpstreamControllerTest extends ControllerBaseTest {

    @Autowired
    private UpstreamController upstreamController;

    @Test
    @JsonData(fileName = "initUpstream.json")
    public void testList() throws Exception {
        String json = this.upstreamController.list();
        JsonResult<List<Upstream>> actualResult = JSON.parseObject(json,
                                                                   new TypeReference<JsonResult<List<Upstream>>>() {
                                                                   });

        JsonResult expectedResult = createSuccessJsonResult();
        Upstream upstreamA = createUpstream("testUpstreamA");
        Upstream upstreamB = createUpstream("testUpstreamB");
        expectedResult.setResult(Lists.newArrayList(upstreamA, upstreamB));

        ReflectionAssert.assertReflectionEquals(expectedResult, actualResult, ReflectionComparatorMode.LENIENT_DATES);
    }

    @Test
    @JsonData(fileName = "initUpstream.json")
    public void testGet() throws Exception {
        String[] keys = { "testUpstreamA" };
        String json = this.upstreamController.get(keys);
        JsonResult<List<Upstream>> actualResult = JSON.parseObject(json,
                                                                   new TypeReference<JsonResult<List<Upstream>>>() {
                                                                   });

        JsonResult expectedResult = createSuccessJsonResult();
        Upstream upstreamA = createUpstream("testUpstreamA");
        expectedResult.setResult(Lists.newArrayList(upstreamA));

        assertJsonResult(expectedResult, actualResult);
    }

    @Test
    @JsonData(fileName = "initUpstream.json")
    public void testSaveAsInsert() throws Exception {
        // new & insert
        Upstream upstream = createUpstream("testUpstreamC");
        upstream.setVersion(null);
        this.upstreamController.save(upstream);

        // get & test
        String[] keys = { upstream.getName() };
        String json = this.upstreamController.get(keys);
        JsonResult<List<Upstream>> actualResult = JSON.parseObject(json,
                                                                   new TypeReference<JsonResult<List<Upstream>>>() {
                                                                   });

        // expect
        JsonResult expectedResult = createSuccessJsonResult();
        upstream.setVersion(1L);// version will inc
        expectedResult.setResult(Lists.newArrayList(upstream));

        assertJsonResult(expectedResult, actualResult);
    }

    @Test
    @JsonData(fileName = "initUpstream.json")
    public void testSaveAsUpdate() throws Exception {
        // update
        Upstream upstream = createUpstream("testUpstreamA");
        upstream.setKeepalive(100);
        Map<String, List<Member>> regions = new HashMap<String, List<Member>>();
        List<Member> listA = createMembers();
        regions.put("testRegionA", listA);
        upstream.setRegions(regions);
        this.upstreamController.save(upstream);

        // get & test
        String[] keys = { upstream.getName() };
        String json = this.upstreamController.get(keys);
        JsonResult<List<Upstream>> actualResult = JSON.parseObject(json,
                                                                   new TypeReference<JsonResult<List<Upstream>>>() {
                                                                   });

        // expect
        JsonResult expectedResult = createSuccessJsonResult();
        upstream.setVersion(2L);// version will inc
        List<Upstream> list = Lists.newArrayList(upstream);
        expectedResult.setResult(list);

        assertJsonResult(expectedResult, actualResult);
    }

    @Test
    @JsonData(fileName = "initUpstream.json")
    public void testDel() throws Exception {
        // del
        String[] keys = { "testUpstreamA" };
        this.upstreamController.del(keys);

        // get
        String json = this.upstreamController.get(keys);
        JsonResult<String> actualResult = JSON.parseObject(json, new TypeReference<JsonResult<String>>() {
        });

        // expect
        JsonResult expectedResult = createFailJsonResult();

        Assert.assertEquals(expectedResult.getErrorCode(), actualResult.getErrorCode());
    }

    @Test
    @JsonData(fileName = "initUpstream.json")
    public void testInfoRegion() throws Exception {
        // save & configGen will auto insert
        {
            Upstream upstream = createUpstream("testUpstreamA");
            upstream.setKeepalive(100);
            Map<String, List<Member>> regions = new HashMap<String, List<Member>>();
            List<Member> listA = createMembers();
            regions.put("testRegionA", listA);
            upstream.setRegions(regions);
            this.upstreamController.save(upstream);
        }

        String region = "testRegionA";
        String json = this.upstreamController.info(region);
        JsonResult<Map<String, Object>> actualResult = JSON.parseObject(json,
                                                                        new TypeReference<JsonResult<Map<String, Object>>>() {
                                                                        });

        assertInfo(actualResult);

    }

    private void assertInfo(JsonResult<Map<String, Object>> actualResult) throws ParseException {
        Map<String, Object> map = actualResult.getResult();
        // gmtModified
        TestUtils.generallyEquals(new Date(),
                                  DateUtils.parseDate((String) map.get("gmtModified"), Constants.DATE_PATTERNS));
        // url
        Assert.assertEquals("/upstream/download?id=" + map.get("version"), map.get("url"));
    }

    @Test
    @JsonData(fileName = "initUpstream.json")
    public void testDownload() throws Exception {
        // save & configGen will auto insert
        {
            Upstream upstream = createUpstream("testUpstreamA");
            upstream.setKeepalive(100);
            Map<String, List<Member>> regions = new HashMap<String, List<Member>>();
            List<Member> listA = createMembers();
            regions.put("testRegionA", listA);
            upstream.setRegions(regions);
            this.upstreamController.save(upstream);
        }

        HttpEntity<String> entity = (HttpEntity<String>) this.upstreamController.download(1L);
        Assert.assertEquals(StringUtils.trim(IOUtils.toString(GlobalControllerTest.class.getResourceAsStream("/download/upstream.conf"))),
                            StringUtils.trim(entity.getBody()));
    }

    private void assertJsonResult(JsonResult expectedResult, JsonResult actualResult) {
        Assert.assertEquals(expectedResult.getErrorCode(), actualResult.getErrorCode());
        Assert.assertEquals(expectedResult.getMsg(), actualResult.getMsg());

        ReflectionAssert.assertReflectionEquals(expectedResult.getResult(), actualResult.getResult(),
                                                ReflectionComparatorMode.LENIENT_DATES);

        // if ((expectedResult.getResult() instanceof Upstream) && (actualResult.getResult() instanceof JSONObject)) {
        // // 转换
        // Upstream expected = (Upstream) expectedResult.getResult();
        // Object objectB = actualResult.getResult();
        // Upstream actual = JSON.parseObject(((JSONObject) objectB).toJSONString(), Upstream.class);
        // // 比较
        // assertAsEntity(expected, actual);
        //
        // } else if ((expectedResult.getResult() instanceof ConfigGen)
        // && (actualResult.getResult() instanceof JSONObject)) {
        // // 转换
        // ConfigGen expected = (ConfigGen) expectedResult.getResult();
        // Object objectB = actualResult.getResult();
        // ConfigGen actual = JSON.parseObject(((JSONObject) objectB).toJSONString(), ConfigGen.class);
        // // 比较
        // assertAsEntity(expected, actual);
        //
        // } else if ((expectedResult.getResult() instanceof Map) && (actualResult.getResult() instanceof JSONObject)) {
        // // 转换
        // Map expected = (Map) expectedResult.getResult();
        // Object objectB = actualResult.getResult();
        // Map actual = JSON.parseObject(((JSONObject) objectB).toJSONString(), HashMap.class);
        // // 比较
        // Assert.assertEquals(expected, actual);
        //
        // } else if ((expectedResult.getResult() instanceof List) && (actualResult.getResult() instanceof JSONArray)) {
        // // 转换
        // List expected = (List) expectedResult.getResult();
        // Object objectB = actualResult.getResult();
        // List actual = JSON.parseObject(((JSONObject) objectB).toJSONString(), ArrayList.class);
        // // 比较
        // Assert.assertEquals(expected, actual);
        // }
    }

    // private void assertAsEntity(BaseEntity expected, BaseEntity actual) {
    // // 对比非日期的所有字段
    // ReflectionAssert.assertReflectionEquals(expected, actual, ReflectionComparatorMode.LENIENT_DATES);
    // // 模糊对比日期
    // TestUtils.generallyEquals(expected.getGmtCreate(), actual.getGmtCreate());
    // TestUtils.generallyEquals(expected.getGmtModified(), actual.getGmtModified());
    // }

    // private void assertAsUpstream(Upstream expectedUpstream, Upstream actualUpstream) {
    // // 对比非日期的所有字段
    // ReflectionAssert.assertReflectionEquals(expectedUpstream, actualUpstream,
    // ReflectionComparatorMode.LENIENT_DATES);
    // // 模糊对比日期
    // TestUtils.generallyEquals(expectedUpstream.getGmtCreate(), actualUpstream.getGmtCreate());
    // TestUtils.generallyEquals(expectedUpstream.getGmtModified(), actualUpstream.getGmtModified());
    // }

    protected Upstream createUpstream(String name) throws ParseException {
        Upstream upstream = new Upstream();
        upstream.setName(name);
        upstream.setIpHash(true);
        upstream.setKeepalive(10);

        Map<String, List<Member>> regions = createRegions();

        upstream.setRegions(regions);
        upstream.setVersion(1L);
        upstream.setGmtCreate(DateUtils.parseDate("2014-12-15 08:59:09", Constants.DATE_PATTERNS));
        upstream.setGmtModified(DateUtils.parseDate("2014-12-16 08:59:09", Constants.DATE_PATTERNS));
        return upstream;
    }

    protected Map<String, List<Member>> createRegions() {
        Map<String, List<Member>> regions = new HashMap<String, List<Member>>();
        List<Member> listA = createMembers();
        List<Member> listB = createMembers();
        regions.put("testRegionA", listA);
        regions.put("testRegionB", listB);
        return regions;
    }

    protected List<Member> createMembers() {
        List<Member> list = new ArrayList<Member>();
        Member memberA = createMember("hostA");
        Member memberB = createMember("hostB");
        list.add(memberA);
        list.add(memberB);
        return list;
    }

    protected Member createMember(String host) {
        Member member = new Member();
        member.setFailTimeout("10s");
        member.setMaxFails(5);
        member.setWeight("8");
        member.setHost(host);
        return member;
    }

}
