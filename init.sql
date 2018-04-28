CREATE SCHEMA IF NOT EXISTS `learn_shop` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
CREATE SCHEMA IF NOT EXISTS `learn_order` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
CREATE SCHEMA IF NOT EXISTS `learn_member` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
CREATE SCHEMA IF NOT EXISTS `learn_payment` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

USE `learn_shop`;

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
  `shop_id` int(11) DEFAULT 0 NOT NULL COMMENT '旺铺ID',
  `product_name` varchar(45) DEFAULT '' COMMENT '产品名称',
  `price` decimal(20,2) DEFAULT '0.00' COMMENT '价格',
  `stock` int(11) DEFAULT 1000 COMMENT '库存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin KEY_BLOCK_SIZE=16 AUTO_INCREMENT=1000;

USE `learn_order`;

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` INT(11) DEFAULT 0 NOT NULL COMMENT '产品ID',
  `seller_id` int(11) DEFAULT 0 NOT NULL COMMENT '卖家ID',
  `buyer_id` int(11) DEFAULT 0 NOT NULL COMMENT '买家ID',
  `price` decimal(20,2) DEFAULT '0.00' COMMENT '订单金额',
  `order_status` int(11) DEFAULT '40' COMMENT '交易状态：10成功,20退款,30失败,40待付款',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin KEY_BLOCK_SIZE=16 AUTO_INCREMENT=1;

USE `learn_member`;

DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT '' COMMENT '用户名',
  `rank` float(6,2) DEFAULT 0.00 COMMENT '等级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin KEY_BLOCK_SIZE=16 AUTO_INCREMENT=1;

USE `learn_payment`;

DROP TABLE IF EXISTS `pay_fund`;
CREATE TABLE `pay_fund` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT 0 NOT NULL COMMENT '会员ID',
  `balance` decimal(20,2) DEFAULT '0.00' COMMENT '可用金额',
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

INSERT INTO learn_member.member(id,user_name) VALUES (1000,'卖家'),(1001,'买家');
INSERT INTO learn_payment.pay_fund(member_id,balance) VALUES (1000,10000000),(1001,10000000);
INSERT INTO learn_shop.shop(id,member_id,shop_name) VALUES (2000,1000,'奥迪4S店');
INSERT INTO learn_shop.product(shop_id,product_name,price,stock) VALUES (2000, '奥迪 a8', 10000,1200);