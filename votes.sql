/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50730
Source Host           : localhost:3306
Source Database       : votes

Target Server Type    : MYSQL
Target Server Version : 50730
File Encoding         : 65001

Date: 2023-06-29 21:40:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for t_account
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role_id` int(11) NOT NULL COMMENT '1 管理员 2活动管理员 3观众 4评委',
  `user_name` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_account
-- ----------------------------
INSERT INTO `t_account` VALUES ('1', 'admin', '$2a$10$LemD2ygRAfDBFxx3mh6EcexVLxGDhlH1rytGlHI7syyVLxmhW56Oy', '1', '张三', '13802054478', '212121萨达萨达撒2121212112');
INSERT INTO `t_account` VALUES ('2', 'wangwu', '$2a$10$.9tZgnaEJCeg50ZwA8Brk.vRsoO76jOdlaBB77AWGCzFHkc2cZoTy', '4', '王五', '13802054477', null);
INSERT INTO `t_account` VALUES ('3', 'zhangsan', '$2a$10$C7wHQk78PuImGO2EAg4s0.KbTdmBxYYa4G/F/fBPzmDVxjazalmxW', '3', '张三', '13802054472', null);
INSERT INTO `t_account` VALUES ('4', 'lisi', '$2a$10$C7wHQk78PuImGO2EAg4s0.KbTdmBxYYa4G/F/fBPzmDVxjazalmxW', '2', 'User4', '13802050004', null);
INSERT INTO `t_account` VALUES ('5', 'account5', '$2a$10$C7wHQk78PuImGO2EAg4s0.KbTdmBxYYa4G/F/fBPzmDVxjazalmxW', '3', 'User5', '13802050005', null);
INSERT INTO `t_account` VALUES ('6', 'account6', '$2a$10$C7wHQk78PuImGO2EAg4s0.KbTdmBxYYa4G/F/fBPzmDVxjazalmxW', '3', 'User6', '13802050006', null);
INSERT INTO `t_account` VALUES ('7', 'account7', '$2a$10$C7wHQk78PuImGO2EAg4s0.KbTdmBxYYa4G/F/fBPzmDVxjazalmxW', '3', 'User7', '13802050007', null);
INSERT INTO `t_account` VALUES ('8', 'account8', '$2a$10$C7wHQk78PuImGO2EAg4s0.KbTdmBxYYa4G/F/fBPzmDVxjazalmxW', '3', 'User8', '13802050008', null);
INSERT INTO `t_account` VALUES ('9', 'account9', '$2a$10$C7wHQk78PuImGO2EAg4s0.KbTdmBxYYa4G/F/fBPzmDVxjazalmxW', '3', 'User9', '13802050009', null);
INSERT INTO `t_account` VALUES ('10', 'account10', '$2a$10$C7wHQk78PuImGO2EAg4s0.KbTdmBxYYa4G/F/fBPzmDVxjazalmxW', '3', 'User10', '13802050010', null);
INSERT INTO `t_account` VALUES ('11', 'account11', '$2a$10$C7wHQk78PuImGO2EAg4s0.KbTdmBxYYa4G/F/fBPzmDVxjazalmxW', '3', 'User11', '13802050011', null);
INSERT INTO `t_account` VALUES ('12', 'account12', '$2a$10$C7wHQk78PuImGO2EAg4s0.KbTdmBxYYa4G/F/fBPzmDVxjazalmxW', '3', 'User12', '13802050012', null);
INSERT INTO `t_account` VALUES ('13', 'account13', '$2a$10$C7wHQk78PuImGO2EAg4s0.KbTdmBxYYa4G/F/fBPzmDVxjazalmxW', '3', 'User13', '13802050013', null);
INSERT INTO `t_account` VALUES ('14', 'account14', '$2a$10$C7wHQk78PuImGO2EAg4s0.KbTdmBxYYa4G/F/fBPzmDVxjazalmxW', '3', 'User14', '13802050014', null);
INSERT INTO `t_account` VALUES ('15', 'account15', '$2a$10$C7wHQk78PuImGO2EAg4s0.KbTdmBxYYa4G/F/fBPzmDVxjazalmxW', '3', 'User15', '13802050015', null);
INSERT INTO `t_account` VALUES ('16', 'account16', '$2a$10$C7wHQk78PuImGO2EAg4s0.KbTdmBxYYa4G/F/fBPzmDVxjazalmxW', '3', 'User16', '13802050016', null);
INSERT INTO `t_account` VALUES ('17', 'account17', '$2a$10$C7wHQk78PuImGO2EAg4s0.KbTdmBxYYa4G/F/fBPzmDVxjazalmxW', '3', 'User17', '13802050017', null);
INSERT INTO `t_account` VALUES ('18', 'account18', '$2a$10$C7wHQk78PuImGO2EAg4s0.KbTdmBxYYa4G/F/fBPzmDVxjazalmxW', '3', 'User18', '13802050018', null);
INSERT INTO `t_account` VALUES ('19', 'account19', '$2a$10$C7wHQk78PuImGO2EAg4s0.KbTdmBxYYa4G/F/fBPzmDVxjazalmxW', '3', 'User19', '13802050019', null);
INSERT INTO `t_account` VALUES ('20', 'account20', '$2a$10$C7wHQk78PuImGO2EAg4s0.KbTdmBxYYa4G/F/fBPzmDVxjazalmxW', '3', 'User20', '13802050020', null);

-- ----------------------------
-- Table structure for t_judge_score
-- ----------------------------
DROP TABLE IF EXISTS `t_judge_score`;
CREATE TABLE `t_judge_score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) DEFAULT NULL,
  `judge_id` int(11) DEFAULT NULL,
  `match_id` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_judge_score
