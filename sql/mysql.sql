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

VERSION                                                                                             not null comment '业务版本',
CREATE_TIME                                                                                             null comment '创建时间',
CREATE_USER_NAME                     varchar(32)             character set utf8 collate utf8_bin        null comment '创建账号',
MODIFY_TIME                                                                                             null comment '修改时间',
MODIFY_USER_NAME                     varchar(32)             character set utf8 collate utf8_bin        null comment '修改账号',

primary key(ACCOUNT_ID)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '系统帐号';

/*==============================================================*/
  --  用户信息      WCT_USER_INFO   --
/*==============================================================*/
rop table if exists WCT_USER_INFO;
create table WCT_USER_INFO(
OPEN_ID                              char(36)                character set utf8 collate utf8_bin    not null comment '用户编号',
NICK_NAME                            varchar(128)            character set utf8 collate utf8_bin    not null comment '用户昵称',
GENDER                               char(1)                 character set utf8 collate utf8_bin    not null comment '用户性别',
CITY                                 varchar(32)             character set utf8 collate utf8_bin    not null comment '用户城市',
PROVINCE                             varchar(32)             character set utf8 collate utf8_bin    not null comment '用户省会',
AVATAR_URL                           varchar(255)            character set utf8 collate utf8_bin    not null comment '头像路径',
UNION_ID                             char(36)                character set utf8 collate utf8_bin    not null comment '统一标识',
primary key(OPEN_ID)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '用户信息';