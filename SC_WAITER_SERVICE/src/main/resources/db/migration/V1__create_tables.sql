DROP TABLE IF EXISTS t_coffee;
CREATE TABLE `t_coffee` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name` varchar(64) default NULL COMMENT '咖啡名称',
    `price` bigint NOT NULL COMMENT '咖啡价格',
    `create_time` timestamp COMMENT '创建时间',
    `update_time` timestamp COMMENT '更新时间',

    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS t_order;
CREATE TABLE `t_order` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `customer` varchar(64) default NULL COMMENT '顾客姓名',
    `state` integer NOT NULL COMMENT '订单状态',
    `create_time` timestamp COMMENT '创建时间',
    `update_time` timestamp COMMENT '更新时间',

    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS t_order_coffee;
CREATE TABLE `t_order_coffee` (
    `coffee_order_id` bigint(20) NOT NULL COMMENT '订单id',
    `items_id` bigint(20) NOT NULL COMMENT '咖啡id'
);