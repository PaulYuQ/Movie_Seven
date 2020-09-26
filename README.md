# seven_movie影柒

## 介绍
基于javaWeb的007电影网站项目。功能包括用户的登陆注册、电影观看、观看历史、收藏电影、评论系统以及对所有表格的增删改查的后台系统，页面风格统一，电影资源采用了直接网址播放。

## 软件架构
开发采用了MVC分层开发，软件整体采用了B/S架构，使用JavaWeb的servlet、mySql、dbUtils、c3p0连接池和Bootstrap等框架



## 电影项目数据库设计

#### 电影和用户表设计

创建movie数据库，建库语句

```sql
 create database movie;
```

选中当前数据库：

```sql
 use movie;
```

项目需要用到的表

#### 表名：admins

|   字段名    | admin_id |    name     |  password   |  phone  | control |
| :------: | :------: | :---------: | :---------: | :-----: | :-----: |
| **字段作用** |  管理员id   |    管理员名字    |    管理员密码    |  电话号码   | 管理员权限等级 |
| **是否主键** |   true   |    false    |    false    |  false  |  false  |
| **类型大小** | int(20)  | varchar(16) | varchar(16) | int(15) | int(1)  |
| **是否为空** | 不为空（自增）  |     不为空     |     不为空     |   可为空   |   不为空   |



#### **表名：users**

|   字段名    | user_id |    name     |  password   |   date    |  phone  |
| :------: | :-----: | :---------: | :---------: | :-------: | :-----: |
| **字段作用** |  用户id   |     用户名     |   用户登录密码    |  用户注册时间   |  电话号码   |
| **是否主键** |  true   |    false    |    false    |   false   |  false  |
| **类型大小** | int(20) | varchar(16) | varchar(16) | timestamp | int(11) |
| **是否为空** | 不为空（自增） |     不为空     |     不为空     |    不为空    |   可为空   |

#### **表名：movies**

|    字段    | movie_id |    name     |    type     | actor | image_url | banner_url | introduction |  url  |
| :------: | :------: | :---------: | :---------: | :---: | :-------: | :--------: | :----------: | :---: |
| **字段作用** |   电影id   |    电影名字     |    电影类型     | 电影演员  |  电影封面图片   |   电影轮播图    |     电影简介     | 电影链接  |
| **是否主键** |   true   |    false    |    false    | false |   false   |   flase    |    false     | false |
| **类型大小** | int(20)  | varchar(16) | varchar(16) | text  |   text    |    text    |     text     | text  |
| **是否为空** | 不为空（自增）  |     不为空     |     不为空     |  不为空  |    不为空    |    可为空     |     不为空      |  不为空  |

#### 表名：histories

|   字段名    | history_id | user_id | movie_id |  progress  |
| :------: | :--------: | :-----: | :------: | :--------: |
| **字段作用** |  历史记录的id   |  用户id   |   电影id   | 观影进度（秒为单位） |
| **是否主键** |    true    |  false  |  false   |   false    |
| **类型大小** |  int(20)   | int(20) | int(20)  |  int(20)   |
| **是否为空** |  不为空（自增）   |   不为空   |   不为空    |     可空     |

#### 表名：collections

|   字段名    | collection_id | user_id | movie_id |   date    |
| :------: | :-----------: | :-----: | :------: | :-------: |
| **字段作用** |     收藏的id     |  用户id   |   电影id   |   收藏时间    |
| **是否主键** |     true      |  false  |  false   |   false   |
| **类型大小** |    int(20)    | int(20) | int(20)  | timestamp |
| **是否为空** |    不为空（自增）    |   不为空   |   不为空    |    不为空    |

### 评论系统表设计

#### 表名：comments

|   字段名    |            comment_id            |  movie_id   |           parent_id           |   content    |  user_id   |   date    |
| :------: | :------------------------------: | :---------: | :---------------------------: | :----------: | :--------: | :-------: |
| **字段作用** | 评论的id，自增值，每个评论都对应一个唯一的comment_id | 评论所对应的电影的id | 指向父评论的id,如果不是对评论的回复,那么该值为null |    评论的内容     | 发出该评论用户的id |  评论产生日期   |
| **是否主键** |               true               |    false    |             flase             |    false     |   false    |   false   |
| **类型大小** |             int(20)              |   int(20)   |            int(20)            | varchar(512) |  int(20)   | timestamp |
| **是否为空** |             不为空（自增）              |     不为空     |              不为空              |     不为空      |    不为空     |    不为空    |

#### 建表语句：