-- ----------------------------
INSERT INTO `t_judge_score` VALUES ('7', '1', '1', '116', '10');
INSERT INTO `t_judge_score` VALUES ('8', '32', '1', '116', '10');

-- ----------------------------
-- Table structure for t_match
-- ----------------------------
DROP TABLE IF EXISTS `t_match`;
CREATE TABLE `t_match` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_a` varchar(255) DEFAULT NULL,
  `a_song` varchar(255) DEFAULT NULL,
  `player_b` varchar(255) DEFAULT NULL,
  `b_song` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL COMMENT '1">正在投票中 2">投票已结束 3">投票未开始',
  `player_a_id` int(11) DEFAULT NULL,
  `player_b_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_match
-- ----------------------------
INSERT INTO `t_match` VALUES ('116', '张三', '夏天的风', '赵六', '告白气球', '2', '1', '32');
INSERT INTO `t_match` VALUES ('117', '孙八', '烟花易冷', '张十三', '山楂树之恋', '1', '6', '11');
INSERT INTO `t_match` VALUES ('118', '赵十六', '成全', '吴三十', '那些花儿', '3', '14', '28');
INSERT INTO `t_match` VALUES ('119', '王五', '千年等一回', '李四', '夜曲', '3', '3', '30');
INSERT INTO `t_match` VALUES ('120', '张三', '我的歌声里', '李四', '夜曲', '1', '33', '34');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(255) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'ROLE_admin', '系统管理员');
INSERT INTO `t_role` VALUES ('2', 'ROLE_event_admin', '活动管理员');
INSERT INTO `t_role` VALUES ('3', 'ROLE_viewer', '观众');
INSERT INTO `t_role` VALUES ('4', 'ROLE_judge', '评委');

