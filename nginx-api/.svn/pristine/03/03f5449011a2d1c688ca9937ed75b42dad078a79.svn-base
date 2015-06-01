package com.yeahmobi.loadbalance_manager.api;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeahmobi.loadbalance_manager.helper.JsonArgument;
import com.yeahmobi.loadbalance_manager.model.Region;
import com.yeahmobi.loadbalance_manager.service.RegionService;

/**
 * @author atell.wu
 */
@Controller
@Validated
@SuppressWarnings("unchecked")
@RequestMapping(value = { "/region" })
public class RegionController extends AbstractController {

    @Autowired
    private RegionService regionService;

    /**
     * 获取所有
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String list() {
        return json(new Handler() {

            public void handle(JsonResult result) {
                List<Region> list = RegionController.this.regionService.listAll();

                result.setResult(list);
            }
        });
    }

    /**
     * 新增一个region
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String save(@NotNull
    @Valid
    final @JsonArgument Region region) {
        return json(new Handler() {

            public void handle(JsonResult result) throws IOException {
                // System.out.println(br);
                RegionController.this.regionService.add(region);
            }
        });
    }

    /**
     * 删除region
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String del(@NotEmpty
    final String[] name) {
        return json(new Handler() {

            public void handle(JsonResult result) throws IOException {
                for (String name0 : name) {
                    RegionController.this.regionService.del(name0);
                }
            }
        });
    }

}
