#Gloabl,Upstream,Vhost 三个模块的下载 API
 
* * *
### global/info
* * *
* **功能**  
  
   获取 6 个 global 配置文件的信息

* **请求方式**  
  
  URL: /global/info   
  Method: GET  
  
* **请求参数**

  无参数

* **返回数据(只解释 result 字段，其他字段含义见前面)**

  result 含 6 个所有配置文件信息的数组，每个数组是一个文件信息，以 map 表示， map 的字段如下：
  
| Key          | 内容             |
|:-----------: |:--------------------:|
| module       | 文件名 |
| url      | 下载地址              |
| md5      | 文件的 md5              |
| gmtModified      | 最近更新时间              |
| version      | 版本              |

* **请求例子(只列举部分)**

 ```
 成功例子:  
 请求：curl http://localhost:8080/global/info
 返回：
  {
    "errorCode": 0,
    "msg": "success",
    "result": [
        {
            "gmtModified": "2014-12-23 17:50:00",
            "md5": "5b8441e64413bd18ca279a434ae8e96b",
            "module": "global_http",
            "url": "/global/download?id=54",
            "version": 54
        },
        {
            "gmtModified": "2014-12-23 17:50:00",
            "md5": "7a379948b09a99bd8475a193366485a7",
            "module": "global_base",
            "url": "/global/download?id=55",
            "version": 55
        },
        {
            "gmtModified": "2014-12-23 17:50:00",
            "md5": "d41d8cd98f00b204e9800998ecf8427e",
            "module": "global_http_custom",
            "url": "/global/download?id=56",
            "version": 56
        },
        {
            "gmtModified": "2014-12-23 17:50:00",
            "md5": "d41d8cd98f00b204e9800998ecf8427e",
            "module": "global_server_custom",
            "url": "/global/download?id=57",
            "version": 57
        },
        {
            "gmtModified": "2014-12-23 17:50:00",
            "md5": "d41d8cd98f00b204e9800998ecf8427e",
            "module": "global_mimetypes",
            "url": "/global/download?id=58",
            "version": 58
        },
        {
            "gmtModified": "2014-12-23 17:50:00",
            "md5": "d41d8cd98f00b204e9800998ecf8427e",
            "module": "global_proxy",
            "url": "/global/download?id=59",
            "version": 59
        }
    ]
}
 ```
 
* * *
### global/download
* * *
* **功能**  
  
   下载配置文件

* **请求方式**  
  
  URL: /global/download   
  Method: GET  
  
* **请求参数**

  id,唯一id。（下载链接可通过 info 接口的返回值获取到，故一般无须关心该参数如何形成）

* **返回数据(只解释 result 字段，其他字段含义见前面)**

  返回的是 text/plain 的用于下载的文件格式

* **请求例子(只列举部分)**

 ```
 成功例子:  
 请求：curl http://localhost:8080/global/download?id=5
 返回：
   下载的文件内容如下：
   user ops;
   worker_processes  2;
   error_log  /var/log/nginx/error.log;
   pid        b;
   worker_rlimit_nofile 65535;
   events {
        worker_connections 65535;
        ulimit 1;
   }
 ```

* * *
### upstream/info
* * *
* **功能**  
  
   获取 6 个 upstream 配置文件的信息

* **请求方式**  
  
  URL: /upstream/info   
  Method: GET  
  
* **请求参数**

  无参数

* **返回数据(只解释 result 字段，其他字段含义见前面)**

  upstream 文件最终只会有一个，含所有 upstream 配置。result 是 upstream 文件信息，以 map 表示， map 的字段如下：
  
| Key          | 内容             |
|:-----------: |:--------------------:|
| url      | 下载地址              |
| md5      | 文件的 md5              |
| gmtModified      | 最近更新时间              |
| version      | 版本              |

* **请求例子(只列举部分)**

 ```
 成功例子:  
 请求：curl http://localhost:8080/upstream/info?region=London
 返回：
 {
    "errorCode": 0,
    "msg": "success",
    "result": {
        "gmtModified": "2014-12-23 19:36:27",
        "md5": "e2a0bf800f61ce08984f8838ba0fa6fd",
        "url": "/upstream/download?id=104",
        "version": 104
    }
}
 ```
 