-- ----------------------------
-- Table structure for t_singer
-- ----------------------------
DROP TABLE IF EXISTS `t_singer`;
CREATE TABLE `t_singer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `real_name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `player_music` varchar(255) DEFAULT NULL,
  `player_opus` varchar(255) DEFAULT NULL,
  `take_state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_singer
-- ----------------------------
INSERT INTO `t_singer` VALUES ('1', '张三', '1', '13888888882', '浪漫', '夏天的风', '0');
INSERT INTO `t_singer` VALUES ('2', '李四', '0', '13999999999', '摇滚', '不要说话', '0');
INSERT INTO `t_singer` VALUES ('3', '王五', '1', '13666666666', '古风', '千年等一回', '1');
INSERT INTO `t_singer` VALUES ('4', '赵六', '1', '13111111111', '民谣', '青春修炼手册', '0');
INSERT INTO `t_singer` VALUES ('5', '钱七', '1', '13222222222', '流行', '追光者', '0');
INSERT INTO `t_singer` VALUES ('6', '孙八', '0', '13333333333', '摇滚', '烟花易冷', '1');
INSERT INTO `t_singer` VALUES ('7', '周九', '0', '13444444444', '古风', '醉赤壁', '0');
INSERT INTO `t_singer` VALUES ('8', '吴十', '0', '13555555555', '民谣', '平凡之路', '0');
INSERT INTO `t_singer` VALUES ('9', '郑十一', '0', '13666666666', '流行', '演员', '0');
INSERT INTO `t_singer` VALUES ('10', '王十二', '0', '13777777777', '摇滚', '光辉岁月', '0');
INSERT INTO `t_singer` VALUES ('11', '张十三', '1', '13888888888', '古风', '山楂树之恋', '1');
INSERT INTO `t_singer` VALUES ('12', '李十四', '1', '13999999999', '民谣', '后来', '0');
INSERT INTO `t_singer` VALUES ('13', '王十五', '1', '13666666666', '流行', '岁月神偷', '0');
INSERT INTO `t_singer` VALUES ('14', '赵十六', '0', '13111111111', '摇滚', '成全', '1');
INSERT INTO `t_singer` VALUES ('15', '钱十七', '0', '13222222222', '古风', '小幸运', '0');
INSERT INTO `t_singer` VALUES ('16', '孙十八', '0', '13333333333', '民谣', '借口', '0');
INSERT INTO `t_singer` VALUES ('17', '周十九', '1', '13444444444', '流行', '丑八怪', '0');
INSERT INTO `t_singer` VALUES ('18', '吴二十', '1', '13555555555', '摇滚', '天空之城', '0');
INSERT INTO `t_singer` VALUES ('19', '郑二十一', '1', '13666666666', '古风', '红昭愿', '0');
INSERT INTO `t_singer` VALUES ('20', '王二十二', '1', '13777777777', '民谣', '青花瓷', '0');
INSERT INTO `t_singer` VALUES ('21', '张二十三', '0', '13888888888', '流行', '一路向北', '0');
INSERT INTO `t_singer` VALUES ('22', '李二十四', '0', '13999999999', '摇滚', '追梦赤子心', '0');
INSERT INTO `t_singer` VALUES ('23', '王二十五', '0', '13666666666', '古风', '红尘客栈', '0');
INSERT INTO `t_singer` VALUES ('24', '赵二十六', '0', '13111111111', '民谣', '晴天', '0');
INSERT INTO `t_singer` VALUES ('25', '钱二十七', '1', '13222222222', '流行', '告白气球', '0');
INSERT INTO `t_singer` VALUES ('26', '孙二十八', '0', '13333333333', '摇滚', '离人愁', '0');
INSERT INTO `t_singer` VALUES ('27', '周二十九', '1', '13444444444', '古风', '夜曲', '0');
INSERT INTO `t_singer` VALUES ('28', '吴三十', '1', '13555555555', '民谣', '那些花儿', '1');
INSERT INTO `t_singer` VALUES ('29', '张三', '1', '13812345678', '流行', '我的歌声里', '0');
INSERT INTO `t_singer` VALUES ('30', '李四', '1', '13987654321', '摇滚', '夜曲', '1');
INSERT INTO `t_singer` VALUES ('31', '王五', '0', '15876543210', '古典', '天空之城', '0');
INSERT INTO `t_singer` VALUES ('32', '赵六', '0', '13598765432', '流行', '告白气球', '0');
INSERT INTO `t_singer` VALUES ('33', '张三', '0', '13812345678', '流行', '我的歌声里', '1');
INSERT INTO `t_singer` VALUES ('34', '李四', '1', '13987654321', '摇滚', '夜曲', '1');
INSERT INTO `t_singer` VALUES ('35', '王五', '1', '15876543210', '古典', '天空之城', '0');
INSERT INTO `t_singer` VALUES ('36', '赵六', '1', '13598765432', '流行', '告白气球', '0');
INSERT INTO `t_singer` VALUES ('37', '张三', '0', '13812345678', '流行', '我的歌声里', '0');
INSERT INTO `t_singer` VALUES ('38', '李四', '1', '13987654321', '摇滚', '夜曲', '0');
INSERT INTO `t_singer` VALUES ('39', '王五', '0', '15876543210', '古典', '天空之城', '0');
INSERT INTO `t_singer` VALUES ('40', '赵六', '1', '13598765432', '流行', '告白气球', '0');
INSERT INTO `t_singer` VALUES ('44', '张三', '0', '13888888881', '浪漫', '夏天的风', '0');
INSERT INTO `t_singer` VALUES ('45', '李世思', '0', '123456', '浪漫', '夏天的风', '0');

-- ----------------------------
-- Table structure for t_sum_score
-- ----------------------------
DROP TABLE IF EXISTS `t_sum_score`;
CREATE TABLE `t_sum_score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) DEFAULT NULL,
  `player_opus` varchar(255) DEFAULT NULL,
  `match_id` int(11) DEFAULT NULL,
  `sum_score` int(11) DEFAULT NULL,
  `player_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sum_score
-- ----------------------------
INSERT INTO `t_sum_score` VALUES ('7', '1', '夏天的风', '116', '11', '张三');
INSERT INTO `t_sum_score` VALUES ('8', '32', '告白气球', '116', '10', '赵六');

-- ----------------------------
-- Table structure for t_vote_count
-- ----------------------------
DROP TABLE IF EXISTS `t_vote_count`;
CREATE TABLE `t_vote_count` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `match_id` int(11) DEFAULT NULL,
  `player_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_vote_count
