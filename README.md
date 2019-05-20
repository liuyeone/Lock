# Lock

```
create table account_wallet
(
   id                   int not null comment '用户钱包主键',
   user_open_id         varchar(64) comment '用户中心的用户唯一编号',
   user_amount          decimal(10,5),
   create_time          datetime,
   update_time          datetime,
   pay_password         varchar(64),
   is_open              int comment '0:代表未开启支付密码，1:代表开发支付密码',
   check_key            varchar(64) comment '平台进行用户余额更改时，首先效验key值，否则无法进行用户余额更改操作',
   version              int comment '基于mysql乐观锁，解决并发访问',
   primary key (id)
);
```