```sql
CREATE TABLE admins(
                     admin_id     INT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT ,
                     name         VARCHAR(16) NOT NULL ,
                     password     VARCHAR(16) NOT NULL,
                 phone         VARCHAR(15) ,
                control       int(1) NOT NULL
) charset = utf8mb4;


CREATE TABLE users(
                     user_id      INT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT ,
                     name     VARCHAR(16) NOT NULL ,
                     password     VARCHAR(16) NOT NULL ,
                     date      timestamp default current_timestamp(),
                 phone         VARCHAR(15)   
) charset = utf8mb4;


CREATE TABLE movies(
                       movie_id       INT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
                       name        VARCHAR(255) NOT NULL ,
                       type       VARCHAR(16) NOT NULL ,
                       actor      text NOT NULL ,
                       image_url      text NOT NULL ,
                       banner_url   text,
                       introduction  text NOT NULL ,
                       url         text NOT NULL
) charset = utf8mb4;

CREATE TABLE histories(
                     history_id       INT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
                     user_id       INT(20) NOT NULL ,
                     movie_id     INT(20) NOT NULL ,
                     progress     INT(20)
) charset = utf8mb4;

CREATE TABLE collections(
                     collection_id    INT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
                     user_id       INT(20) NOT NULL ,
                     movie_id     INT(20) NOT NULL ,
                     date      timestamp default current_timestamp()
) charset = utf8mb4;

create table  comments
(
    comment_id int(20) auto_increment primary key,
    movie_id   int(20) not null,
    parent_id  int(20),
    content    varchar(512)   not null,
    user_id    int(20) not null,
    date       timestamp default current_timestamp(),
    foreign key (parent_id) references comments (comment_id),
    foreign key (user_id) references users (user_id),
    foreign key (movie_id) references movies (movie_id)
) charset = utf8mb4;

```



### 项目架构展示

采用分层的思想，整体项目架构分为dao层、service层、controller层、pojo层、factory层和web层等等，来进行分模块开

发。

- dao层：代用dbUtils对数据库进行调用，对数据进行持久化的操作。

- service层：业务层，调用dao层，主要是实现业务需求。

- controller层：主要是对获取的请求进行分发和处理，同时调用service层的方法，获得后台返回的结果，再传入到前端。

- pojo层：实体类，和数据库中的表字段相对应，用来存储数据。

  ​

采用分层开发的思想，不仅提高了开发效率，同时实现了模块与模块之间的低耦合和高内聚。同时亮点是，采用了工厂的设计模式，利用反射的方式，动态的创建一个对象，无需手动new 一个对象。实现了将对象的创建和使用分离，也使得系统更加符合“单一职责原则”，有利于对功能的复用和系统的维护。

![项目架构](images\项目架构.png)



### 功能展示

#### 登陆注册和用户模块

登陆注册页面采用了简单CSS动画，来实现登陆和注册的二者切换。采用ajax对前端用户输入的数据进行处理，由后台进行处理，进行判断和持久化操作以JSON数据的格式传入前端。同时实现了对非用户登陆执行收藏，评论的拦截。

![登陆注册](images\登陆注册.png)

#### 首页

首页包含的功能有：对数据库中的电影分类展示，包括动作片，喜剧片，爱情片，恐怖片等等；查询功能：通过模糊查询，显示数据库中的数据，以及实现了电影的播放功能。

首页

![首页](images\首页.png)

搜索

![搜索](images\搜索.png)

电影播放

![播放](images\播放.png)

#### 评论系统

评论系统添加在电影播放页面中间，当用户处于登陆状态时，才能够对播放的电影进行实时评论。在实现一级评论的同时，追加了二级评论，动态的展示用户发的评论，也能够显示用户发表评论的时间。同时对评论进行了敏感词过滤处理，不能发表有关（法轮功）等等敏感词汇，同时当前用户只能删除自己的评论。

![评论](images\评论.png)

#### 收藏模块

当用户处于登陆状态时，可以对所播放电影进行收藏，在个人中心会动态显示该用户收藏了该电影，同时做到了，对收藏电影的删除等等操作。

![收藏](images\收藏.png)

#### 历史记录

当用户处于登陆状态时，浏览过的电影会添加到浏览记录，并最近浏览的电影在最前面。

![浏览记录](images\浏览记录.png)

#### 后台模块

后台模块包括登陆注册，用户权限控制，对用户进行和管理员进行管理，和对数据库中的电影进行增删改查等操作。

登陆

![后台](images\后台.png)



后台

![后台展示](images\后台展示.png)

参考网站

```http
https://www.cnblogs.com/godlovesme/p/10708358.html
```




#### 参与贡献

1.  Fork 本仓库
2.  新建 你自己的分支
3.  提交代码
4.  新建 Pull Request