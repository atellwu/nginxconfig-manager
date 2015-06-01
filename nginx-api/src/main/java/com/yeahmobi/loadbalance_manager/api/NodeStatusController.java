package com.yeahmobi.loadbalance_manager.api;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeahmobi.loadbalance_manager.model.NodeStatus;
import com.yeahmobi.loadbalance_manager.service.NodeStatusService;

/**
 * @author atell
 */
@Controller
@Validated
@SuppressWarnings("unchecked")
@RequestMapping(value = { "/nodeStatus" })
public class NodeStatusController extends AbstractController {

    @Autowired
    private NodeStatusService nodeStatusService;

    /**
     * 获取所有nodeStatus
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String list() {
        return json(new Handler() {

            public void handle(JsonResult result) {
                List<NodeStatus> list = NodeStatusController.this.nodeStatusService.listAll();

                result.setResult(list);
            }
        });
    }

    /**
     * 获取指定node的状态（由node主动定期发起心跳查询）
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String get(@NotBlank
    final String region, @NotBlank
    final String nodeId) {
        return json(new Handler() {

            public void handle(JsonResult result) {
                // 记录心跳
                Date lastHeartbeatTime = new Date();
                NodeStatusController.this.nodeStatusService.updateHeartbeat(region, nodeId, lastHeartbeatTime);

                NodeStatus nodeStatus = NodeStatusController.this.nodeStatusService.get(region, nodeId);

                result.setResult(nodeStatus);

            }
        });
    }

    /**
     * 记录状态
     */
    @RequestMapping(value = "/set", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String set(@NotBlank
    final String region, @NotBlank
    final String nodeId, @NotNull
    final NodeStatus.State state, final String detail) {
        return json(new Handler() {

            public void handle(JsonResult result) throws IOException {
                NodeStatusController.this.nodeStatusService.save(region, nodeId, state, detail);
            }
        });
    }

}
