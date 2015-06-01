## Vhost配置
* * *
### vhost/save
* * *
* **功能**  
  
  修改/新增 Vhost，vhost 不存在则新增，存在则是修改

* **请求方式**  
  
  URL: /vhost/save   
  Method: POST  
  Content-Type: application/json 

  
* **请求参数**

  {"name":"\[vhost名称\]","upstreamName":"\[upstream名称\]","listenPort":\[listenPort\],"isDefaultServer":true或false,"custom":"自定义内容","serverNames":["serverA","serverB"]}

  取值解释如下：
  
| 参数    | 说明             | 是否必须                        |
|:------------: |:----------------:| :-------------------------:|
| name                  | 每个vhost都有一个唯一的名称 | 是 |
| upstreamName       | 每个 vhost 有且仅有一个对应的 upstream | 新增时必须有值，修改时可为空 |
| listenPort       | 监听的端口 | 否，默认值80 |
| isDefaultServer              | 是否是默认的 server | 否，默认值false |
| custom              | 自定义的配置内容 | 否 |
| serverNames              | 可以有多个serverName，用数组表示 | 新增时必须有值，修改时可为空 |

* **返回数据(只解释 result 字段，其他字段含义见前面)**

 result字段永远为空

* **请求例子(只列举部分)**

 ```
 成功例子:  
 请求：curl -H "Content-Type: application/json" -d '{"name":"vhostA","upstreamName":"upstreamA","serverNames":["serverA","serverB"]}' http://localhost:8080/vhost/save
 返回：
  {"errorCode":0,"msg":"success","result":""}
 ```

 ```
 错误的例子: （upstream 取值不存在，会报错）  
 请求：curl -H "Content-Type: application/json" -d '{"name":"vhostA","upstreamName":"upstreamXX"}' http://localhost:8080/vhost/save
 返回：
  {"errorCode":1,"msg":"指定的upstream不存在，请检查upstream是否正确","result":""}
 ```
 
* * *
### vhost/del
* * *
* **功能**  

  删除指定的 vhost  

* **请求方式**  
  
  URL: /vhost/del   
  Method: POST  
  Content-Type: application/x-www-form-urlencoded
  
* **请求参数**

  name=\[someName1\]&key=\[someName2\]

* **返回数据(只解释 result 字段，其他字段含义见前面)**
  
  result 字段为空

* **请求例子(只列举部分)**

 ```
 成功例子:  
 请求：curl -H "Content-Type: application/x-www-form-urlencoded" -d 'name=vhostA' http://localhost:8080/vhost/del
 返回：
  {"errorCode":0,"msg":"success","result":""}
 ```

 ```
 错误的例子:  
 请求：curl -H "Content-Type: application/x-www-form-urlencoded" -d 'name=someName' http://localhost:8080/vhost/del
 返回：
  {"errorCode":1,"msg":"找不到对应的vhost","result":""}
 ```
 
* * *
### vhost/get
* * *
* **功能**  
  
  获取 Vhost 配置文件中的值

* **请求方式**  
  
  URL: /vhost/get   
  Method: GET  
  
* **请求参数**

  name=\[someName1\]&key=\[someName2\]

* **返回数据(只解释 result 字段，其他字段含义见前面)**
  
  result 字段为指定 vhost 的数组

* **请求例子(只列举部分)**

 ```
 成功例子:  
 请求：curl http://localhost:8080/vhost/get?name=vhostA
 返回：
  {
    "errorCode": 0,
    "msg": "success",
    "result": [
        {
            "custom": "",
            "gmtCreate": "2014-12-16 16:14:14",
            "gmtModified": "2014-12-16 16:14:14",
            "isDefaultServer": false,
            "listenPort": 90,
            "name": "vhostA",
            "serverNames": [],
            "upstreamName": "",
            "version": 2
        }
    ]
}

 ```

 ```
 错误的例子:  
 请求：curl http://localhost:8080/vhost/get?name=someName
 返回：
  {"errorCode":1,"msg":"找不到对应的vhost","result":""}
 ```

* * *
### vhost/list
* * *
* **功能**  
  
  获取所有配置项

* **请求方式**  
  
  URL: /vhost/list   
  Method: GET  
  
* **请求参数**

  无参数

* **返回数据(只解释 result 字段，其他字段含义见前面)**

  result 字段为所有 vhost 的数组

* **请求例子(只列举部分)**

 ```
 成功例子:  
 请求：curl http://localhost:8080/vhost/list
 返回：
  {
    "errorCode": 0,
    "msg": "success",
    "result": [
        {
            "custom": "",
            "gmtCreate": "2014-12-15 14:19:37",
            "gmtModified": "2014-12-15 14:19:37",
            "isDefaultServer": false,
            "listenPort": 80,
            "name": "vhostA",
            "serverNames": [
                "serverA",
                "serverB"
            ],
            "upstreamName": "upstreamA",
            "version": 5
        }
    ]
}
 ```
