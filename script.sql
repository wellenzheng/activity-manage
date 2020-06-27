create table activity
(
    id               int auto_increment comment '主键'
        primary key,
    name             varchar(255) default ''                not null comment '活动名称',
    type             varchar(255) default 'INDEPENDENT'     not null comment '活动类型：独立，系统',
    description      text                                   not null comment '活动描述文本',
    status           varchar(255) default 'UNPUBLISHED'     not null comment '活动状态：未发布，已运行N天，已结束',
    limit_type       varchar(255) default 'EVERYDAY'        not null comment '抽奖次数限制类型：每日限定，全程限定',
    limit_times      int          default 2                 not null comment '抽奖次数限制',
    background_image varchar(255) default ''                null comment '活动背景图url',
    prize_ids        varchar(255) default ''                not null comment '奖品id：使用逗号分隔',
    auth_user_id     int                                    not null comment '创建者id',
    create_time      datetime     default CURRENT_TIMESTAMP not null comment '活动创建时间',
    start_time       datetime     default CURRENT_TIMESTAMP not null comment '活动开始时间',
    end_time         datetime     default CURRENT_TIMESTAMP not null comment '活动结束时间',
    url              varchar(255) default ''                not null comment '活动链接'
)
    comment '活动主表';

create table auth_user
(
    id       int auto_increment comment '管理员id'
        primary key,
    username varchar(255) not null comment '管理员用户名',
    password varchar(255) not null comment '登陆密码',
    name     varchar(255) not null comment '管理员名字',
    email    varchar(255) not null comment '电子邮箱',
    role     varchar(255) not null comment '管理员角色',
    constraint auth_user_email_uindex
        unique (email),
    constraint auth_user_username_uindex
        unique (username)
)
    comment '管理员表';

create table prize
(
    id               int auto_increment comment '奖品id'
        primary key,
    activity_id      int          not null comment '所属活动id',
    ranking          varchar(255) not null comment '奖品等级',
    name             varchar(255) not null comment '奖品名称',
    total_number     int          not null comment '奖品总数',
    collected_number int          not null comment '已领取数量',
    probability      double       not null comment '中奖概率',
    isLucky          tinyint(1)   not null comment '是否中奖'
)
    comment '活动奖品';

create table record
(
    id          int auto_increment comment '活动记录id'
        primary key,
    type        varchar(255)                       not null comment '活动记录类型：抽奖，报名，拼团，砍价',
    activity_id int                                not null comment '所属活动id',
    prize_id    int                                null comment '奖品id：若无奖品则为空',
    user_id     int                                not null comment '记录对应的用户id',
    date        datetime default CURRENT_TIMESTAMP not null comment '记录产生时间'
)
    comment '活动记录';

create table statistics
(
    id          int auto_increment comment '统计数据id'
        primary key,
    activity_id int          not null comment '所属活动id',
    type        varchar(255) not null comment '统计类型：浏览，参与，获奖，分享',
    number      int          null comment '统计人数',
    date        datetime     not null comment '统计日期'
);

create table user
(
    id          int auto_increment comment '用户id'
        primary key,
    wechat_id   varchar(255)          not null comment '微信号',
    wechat_name varchar(255) null comment '微信名',
    name        varchar(255) null comment '真实姓名',
    phone       varchar(13)  null comment '电话号码',
    email       varchar(255) null comment '电子邮箱',
    address     varchar(255) null comment '发货地址',
    constraint user_wechat_id_uindex
        unique (wechat_id)
)
    comment '用户表：管理员和普通用户';


