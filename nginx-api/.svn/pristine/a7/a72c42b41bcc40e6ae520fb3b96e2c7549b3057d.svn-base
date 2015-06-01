## Global配置
* * *
### global/set
* * *
* **功能**  
  
  修改 Global 配置文件中的值

* **请求方式**  
  
  URL: /global/set   
  Method: POST  
  Content-Type: application/x-www-form-urlencoded 

  
* **请求参数**

  \[key1\]=\[value1\]&\[key2\]=\[value2\]

  Global 的 key 取值如下：
  
| Request参数    | 默认值             | 是否必须                        |
|:------------: |:----------------:| :-------------------------:|
| user                  | nginx | 否 |
| workerProcesses       | 1 | 否 |
| errorLog              | /var/log/nginx/error.log | 否 |
| pid                   | /var/run/nginx.pid | 否 |
| useEpoll              | true | 否 |
| workerConnections     | 65535 | 否 |
| workerRlimitNofile    | 65535 | 否 |
| ulimit                | 1 | 否 |
| sendfile              | true | 否 |
| resetTimedoutConnection   | true | 否 |
| tcpNopush             | true | 否 |
| defaultType           | application/octet-stream | 否 |
| sslSessionCache       | shared:SSL:10m | 否 |
| sslSessionTimeout     | 10m | 否 |
| openFileCcache        | max&#166;65535 inactive&#166;20s | 否 |
| openFileCacheMinUses  | 1 | 否 |
| openFileCacheValid    | 30s | 否 |
| tcpNodelay            | true | 否 |
| index                 | index.html index.htm | 否 |
| keepaliveTimeout      | 20 | 否 |
| keepaliveRequests     | 10240 | 否 |
| clientHeaderBufferSize| 32k | 否 |
| largeClientHeaderBuffers  | 4 32k | 否 |
| serverNamesHashBucketSize | 128 | 否 |
| clientMaxBodySize     | 20M | 否 |
|  logFormat             | nginx_access  '$request_time-_-$remote_addr-_-$host-_-$upstream_addr-_-$upstream_status-_-$time_local-_-$request-_-$status-_-$body_bytes_sent-_-$http_referer-_-$http_user_agent-_-$http_x_forwarded_for-_-$upstream_response_time' | 否 |
| customHttp | 空 | 否 |
| customMimetypes | 空 | 否 |
| customProxy | 空 | 否 |
| customServer | 空 | 否 |

* **返回数据(只解释 result 字段，其他字段含义见前面)**

 result字段永远为空

* **请求例子(只列举部分)**

 ```
 成功例子:  
 请求：curl -H "Content-Type: application/x-www-form-urlencoded" -d 'user=ops&workerProcesses=2' http://localhost:8080/global/set
 返回：
  {"errorCode":0,"msg":"success","result":""}
 ```

 ```
 错误的例子:  
 请求：curl -H "Content-Type: application/x-www-form-urlencoded" -d 'somekey=ops' http://localhost:8080/global/set
 返回：
  {"errorCode":1,"msg":"unknown property found: somekey","result":""}
 ```

* * *
### global/get
* * *
* **功能**  
  
  获取 Global 配置文件中的值

* **请求方式**  
  
  URL: /global/get   
  Method: GET  
  
* **请求参数**

  key=\[someProperty2\]&key=\[someProperty2\]

* **返回数据(只解释 result 字段，其他字段含义见前面)**
  
  result 字段为所指定配置项的 map，map 字段如下：
  
| Key          | 内容             |
|:-----------: |:--------------------:|
| keyValues       | 含所有指定 key 的所有 key-value 值的 map|
| gmtModified      | global 的最近更新时间              |
| version      | global 的版本              |

* **请求例子(只列举部分)**

 ```
 成功例子:  
 请求：curl http://localhost:8080/global/get?key=user&key=workerProcesses
 返回：
  {"errorCode":0,"msg":"success","result":{"gmtModified":"2014-12-23 17:49:59","keyValues":{"user":"ops"},"version":5}}
 ```

 ```
 错误的例子:  
 请求：curl http://localhost:8080/global/get?key=someKey
 返回：
  {"errorCode":1,"msg":"unknown property found: somekey","result":""}
 ```

* * *
### global/list
* * *
* **功能**  
  
  获取所有配置项

* **请求方式**  
  
  URL: /global/list   
  Method: GET  
  
* **请求参数**

  无参数

* **返回数据(只解释 result 字段，其他字段含义见前面)**

  result 字段为所有配置项的 map

* **请求例子(只列举部分)**

 ```
 成功例子:  
 请求：curl http://localhost:8080/global/list
 返回：
  {
    "errorCode": 0,
    "msg": "success",
    "result": {
        "clientHeaderBufferSize": "32k",
        "clientMaxBodySize": "20M",
        "customHttp": "",
        "customMimetypes": "",
        "customProxy": "",
        "customServer": "",
        "defaultType": "application/octet-stream",
        "errorLog": "/var/log/nginx/error.log",
        "gmtCreate": "2014-12-15 16:59:09",
        "gmtModified": "2014-12-23 17:49:59",
        "index": "index.html index.htm",
        "keepaliveRequests": 10240,
        "keepaliveTimeout": 20,
        "largeClientHeaderBuffers": "4 32k",
        "logFormat": "nginx_access  '$request_time-_-$remote_addr-_-$host-_-$upstream_addr-_-$upstream_status-_-$time_local-_-$request-_-$status-_-$body_bytes_sent-_-$http_referer-_-$http_user_agent-_-$http_x_forwarded_for-_-$upstream_response_time'",
        "openFileCacheMinUses": 1,
        "openFileCacheValid": "30s",
        "openFileCcache": "max=65535 inactive=20s",
        "pid": "b",
        "resetTimedoutConnection": true,
        "sendfile": true,
        "serverNamesHashBucketSize": 128,
        "sslSessionCache": "shared:SSL:10m",
        "sslSessionTimeout": "10m",
        "tcpNodelay": true,
        "tcpNopush": false,
        "ulimit": 1,
        "useEpoll": true,
        "user": "ops",
        "version": 5,
        "workerConnections": 65535,
        "workerProcesses": 2,
        "workerRlimitNofile": 65535
    }
}
 ```
 