-- ----------------------------
INSERT INTO `t_vote_count` VALUES ('17', '1', '116', '1');

-- ----------------------------
-- Table structure for t_vote_details
-- ----------------------------
DROP TABLE IF EXISTS `t_vote_details`;
CREATE TABLE `t_vote_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) DEFAULT NULL,
  `player_poll` int(11) DEFAULT NULL,
  `match_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_vote_details
-- ----------------------------
INSERT INTO `t_vote_details` VALUES ('17', '12', '0', '90');
INSERT INTO `t_vote_details` VALUES ('18', '24', '0', '90');
INSERT INTO `t_vote_details` VALUES ('19', '28', '0', '91');
INSERT INTO `t_vote_details` VALUES ('20', '37', '0', '91');
INSERT INTO `t_vote_details` VALUES ('21', '3', '0', '92');
INSERT INTO `t_vote_details` VALUES ('22', '4', '0', '92');
INSERT INTO `t_vote_details` VALUES ('23', '39', '0', '93');
INSERT INTO `t_vote_details` VALUES ('24', '26', '0', '93');
INSERT INTO `t_vote_details` VALUES ('25', '44', '0', '94');
INSERT INTO `t_vote_details` VALUES ('26', '19', '0', '94');
INSERT INTO `t_vote_details` VALUES ('27', '6', '0', '95');
INSERT INTO `t_vote_details` VALUES ('28', '33', '0', '95');
INSERT INTO `t_vote_details` VALUES ('29', '21', '0', '96');
INSERT INTO `t_vote_details` VALUES ('30', '16', '0', '96');
INSERT INTO `t_vote_details` VALUES ('31', '34', '0', '97');
INSERT INTO `t_vote_details` VALUES ('32', '17', '0', '97');
INSERT INTO `t_vote_details` VALUES ('33', '2', '0', '98');
INSERT INTO `t_vote_details` VALUES ('34', '25', '0', '98');
INSERT INTO `t_vote_details` VALUES ('35', '35', '0', '100');
INSERT INTO `t_vote_details` VALUES ('36', '23', '0', '100');
INSERT INTO `t_vote_details` VALUES ('37', '36', '0', '101');
INSERT INTO `t_vote_details` VALUES ('38', '30', '0', '101');
INSERT INTO `t_vote_details` VALUES ('39', '18', '0', '102');
INSERT INTO `t_vote_details` VALUES ('40', '22', '0', '102');
INSERT INTO `t_vote_details` VALUES ('41', '31', '0', '99');
INSERT INTO `t_vote_details` VALUES ('42', '7', '0', '99');
INSERT INTO `t_vote_details` VALUES ('43', '27', '0', '103');
INSERT INTO `t_vote_details` VALUES ('44', '40', '0', '103');
INSERT INTO `t_vote_details` VALUES ('45', '8', '0', '104');
INSERT INTO `t_vote_details` VALUES ('46', '5', '0', '104');
INSERT INTO `t_vote_details` VALUES ('47', '38', '0', '105');
INSERT INTO `t_vote_details` VALUES ('48', '1', '0', '105');
INSERT INTO `t_vote_details` VALUES ('49', '1', '1', '116');
INSERT INTO `t_vote_details` VALUES ('50', '32', '0', '116');
INSERT INTO `t_vote_details` VALUES ('51', '6', '0', '117');
INSERT INTO `t_vote_details` VALUES ('52', '11', '0', '117');
INSERT INTO `t_vote_details` VALUES ('53', '33', '0', '120');
INSERT INTO `t_vote_details` VALUES ('54', '34', '0', '120');
