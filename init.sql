CREATE SCHEMA `learn-shop` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
CREATE SCHEMA `learn-order` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
CREATE SCHEMA `learn-member` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
CREATE SCHEMA `learn-payment` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

USE `learn-shop`;

DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT 0 NOT NULL COMMENT '会员ID',
  `shop_name` varchar(45) DEFAULT '' COMMENT '旺铺名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin KEY_BLOCK_SIZE=16 AUTO_INCREMENT=1000;

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT 0 NOT NULL COMMENT '会员ID',
  `product_name` varchar(45) DEFAULT '' COMMENT '产品名称',
  `price` decimal(20,2) DEFAULT '0.00' COMMENT '价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin KEY_BLOCK_SIZE=16 AUTO_INCREMENT=1000;

USE `learn-order`;

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` INT(11) DEFAULT 0 NOT NULL COMMENT '产品ID',
  `seller_id` int(11) DEFAULT 0 NOT NULL COMMENT '卖家ID',
  `buyer_id` int(11) DEFAULT 0 NOT NULL COMMENT '买家ID',
  `order_status` int(11) DEFAULT '10' COMMENT '交易状态：10成功,20退款,30失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin KEY_BLOCK_SIZE=16 AUTO_INCREMENT=1;

USE `learn-member`;

DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT '' COMMENT '用户名',
  `user_phone` varchar(45) DEFAULT '' COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin KEY_BLOCK_SIZE=16 AUTO_INCREMENT=1;

USE `learn-payment`;

DROP TABLE IF EXISTS `pay_fund`;
CREATE TABLE `pay_fund` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT 0 NOT NULL COMMENT '会员ID',
  `balance` decimal(20,2) DEFAULT '0.00' COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin KEY_BLOCK_SIZE=16 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `pay_trade`;
CREATE TABLE `pay_trade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT 0 COMMENT '订单ID',
  `tradeAmount`decimal(20,2) DEFAULT '0.00' COMMENT '交易金额',
  `trade_status` int(11) DEFAULT '10' COMMENT '交易状态：10成功,20退款,30失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin KEY_BLOCK_SIZE=16 AUTO_INCREMENT=1;