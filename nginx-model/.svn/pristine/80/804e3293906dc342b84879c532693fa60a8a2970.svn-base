package com.yeahmobi.loadbalance_manager.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.code.morphia.annotations.Id;

public class Global extends BaseEntity {

    @Id
    @JSONField(serialize = false, deserialize = false)
    private Long    id;

    private String  user                      = "nginx";
    private Integer workerProcesses           = 1;
    private String  errorLog                  = "/var/log/nginx/error.log";
    private String  pid                       = "/var/run/nginx.pid";
    private Boolean useEpoll                  = true;
    private Integer workerConnections         = 65535;
    private Integer workerRlimitNofile        = 65535;
    private Integer ulimit                    = 1;

    // http
    private Boolean sendfile                  = true;
    private Boolean resetTimedoutConnection   = true;
    private Boolean tcpNopush                 = true;
    private String  defaultType               = "application/octet-stream";
    private String  sslSessionCache           = "shared:SSL:10m";
    private String  sslSessionTimeout         = "10m";
    private String  openFileCcache            = "max=65535 inactive=20s";
    private Integer openFileCacheMinUses      = 1;
    private String  openFileCacheValid        = "30s";
    private Boolean tcpNodelay                = true;
    private String  index                     = "index.html index.htm";
    private Integer keepaliveTimeout          = 20;
    private Integer keepaliveRequests         = 10240;
    private String  clientHeaderBufferSize    = "32k";
    private String  largeClientHeaderBuffers  = "4 32k";
    private Integer serverNamesHashBucketSize = 128;
    private String  clientMaxBodySize         = "20M";
    private String  logFormat                 = "nginx_access  '$request_time-_-$remote_addr-_-$host-_-$upstream_addr-_-$upstream_status-_-$time_local-_-$request-_-$status-_-$body_bytes_sent-_-$http_referer-_-$http_user_agent-_-$http_x_forwarded_for-_-$upstream_response_time'";

    private String  customHttp;
    private String  customMimetypes;
    private String  customProxy;
    private String  customServer;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getWorkerProcesses() {
        return this.workerProcesses;
    }

    public void setWorkerProcesses(Integer workerProcesses) {
        this.workerProcesses = workerProcesses;
    }

    public String getErrorLog() {
        return this.errorLog;
    }

    public void setErrorLog(String errorLog) {
        this.errorLog = errorLog;
    }

    public String getPid() {
        return this.pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Boolean getUseEpoll() {
        return this.useEpoll;
    }

    public void setUseEpoll(Boolean useEpoll) {
        this.useEpoll = useEpoll;
    }

    public Integer getWorkerConnections() {
        return this.workerConnections;
    }

    public void setWorkerConnections(Integer workerConnections) {
        this.workerConnections = workerConnections;
    }

    public Integer getUlimit() {
        return this.ulimit;
    }

    public void setUlimit(Integer ulimit) {
        this.ulimit = ulimit;
    }

    public Boolean getSendfile() {
        return this.sendfile;
    }

    public void setSendfile(Boolean sendfile) {
        this.sendfile = sendfile;
    }

    public Boolean getResetTimedoutConnection() {
        return this.resetTimedoutConnection;
    }

    public void setResetTimedoutConnection(Boolean resetTimedoutConnection) {
        this.resetTimedoutConnection = resetTimedoutConnection;
    }

    public Boolean getTcpNopush() {
        return this.tcpNopush;
    }

    public void setTcpNopush(Boolean tcpNopush) {
        this.tcpNopush = tcpNopush;
    }

    public String getCustomHttp() {
        return this.customHttp;
    }

    public void setCustomHttp(String customHttp) {
        this.customHttp = customHttp;
    }

    public String getCustomMimetypes() {
        return this.customMimetypes;
    }

    public void setCustomMimetypes(String customMimetypes) {
        this.customMimetypes = customMimetypes;
    }

    public String getCustomProxy() {
        return this.customProxy;
    }

    public void setCustomProxy(String customProxy) {
        this.customProxy = customProxy;
    }

    public String getCustomServer() {
        return this.customServer;
    }

    public void setCustomServer(String customServer) {
        this.customServer = customServer;
    }

    public String getDefaultType() {
        return this.defaultType;
    }

    public void setDefaultType(String defaultType) {
        this.defaultType = defaultType;
    }

    public String getSslSessionCache() {
        return this.sslSessionCache;
    }

    public void setSslSessionCache(String sslSessionCache) {
        this.sslSessionCache = sslSessionCache;
    }

    public String getSslSessionTimeout() {
        return this.sslSessionTimeout;
    }

    public void setSslSessionTimeout(String sslSessionTimeout) {
        this.sslSessionTimeout = sslSessionTimeout;
    }

    public String getOpenFileCcache() {
        return this.openFileCcache;
    }

    public void setOpenFileCcache(String openFileCcache) {
        this.openFileCcache = openFileCcache;
    }

    public Integer getOpenFileCacheMinUses() {
        return this.openFileCacheMinUses;
    }

    public void setOpenFileCacheMinUses(Integer openFileCacheMinUses) {
        this.openFileCacheMinUses = openFileCacheMinUses;
    }

    public String getOpenFileCacheValid() {
        return this.openFileCacheValid;
    }

    public void setOpenFileCacheValid(String openFileCacheValid) {
        this.openFileCacheValid = openFileCacheValid;
    }

    public Boolean getTcpNodelay() {
        return this.tcpNodelay;
    }

    public void setTcpNodelay(Boolean tcpNodelay) {
        this.tcpNodelay = tcpNodelay;
    }

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public Integer getKeepaliveTimeout() {
        return this.keepaliveTimeout;
    }

    public void setKeepaliveTimeout(Integer keepaliveTimeout) {
        this.keepaliveTimeout = keepaliveTimeout;
    }

    public Integer getKeepaliveRequests() {
        return this.keepaliveRequests;
    }

    public void setKeepaliveRequests(Integer keepaliveRequests) {
        this.keepaliveRequests = keepaliveRequests;
    }

    public String getClientHeaderBufferSize() {
        return this.clientHeaderBufferSize;
    }

    public void setClientHeaderBufferSize(String clientHeaderBufferSize) {
        this.clientHeaderBufferSize = clientHeaderBufferSize;
    }

    public String getLargeClientHeaderBuffers() {
        return this.largeClientHeaderBuffers;
    }

    public void setLargeClientHeaderBuffers(String largeClientHeaderBuffers) {
        this.largeClientHeaderBuffers = largeClientHeaderBuffers;
    }

    public Integer getServerNamesHashBucketSize() {
        return this.serverNamesHashBucketSize;
    }

    public void setServerNamesHashBucketSize(Integer serverNamesHashBucketSize) {
        this.serverNamesHashBucketSize = serverNamesHashBucketSize;
    }

    public String getClientMaxBodySize() {
        return this.clientMaxBodySize;
    }

    public void setClientMaxBodySize(String clientMaxBodySize) {
        this.clientMaxBodySize = clientMaxBodySize;
    }

    public String getLogFormat() {
        return this.logFormat;
    }

    public void setLogFormat(String logFormat) {
        this.logFormat = logFormat;
    }

    public Integer getWorkerRlimitNofile() {
        return this.workerRlimitNofile;
    }

    public void setWorkerRlimitNofile(Integer workerRlimitNofile) {
        this.workerRlimitNofile = workerRlimitNofile;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
