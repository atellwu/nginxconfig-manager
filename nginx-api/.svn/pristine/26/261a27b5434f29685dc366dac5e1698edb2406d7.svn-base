# Nginx API 使用说明

Nginx API通过 **HTTP** 方式提供服务。

## 权限验证

目前无权限验证

## 数据返回格式

文件下载的 API 接口返回文件，其他 API 接口统一返回 **JSON**

**例子:**
```
操作成功:  

{"errorCode":0,"msg":"success","result":[xx1,xx2]}
或
{"errorCode":0,"msg":"success","result":{key1:value1,key2:value2}}
```

```
操作失败:  

{"errorCode":1,"msg":"No config.","result":""}
```
  
  
**具体说明：**

| Key          | Value含义             | 备注                |
|:-----------: |:--------------------:|:-------------------:|
| errorCode    | 错误码                |状态码请参看下一个表格   |
| msg      | 附加信息              |                     |
| result  | 返回数据，类型可以为 String / Map / Array | 不同api会返回不同类型的数据 |

**状态码：**

| Code         | 含义                          |
|:-----------: |:-----------------------------:|
| 0            | 成功                           |
| 1          | 参数错误                    |
| 2          | 服务器内部错误 |

* **例子**

```
返回正确的例子：
  curl http://localhost:8080/global/get?key=pid
返回：
  {"errorCode":0,"msg":"success","result":{"gmtModified":"2014-12-19 15:22:39","keyValues":{"pid":"b"},"version":4}}
```

```
返回错误的例子：
  curl -H "Content-Type: application/x-www-form-urlencoded" -d 'someKey=pid' http://localhost:8080/global/set
返回：
  {"errorCode":1,"msg":"unknown property found: someKey","result":""}
```

# API详细说明
## 前端交互相关的API
* [global配置 API](global "Title")
* [upstream配置 API](upstream "Title")
* [vhost配置 API](vhost "Title")

## 与节点交互相关的API
* [Gloabl,Upstream,Vhost 三个模块的下载 API](infoDownload "Title")
* [节点状态记录 API](nodeStatus "Title")




 ----
