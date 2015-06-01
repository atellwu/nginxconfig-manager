package com.yeahmobi.loadbalance_manager.service;

import com.yeahmobi.loadbalance_manager.model.ConfigGen;

public interface ConfigGenService {

    // ////////// global

    ConfigGen getGlobal(long codegenId);

    /** 获取最新的 */
    ConfigGen getLastGlobal(String module);

    /** 保存一个新版本的global_<module>.conf文件 */
    void addGlobal(String module, String content);

    // ////////// upstream

    ConfigGen getUpstream(long codegenId);

    /** 获取最新的 */
    ConfigGen getLastUpstream(String region);

    /** 保存一个新版本的upstream.conf文件 */
    void addUpstream(String region, String content);

    // ////////// vhost

    ConfigGen getVhost(long codegenId);

    /** 获取最新的 */
    ConfigGen getLastVhost(String vhostName, String module);

    /** 保存一个新版本的vhost.conf文件 */
    void addVhost(String vhostName, String module, String content);

    // ///////// clear history
    void delLessThan(long codegenId);

}
