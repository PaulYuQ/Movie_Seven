# seven_movie影柒

## 介绍
基于javaWeb的007电影网站项目。功能包括用户的登陆注册、电影观看、观看历史、收藏电影、评论系统以及对所有表格的增删改查的后台系统，页面风格统一，电影资源采用了直接网址播放。

## 软件架构
开发采用了MVC分层开发，软件整体采用了B/S架构，使用JavaWeb的servlet、mySql、dbUtils、Bootstrap等框架



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

|    字段名    |    admin_id    |    name     |  password   |  phone   |    control     |
| :----------: | :------------: | :---------: | :---------: | :------: | :------------: |
| **字段作用** |    管理员id    | 管理员名字  | 管理员密码  | 电话号码 | 管理员权限等级 |
| **是否主键** |      true      |    false    |    false    |  false   |     false      |
| **类型大小** |    int(20)     | varchar(16) | varchar(16) | int(15)  |     int(1)     |
| **是否为空** | 不为空（自增） |   不为空    |   不为空    |  可为空  |     不为空     |



#### **表名：users**

|    字段名    |    user_id     |    name     |   password   |     date     |  phone   |
| :----------: | :------------: | :---------: | :----------: | :----------: | :------: |
| **字段作用** |     用户id     |   用户名    | 用户登录密码 | 用户注册时间 | 电话号码 |
| **是否主键** |      true      |    false    |    false     |    false     |  false   |
| **类型大小** |    int(20)     | varchar(16) | varchar(16)  |  timestamp   | int(11)  |
| **是否为空** | 不为空（自增） |   不为空    |    不为空    |    不为空    |  可为空  |

#### **表名：movies**

|     字段     |    movie_id    |    name     |    type     |  actor   |  image_url   | banner_url | introduction |   url    |
| :----------: | :------------: | :---------: | :---------: | :------: | :----------: | :--------: | :----------: | :------: |
| **字段作用** |     电影id     |  电影名字   |  电影类型   | 电影演员 | 电影封面图片 | 电影轮播图 |   电影简介   | 电影链接 |
| **是否主键** |      true      |    false    |    false    |  false   |    false     |   flase    |    false     |  false   |
| **类型大小** |    int(20)     | varchar(16) | varchar(16) |   text   |     text     |    text    |     text     |   text   |
| **是否为空** | 不为空（自增） |   不为空    |   不为空    |  不为空  |    不为空    |   可为空   |    不为空    |  不为空  |

#### 表名：histories

|    字段名    |   history_id   | user_id | movie_id |       progress       |
| :----------: | :------------: | :-----: | :------: | :------------------: |
| **字段作用** |  历史记录的id  | 用户id  |  电影id  | 观影进度（秒为单位） |
| **是否主键** |      true      |  false  |  false   |        false         |
| **类型大小** |    int(20)     | int(20) | int(20)  |       int(20)        |
| **是否为空** | 不为空（自增） | 不为空  |  不为空  |         可空         |

#### 表名：collections

|    字段名    | collection_id  | user_id | movie_id |   date    |
| :----------: | :------------: | :-----: | :------: | :-------: |
| **字段作用** |    收藏的id    | 用户id  |  电影id  | 收藏时间  |
| **是否主键** |      true      |  false  |  false   |   false   |
| **类型大小** |    int(20)     | int(20) | int(20)  | timestamp |
| **是否为空** | 不为空（自增） | 不为空  |  不为空  |  不为空   |

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

```

### 评论系统表设计

#### 表名：comments

|    字段名    |                      comment_id                      |       movie_id       |                     parent_id                      |   content    |      user_id       |     date     |
| :----------: | :--------------------------------------------------: | :------------------: | :------------------------------------------------: | :----------: | :----------------: | :----------: |
| **字段作用** | 评论的id，自增值，每个评论都对应一个唯一的comment_id | 评论所对应的电影的id | 指向父评论的id,如果不是对评论的回复,那么该值为null |  评论的内容  | 发出该评论用户的id | 评论产生日期 |
| **是否主键** |                         true                         |        false         |                       flase                        |    false     |       false        |    false     |
| **类型大小** |                       int(20)                        |       int(20)        |                      int(20)                       | varchar(512) |      int(20)       |  timestamp   |
| **是否为空** |                    不为空（自增）                    |        不为空        |                       不为空                       |    不为空    |       不为空       |    不为空    |



```sql
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

评论系统的设计思路：

```http
https://www.cnblogs.com/godlovesme/p/10708358.html
```






#### 参与贡献

1.  Fork 本仓库
2.  新建 你自己的分支
3.  提交代码
4.  新建 Pull Request