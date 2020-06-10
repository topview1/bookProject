/*
 Navicat Premium Data Transfer

 Source Server         : chen
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : bookproject

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 10/06/2020 09:57:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem`  (
  `order_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单id，关联orders表中的主键',
  `product_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品id，关联products表中的主键',
  `buynum` int(11) NULL DEFAULT NULL COMMENT '单个商品的购买数量',
  PRIMARY KEY (`order_id`, `product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('5d42200c-744b-4ebc-bdbf-265d222363e2', '2', 1);
INSERT INTO `orderitem` VALUES ('5d42200c-744b-4ebc-bdbf-265d222363e2', 'd0b0a073-ad30-4cb1-a40a-dfe251f732bd', 2);
INSERT INTO `orderitem` VALUES ('b011a87c-dab1-4762-8398-8940fbe27902', '54a42f7a-af8e-454f-a611-6f38543e961d', 2);
INSERT INTO `orderitem` VALUES ('b011a87c-dab1-4762-8398-8940fbe27902', 'a26394ad-13f3-4967-8a70-981a99eb1140', 1);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单id',
  `money` double(255, 0) NULL DEFAULT NULL COMMENT '订单价格',
  `receiverAddress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货地址',
  `receiverName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人姓名',
  `receiverPhone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收货人电话',
  `paystate` int(11) NULL DEFAULT 0 COMMENT '订单状态：1.已经支付 ，0未支付',
  `ordertime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单生成时间',
  `use_id` int(11) NULL DEFAULT NULL COMMENT '用户id,关联user表中的主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('5d42200c-744b-4ebc-bdbf-265d222363e2', 134, '天堂', 'cyw', '15623696627', 1, '2020-05-30 19:13:31', 18);
INSERT INTO `orders` VALUES ('b011a87c-dab1-4762-8398-8940fbe27902', 118, '宜昌', '张三', '15171890940', 0, '2020-06-05 15:00:49', 20);

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品id',
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品名称',
  `price` double(10, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `category` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品分类',
  `pnum` int(11) NULL DEFAULT NULL COMMENT '商品库存',
  `imgurl` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片地址',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES ('185bdc19-f5cd-4dfa-8e45-3d37360f4699', '被讨厌的勇气', 45.90, '文学', 21, '/productImg/5db657ff-6f72-4906-987e-b07abf64a329.jpg', '所谓的勇气，就是被别人讨厌');
INSERT INTO `products` VALUES ('2', '美国摄影教材', 45.20, '外语', 9, '/productImg/foreignLanguage/20788b05-d298-4a7c-91d9-370fb056f6a5.jpg', '要想阅读他必须有英语功底');
INSERT INTO `products` VALUES ('3', 'The TRAVEL BOOK', 22.50, '外语', 33, '/productImg/foreignLanguage/5394df32-ed6e-4203-b9c0-fc175cfc187e.jpg', '一本时尚杂志');
INSERT INTO `products` VALUES ('4', '时空穿行', 60.30, '文学', 10, '/productImg/literature/0aafeea2-b2bf-49d8-8153-ff94efcc5ead.jpg', '在时空里探寻秘密');
INSERT INTO `products` VALUES ('5', '别做正常的傻瓜', 20.90, '文学', 12, '/productImg/literature/6d3c7635-545a-46b3-bd3e-d2d74f11ed69.jpg', '受益匪浅');
INSERT INTO `products` VALUES ('54a42f7a-af8e-454f-a611-6f38543e961d', 'Python实践', 36.70, '计算机', 56, '/productImg/f2f53b13-3da4-4159-ab38-2a160f05c61c.jpg', 'python变成，零基础入门教程');
INSERT INTO `products` VALUES ('6', '学会宽容', 27.60, '文学', 7, '/productImg/literature/a2da626c-c72d-4972-83de-cf48405c5563.jpg', '畅销书，讲述一个恶徒和警察的故事');
INSERT INTO `products` VALUES ('7', '杜拉拉升职记', 40.20, '文学', 50, '/productImg/literature/c4ab442f-95c7-4d6f-a57e-3eb7dc6b83c4.jpg', '职场必读');
INSERT INTO `products` VALUES ('8', '中国地理', 22.30, '考试', 20, '/productImg/test/2105fbe5-400f-4193-a7db-d7ebac389550.jpg', '考试重点书籍');
INSERT INTO `products` VALUES ('80cddc42-71fe-496d-9987-1fe2edcedeee', '深入理解java虚拟机', 70.90, '计算机', 104, '/productImg/ac2c9dcf-1ecf-4fa8-8176-7e21a25c921a.jpg', '5个维度全面解析jvm');
INSERT INTO `products` VALUES ('9', '系统分析教材', 40.90, '考试', 33, '/productImg/test/e4d290ce-3355-466f-a51e-13c62552d2cb.jpg', '软考必备');
INSERT INTO `products` VALUES ('99c5779b-406f-484b-9d6e-5d50b40bd418', 'Head First Java', 48.30, '计算机', 55, '/productImg/1e393c3c-fe99-4bf4-86ca-4fe70d96fc9c.jpg', '重视大脑的学习指南');
INSERT INTO `products` VALUES ('a26394ad-13f3-4967-8a70-981a99eb1140', '越自律越自由', 44.51, '经管', 23, '/productImg/61a8aa67-decc-4354-9208-5de832552447.jpg', '百万畅销书作家晚晴新作');
INSERT INTO `products` VALUES ('bbe14eed-e4b6-48d6-8758-f6f75841bf19', '金阁寺', 49.50, '经管', 35, '/productImg/1efea593-0812-4fc0-bc50-f61a6147c8d5.jpg', '三岛由纪夫力作');
INSERT INTO `products` VALUES ('d0b0a073-ad30-4cb1-a40a-dfe251f732bd', 'java基础入门', 45.90, '计算机', 24, '/productImg/833504d2-bd4a-49c9-b8c3-3d3beeff8c92.jpg', 'java入门书');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统自动编号，递增',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `gender` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  `telephone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话号码',
  `introduce` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户简介',
  `activeCode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '注册激活码',
  `state` int(11) NULL DEFAULT 1 COMMENT '用户状态1：激活 2：未激活',
  `role` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '普通用户' COMMENT '用户角色：普通用户，超级用户',
  `registTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456', '女', '1294273640@qq.com', '15171837057', '一个人', NULL, 1, '超级用户', '2020-06-04 16:30:38');
INSERT INTO `user` VALUES (18, 'cyw', '123456', '男', '1019571862@qq.com', '15271890940', '我所有', NULL, 1, '普通用户', '2020-05-25 20:16:43');
INSERT INTO `user` VALUES (20, '张三', '123456', '男', '1019571863@163.com', '15171890940', '不羁的男子', NULL, 1, '普通用户', '2020-06-05 14:59:50');

SET FOREIGN_KEY_CHECKS = 1;
