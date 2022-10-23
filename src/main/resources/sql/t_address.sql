/*收货地址数据库*/
use takeaway;
drop table if exists t_address;
CREATE TABLE t_address(
  aid INT AUTO_INCREMENT COMMENT '收货地址id',
  uid INT COMMENT '用户的id',
  name VARCHAR(20) COMMENT '收货人的姓名',
  province_name VARCHAR(15) COMMENT '省',
  city_name VARCHAR(15) COMMENT '市',
  area_name VARCHAR(15) COMMENT '县/区',
  address VARCHAR(50) COMMENT '详细地址',
  zip CHAR(6) COMMENT '邮政编码',
  phone VARCHAR(20) COMMENT '手机',
  is_default INT COMMENT '是否默认:0-不默认,1-默认',
  created_user VARCHAR(20) COMMENT '创建人',
  created_time DATETIME COMMENT '创建时间',
  modified_user VARCHAR(20) COMMENT '修改人',
  modified_time DATETIME COMMENT '修改时间',
  PRIMARY KEY (aid)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;