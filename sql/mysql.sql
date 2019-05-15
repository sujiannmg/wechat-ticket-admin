/*
 * wct-wechat_ticket
 */
CREATE DATABASE wct DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
use wct;
/*==============================================================*/
  --  系统帐号      WCT_ACCOUNT_INFO   --
/*==============================================================*/
drop table if exists WCT_ACCOUNT_INFO;
create table WCT_ACCOUNT_INFO(
ACCOUNT_ID                           char(36)                character set utf8 collate utf8_bin    not null comment '系统帐号编号',
ACCOUNT                              varchar(32)             character set utf8 collate utf8_bin    not null comment '系统帐号',
PASSWORD                             char(32)                character set utf8 collate utf8_bin    not null comment '系统密码',
DESCRIPTION                          varchar(255)            character set utf8 collate utf8_bin        null comment '系统描述',

VERSION                              int                                                                null comment '业务版本',
CREATE_TIME                          varchar(32)             character set utf8 collate utf8_bin        null comment '创建时间', --新建
CREATE_USERNAME                      varchar(32)             character set utf8 collate utf8_bin        null comment '创建账号',
MODIFY_TIME                          varchar(32)             character set utf8 collate utf8_bin        null comment '修改时间', --更改
MODIFY_USERNAME                      varchar(32)             character set utf8 collate utf8_bin        null comment '修改账号',
primary key(ACCOUNT_ID)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '系统帐号';

/*==============================================================*/
  --  微信用户信息      WCT_USER_INFO   --
/*==============================================================*/
drop table if exists WCT_USER_INFO;
create table WCT_USER_INFO(
OPEN_ID                              char(36)                character set utf8 collate utf8_bin    not null comment '用户编号',
NICK_NAME                            varchar(128)            character set utf8 collate utf8_bin    not null comment '用户昵称',
GENDER                               char(1)                 character set utf8 collate utf8_bin    not null comment '用户性别',
CITY                                 varchar(32)             character set utf8 collate utf8_bin    not null comment '用户城市',
PROVINCE                             varchar(32)             character set utf8 collate utf8_bin    not null comment '用户省会',
AVATAR_URL                           varchar(255)            character set utf8 collate utf8_bin    not null comment '头像路径',
UNION_ID                             char(36)                character set utf8 collate utf8_bin    not null comment '统一标识',

VERSION                              int                                                                null comment '业务版本',
CREATE_TIME                          varchar(32)             character set utf8 collate utf8_bin        null comment '新建时间',
CREATE_USERNAME                      varchar(32)             character set utf8 collate utf8_bin        null comment '新建账号',
MODIFY_TIME                          varchar(32)             character set utf8 collate utf8_bin        null comment '更改时间',
MODIFY_USERNAME                      varchar(32)             character set utf8 collate utf8_bin        null comment '更改账号',
primary key(OPEN_ID)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '微信用户信息';

/*==============================================================*/
  --  电影接口信息      WCT_MOVIE_INTERFACE_INFO   --
/*==============================================================*/
drop table if exists WCT_MOVIE_INTERFACE_INFO;
create table WCT_MOVIE_INTERFACE_INFO(
MOVIE_INTERFACE_ID                   char(36)                character set utf8 collate utf8_bin    not null comment '电影接口编号',
MOVIE_INTERFACE_NAME                 varchar(128)            character set utf8 collate utf8_bin    not null comment '电影接口名称',
MOVIE_INTERFACE_TYPE                 varchar(16)             character set utf8 collate utf8_bin    not null comment '电影接口类型',
MOVIE_INTERFACE_URL                  varchar(128)            character set utf8 collate utf8_bin    not null comment '电影接口地址',
MOVIE_INTERFACE_PAPRM                varchar(8)              character set utf8 collate utf8_bin        null comment '电影接口参数', --有--无
MOVIE_INTERFACE_ASCII                varchar(8)              character set utf8 collate utf8_bin        null comment '电影接口编码', --UTF-8
MOVIE_INTERFACE_CODE                 int                                                                null comment '接口响应状态',
MOVIE_REPONSE_LENGTH                 int                                                                null comment '响应内容长度',
MOVIE_INTERFACE_CONTENT              text                    character set utf8 collate utf8_bin        null comment '接口响应内容',

MOVIE_REPONSE_NUM                    int                                                                null comment '调用接口次数',
MOVIE_REPONSE_TIME                   varchar(32)             character set utf8 collate utf8_bin        null comment '调用接口时间',

VERSION                              int                                                                null comment '业务版本',
CREATE_TIME                          varchar(32)             character set utf8 collate utf8_bin        null comment '新建时间',
CREATE_USERNAME                      varchar(32)             character set utf8 collate utf8_bin        null comment '新建账号',
MODIFY_TIME                          varchar(32)             character set utf8 collate utf8_bin        null comment '更改时间',
MODIFY_USERNAME                      varchar(32)             character set utf8 collate utf8_bin        null comment '更改账号',
primary key(MOVIE_INTERFACE_ID)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '电影接口信息';

/*==============================================================*/
  --  影院接口信息      WCT_CINEMA_INTERFACE_INFO   --
/*==============================================================*/
drop table if exists WCT_CINEMA_INTERFACE_INFO;
create table WCT_CINEMA_INTERFACE_INFO(
CINEMA_INTERFACE_ID                  char(36)                character set utf8 collate utf8_bin    not null comment '影院接口编号',
CINEMA_INTERFACE_NAME                varchar(128)            character set utf8 collate utf8_bin    not null comment '影院接口名称',
CINEMA_INTERFACE_TYPE                varchar(16)             character set utf8 collate utf8_bin    not null comment '影院接口类型',
CINEMA_INTERFACE_URL                 varchar(128)            character set utf8 collate utf8_bin    not null comment '影院接口地址',
CINEMA_INTERFACE_PAPRM               varchar(8)              character set utf8 collate utf8_bin        null comment '影院接口参数', --有--无
CINEMA_INTERFACE_ASCII               varchar(8)              character set utf8 collate utf8_bin        null comment '影院接口编码', --UTF-8
CINEMA_INTERFACE_CODE                int                                                                null comment '接口响应状态',
CINEMA_REPONSE_LENGTH                int                                                                null comment '响应内容长度',
CINEMA_INTERFACE_CONTENT             text                    character set utf8 collate utf8_bin        null comment '接口响应内容',

CINEMA_REPONSE_NUM                   int                                                                null comment '调用接口次数',
CINEMA_REPONSE_TIME                  varchar(32)             character set utf8 collate utf8_bin        null comment '调用接口时间',

VERSION                              int                                                                null comment '业务版本',
CREATE_TIME                          varchar(32)             character set utf8 collate utf8_bin        null comment '新建时间',
CREATE_USERNAME                      varchar(32)             character set utf8 collate utf8_bin        null comment '新建账号',
MODIFY_TIME                          varchar(32)             character set utf8 collate utf8_bin        null comment '更改时间',
MODIFY_USERNAME                      varchar(32)             character set utf8 collate utf8_bin        null comment '更改账号',
primary key(CINEMA_INTERFACE_ID)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '影院接口信息';

/*==============================================================*/
  --  影评接口信息      WCT_COMMENT_INTERFACE_INFO   --
/*==============================================================*/
drop table if exists WCT_COMMENT_INTERFACE_INFO;
create table WCT_COMMENT_INTERFACE_INFO
COMMENT_INTERFACE_ID                 char(36)                character set utf8 collate utf8_bin    not null comment '影评接口编号',
COMMENT_INTERFACE_NAME               varchar(128)            character set utf8 collate utf8_bin    not null comment '影评接口名称',
COMMENT_INTERFACE_TYPE               varchar(16)             character set utf8 collate utf8_bin    not null comment '影评接口类型',
COMMENT_INTERFACE_URL                varchar(128)            character set utf8 collate utf8_bin    not null comment '影评接口地址',
COMMENT_INTERFACE_PAPRM              varchar(8)              character set utf8 collate utf8_bin        null comment '影评接口参数', --有--无
COMMENT_INTERFACE_ASCII              varchar(8)              character set utf8 collate utf8_bin        null comment '影评接口编码', --UTF-8
COMMENT_INTERFACE_CODE               int                                                                null comment '接口响应状态',
COMMENT_REPONSE_LENGTH               int                                                                null comment '响应内容长度',
COMMENT_INTERFACE_CONTENT            text                    character set utf8 collate utf8_bin        null comment '接口响应内容',

COMMENT_REPONSE_NUM                  int                                                                null comment '调用接口次数',
COMMENT_REPONSE_TIME                 varchar(32)             character set utf8 collate utf8_bin        null comment '调用接口时间',

VERSION                              int                                                                null comment '业务版本',
CREATE_TIME                          varchar(32)             character set utf8 collate utf8_bin        null comment '新建时间',
CREATE_USERNAME                      varchar(32)             character set utf8 collate utf8_bin        null comment '新建账号',
MODIFY_TIME                          varchar(32)             character set utf8 collate utf8_bin        null comment '更改时间',
MODIFY_USERNAME                      varchar(32)             character set utf8 collate utf8_bin        null comment '更改账号',
primary key(CINEMA_INTERFACE_ID)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '影评接口信息';
