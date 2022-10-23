/*商品信息数据库*/
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product`  (
  `pid` int(20) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品标题',
  `sell_point` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品详情',
  `price` bigint(20) NULL DEFAULT NULL COMMENT '商品单价',
  `num` int(10) NULL DEFAULT NULL COMMENT '库存数量',
  `image` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `status` int(1) NULL DEFAULT 1 COMMENT '商品状态  1：上架   2：下架   3：删除',
  `priority` int(10) NULL DEFAULT NULL COMMENT '热度',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime(0) NULL DEFAULT NULL COMMENT '最后修改时间',
  `created_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `modified_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES (1, 'vivo X Fold+ 12GB+256GB 梧桐灰', '2K+120Hz E5折叠巨幕|80W双电池闪充|双屏幕指纹|第一代骁龙8+', 9999, 699, 'assets/images/products/medium-product/vivo/vivoXFold/', 1, 87, '2022-06-25 15:08:55', '2022-06-25 15:08:55', 'admin', 'admin');
INSERT INTO `t_product` VALUES (2, 'iQOO 10 Pro 12GB+256GB 传奇版', '200W 超快闪充|自研芯片 V1+|第一代骁龙 8+|2K E5 LTPO 屏幕|超声波 3D 广域指纹', 5499, 599, 'assets/images/products/medium-product/vivo/iQOO10/', 1, 99, '2021-10-25 15:08:55', '2021-10-25 15:08:55', 'admin', 'admin');
INSERT INTO `t_product` VALUES (3, 'vivo S15 Pro 12GB+256GB 盛夏', '80W双芯闪充|双芯旗舰性能|索尼定制大底主摄', 1899, 799, 'assets/images/products/medium-product/vivo/S15_Pro/', 1, 55, '2022-07-25 15:08:55', '2022-07-25 15:08:55', 'admin', 'admin');
INSERT INTO `t_product` VALUES (4, 'HUAWEI MateBook X Pro 微绒典藏版 英特尔Evo 12代酷睿 i7 16GB 1TB 14.2英寸3.1K原色全面屏 墨蓝', '80W双芯闪充|双芯旗舰性能|索尼定制大底主摄', 12799, 899, 'assets/images/products/medium-product/huawei/HUAWEI_MateBook_X_Pro/', 1, 180, '2021-06-25 15:08:55', '2021-06-25 15:08:55', 'admin', 'admin');
INSERT INTO `t_product` VALUES (5, 'HUAWEI P50 Pocket 8GB+256GB 晶钻白 鸿蒙手机', '新一代水滴铰链设计', 8388, 899, 'assets/images/products/medium-product/huawei/HUAWEI_P50/', 1, 66, '2023-10-25 15:08:55', '2023-10-25 15:08:55', 'admin', 'admin');
INSERT INTO `t_product` VALUES (6, '【新品丨含66W快充套装】HUAWEI nova 10 256GB（10号色）鸿蒙手机', ' 【现货速发 : V1及以上会员领券优惠50元+赠华为经典耳机】① 学生认证再领50元券 | 点击了解更多手机>>]前置6000万超广角镜头', 2999, 799, 'assets/images/products/medium-product/huawei/nova10/', 1, 77, '2021-01-25 15:08:55', '2021-01-25 15:08:55', 'admin', 'admin');
INSERT INTO `t_product` VALUES (7, 'HUAWEI Mate 50 RS 保时捷设计 512GB （墨蓝瓷）', ' 新一代保时捷设计：致敬时代 每周一/三/五限量抢购 ', 12999, 899, 'assets/images/products/medium-product/huawei/HUAWEI_Mate50_RS/', 1, 44, '2022-01-25 15:08:55', '2022-01-25 15:08:55', 'admin', 'admin');
INSERT INTO `t_product` VALUES (8, 'HUAWEI MatePad 11 6GB+256GB 曜石灰', '后置摄像头1300万像素摄像头，f/1.8光圈，支持自动对焦 ，高通骁龙™865', 3999, 499, 'assets/images/products/medium-product/huawei/HUAWEI_MatePad_11/', 1, 120, '2022-02-25 15:08:55', '2022-02-25 15:08:55', 'admin', 'admin');
INSERT INTO `t_product` VALUES (9, 'HUAWEI MatePad Pro 11英寸 2022 8+128GB 曜金黑', '后置摄像头1300万，F1.8光圈，自动对焦；8M，F2.2光圈，固定焦距, 高通骁龙™ 888', 3999, 499, 'assets/images/products/medium-product/huawei/HUAWEI_MatePad_Pro/', 1, 76, '2022-03-25 15:08:55', '2022-03-25 15:08:55', 'admin', 'admin');
INSERT INTO `t_product` VALUES (10, 'iQOO Z6 12GB+256GB 星海', '双电芯 80W 闪充|6400万像素 OIS 光学防抖|骁龙778G Plus 性能铁三角|120Hz 原色护眼屏', 2099, 499, 'assets/images/products/medium-product/vivo/iQOOZ6/', 1, 76, '2022-04-25 15:08:55', '2022-04-25 15:08:55', 'admin', 'admin');
INSERT INTO `t_product` VALUES (11, 'vivo T1x 8GB+128GB 虹彩', '120Hz流畅护眼屏|44W闪充|6400万超清影像系统|天玑900', 1599, 623, 'assets/images/products/medium-product/vivo/vivoT1x/', 1, 58, '2024-10-25 15:08:55', '2024-10-25 15:08:55', 'admin', 'admin');
INSERT INTO `t_product` VALUES (12, 'vivo X70 Pro+ 12GB+256GB 至黑', '全四摄光学防抖|陶瓷云窗|IP68级防尘抗水性能', 6999, 489, 'assets/images/products/medium-product/vivo/vivoX70Pro/', 1, 58, '2020-11-25 15:08:55', '2022-11-25 15:08:55', 'admin', 'admin');
INSERT INTO `t_product` VALUES (13, 'vivo T2x 8GB+256GB 雾蓝', '天玑1300芯片|144Hz变速高刷护眼屏|6000mAh巨能量电池', 1899, 487, 'assets/images/products/medium-product/vivo/vivoT2x/', 1, 77, '2019-11-25 15:08:55', '2022-11-25 15:08:55', 'admin', 'admin');

SET FOREIGN_KEY_CHECKS = 1;
