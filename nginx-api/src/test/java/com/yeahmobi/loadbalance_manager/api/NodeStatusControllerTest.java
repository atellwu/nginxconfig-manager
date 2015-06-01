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
import com.yeahmobi.loadbalance_manager.model.NodeStatus;
import com.yeahmobi.loadbalance_manager.model.NodeStatus.State;

@SuppressWarnings("unchecked")
public class NodeStatusControllerTest extends ControllerBaseTest {

    @Autowired
    private NodeStatusController nodeStatusController;

    @Test
    @JsonData(fileName = "initNodeStatus.json")
    public void testList() throws Exception {
        String json = this.nodeStatusController.list();
        JsonResult<List<NodeStatus>> actualResult = JSON.parseObject(json,
                                                                     new TypeReference<JsonResult<List<NodeStatus>>>() {
                                                                     });

        JsonResult expectedResult = createSuccessJsonResult();
        NodeStatus nodeStatusA = createNodeStatus("testNodeStatusA");
        NodeStatus nodeStatusB = createNodeStatus("testNodeStatusB");
        expectedResult.setResult(Lists.newArrayList(nodeStatusA, nodeStatusB));

        ReflectionAssert.assertReflectionEquals(expectedResult, actualResult, ReflectionComparatorMode.LENIENT_DATES);
    }

    @Test
    @JsonData(fileName = "initNodeStatus.json")
    public void testGet() throws Exception {
        String json = this.nodeStatusController.get("testRegionA", "testNodeStatusA");
        JsonResult<NodeStatus> actualResult = JSON.parseObject(json, new TypeReference<JsonResult<NodeStatus>>() {
        });

        JsonResult expectedResult = createSuccessJsonResult();
        NodeStatus nodeStatusA = createNodeStatus("testNodeStatusA");
        nodeStatusA.setVersion(2L);
        expectedResult.setResult(nodeStatusA);

        assertJsonResult(expectedResult, actualResult);
    }

    @Test
    @JsonData(fileName = "initNodeStatus.json")
    public void testSet() throws Exception {
        this.nodeStatusController.set("testRegionA", "testNodeStatusA", State.UPDATE_SUCCESS, "success");

        // get & test
        String json = this.nodeStatusController.get("testRegionA", "testNodeStatusA");
        JsonResult<NodeStatus> actualResult = JSON.parseObject(json, new TypeReference<JsonResult<NodeStatus>>() {
        });

        JsonResult expectedResult = createSuccessJsonResult();
        NodeStatus nodeStatusA = createNodeStatus("testNodeStatusA");
        nodeStatusA.setState(State.UPDATE_SUCCESS);
        nodeStatusA.setDetail("success");
        nodeStatusA.setVersion(3L);
        expectedResult.setResult(nodeStatusA);

        assertJsonResult(expectedResult, actualResult);
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

    protected NodeStatus createNodeStatus(String nodeId) throws ParseException {
        NodeStatus nodeStatus = new NodeStatus();
        nodeStatus.setDetail("detail");
        nodeStatus.setLastHeartbeatTime(DateUtils.parseDate("2014-12-15 08:59:09", Constants.DATE_PATTERNS));
        nodeStatus.setLastStatusUpdateTime(DateUtils.parseDate("2014-12-15 08:59:09", Constants.DATE_PATTERNS));
        nodeStatus.setNodeId(nodeId);
        nodeStatus.setRegion("testRegionA");
        nodeStatus.setState(State.TOBE_UPDATED);
        nodeStatus.setVersion(1L);
        nodeStatus.setGmtCreate(DateUtils.parseDate("2014-12-15 08:59:09", Constants.DATE_PATTERNS));
        nodeStatus.setGmtModified(DateUtils.parseDate("2014-12-16 08:59:09", Constants.DATE_PATTERNS));
        return nodeStatus;
    }

}