* * *
### upstream/download
* * *
* **功能**  
  
   下载配置文件

* **请求方式**  
  
  URL: /upstream/download   
  Method: GET  
  
* **请求参数**

  id,唯一id。（下载链接可通过 info 接口的返回值获取到，故一般无须关心该参数如何形成）

* **返回数据(只解释 result 字段，其他字段含义见前面)**

  返回的是 text/plain 的用于下载的文件格式

* **请求例子(只列举部分)**

 ```
 成功例子:  
 请求：curl http://localhost:8080/upstream/download?id=104
 返回：
   下载的文件内容如下：
  upstream B {
     keepalive 22;   
  }
  upstream A {
     ip_hash;  
     keepalive 2;   
  }
  upstream C {
     ip_hash;  
     keepalive 2;   
  }
 ```
 
* * *
### vhost/info
* * *
* **功能**  
  
   获取 6 个 vhost 配置文件的信息

* **请求方式**  
  
  URL: /vhost/info   
  Method: GET  
  
* **请求参数**

  无参数

* **返回数据(只解释 result 字段，其他字段含义见前面)**

  vhost 文件最终只会有一个，含所有 vhost 配置。result 是 vhost 文件信息，以 map 表示， map 的字段如下：
  
| Key          | 内容             |
|:-----------: |:--------------------:|
| url      | 下载地址              |
| md5      | 文件的 md5              |
| gmtModified      | 最近更新时间              |
| version      | 版本              |

* **请求例子(只列举部分)**

 ```
 成功例子:  
 请求：curl http://localhost:8080/vhost/info?region=London
 返回：
{
    "errorCode": 0,
    "msg": "success",
    "result": [
        {
            "gmtModified": "2014-12-15 14:28:25",
            "modules": [
                {
                    "md5": "d41d8cd98f00b204e9800998ecf8427e",
                    "module": "server_custom",
                    "url": "/vhost/download?id=11",
                    "version": 11
                },
                {
                    "md5": "391891107194e0852ff9711da0814352",
                    "module": "server_name",
                    "url": "/vhost/download?id=12",
                    "version": 12
                },
                {
                    "md5": "abcdb79a18c4328ca1c366e7e6a5c577",
                    "module": "server",
                    "url": "/vhost/download?id=13",
                    "version": 13
                }
            ],
            "vhost": "B"
        },
        {
            "gmtModified": "2014-12-16 16:14:48",
            "modules": [
                {
                    "md5": "d41d8cd98f00b204e9800998ecf8427e",
                    "module": "server_custom",
                    "url": "/vhost/download?id=29",
                    "version": 29
                },
                {
                    "md5": "391891107194e0852ff9711da0814352",
                    "module": "server_name",
                    "url": "/vhost/download?id=30",
                    "version": 30
                },
                {
                    "md5": "abcdb79a18c4328ca1c366e7e6a5c577",
                    "module": "server",
                    "url": "/vhost/download?id=31",
                    "version": 31
                }
            ],
            "vhost": "xx"
        }
    ]
}
 ```
 
* * *
### vhost/download
* * *
* **功能**  
  
   下载配置文件

* **请求方式**  
  
  URL: /vhost/download   
  Method: GET  
  
* **请求参数**

  id,唯一id。（下载链接可通过 info 接口的返回值获取到，故一般无须关心该参数如何形成）

* **返回数据(只解释 result 字段，其他字段含义见前面)**

  返回的是 text/plain 的用于下载的文件格式

* **请求例子(只列举部分)**

 ```
 成功例子:  
 请求：curl http://localhost:8080/vhost/download?id=31
 返回：
   下载的文件内容如下：
server {
   listen       80 default_server;

   include conf.d/vhost/ndpsearch.com/server_name.conf;
   include conf.d/vhost/ndpsearch.com/server_custom.conf; 

   root /usr/share/nginx/html;
   location / {
     proxy_pass http://upstream1;
   }
   access_log  /var/log/nginx/access.log  nginx_access;
}
 ```
 
