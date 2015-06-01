## NodeStatus配置
* * *
### nodeStatus/set
* * *
* **功能**  
  
  记录节点 node 状态

* **请求方式**  
  
  URL: /nodeStatus/set   
  Method: POST  
  Content-Type: application/json 

  
* **请求参数**

  region=\[区域名\]&nodeId=\[节点id\]&state=\[节点状态\]&detail=\[附加信息\]
  
  参数说明：
  
| 参数    | 说明             | 是否必须                        |
|:------------: |:----------------:| :-------------------------:|
| region  | 区域名称 | 是 |
| nodeId       | 节点id | 是 |
| state              | 节点状态，取值：TOBE_UPDATED, UPDATING, UPDATE_SUCCESS, UPDATE_FAILED | 是 |
| detail              | 附加信息 | 否 |

* **返回数据(只解释 result 字段，其他字段含义见前面)**

 result字段永远为空

* **请求例子(只列举部分)**

 ```
 成功例子:  
 请求：curl -H "Content-Type: application/x-www-form-urlencoded" -d 'region=London&nodeId=12&state=TOBE_UPDATED' http://localhost:8080/nodeStatus/set
 返回：
  {"errorCode":0,"msg":"success","result":""}
 ```

 ```
 错误的例子: （upstream 取值不存在，会报错）  
 请求：curl -H "Content-Type: application/x-www-form-urlencoded" -d 'region=www&nodeId=12&state=TOBE_UPDATED' http://localhost:8080/nodeStatus/set
 返回：
  {"errorCode":1,"msg":"指定的region不存在，请检查region是否正确","result":""}
 ```
 
* * *
### nodeStatus/get
* * *
* **功能**  
  
  获取 NodeStatus 配置文件中的值

* **请求方式**  
  
  URL: /nodeStatus/get   
  Method: GET  
  
* **请求参数**

  region=\[区域名\]&nodeId=\[节点id\]

* **返回数据(只解释 result 字段，其他字段含义见前面)**
  
  result 字段为指定节点的状态信息

* **请求例子(只列举部分)**

 ```
 成功例子:  
 请求：curl http://localhost:8080/nodeStatus/get?region=London&nodeId=12
 返回：
  {
    "errorCode": 0,
    "msg": "success",
    "result": {
        "detail": "",
        "gmtCreate": "2014-12-24 11:53:15",
        "gmtModified": "2014-12-24 11:53:15",
        "lastHeartbeatTime": "2014-12-24 11:55:10",
        "lastStatusUpdateTime": "2014-12-24 11:53:15",
        "nodeId": "12",
        "region": "London",
        "state": "TOBE_UPDATED",
        "version": 2
    }
}

 ```

 ```
 错误的例子:  
 请求：curl http://localhost:8080/nodeStatus/get?region=someRegion&nodeId=12
 返回：
  {"errorCode":1,"msg":"指定的region不存在，请检查region是否正确","result":""}
 ```

* * *
### nodeStatus/list
* * *
* **功能**  
  
  获取所有配置项

* **请求方式**  
  
  URL: /nodeStatus/list   
  Method: GET  
  
* **请求参数**

  无参数

* **返回数据(只解释 result 字段，其他字段含义见前面)**

  result 字段为所有 nodeStatus 的数组

* **请求例子(只列举部分)**

 ```
 成功例子:  
 请求：curl http://localhost:8080/nodeStatus/list
 返回：
  {
    "errorCode": 0,
    "msg": "success",
    "result": [
        {
            "detail": "ok!",
            "gmtCreate": "2014-12-15 13:46:50",
            "gmtModified": "2014-12-15 13:49:39",
            "lastHeartbeatTime": "2014-12-16 17:32:50",
            "lastStatusUpdateTime": "2014-12-15 13:49:39",
            "nodeId": "12",
            "region": "Hongkong",
            "state": "TOBE_UPDATED",
            "version": 21
        },
        {
            "detail": "",
            "gmtCreate": "2014-12-24 11:53:15",
            "gmtModified": "2014-12-24 11:53:15",
            "lastHeartbeatTime": "2014-12-24 11:55:10",
            "lastStatusUpdateTime": "2014-12-24 11:53:15",
            "nodeId": "12",
            "region": "London",
            "state": "TOBE_UPDATED",
            "version": 2
        }
    ]
}
 ```
