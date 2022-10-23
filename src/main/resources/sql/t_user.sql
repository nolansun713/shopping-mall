/*用户信息数据库*/
use takeaway;
drop table if exists t_user;
create table t_user(
   uid INT NOT NULL AUTO_INCREMENT COMMENT '用户id',
   username VARCHAR(20) NOT NULL UNIQUE COMMENT '用户名称',
   password CHAR(32) NOT NULL COMMENT '用户密码',
   salt CHAR(36) COMMENT '加密盐值',
   phone VARCHAR(20) COMMENT '电话',
   email VARCHAR(20) NOT NULL UNIQUE COMMENT '邮箱',
   is_delete INT COMMENT '是否删除:0-未删除,1-删除',
   created_user VARCHAR(20) COMMENT '创建人',
   created_time DATETIME COMMENT '创建时间',
   modified_user VARCHAR(20) COMMENT '修改人',
   modified_time DATETIME COMMENT '修改时间',
   PRIMARY KEY (uid)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;