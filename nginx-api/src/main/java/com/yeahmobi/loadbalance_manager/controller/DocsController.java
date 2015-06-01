package com.yeahmobi.loadbalance_manager.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author atell.wu
 */
@Controller
@RequestMapping(value = { DocsController.SCREEN_NAME })
public class DocsController extends AbstractController {

    public static final String SCREEN_NAME = "docs";

    @RequestMapping(value = { "/{doc}" }, method = RequestMethod.GET)
    public ModelAndView index(@PathVariable String doc) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("doc", doc);
        return screen(map, SCREEN_NAME);
    }

}
