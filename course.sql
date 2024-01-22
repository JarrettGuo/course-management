/*
 Navicat Premium Data Transfer

 Source Server         : 选课管理系统
 Source Server Type    : MySQL
 Source Server Version : 50650
 Source Host           : 121.5.75.29:3306
 Source Schema         : course

 Target Server Type    : MySQL
 Target Server Version : 50650
 File Encoding         : 65001

 Date: 23/12/2021 23:19:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '删除学院旗下学生也会删除',
  `college` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`college`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES (61, '国交学院');
INSERT INTO `college` VALUES (62, '电信学院');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `colleges` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teachname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `star` int(11) NULL DEFAULT 0,
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `co`(`colleges`) USING BTREE,
  INDEX `bo_1`(`teachname`) USING BTREE,
  INDEX `cname`(`cname`) USING BTREE,
  INDEX `tem`(`id`) USING BTREE,
  CONSTRAINT `bo_1` FOREIGN KEY (`teachname`) REFERENCES `teacher` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `co` FOREIGN KEY (`colleges`) REFERENCES `college` (`college`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tem` FOREIGN KEY (`id`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('国交学院', '朱小晋', 'Java项目实训', 1, '12021611');
INSERT INTO `course` VALUES ('国交学院', '朱小晋', 'Python程序设计', 0, '12021611');
INSERT INTO `course` VALUES ('国交学院', '林小勇', '数据库设计与应用', 1, '12021612');

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `college` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学院',
  `proname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专业名称',
  PRIMARY KEY (`proname`) USING BTREE,
  UNIQUE INDEX `id_name`(`id`) USING BTREE COMMENT '唯一',
  INDEX `co_1`(`college`) USING BTREE,
  CONSTRAINT `co_1` FOREIGN KEY (`college`) REFERENCES `college` (`college`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES (36, '国交学院', '会计学');
INSERT INTO `major` VALUES (35, '国交学院', '信息与计算科学');
INSERT INTO `major` VALUES (37, '国交学院', '机械制造及其自动化');
INSERT INTO `major` VALUES (39, '电信学院', '网络工程');
INSERT INTO `major` VALUES (38, '电信学院', '计算机科学与技术');

-- ----------------------------
-- Table structure for optcous
-- ----------------------------
DROP TABLE IF EXISTS `optcous`;
CREATE TABLE `optcous`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程编码',
  `college` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学院',
  `teanum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '老师工号',
  `teacher` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '老师',
  `coursname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名字',
  `clstime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '时间',
  `numpeo` int(11) NULL DEFAULT 0 COMMENT '人数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `co_3`(`college`) USING BTREE,
  INDEX `t_1`(`teacher`) USING BTREE,
  INDEX `t_2`(`coursname`) USING BTREE,
  INDEX `t_4`(`teanum`) USING BTREE,
  CONSTRAINT `co_3` FOREIGN KEY (`college`) REFERENCES `college` (`college`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_1` FOREIGN KEY (`teacher`) REFERENCES `teacher` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_2` FOREIGN KEY (`coursname`) REFERENCES `course` (`cname`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_4` FOREIGN KEY (`teanum`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 12021546 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of optcous
-- ----------------------------
INSERT INTO `optcous` VALUES (12021544, '国交学院', '12021611', '朱小晋', 'Java项目实训', '第一大节', 1);
INSERT INTO `optcous` VALUES (12021545, '国交学院', '12021612', '林小勇', '数据库设计与应用', '第二大节', 1);

-- ----------------------------
-- Table structure for studengcours
-- ----------------------------
DROP TABLE IF EXISTS `studengcours`;
CREATE TABLE `studengcours`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `college` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学院',
  `pro` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '专业',
  `optcouse` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选课的名称',
  `count` int(11) NULL DEFAULT NULL COMMENT '分数',
  `stunum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学号',
  `cousid` int(11) NULL DEFAULT NULL COMMENT '课程ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `stu_s`(`stunum`) USING BTREE,
  INDEX `cou_s`(`cousid`) USING BTREE,
  INDEX `co_4`(`college`) USING BTREE,
  INDEX `t_5`(`optcouse`) USING BTREE,
  INDEX `t_6`(`pro`) USING BTREE,
  CONSTRAINT `co_4` FOREIGN KEY (`college`) REFERENCES `college` (`college`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cou_s` FOREIGN KEY (`cousid`) REFERENCES `optcous` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stu_s` FOREIGN KEY (`stunum`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_5` FOREIGN KEY (`optcouse`) REFERENCES `course` (`cname`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_6` FOREIGN KEY (`pro`) REFERENCES `student` (`peojob`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 175 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of studengcours
-- ----------------------------

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `oncollege` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `peojob` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `inyear` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `co_5`(`oncollege`) USING BTREE,
  INDEX `peojob`(`peojob`) USING BTREE,
  CONSTRAINT `co_5` FOREIGN KEY (`oncollege`) REFERENCES `college` (`college`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `q_8` FOREIGN KEY (`peojob`) REFERENCES `major` (`proname`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('2021612', '叶汪洋', '国交学院', '信息与计算科学', '男', '2021年');
INSERT INTO `student` VALUES ('2021613', '郭佳俊', '国交学院', '信息与计算科学', '男', '2021年');
INSERT INTO `student` VALUES ('2021614', '王力宏', '国交学院', '会计学', '男', '2021年');
INSERT INTO `student` VALUES ('2021615', '吴亦凡', '国交学院', '机械制造及其自动化', '男', '2021年');
INSERT INTO `student` VALUES ('2021622', '罗志祥', '电信学院', '计算机科学与技术', '男', '2021年');
INSERT INTO `student` VALUES ('2021623', 'Donald Trump', '电信学院', '网络工程', '男', '2021年');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `oncollege` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `edu` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `inyear` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `co_6`(`oncollege`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  CONSTRAINT `co_6` FOREIGN KEY (`oncollege`) REFERENCES `college` (`college`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('12021611', '朱小晋', '国交学院', '研究生', '男', '2021年');
INSERT INTO `teacher` VALUES ('12021612', '林小勇', '国交学院', '研究生', '男', '2021年');
INSERT INTO `teacher` VALUES ('12021613', '刘小林', '国交学院', '研究生', '女', '2021年');
INSERT INTO `teacher` VALUES ('12021621', '陈小萌', '电信学院', '研究生', '女', '2021年');
INSERT INTO `teacher` VALUES ('12021622', '鲍小娣', '电信学院', '博士生', '女', '2021年');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是数字的表示',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pow` int(11) NULL DEFAULT NULL COMMENT '1是管理员2是老师3是学生',
  PRIMARY KEY (`account`) USING BTREE,
  UNIQUE INDEX `id_one`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 161187 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (161174, '12021591', '12021591', 2);
INSERT INTO `users` VALUES (161176, '12021611', '12021611', 2);
INSERT INTO `users` VALUES (161177, '12021612', '12021612', 2);
INSERT INTO `users` VALUES (161178, '12021613', '12021613', 2);
INSERT INTO `users` VALUES (161179, '12021621', '12021621', 2);
INSERT INTO `users` VALUES (161180, '12021622', '12021622', 2);
INSERT INTO `users` VALUES (161175, '2021592', '2021592', 3);
INSERT INTO `users` VALUES (161181, '2021612', '2021612', 3);
INSERT INTO `users` VALUES (161182, '2021613', '2021613', 3);
INSERT INTO `users` VALUES (161183, '2021614', '2021614', 3);
INSERT INTO `users` VALUES (161184, '2021615', '2021615', 3);
INSERT INTO `users` VALUES (161185, '2021622', '2021622', 3);
INSERT INTO `users` VALUES (161186, '2021623', '2021623', 3);
INSERT INTO `users` VALUES (1, 'root', 'root', 1);

-- ----------------------------
-- Triggers structure for table student
-- ----------------------------
DROP TRIGGER IF EXISTS `demos`;
delimiter ;;
CREATE TRIGGER `demos` BEFORE DELETE ON `student` FOR EACH ROW BEGIN

delete from users where account=old.id;


end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table teacher
-- ----------------------------
DROP TRIGGER IF EXISTS `demo`;
delimiter ;;
CREATE TRIGGER `demo` BEFORE DELETE ON `teacher` FOR EACH ROW BEGIN

delete from users where account=old.id;


end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
