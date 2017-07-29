/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.0.67-community-log : Database - db_budget
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_budget` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_budget`;

/*Table structure for table `budget_accounting_subject` */

DROP TABLE IF EXISTS `budget_accounting_subject`;

CREATE TABLE `budget_accounting_subject` (
  `f_id` varchar(32) NOT NULL,
  `f_create_time` varchar(50) default NULL,
  `f_remark` longtext,
  `f_adjust_accounts` bit(1) default NULL,
  `f_code` varchar(255) default NULL,
  `f_count_unit` varchar(255) default NULL,
  `f_direction` varchar(255) default NULL,
  `f_level` int(11) default NULL,
  `f_name` varchar(255) default NULL,
  `f_status` bit(1) default NULL,
  `f_version` int(11) NOT NULL,
  PRIMARY KEY  (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `budget_accounting_subject` */

/*Table structure for table `budget_area_archives` */

DROP TABLE IF EXISTS `budget_area_archives`;

CREATE TABLE `budget_area_archives` (
  `f_id` varchar(32) NOT NULL,
  `f_create_time` varchar(50) default NULL,
  `f_remark` longtext,
  `f_version` int(11) NOT NULL,
  `f_classification_code` varchar(255) default NULL,
  `f_classification_name` varchar(255) default NULL,
  `f_code` varchar(255) default NULL,
  `f_name` varchar(255) default NULL,
  PRIMARY KEY  (`f_id`),
  UNIQUE KEY `f_code` (`f_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `budget_area_archives` */

/*Table structure for table `budget_budget_target` */

DROP TABLE IF EXISTS `budget_budget_target`;

CREATE TABLE `budget_budget_target` (
  `f_id` varchar(32) NOT NULL,
  `f_create_time` varchar(50) default NULL,
  `f_remark` longtext,
  `f_code` varchar(255) default NULL,
  PRIMARY KEY  (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `budget_budget_target` */

/*Table structure for table `budget_client_record` */

DROP TABLE IF EXISTS `budget_client_record`;

CREATE TABLE `budget_client_record` (
  `f_id` varchar(32) NOT NULL,
  `f_create_time` varchar(50) default NULL,
  `f_remark` longtext,
  `f_version` int(11) NOT NULL,
  `f_account` varchar(255) default NULL,
  `f_bank_of_deposit` varchar(255) default NULL,
  `f_category` varchar(255) default NULL,
  `f_code` varchar(255) default NULL,
  `f_company_name` varchar(255) default NULL,
  `f_customer_phone` varchar(255) default NULL,
  `f_department` varchar(255) default NULL,
  `f_email` varchar(255) default NULL,
  `f_faxes` varchar(255) default NULL,
  `f_filing_date` varchar(255) default NULL,
  `f_nature` varchar(255) default NULL,
  `f_personnel` varchar(255) default NULL,
  `f_phone` varchar(255) default NULL,
  `f_ratepaying_number` varchar(255) default NULL,
  `f_region` varchar(255) default NULL,
  `f_shortened_form` varchar(255) default NULL,
  `f_status` bit(1) NOT NULL,
  PRIMARY KEY  (`f_id`),
  UNIQUE KEY `f_code` (`f_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `budget_client_record` */

/*Table structure for table `budget_collection` */

DROP TABLE IF EXISTS `budget_collection`;

CREATE TABLE `budget_collection` (
  `f_id` varchar(32) NOT NULL,
  `f_create_time` varchar(50) default NULL,
  `f_remark` longtext,
  `f_version` int(11) NOT NULL,
  `f_bz` varchar(255) default NULL,
  `f_bz1` varchar(255) default NULL,
  `f_category_name` varchar(255) default NULL,
  `f_code` varchar(255) default NULL,
  `f_paper` varchar(255) default NULL,
  `f_phase` varchar(255) default NULL,
  `f_price` float default NULL,
  `f_startdate` varchar(255) default NULL,
  PRIMARY KEY  (`f_id`),
  UNIQUE KEY `f_code` (`f_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `budget_collection` */

/*Table structure for table `budget_cost_item` */

DROP TABLE IF EXISTS `budget_cost_item`;

CREATE TABLE `budget_cost_item` (
  `f_id` varchar(32) NOT NULL,
  `f_create_time` varchar(50) default NULL,
  `f_remark` longtext,
  `f_version` int(11) NOT NULL,
  `f_accounting_subject` varchar(255) default NULL,
  `f_cost_code` varchar(255) default NULL,
  `f_cost_explain` varchar(255) default NULL,
  `f_cost_name` varchar(255) default NULL,
  `f_status` bit(1) NOT NULL,
  PRIMARY KEY  (`f_id`),
  UNIQUE KEY `f_cost_code` (`f_cost_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `budget_cost_item` */

/*Table structure for table `budget_customer` */

DROP TABLE IF EXISTS `budget_customer`;

CREATE TABLE `budget_customer` (
  `f_id` varchar(32) NOT NULL,
  `f_create_time` varchar(50) default NULL,
  `f_remark` longtext,
  `f_code` varchar(255) default NULL,
  `f_nature` varchar(255) default NULL,
  `f_unit_name` varchar(255) default NULL,
  `f_unit_name_short` varchar(255) default NULL,
  PRIMARY KEY  (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `budget_customer` */

/*Table structure for table `budget_field_value` */

DROP TABLE IF EXISTS `budget_field_value`;

CREATE TABLE `budget_field_value` (
  `f_id` int(11) NOT NULL auto_increment,
  `f_bill_type` int(11) NOT NULL,
  `f_date` varchar(10) NOT NULL,
  `f_max_value` int(11) NOT NULL,
  PRIMARY KEY  (`f_id`),
  UNIQUE KEY `f_bill_type` (`f_bill_type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `budget_field_value` */

insert  into `budget_field_value`(`f_id`,`f_bill_type`,`f_date`,`f_max_value`) values (2,0,'20131130',1);

/*Table structure for table `budget_flows` */

DROP TABLE IF EXISTS `budget_flows`;

CREATE TABLE `budget_flows` (
  `f_id` varchar(32) NOT NULL,
  `f_create_time` varchar(50) default NULL,
  `f_remark` longtext,
  `f_version` int(11) NOT NULL,
  `f_category` varchar(255) default NULL,
  `f_creditor` varchar(255) default NULL,
  `f_debtor` varchar(255) default NULL,
  `f_direction` varchar(255) default NULL,
  `f_flows_code` varchar(255) default NULL,
  `f_flows_name` varchar(255) default NULL,
  `f_status` bit(1) default NULL,
  PRIMARY KEY  (`f_id`),
  UNIQUE KEY `f_flows_code` (`f_flows_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `budget_flows` */

/*Table structure for table `budget_item_archives` */

DROP TABLE IF EXISTS `budget_item_archives`;

CREATE TABLE `budget_item_archives` (
  `f_id` varchar(32) NOT NULL,
  `f_create_time` varchar(50) default NULL,
  `f_remark` longtext,
  `f_version` int(11) NOT NULL,
  `f_category_code` varchar(255) default NULL,
  `f_category_name` varchar(255) default NULL,
  `f_customer` varchar(255) default NULL,
  `f_department` varchar(255) default NULL,
  `f_enddate` varchar(255) default NULL,
  `f_item_code` varchar(255) default NULL,
  `f_item_name` varchar(255) default NULL,
  `f_nature` varchar(255) default NULL,
  `f_program_director` varchar(255) default NULL,
  `f_project_director` varchar(255) default NULL,
  `f_project_status` varchar(255) default NULL,
  `f_region` varchar(255) default NULL,
  `f_sales` float default NULL,
  `f_startdate` varchar(255) default NULL,
  `f_timespan` int(11) default NULL,
  PRIMARY KEY  (`f_id`),
  UNIQUE KEY `f_item_code` (`f_item_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `budget_item_archives` */

/*Table structure for table `budget_items_state` */

DROP TABLE IF EXISTS `budget_items_state`;

CREATE TABLE `budget_items_state` (
  `f_id` varchar(32) NOT NULL,
  `f_create_time` varchar(50) default NULL,
  `f_remark` longtext,
  `f_version` int(11) NOT NULL,
  `f_bz` varchar(255) default NULL,
  `f_code` varchar(255) default NULL,
  `f_item_state` varchar(255) default NULL,
  PRIMARY KEY  (`f_id`),
  UNIQUE KEY `f_code` (`f_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `budget_items_state` */

/*Table structure for table `budget_project_budget_detail` */

DROP TABLE IF EXISTS `budget_project_budget_detail`;

CREATE TABLE `budget_project_budget_detail` (
  `f_id` varchar(32) NOT NULL,
  `f_create_time` varchar(50) default NULL,
  `f_remark` longtext,
  `f_version` int(11) NOT NULL,
  `f_add_money` double NOT NULL,
  `f_budget_money` double NOT NULL,
  `f_budget_target_code` varchar(20) NOT NULL,
  `f_budget_target_name` varchar(100) NOT NULL,
  `f_code` int(11) NOT NULL,
  `f_grand_total_money` double NOT NULL,
  `f_last_money` double NOT NULL,
  `f_document` varchar(32) NOT NULL,
  PRIMARY KEY  (`f_id`),
  KEY `FK3AA5DA6B7329525C` (`f_document`),
  CONSTRAINT `FK3AA5DA6B7329525C` FOREIGN KEY (`f_document`) REFERENCES `budget_project_budget_document` (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `budget_project_budget_detail` */

insert  into `budget_project_budget_detail`(`f_id`,`f_create_time`,`f_remark`,`f_version`,`f_add_money`,`f_budget_money`,`f_budget_target_code`,`f_budget_target_name`,`f_code`,`f_grand_total_money`,`f_last_money`,`f_document`) values ('4028fe8142a91c160142a91c63ff0001','2013-11-30 09:04:38','',0,0,121,'121','32',0,121,121,'4028fe8142a91c160142a91c63f90000');

/*Table structure for table `budget_project_budget_document` */

DROP TABLE IF EXISTS `budget_project_budget_document`;

CREATE TABLE `budget_project_budget_document` (
  `f_id` varchar(32) NOT NULL,
  `f_create_time` varchar(50) default NULL,
  `f_remark` longtext,
  `f_version` int(11) NOT NULL,
  `f_audit_state` int(11) NOT NULL,
  `f_document_num` varchar(20) NOT NULL,
  `f_document_state` int(11) default NULL,
  `f_document_type` int(11) NOT NULL,
  `f_next_person` varchar(30) default NULL,
  `f_submit_state` int(11) NOT NULL,
  `f_submit_time` varchar(255) default NULL,
  `f_total_money` double NOT NULL,
  `f_write_date` varchar(20) NOT NULL,
  `f_write_department_name` varchar(50) NOT NULL,
  `f_write_people` varchar(30) NOT NULL,
  `f_write_people_tel` varchar(20) default NULL,
  `f_writer_department_code` varchar(20) NOT NULL,
  `f_writer_people_code` varchar(20) NOT NULL,
  `f_project_category` varchar(50) NOT NULL,
  `f_project_name` varchar(50) NOT NULL,
  PRIMARY KEY  (`f_id`),
  UNIQUE KEY `f_document_num` (`f_document_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `budget_project_budget_document` */

insert  into `budget_project_budget_document`(`f_id`,`f_create_time`,`f_remark`,`f_version`,`f_audit_state`,`f_document_num`,`f_document_state`,`f_document_type`,`f_next_person`,`f_submit_state`,`f_submit_time`,`f_total_money`,`f_write_date`,`f_write_department_name`,`f_write_people`,`f_write_people_tel`,`f_writer_department_code`,`f_writer_people_code`,`f_project_category`,`f_project_name`) values ('4028fe8142a91c160142a91c63f90000','2013-11-30 09:04:38','23',0,0,'20131130000001',0,1,'开发者',1,'2013-11-30 09:11:57',121,'2013-11-30','阿里巴巴','开发者','10011','1001','1','123','122');

/*Table structure for table `budget_spending` */

DROP TABLE IF EXISTS `budget_spending`;

CREATE TABLE `budget_spending` (
  `f_id` varchar(32) NOT NULL,
  `f_create_time` varchar(50) default NULL,
  `f_remark` longtext,
  `f_version` int(11) NOT NULL,
  `f_bz` varchar(255) default NULL,
  `f_bz1` varchar(255) default NULL,
  `f_categories` varchar(255) default NULL,
  `f_code` varchar(255) default NULL,
  `f_price` float default NULL,
  `f_project_spending` varchar(255) default NULL,
  `f_proportion` varchar(255) default NULL,
  PRIMARY KEY  (`f_id`),
  UNIQUE KEY `f_code` (`f_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `budget_spending` */

/*Table structure for table `budget_supplier_record` */

DROP TABLE IF EXISTS `budget_supplier_record`;

CREATE TABLE `budget_supplier_record` (
  `f_id` varchar(32) NOT NULL,
  `f_create_time` varchar(50) default NULL,
  `f_remark` longtext,
  `f_version` int(11) NOT NULL,
  `f_account` varchar(255) default NULL,
  `f_bank_of_deposit` varchar(255) default NULL,
  `f_category` varchar(255) default NULL,
  `f_code` varchar(255) default NULL,
  `f_company_name` varchar(255) default NULL,
  `f_customer_phone` varchar(255) default NULL,
  `f_department` varchar(255) default NULL,
  `f_email` varchar(255) default NULL,
  `f_faxes` varchar(255) default NULL,
  `f_filing_date` varchar(255) default NULL,
  `f_nature` varchar(255) default NULL,
  `f_personnel` varchar(255) default NULL,
  `f_phone` varchar(255) default NULL,
  `f_ratepaying_number` varchar(255) default NULL,
  `f_region` varchar(255) default NULL,
  `f_shortened_form` varchar(255) default NULL,
  `f_status` bit(1) NOT NULL,
  PRIMARY KEY  (`f_id`),
  UNIQUE KEY `f_code` (`f_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `budget_supplier_record` */

/*Table structure for table `budget_target` */

DROP TABLE IF EXISTS `budget_target`;

CREATE TABLE `budget_target` (
  `f_id` varchar(32) NOT NULL,
  `f_create_time` varchar(50) default NULL,
  `f_remark` longtext,
  `f_version` int(11) NOT NULL,
  `f_budget_nature` varchar(255) default NULL,
  `f_cost_item` varchar(255) default NULL,
  `f_status` bit(1) NOT NULL,
  `f_target_classify` varchar(255) default NULL,
  `f_target_code` varchar(255) default NULL,
  `f_target_explain` varchar(255) default NULL,
  `f_target_name` varchar(255) default NULL,
  PRIMARY KEY  (`f_id`),
  UNIQUE KEY `f_target_code` (`f_target_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `budget_target` */

insert  into `budget_target`(`f_id`,`f_create_time`,`f_remark`,`f_version`,`f_budget_nature`,`f_cost_item`,`f_status`,`f_target_classify`,`f_target_code`,`f_target_explain`,`f_target_name`) values ('4028fe81426a1ddb01426a3244a30000','2013-11-18 03:52:28',NULL,0,'性质','差旅费、礼品费、餐费、住宿费','','分类','010001','','招待费'),('4028fe81426a1ddb01426a3315410001','2013-11-18 03:53:21',NULL,0,'性质','待定','','分类','010002','','外协费');

/*Table structure for table `budget_work_flow` */

DROP TABLE IF EXISTS `budget_work_flow`;

CREATE TABLE `budget_work_flow` (
  `f_id` varchar(32) NOT NULL,
  `f_create_time` varchar(50) default NULL,
  `f_remark` longtext,
  `f_version` int(11) NOT NULL,
  `f_audit_state` int(11) NOT NULL,
  `f_bill_id` varchar(32) NOT NULL,
  `f_bill_type` int(11) NOT NULL,
  `f_hand_time` varchar(30) default NULL,
  `f_last_work_flow_id` varchar(32) default NULL,
  `f_opinion` longtext,
  `f_send_from_department_code` varchar(30) NOT NULL,
  `f_send_from_department_name` varchar(50) NOT NULL,
  `f_send_from_people` varchar(50) NOT NULL,
  `f_send_from_people_code` varchar(30) NOT NULL,
  `f_send_to_department_code` varchar(30) NOT NULL,
  `f_send_to_department_name` varchar(50) NOT NULL,
  `f_send_to_people` varchar(50) NOT NULL,
  `f_send_to_people_code` varchar(30) NOT NULL,
  PRIMARY KEY  (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `budget_work_flow` */

insert  into `budget_work_flow`(`f_id`,`f_create_time`,`f_remark`,`f_version`,`f_audit_state`,`f_bill_id`,`f_bill_type`,`f_hand_time`,`f_last_work_flow_id`,`f_opinion`,`f_send_from_department_code`,`f_send_from_department_name`,`f_send_from_people`,`f_send_from_people_code`,`f_send_to_department_code`,`f_send_to_department_name`,`f_send_to_people`,`f_send_to_people_code`) values ('4028fe8142a9229d0142a92315180000','2013-11-30 09:11:57','',0,0,'4028fe8142a91c160142a91c63f90000',0,NULL,NULL,NULL,'1001','阿里巴巴','开发者','1','1001','阿里巴巴','开发者','1');

/*Table structure for table `sys_department` */

DROP TABLE IF EXISTS `sys_department`;

CREATE TABLE `sys_department` (
  `f_id` varchar(32) NOT NULL,
  `f_create_time` varchar(50) default NULL,
  `f_remark` longtext,
  `f_code` varchar(20) NOT NULL,
  `f_name` varchar(100) NOT NULL,
  `f_state` varchar(255) NOT NULL,
  `f_unit_code` varchar(255) NOT NULL,
  `f_unit_name` varchar(255) NOT NULL,
  `f_united` bit(1) NOT NULL,
  `f_version` int(11) NOT NULL,
  PRIMARY KEY  (`f_id`),
  UNIQUE KEY `f_code` (`f_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_department` */

insert  into `sys_department`(`f_id`,`f_create_time`,`f_remark`,`f_code`,`f_name`,`f_state`,`f_unit_code`,`f_unit_name`,`f_united`,`f_version`) values ('4028fe814256412e01425643d5db0000','2013-11-14 06:59:15',NULL,'1','阿里巴巴集团','启用','root','','',0),('4028fe814256412e014256472a2b003e','2013-11-14 07:02:53',NULL,'1001','阿里巴巴','启用','1','阿里巴巴集团','\0',0),('4028fe814256412e014256474cac003f','2013-11-14 07:03:02',NULL,'1002','淘宝','启用','1','阿里巴巴集团','\0',0),('4028fe814256412e014256477ee50040','2013-11-14 07:03:15',NULL,'1003','聚划算','启用','1','阿里巴巴集团','\0',0),('4028fe814256412e014256479d7b0041','2013-11-14 07:03:23',NULL,'1004','余额宝','启用','1','阿里巴巴集团','\0',0),('4028fe814256412e01425647bb140042','2013-11-14 07:03:30',NULL,'1005','支付宝','启用','1','阿里巴巴集团','\0',0),('4028fe814256412e01425647f8030044','2013-11-14 07:03:46',NULL,'1006','阿里云','启用','1','阿里巴巴集团','\0',0),('4028fe814256412e014256481cd50045','2013-11-14 07:03:55',NULL,'1007','阿里妈妈','启用','1','阿里巴巴集团','\0',0),('4028fe814256412e0142564860e20046','2013-11-14 07:04:13',NULL,'1008','来往','启用','1','阿里巴巴集团','\0',0),('4028fe8142731d1c01427320f3c40000','2013-11-20 09:30:08',NULL,'1009','测试部门','禁用','1','阿里巴巴集团','\0',0);

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `f_id` varchar(32) NOT NULL,
  `f_create_time` varchar(50) default NULL,
  `f_remark` longtext,
  `f_code` varchar(20) NOT NULL,
  `f_name` varchar(100) NOT NULL,
  `f_d_code` varchar(255) default NULL,
  `f_d_name` varchar(255) default NULL,
  `f_state` varchar(255) default NULL,
  `f_version` int(11) NOT NULL,
  PRIMARY KEY  (`f_id`),
  UNIQUE KEY `f_code` (`f_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_dict` */

insert  into `sys_dict`(`f_id`,`f_create_time`,`f_remark`,`f_code`,`f_name`,`f_d_code`,`f_d_name`,`f_state`,`f_version`) values ('4028fe8142a8cba30142a911cea10000','2013-11-30 08:53:05','','100501','预算单','1005','单据类型','启用',0),('4028fe8142a8cba30142a911cea102','2013-11-30 08:53:05','','100504','预算单','1005','单据类型','启用',0),('4028fe8142a8cba30142a9123b5c0001','2013-11-30 08:53:33','','100502','追加预算','1005','单据类型','启用',0),('4028fe8142a8cba30142a9127dd40002','2013-11-30 08:53:50','','100503','预算调整','1005','单据类型','启用',0);

/*Table structure for table `sys_file` */

DROP TABLE IF EXISTS `sys_file`;

CREATE TABLE `sys_file` (
  `f_id` varchar(32) NOT NULL,
  `f_create_time` varchar(50) default NULL,
  `f_remark` longtext,
  `f_code` varchar(20) NOT NULL,
  `f_name` varchar(255) default NULL,
  `f_new_name` varchar(255) default NULL,
  `f_size` double NOT NULL,
  `f_total_page` int(11) NOT NULL,
  `f_type` varchar(255) default NULL,
  `f_version` int(11) NOT NULL,
  PRIMARY KEY  (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_file` */

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `f_id` int(11) NOT NULL auto_increment,
  `f_create_time` varchar(50) default NULL,
  `f_exception_msg` longtext,
  `f_loginip` varchar(30) NOT NULL,
  `f_operate_model` varchar(50) NOT NULL,
  `f_operate_time` varchar(30) NOT NULL,
  `f_remark` longtext,
  `f_user_name` varchar(50) NOT NULL,
  PRIMARY KEY  (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8;

/*Data for the table `sys_log` */

insert  into `sys_log`(`f_id`,`f_create_time`,`f_exception_msg`,`f_loginip`,`f_operate_model`,`f_operate_time`,`f_remark`,`f_user_name`) values (1,'2013-11-20 09:01:31',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-20 21:01:31',NULL,'开发者'),(2,'2013-11-20 09:01:36',NULL,'0:0:0:0:0:0:0:1','查询数据字典分组信息','2013-11-20 21:01:36',NULL,'开发者'),(3,'2013-11-20 09:01:36',NULL,'0:0:0:0:0:0:0:1','查询数据字典信息','2013-11-20 21:01:36',NULL,'开发者'),(4,'2013-11-20 09:01:36',NULL,'0:0:0:0:0:0:0:1','查询数据字典分组信息','2013-11-20 21:01:36',NULL,'开发者'),(5,'2013-11-20 09:01:46',NULL,'0:0:0:0:0:0:0:1','查询数据字典信息','2013-11-20 21:01:46',NULL,'开发者'),(6,'2013-11-20 09:01:47',NULL,'0:0:0:0:0:0:0:1','查询数据字典信息','2013-11-20 21:01:47',NULL,'开发者'),(7,'2013-11-20 09:01:59',NULL,'0:0:0:0:0:0:0:1','得到所有的可用的角色下拉列表','2013-11-20 21:01:59',NULL,'开发者'),(8,'2013-11-20 09:02:04',NULL,'0:0:0:0:0:0:0:1','查询数据字典信息','2013-11-20 21:02:04',NULL,'开发者'),(9,'2013-11-20 09:02:05',NULL,'0:0:0:0:0:0:0:1','查询数据字典信息','2013-11-20 21:02:05',NULL,'开发者'),(10,'2013-11-20 09:29:27',NULL,'0:0:0:0:0:0:0:1','保存用户信息','2013-11-20 21:29:27',NULL,'开发者'),(11,'2013-11-20 09:29:44',NULL,'0:0:0:0:0:0:0:1','查询数据字典信息','2013-11-20 21:29:44',NULL,'开发者'),(12,'2013-11-20 09:29:45',NULL,'0:0:0:0:0:0:0:1','查询数据字典信息','2013-11-20 21:29:45',NULL,'开发者'),(13,'2013-11-20 09:30:12',NULL,'0:0:0:0:0:0:0:1','查询数据字典信息','2013-11-20 21:30:12',NULL,'开发者'),(14,'2013-11-20 09:30:24',NULL,'0:0:0:0:0:0:0:1','查询数据字典信息','2013-11-20 21:30:24',NULL,'开发者'),(15,'2013-11-20 09:33:30',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-20 21:33:30',NULL,'开发者'),(16,'2013-11-20 09:33:33',NULL,'0:0:0:0:0:0:0:1','查询角色信息','2013-11-20 21:33:33',NULL,'开发者'),(17,'2013-11-20 09:33:33',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:33',NULL,'开发者'),(18,'2013-11-20 09:33:33',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:33',NULL,'开发者'),(19,'2013-11-20 09:33:33',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:33',NULL,'开发者'),(20,'2013-11-20 09:33:33',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:33',NULL,'开发者'),(21,'2013-11-20 09:33:34',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:34',NULL,'开发者'),(22,'2013-11-20 09:33:34',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:34',NULL,'开发者'),(23,'2013-11-20 09:33:34',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:34',NULL,'开发者'),(24,'2013-11-20 09:33:34',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:34',NULL,'开发者'),(25,'2013-11-20 09:33:34',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:34',NULL,'开发者'),(26,'2013-11-20 09:33:34',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:34',NULL,'开发者'),(27,'2013-11-20 09:33:34',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:34',NULL,'开发者'),(28,'2013-11-20 09:33:34',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:34',NULL,'开发者'),(29,'2013-11-20 09:33:34',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:34',NULL,'开发者'),(30,'2013-11-20 09:33:34',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:34',NULL,'开发者'),(31,'2013-11-20 09:33:34',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:34',NULL,'开发者'),(32,'2013-11-20 09:33:34',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:34',NULL,'开发者'),(33,'2013-11-20 09:33:34',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:34',NULL,'开发者'),(34,'2013-11-20 09:33:34',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:34',NULL,'开发者'),(35,'2013-11-20 09:33:34',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:34',NULL,'开发者'),(36,'2013-11-20 09:33:34',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:34',NULL,'开发者'),(37,'2013-11-20 09:33:35',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:35',NULL,'开发者'),(38,'2013-11-20 09:33:35',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:35',NULL,'开发者'),(39,'2013-11-20 09:33:35',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:35',NULL,'开发者'),(40,'2013-11-20 09:33:35',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:35',NULL,'开发者'),(41,'2013-11-20 09:33:35',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:35',NULL,'开发者'),(42,'2013-11-20 09:33:35',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:35',NULL,'开发者'),(43,'2013-11-20 09:33:35',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:35',NULL,'开发者'),(44,'2013-11-20 09:33:35',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:35',NULL,'开发者'),(45,'2013-11-20 09:33:35',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:35',NULL,'开发者'),(46,'2013-11-20 09:33:35',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:35',NULL,'开发者'),(47,'2013-11-20 09:33:36',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:36',NULL,'开发者'),(48,'2013-11-20 09:33:36',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:36',NULL,'开发者'),(49,'2013-11-20 09:33:36',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:36',NULL,'开发者'),(50,'2013-11-20 09:33:36',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:36',NULL,'开发者'),(51,'2013-11-20 09:33:36',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:36',NULL,'开发者'),(52,'2013-11-20 09:33:36',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:36',NULL,'开发者'),(53,'2013-11-20 09:33:36',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:36',NULL,'开发者'),(54,'2013-11-20 09:33:36',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:36',NULL,'开发者'),(55,'2013-11-20 09:33:36',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-20 21:33:36',NULL,'开发者'),(56,'2013-11-20 09:36:28',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-20 21:36:28',NULL,'开发者'),(57,'2013-11-20 09:41:13',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-20 21:41:13',NULL,'开发者'),(58,'2013-11-20 09:42:32',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-20 21:42:32',NULL,'开发者'),(59,'2013-11-20 09:43:47',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-20 21:43:47',NULL,'开发者'),(60,'2013-11-20 09:44:58',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-20 21:44:58',NULL,'开发者'),(61,'2013-11-20 09:47:07',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-20 21:47:07',NULL,'开发者'),(62,'2013-11-20 09:47:38',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-20 21:47:38',NULL,'开发者'),(63,'2013-11-20 09:53:56',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-20 21:53:56',NULL,'开发者'),(64,'2013-11-20 09:54:50',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-20 21:54:50',NULL,'开发者'),(65,'2013-11-20 09:55:39',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-20 21:55:39',NULL,'开发者'),(66,'2013-11-20 09:56:39',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-20 21:56:39',NULL,'开发者'),(67,'2013-11-20 09:56:50',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-20 21:56:50',NULL,'开发者'),(68,'2013-11-21 11:30:12',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 11:30:12',NULL,'开发者'),(69,'2013-11-21 11:30:23',NULL,'0:0:0:0:0:0:0:1','保存项目预算单信息','2013-11-21 11:30:23',NULL,'开发者'),(70,'2013-11-21 11:39:38',NULL,'0:0:0:0:0:0:0:1','保存项目预算单信息','2013-11-21 11:39:38',NULL,'开发者'),(71,'2013-11-21 11:39:39',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 11:39:39',NULL,'开发者'),(72,'2013-11-21 11:40:40',NULL,'0:0:0:0:0:0:0:1','保存项目预算单信息','2013-11-21 11:40:40',NULL,'开发者'),(73,'2013-11-21 11:40:40',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 11:40:40',NULL,'开发者'),(74,'2013-11-21 11:55:18',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 11:55:18',NULL,'开发者'),(75,'2013-11-21 11:55:18',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 11:55:18',NULL,'开发者'),(76,'2013-11-21 11:55:35',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 11:55:35',NULL,'开发者'),(77,'2013-11-21 11:56:04',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 11:56:04',NULL,'开发者'),(78,'2013-11-21 12:30:37',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 12:30:37',NULL,'开发者'),(79,'2013-11-21 12:34:57',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 12:34:57',NULL,'开发者'),(80,'2013-11-21 12:35:09',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 12:35:09',NULL,'开发者'),(81,'2013-11-21 12:35:11',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 12:35:11',NULL,'开发者'),(82,'2013-11-21 12:35:15',NULL,'0:0:0:0:0:0:0:1','根据id查找项目预算单信息','2013-11-21 12:35:15',NULL,'开发者'),(83,'2013-11-21 12:35:18',NULL,'0:0:0:0:0:0:0:1','根据id查找项目预算单信息','2013-11-21 12:35:18',NULL,'开发者'),(84,'2013-11-21 12:35:48',NULL,'0:0:0:0:0:0:0:1','根据id查找项目预算单信息','2013-11-21 12:35:48',NULL,'开发者'),(85,'2013-11-21 12:48:16',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 12:48:16',NULL,'开发者'),(86,'2013-11-21 12:48:25',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 12:48:25',NULL,'开发者'),(87,'2013-11-21 01:11:40',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 13:11:40',NULL,'开发者'),(88,'2013-11-21 01:11:52',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 13:11:52',NULL,'开发者'),(89,'2013-11-21 01:12:00',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 13:12:00',NULL,'开发者'),(90,'2013-11-21 01:21:13',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 13:21:13',NULL,'开发者'),(91,'2013-11-21 01:21:22',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 13:21:22',NULL,'开发者'),(92,'2013-11-21 01:21:30',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 13:21:30',NULL,'开发者'),(93,'2013-11-21 01:23:27',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 13:23:27',NULL,'开发者'),(94,'2013-11-21 01:23:37',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 13:23:37',NULL,'开发者'),(95,'2013-11-21 01:23:47',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 13:23:47',NULL,'开发者'),(96,'2013-11-21 01:26:18',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 13:26:18',NULL,'开发者'),(97,'2013-11-21 01:33:51',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 13:33:51',NULL,'开发者'),(98,'2013-11-21 01:35:24',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 13:35:24',NULL,'开发者'),(99,'2013-11-21 01:53:21',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 13:53:21',NULL,'开发者'),(100,'2013-11-21 01:53:33',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 13:53:33',NULL,'开发者'),(101,'2013-11-21 02:02:45',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 14:02:45',NULL,'开发者'),(102,'2013-11-21 02:02:51',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 14:02:51',NULL,'开发者'),(103,'2013-11-21 02:02:52',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 14:02:52',NULL,'开发者'),(104,'2013-11-21 02:02:53',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 14:02:53',NULL,'开发者'),(105,'2013-11-21 02:02:54',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-21 14:02:54',NULL,'开发者'),(106,'2013-11-25 02:24:40',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-25 14:24:40',NULL,'开发者'),(107,'2013-11-25 02:24:48',NULL,'0:0:0:0:0:0:0:1','得到所有的可用的角色下拉列表','2013-11-25 14:24:48',NULL,'开发者'),(108,'2013-11-30 07:37:29',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 19:37:29',NULL,'开发者'),(109,'2013-11-30 07:37:34',NULL,'0:0:0:0:0:0:0:1','得到所有的可用的角色下拉列表','2013-11-30 19:37:34',NULL,'开发者'),(110,'2013-11-30 07:42:15',NULL,'0:0:0:0:0:0:0:1','得到所有的可用的角色下拉列表','2013-11-30 19:42:15',NULL,'开发者'),(111,'2013-11-30 07:42:17',NULL,'0:0:0:0:0:0:0:1','查询角色信息','2013-11-30 19:42:17',NULL,'开发者'),(112,'2013-11-30 07:42:17',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:42:17',NULL,'开发者'),(113,'2013-11-30 07:42:23',NULL,'0:0:0:0:0:0:0:1','查询资源信息','2013-11-30 19:42:23',NULL,'开发者'),(114,'2013-11-30 07:45:13',NULL,'0:0:0:0:0:0:0:1','查询资源信息','2013-11-30 19:45:13',NULL,'开发者'),(115,'2013-11-30 07:45:22',NULL,'0:0:0:0:0:0:0:1','查询角色信息','2013-11-30 19:45:22',NULL,'开发者'),(116,'2013-11-30 07:45:23',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:23',NULL,'开发者'),(117,'2013-11-30 07:45:23',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:23',NULL,'开发者'),(118,'2013-11-30 07:45:23',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:23',NULL,'开发者'),(119,'2013-11-30 07:45:23',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:23',NULL,'开发者'),(120,'2013-11-30 07:45:23',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:23',NULL,'开发者'),(121,'2013-11-30 07:45:23',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:23',NULL,'开发者'),(122,'2013-11-30 07:45:23',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:23',NULL,'开发者'),(123,'2013-11-30 07:45:23',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:23',NULL,'开发者'),(124,'2013-11-30 07:45:23',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:23',NULL,'开发者'),(125,'2013-11-30 07:45:23',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:23',NULL,'开发者'),(126,'2013-11-30 07:45:23',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:23',NULL,'开发者'),(127,'2013-11-30 07:45:24',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:24',NULL,'开发者'),(128,'2013-11-30 07:45:24',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:24',NULL,'开发者'),(129,'2013-11-30 07:45:24',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:24',NULL,'开发者'),(130,'2013-11-30 07:45:25',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:25',NULL,'开发者'),(131,'2013-11-30 07:45:25',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:25',NULL,'开发者'),(132,'2013-11-30 07:45:25',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:25',NULL,'开发者'),(133,'2013-11-30 07:45:25',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:25',NULL,'开发者'),(134,'2013-11-30 07:45:26',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:26',NULL,'开发者'),(135,'2013-11-30 07:45:26',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:26',NULL,'开发者'),(136,'2013-11-30 07:45:26',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:26',NULL,'开发者'),(137,'2013-11-30 07:45:26',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:26',NULL,'开发者'),(138,'2013-11-30 07:45:26',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:26',NULL,'开发者'),(139,'2013-11-30 07:45:26',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:26',NULL,'开发者'),(140,'2013-11-30 07:45:27',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:27',NULL,'开发者'),(141,'2013-11-30 07:45:27',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:27',NULL,'开发者'),(142,'2013-11-30 07:45:27',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:27',NULL,'开发者'),(143,'2013-11-30 07:45:27',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:27',NULL,'开发者'),(144,'2013-11-30 07:45:27',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:27',NULL,'开发者'),(145,'2013-11-30 07:45:27',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:27',NULL,'开发者'),(146,'2013-11-30 07:45:27',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:27',NULL,'开发者'),(147,'2013-11-30 07:45:28',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:28',NULL,'开发者'),(148,'2013-11-30 07:45:28',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:28',NULL,'开发者'),(149,'2013-11-30 07:45:28',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:28',NULL,'开发者'),(150,'2013-11-30 07:45:28',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:28',NULL,'开发者'),(151,'2013-11-30 07:45:29',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:29',NULL,'开发者'),(152,'2013-11-30 07:45:29',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:29',NULL,'开发者'),(153,'2013-11-30 07:45:29',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:29',NULL,'开发者'),(154,'2013-11-30 07:45:29',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:29',NULL,'开发者'),(155,'2013-11-30 07:45:29',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:29',NULL,'开发者'),(156,'2013-11-30 07:45:29',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:29',NULL,'开发者'),(157,'2013-11-30 07:45:29',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:29',NULL,'开发者'),(158,'2013-11-30 07:45:29',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:29',NULL,'开发者'),(159,'2013-11-30 07:45:29',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:29',NULL,'开发者'),(160,'2013-11-30 07:45:29',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:29',NULL,'开发者'),(161,'2013-11-30 07:45:29',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:29',NULL,'开发者'),(162,'2013-11-30 07:45:30',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:30',NULL,'开发者'),(163,'2013-11-30 07:45:30',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:30',NULL,'开发者'),(164,'2013-11-30 07:45:30',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:30',NULL,'开发者'),(165,'2013-11-30 07:45:30',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:30',NULL,'开发者'),(166,'2013-11-30 07:45:30',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:30',NULL,'开发者'),(167,'2013-11-30 07:45:31',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:31',NULL,'开发者'),(168,'2013-11-30 07:45:38',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:38',NULL,'开发者'),(169,'2013-11-30 07:45:39',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:39',NULL,'开发者'),(170,'2013-11-30 07:45:39',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:39',NULL,'开发者'),(171,'2013-11-30 07:45:39',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:39',NULL,'开发者'),(172,'2013-11-30 07:45:39',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:39',NULL,'开发者'),(173,'2013-11-30 07:45:39',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:39',NULL,'开发者'),(174,'2013-11-30 07:45:39',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:39',NULL,'开发者'),(175,'2013-11-30 07:45:39',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:39',NULL,'开发者'),(176,'2013-11-30 07:45:39',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:39',NULL,'开发者'),(177,'2013-11-30 07:45:39',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:39',NULL,'开发者'),(178,'2013-11-30 07:45:39',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:39',NULL,'开发者'),(179,'2013-11-30 07:45:39',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:39',NULL,'开发者'),(180,'2013-11-30 07:45:40',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:40',NULL,'开发者'),(181,'2013-11-30 07:45:40',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:40',NULL,'开发者'),(182,'2013-11-30 07:45:40',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:40',NULL,'开发者'),(183,'2013-11-30 07:45:40',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:40',NULL,'开发者'),(184,'2013-11-30 07:45:40',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:40',NULL,'开发者'),(185,'2013-11-30 07:45:41',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:41',NULL,'开发者'),(186,'2013-11-30 07:45:41',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:41',NULL,'开发者'),(187,'2013-11-30 07:45:41',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:41',NULL,'开发者'),(188,'2013-11-30 07:45:41',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:41',NULL,'开发者'),(189,'2013-11-30 07:45:41',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:41',NULL,'开发者'),(190,'2013-11-30 07:45:41',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:41',NULL,'开发者'),(191,'2013-11-30 07:45:41',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:41',NULL,'开发者'),(192,'2013-11-30 07:45:41',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:41',NULL,'开发者'),(193,'2013-11-30 07:45:42',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:42',NULL,'开发者'),(194,'2013-11-30 07:45:42',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:42',NULL,'开发者'),(195,'2013-11-30 07:45:42',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:42',NULL,'开发者'),(196,'2013-11-30 07:45:42',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:42',NULL,'开发者'),(197,'2013-11-30 07:45:42',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:42',NULL,'开发者'),(198,'2013-11-30 07:45:42',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:42',NULL,'开发者'),(199,'2013-11-30 07:45:42',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:42',NULL,'开发者'),(200,'2013-11-30 07:45:42',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:42',NULL,'开发者'),(201,'2013-11-30 07:45:43',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:43',NULL,'开发者'),(202,'2013-11-30 07:45:43',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:43',NULL,'开发者'),(203,'2013-11-30 07:45:43',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:43',NULL,'开发者'),(204,'2013-11-30 07:45:43',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:43',NULL,'开发者'),(205,'2013-11-30 07:45:43',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:43',NULL,'开发者'),(206,'2013-11-30 07:45:43',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:43',NULL,'开发者'),(207,'2013-11-30 07:45:44',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:44',NULL,'开发者'),(208,'2013-11-30 07:45:44',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:44',NULL,'开发者'),(209,'2013-11-30 07:45:44',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:44',NULL,'开发者'),(210,'2013-11-30 07:45:45',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:45',NULL,'开发者'),(211,'2013-11-30 07:45:45',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:45',NULL,'开发者'),(212,'2013-11-30 07:45:45',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:45',NULL,'开发者'),(213,'2013-11-30 07:45:46',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:46',NULL,'开发者'),(214,'2013-11-30 07:45:46',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:46',NULL,'开发者'),(215,'2013-11-30 07:45:46',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:46',NULL,'开发者'),(216,'2013-11-30 07:45:46',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:46',NULL,'开发者'),(217,'2013-11-30 07:45:46',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:46',NULL,'开发者'),(218,'2013-11-30 07:45:47',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:47',NULL,'开发者'),(219,'2013-11-30 07:45:47',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:47',NULL,'开发者'),(220,'2013-11-30 07:45:47',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:47',NULL,'开发者'),(221,'2013-11-30 07:45:48',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:48',NULL,'开发者'),(222,'2013-11-30 07:45:48',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:48',NULL,'开发者'),(223,'2013-11-30 07:45:48',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:48',NULL,'开发者'),(224,'2013-11-30 07:45:48',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:48',NULL,'开发者'),(225,'2013-11-30 07:45:48',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:48',NULL,'开发者'),(226,'2013-11-30 07:45:49',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:49',NULL,'开发者'),(227,'2013-11-30 07:45:49',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:49',NULL,'开发者'),(228,'2013-11-30 07:45:49',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:49',NULL,'开发者'),(229,'2013-11-30 07:45:49',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:49',NULL,'开发者'),(230,'2013-11-30 07:45:49',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:49',NULL,'开发者'),(231,'2013-11-30 07:45:49',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:49',NULL,'开发者'),(232,'2013-11-30 07:45:49',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:49',NULL,'开发者'),(233,'2013-11-30 07:45:49',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:49',NULL,'开发者'),(234,'2013-11-30 07:45:49',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:49',NULL,'开发者'),(235,'2013-11-30 07:45:49',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:49',NULL,'开发者'),(236,'2013-11-30 07:45:50',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:50',NULL,'开发者'),(237,'2013-11-30 07:45:50',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:50',NULL,'开发者'),(238,'2013-11-30 07:45:50',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:50',NULL,'开发者'),(239,'2013-11-30 07:45:50',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:50',NULL,'开发者'),(240,'2013-11-30 07:45:51',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:51',NULL,'开发者'),(241,'2013-11-30 07:45:51',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:51',NULL,'开发者'),(242,'2013-11-30 07:45:51',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:51',NULL,'开发者'),(243,'2013-11-30 07:45:51',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:51',NULL,'开发者'),(244,'2013-11-30 07:45:52',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:52',NULL,'开发者'),(245,'2013-11-30 07:45:52',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:52',NULL,'开发者'),(246,'2013-11-30 07:45:52',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:52',NULL,'开发者'),(247,'2013-11-30 07:45:52',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:52',NULL,'开发者'),(248,'2013-11-30 07:45:52',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:52',NULL,'开发者'),(249,'2013-11-30 07:45:52',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:52',NULL,'开发者'),(250,'2013-11-30 07:45:52',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:52',NULL,'开发者'),(251,'2013-11-30 07:45:52',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:52',NULL,'开发者'),(252,'2013-11-30 07:45:52',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:52',NULL,'开发者'),(253,'2013-11-30 07:45:53',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:53',NULL,'开发者'),(254,'2013-11-30 07:45:53',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:53',NULL,'开发者'),(255,'2013-11-30 07:45:53',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:53',NULL,'开发者'),(256,'2013-11-30 07:45:53',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:53',NULL,'开发者'),(257,'2013-11-30 07:45:53',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:53',NULL,'开发者'),(258,'2013-11-30 07:45:53',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:53',NULL,'开发者'),(259,'2013-11-30 07:45:53',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:53',NULL,'开发者'),(260,'2013-11-30 07:45:54',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:54',NULL,'开发者'),(261,'2013-11-30 07:45:54',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:54',NULL,'开发者'),(262,'2013-11-30 07:45:54',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:54',NULL,'开发者'),(263,'2013-11-30 07:45:54',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:54',NULL,'开发者'),(264,'2013-11-30 07:45:54',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:54',NULL,'开发者'),(265,'2013-11-30 07:45:54',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:54',NULL,'开发者'),(266,'2013-11-30 07:45:54',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:54',NULL,'开发者'),(267,'2013-11-30 07:45:54',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:54',NULL,'开发者'),(268,'2013-11-30 07:45:55',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:55',NULL,'开发者'),(269,'2013-11-30 07:45:55',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:55',NULL,'开发者'),(270,'2013-11-30 07:45:55',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:55',NULL,'开发者'),(271,'2013-11-30 07:45:55',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:45:55',NULL,'开发者'),(272,'2013-11-30 07:46:00',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:00',NULL,'开发者'),(273,'2013-11-30 07:46:00',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:00',NULL,'开发者'),(274,'2013-11-30 07:46:00',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:00',NULL,'开发者'),(275,'2013-11-30 07:46:00',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:00',NULL,'开发者'),(276,'2013-11-30 07:46:00',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:00',NULL,'开发者'),(277,'2013-11-30 07:46:01',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:01',NULL,'开发者'),(278,'2013-11-30 07:46:01',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:01',NULL,'开发者'),(279,'2013-11-30 07:46:01',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:01',NULL,'开发者'),(280,'2013-11-30 07:46:01',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:01',NULL,'开发者'),(281,'2013-11-30 07:46:01',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:01',NULL,'开发者'),(282,'2013-11-30 07:46:01',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:01',NULL,'开发者'),(283,'2013-11-30 07:46:01',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:01',NULL,'开发者'),(284,'2013-11-30 07:46:01',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:01',NULL,'开发者'),(285,'2013-11-30 07:46:01',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:01',NULL,'开发者'),(286,'2013-11-30 07:46:01',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:01',NULL,'开发者'),(287,'2013-11-30 07:46:01',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:01',NULL,'开发者'),(288,'2013-11-30 07:46:01',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:01',NULL,'开发者'),(289,'2013-11-30 07:46:02',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:02',NULL,'开发者'),(290,'2013-11-30 07:46:02',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:02',NULL,'开发者'),(291,'2013-11-30 07:46:02',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:02',NULL,'开发者'),(292,'2013-11-30 07:46:03',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:03',NULL,'开发者'),(293,'2013-11-30 07:46:03',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:03',NULL,'开发者'),(294,'2013-11-30 07:46:03',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:03',NULL,'开发者'),(295,'2013-11-30 07:46:03',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:03',NULL,'开发者'),(296,'2013-11-30 07:46:03',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:03',NULL,'开发者'),(297,'2013-11-30 07:46:03',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:03',NULL,'开发者'),(298,'2013-11-30 07:46:03',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:03',NULL,'开发者'),(299,'2013-11-30 07:46:03',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:03',NULL,'开发者'),(300,'2013-11-30 07:46:03',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:03',NULL,'开发者'),(301,'2013-11-30 07:46:04',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:04',NULL,'开发者'),(302,'2013-11-30 07:46:04',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:04',NULL,'开发者'),(303,'2013-11-30 07:46:04',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:04',NULL,'开发者'),(304,'2013-11-30 07:46:04',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:04',NULL,'开发者'),(305,'2013-11-30 07:46:04',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:04',NULL,'开发者'),(306,'2013-11-30 07:46:04',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:04',NULL,'开发者'),(307,'2013-11-30 07:46:04',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:04',NULL,'开发者'),(308,'2013-11-30 07:46:04',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:04',NULL,'开发者'),(309,'2013-11-30 07:46:05',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:05',NULL,'开发者'),(310,'2013-11-30 07:46:05',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:05',NULL,'开发者'),(311,'2013-11-30 07:46:05',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:05',NULL,'开发者'),(312,'2013-11-30 07:46:05',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:05',NULL,'开发者'),(313,'2013-11-30 07:46:05',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:05',NULL,'开发者'),(314,'2013-11-30 07:46:05',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:05',NULL,'开发者'),(315,'2013-11-30 07:46:05',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:05',NULL,'开发者'),(316,'2013-11-30 07:46:05',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:05',NULL,'开发者'),(317,'2013-11-30 07:46:06',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:06',NULL,'开发者'),(318,'2013-11-30 07:46:06',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:06',NULL,'开发者'),(319,'2013-11-30 07:46:06',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:06',NULL,'开发者'),(320,'2013-11-30 07:46:06',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:06',NULL,'开发者'),(321,'2013-11-30 07:46:06',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:06',NULL,'开发者'),(322,'2013-11-30 07:46:06',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:06',NULL,'开发者'),(323,'2013-11-30 07:46:07',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:07',NULL,'开发者'),(324,'2013-11-30 07:46:10',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:10',NULL,'开发者'),(325,'2013-11-30 07:46:10',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:10',NULL,'开发者'),(326,'2013-11-30 07:46:10',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:10',NULL,'开发者'),(327,'2013-11-30 07:46:10',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:10',NULL,'开发者'),(328,'2013-11-30 07:46:10',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:10',NULL,'开发者'),(329,'2013-11-30 07:46:10',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:10',NULL,'开发者'),(330,'2013-11-30 07:46:10',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:10',NULL,'开发者'),(331,'2013-11-30 07:46:10',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:10',NULL,'开发者'),(332,'2013-11-30 07:46:10',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:10',NULL,'开发者'),(333,'2013-11-30 07:46:10',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:10',NULL,'开发者'),(334,'2013-11-30 07:46:10',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:10',NULL,'开发者'),(335,'2013-11-30 07:46:11',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:11',NULL,'开发者'),(336,'2013-11-30 07:46:11',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:11',NULL,'开发者'),(337,'2013-11-30 07:46:11',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:11',NULL,'开发者'),(338,'2013-11-30 07:46:11',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:11',NULL,'开发者'),(339,'2013-11-30 07:46:11',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:11',NULL,'开发者'),(340,'2013-11-30 07:46:11',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:11',NULL,'开发者'),(341,'2013-11-30 07:46:11',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:11',NULL,'开发者'),(342,'2013-11-30 07:46:12',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:12',NULL,'开发者'),(343,'2013-11-30 07:46:12',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:12',NULL,'开发者'),(344,'2013-11-30 07:46:12',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:12',NULL,'开发者'),(345,'2013-11-30 07:46:12',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:12',NULL,'开发者'),(346,'2013-11-30 07:46:12',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:12',NULL,'开发者'),(347,'2013-11-30 07:46:12',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:12',NULL,'开发者'),(348,'2013-11-30 07:46:12',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:12',NULL,'开发者'),(349,'2013-11-30 07:46:12',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:12',NULL,'开发者'),(350,'2013-11-30 07:46:12',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:12',NULL,'开发者'),(351,'2013-11-30 07:46:13',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:13',NULL,'开发者'),(352,'2013-11-30 07:46:13',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:13',NULL,'开发者'),(353,'2013-11-30 07:46:13',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:13',NULL,'开发者'),(354,'2013-11-30 07:46:13',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:13',NULL,'开发者'),(355,'2013-11-30 07:46:13',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:13',NULL,'开发者'),(356,'2013-11-30 07:46:13',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:13',NULL,'开发者'),(357,'2013-11-30 07:46:14',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:14',NULL,'开发者'),(358,'2013-11-30 07:46:14',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:14',NULL,'开发者'),(359,'2013-11-30 07:46:14',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:14',NULL,'开发者'),(360,'2013-11-30 07:46:14',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:14',NULL,'开发者'),(361,'2013-11-30 07:46:14',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:14',NULL,'开发者'),(362,'2013-11-30 07:46:14',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:14',NULL,'开发者'),(363,'2013-11-30 07:46:14',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:14',NULL,'开发者'),(364,'2013-11-30 07:46:15',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:15',NULL,'开发者'),(365,'2013-11-30 07:46:15',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:15',NULL,'开发者'),(366,'2013-11-30 07:46:15',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:15',NULL,'开发者'),(367,'2013-11-30 07:46:15',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:15',NULL,'开发者'),(368,'2013-11-30 07:46:15',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:15',NULL,'开发者'),(369,'2013-11-30 07:46:15',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:15',NULL,'开发者'),(370,'2013-11-30 07:46:15',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:15',NULL,'开发者'),(371,'2013-11-30 07:46:15',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:15',NULL,'开发者'),(372,'2013-11-30 07:46:16',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:16',NULL,'开发者'),(373,'2013-11-30 07:46:16',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:16',NULL,'开发者'),(374,'2013-11-30 07:46:16',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:16',NULL,'开发者'),(375,'2013-11-30 07:46:16',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:16',NULL,'开发者'),(376,'2013-11-30 07:46:19',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:19',NULL,'开发者'),(377,'2013-11-30 07:46:19',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:19',NULL,'开发者'),(378,'2013-11-30 07:46:19',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:19',NULL,'开发者'),(379,'2013-11-30 07:46:19',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:19',NULL,'开发者'),(380,'2013-11-30 07:46:19',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:19',NULL,'开发者'),(381,'2013-11-30 07:46:20',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:20',NULL,'开发者'),(382,'2013-11-30 07:46:20',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:20',NULL,'开发者'),(383,'2013-11-30 07:46:20',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:20',NULL,'开发者'),(384,'2013-11-30 07:46:20',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:20',NULL,'开发者'),(385,'2013-11-30 07:46:20',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:20',NULL,'开发者'),(386,'2013-11-30 07:46:20',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:20',NULL,'开发者'),(387,'2013-11-30 07:46:20',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:20',NULL,'开发者'),(388,'2013-11-30 07:46:20',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:20',NULL,'开发者'),(389,'2013-11-30 07:46:20',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:20',NULL,'开发者'),(390,'2013-11-30 07:46:20',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:20',NULL,'开发者'),(391,'2013-11-30 07:46:20',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:20',NULL,'开发者'),(392,'2013-11-30 07:46:20',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:20',NULL,'开发者'),(393,'2013-11-30 07:46:20',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:20',NULL,'开发者'),(394,'2013-11-30 07:46:21',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:21',NULL,'开发者'),(395,'2013-11-30 07:46:21',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:21',NULL,'开发者'),(396,'2013-11-30 07:46:21',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:21',NULL,'开发者'),(397,'2013-11-30 07:46:21',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:21',NULL,'开发者'),(398,'2013-11-30 07:46:21',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:21',NULL,'开发者'),(399,'2013-11-30 07:46:21',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:21',NULL,'开发者'),(400,'2013-11-30 07:46:21',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:21',NULL,'开发者'),(401,'2013-11-30 07:46:22',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:22',NULL,'开发者'),(402,'2013-11-30 07:46:22',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:22',NULL,'开发者'),(403,'2013-11-30 07:46:22',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:22',NULL,'开发者'),(404,'2013-11-30 07:46:22',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:22',NULL,'开发者'),(405,'2013-11-30 07:46:22',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:22',NULL,'开发者'),(406,'2013-11-30 07:46:22',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:22',NULL,'开发者'),(407,'2013-11-30 07:46:22',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:22',NULL,'开发者'),(408,'2013-11-30 07:46:23',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:23',NULL,'开发者'),(409,'2013-11-30 07:46:23',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:23',NULL,'开发者'),(410,'2013-11-30 07:46:23',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:23',NULL,'开发者'),(411,'2013-11-30 07:46:23',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:23',NULL,'开发者'),(412,'2013-11-30 07:46:23',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:23',NULL,'开发者'),(413,'2013-11-30 07:46:23',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:23',NULL,'开发者'),(414,'2013-11-30 07:46:23',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:23',NULL,'开发者'),(415,'2013-11-30 07:46:23',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:23',NULL,'开发者'),(416,'2013-11-30 07:46:24',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:24',NULL,'开发者'),(417,'2013-11-30 07:46:24',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:24',NULL,'开发者'),(418,'2013-11-30 07:46:24',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:24',NULL,'开发者'),(419,'2013-11-30 07:46:24',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:24',NULL,'开发者'),(420,'2013-11-30 07:46:24',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:24',NULL,'开发者'),(421,'2013-11-30 07:46:24',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:24',NULL,'开发者'),(422,'2013-11-30 07:46:24',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:24',NULL,'开发者'),(423,'2013-11-30 07:46:25',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:25',NULL,'开发者'),(424,'2013-11-30 07:46:25',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:25',NULL,'开发者'),(425,'2013-11-30 07:46:26',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:26',NULL,'开发者'),(426,'2013-11-30 07:46:26',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:26',NULL,'开发者'),(427,'2013-11-30 07:46:27',NULL,'0:0:0:0:0:0:0:1','查询角色的功能权限信息','2013-11-30 19:46:27',NULL,'开发者'),(428,'2013-11-30 08:50:35',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-30 20:50:35',NULL,'开发者'),(429,'2013-11-30 08:51:49',NULL,'0:0:0:0:0:0:0:1','查询数据字典分组信息','2013-11-30 20:51:49',NULL,'开发者'),(430,'2013-11-30 08:51:49',NULL,'0:0:0:0:0:0:0:1','查询数据字典分组信息','2013-11-30 20:51:49',NULL,'开发者'),(431,'2013-11-30 08:51:50',NULL,'0:0:0:0:0:0:0:1','查询数据字典信息','2013-11-30 20:51:50',NULL,'开发者'),(432,'2013-11-30 08:53:05',NULL,'0:0:0:0:0:0:0:1','保存数据字典信息','2013-11-30 20:53:05',NULL,'开发者'),(433,'2013-11-30 08:53:06',NULL,'0:0:0:0:0:0:0:1','查询数据字典信息','2013-11-30 20:53:06',NULL,'开发者'),(434,'2013-11-30 08:53:33',NULL,'0:0:0:0:0:0:0:1','保存数据字典信息','2013-11-30 20:53:33',NULL,'开发者'),(435,'2013-11-30 08:53:34',NULL,'0:0:0:0:0:0:0:1','查询数据字典信息','2013-11-30 20:53:34',NULL,'开发者'),(436,'2013-11-30 08:53:50',NULL,'0:0:0:0:0:0:0:1','保存数据字典信息','2013-11-30 20:53:50',NULL,'开发者'),(437,'2013-11-30 08:53:51',NULL,'0:0:0:0:0:0:0:1','查询数据字典信息','2013-11-30 20:53:51',NULL,'开发者'),(438,'2013-11-30 08:55:48',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-30 20:55:48',NULL,'开发者'),(439,'2013-11-30 08:56:11',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-30 20:56:11',NULL,'开发者'),(440,'2013-11-30 08:56:20',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-30 20:56:20',NULL,'开发者'),(441,'2013-11-30 08:56:25',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-30 20:56:25',NULL,'开发者'),(442,'2013-11-30 08:56:30',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-30 20:56:30',NULL,'开发者'),(443,'2013-11-30 08:57:16',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-30 20:57:16',NULL,'开发者'),(444,'2013-11-30 08:57:17',NULL,'0:0:0:0:0:0:0:1','通过分组编号查找字典信息','2013-11-30 20:57:17',NULL,'开发者'),(445,'2013-11-30 09:00:20',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-30 21:00:20',NULL,'开发者'),(446,'2013-11-30 09:00:21',NULL,'0:0:0:0:0:0:0:1','通过分组编号查找字典信息','2013-11-30 21:00:21',NULL,'开发者'),(447,'2013-11-30 09:01:29',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-30 21:01:29',NULL,'开发者'),(448,'2013-11-30 09:01:31',NULL,'0:0:0:0:0:0:0:1','通过分组编号查找字典信息','2013-11-30 21:01:31',NULL,'开发者'),(449,'2013-11-30 09:02:32','Could not execute JDBC batch update','0:0:0:0:0:0:0:1','保存项目预算单信息','2013-11-30 21:02:32',NULL,'开发者'),(450,'2013-11-30 09:04:39',NULL,'0:0:0:0:0:0:0:1','保存项目预算单信息','2013-11-30 21:04:39',NULL,'开发者'),(451,'2013-11-30 09:04:40',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-30 21:04:40',NULL,'开发者'),(452,'2013-11-30 09:10:14',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-30 21:10:14',NULL,'开发者'),(453,'2013-11-30 09:11:58',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-30 21:11:58',NULL,'开发者'),(454,'2013-11-30 09:12:43',NULL,'0:0:0:0:0:0:0:1','通过分组编号查找字典信息','2013-11-30 21:12:43',NULL,'开发者'),(455,'2013-11-30 09:12:48',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-30 21:12:48',NULL,'开发者'),(456,'2013-11-30 09:12:49',NULL,'0:0:0:0:0:0:0:1','通过分组编号查找字典信息','2013-11-30 21:12:49',NULL,'开发者'),(457,'2013-11-30 09:16:15',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:16:15',NULL,'开发者'),(458,'2013-11-30 09:19:41',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:19:41',NULL,'开发者'),(459,'2013-11-30 09:20:12',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:20:12',NULL,'开发者'),(460,'2013-11-30 09:21:50',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:21:50',NULL,'开发者'),(461,'2013-11-30 09:23:44',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:23:44',NULL,'开发者'),(462,'2013-11-30 09:24:26',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:24:26',NULL,'开发者'),(463,'2013-11-30 09:24:57',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:24:57',NULL,'开发者'),(464,'2013-11-30 09:25:44',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:25:44',NULL,'开发者'),(465,'2013-11-30 09:26:24',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:26:24',NULL,'开发者'),(466,'2013-11-30 09:26:26',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:26:26',NULL,'开发者'),(467,'2013-11-30 09:28:56',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:28:56',NULL,'开发者'),(468,'2013-11-30 09:29:06',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:29:06',NULL,'开发者'),(469,'2013-11-30 09:29:22',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:29:22',NULL,'开发者'),(470,'2013-11-30 09:29:59',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:29:59',NULL,'开发者'),(471,'2013-11-30 09:30:02',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:30:02',NULL,'开发者'),(472,'2013-11-30 09:30:27',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:30:27',NULL,'开发者'),(473,'2013-11-30 09:30:29',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:30:29',NULL,'开发者'),(474,'2013-11-30 09:31:01',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:31:01',NULL,'开发者'),(475,'2013-11-30 09:31:49',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:31:49',NULL,'开发者'),(476,'2013-11-30 09:32:05',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:32:05',NULL,'开发者'),(477,'2013-11-30 09:32:24',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:32:24',NULL,'开发者'),(478,'2013-11-30 09:34:18',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:34:18',NULL,'开发者'),(479,'2013-11-30 09:38:08',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:38:08',NULL,'开发者'),(480,'2013-11-30 09:38:18',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:38:18',NULL,'开发者'),(481,'2013-11-30 09:38:30',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:38:30',NULL,'开发者'),(482,'2013-11-30 09:38:33',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:38:33',NULL,'开发者'),(483,'2013-11-30 09:38:49',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:38:49',NULL,'开发者'),(484,'2013-11-30 09:39:01',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 21:39:01',NULL,'开发者'),(485,'2013-11-30 11:01:53',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 23:01:53',NULL,'开发者'),(486,'2013-11-30 11:01:59',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-30 23:01:59',NULL,'开发者'),(487,'2013-11-30 11:02:03',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 23:02:03',NULL,'开发者'),(488,'2013-11-30 11:02:07',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 23:02:07',NULL,'开发者'),(489,'2013-11-30 11:02:12',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 23:02:12',NULL,'开发者'),(490,'2013-11-30 11:25:55',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-11-30 23:25:55',NULL,'开发者'),(491,'2013-11-30 11:25:58',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-30 23:25:58',NULL,'开发者'),(492,'2013-11-30 11:26:03',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-11-30 23:26:03',NULL,'开发者'),(493,'2013-11-30 11:26:04',NULL,'0:0:0:0:0:0:0:1','通过分组编号查找字典信息','2013-11-30 23:26:04',NULL,'开发者'),(494,'2013-11-30 11:27:57','Document is not mapped [FROM Document, com.bluefat.budget.bean.WorkFlow]','0:0:0:0:0:0:0:1','保存项目预算单信息','2013-11-30 23:27:57',NULL,'开发者'),(495,'2013-12-01 11:26:43',NULL,'0:0:0:0:0:0:0:1','查询部门列表信息','2013-12-01 11:26:43',NULL,'开发者'),(496,'2013-12-01 11:26:48',NULL,'0:0:0:0:0:0:0:1','项目预算单查询','2013-12-01 11:26:48',NULL,'开发者'),(100000,'2013-11-20 09:33:36',NULL,'192.168.1.111','测试','2013-11-20 09:33:36','测试','开发者');

/*Table structure for table `sys_resource` */

DROP TABLE IF EXISTS `sys_resource`;

CREATE TABLE `sys_resource` (
  `f_id` varchar(32) NOT NULL,
  `f_create_time` varchar(50) default NULL,
  `f_remark` longtext,
  `f_code` varchar(20) NOT NULL,
  `f_name` varchar(100) NOT NULL,
  `f_handler` longtext,
  `f_icon` varchar(50) default NULL,
  `f_parent_code` varchar(20) default NULL,
  `f_state` varchar(10) default NULL,
  `f_type` varchar(50) default NULL,
  `f_url` varchar(200) default NULL,
  `f_version` int(11) NOT NULL,
  PRIMARY KEY  (`f_id`),
  UNIQUE KEY `f_code` (`f_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_resource` */

insert  into `sys_resource`(`f_id`,`f_create_time`,`f_remark`,`f_code`,`f_name`,`f_handler`,`f_icon`,`f_parent_code`,`f_state`,`f_type`,`f_url`,`f_version`) values ('4028fe81429270e2014292b8b5650034','2013-11-26 12:44:07',NULL,'4','事务处理',NULL,NULL,'root','启用','accordion',NULL,0),('4028fe81429270e2014292b8b5650035','2013-11-26 12:44:07',NULL,'41','项目预算单',NULL,NULL,'4','启用','menu','budget/projectBudgetDocument/auditListPage.do',0),('4028fe81429270e2014292b8b5690036','2013-11-26 12:44:07',NULL,'42','项目支出预算单',NULL,NULL,'4','启用','menu','budget/projectBudgetDocument/listPage.do',0),('4028fe81429270e2014292b8b56a0037','2013-11-26 12:44:07',NULL,'43','费用年度预算单',NULL,NULL,'4','启用','menu','budget/projectBudgetDocument/listPage.do',0),('4028fe81429270e2014292b8b56b0038','2013-11-26 12:44:07',NULL,'44','年度支出预算单',NULL,NULL,'4','启用','menu','budget/projectBudgetDocument/listPage.do',0),('4028fe81429270e2014292b8b56b0039','2013-11-26 12:44:07',NULL,'45','季度或月度支出预算单',NULL,NULL,'4','启用','menu','budget/projectBudgetDocument/listPage.do',0),('4028fe81429270e2014292b8b56c003a','2013-11-26 12:44:07',NULL,'46','报销单',NULL,NULL,'4','启用','menu','budget/projectBudgetDocument/listPage.do',0),('4028fe81429270e2014292b8b56c003b','2013-11-26 12:44:07',NULL,'47','付款申请单',NULL,NULL,'4','启用','menu','budget/projectBudgetDocument/listPage.do',0),('4028fe81429270e2014292b8b56d003c','2013-11-26 12:44:07',NULL,'48','借款申请单',NULL,NULL,'4','启用','menu','budget/projectBudgetDocument/listPage.do',0),('4028fe81429270e2014292b8b56e003d','2013-11-26 12:44:07',NULL,'49','核销单',NULL,NULL,'4','启用','menu','budget/projectBudgetDocument/listPage.do',0),('4028fe81429270e2014292b8b56e003e','2013-11-26 12:44:07',NULL,'4A','付款单',NULL,NULL,'4','启用','menu','budget/projectBudgetDocument/listPage.do',0),('4028fe81429270e2014292b8b570003f','2013-11-26 12:44:07',NULL,'4B','其他费用确认单',NULL,NULL,'4','启用','menu','budget/projectBudgetDocument/listPage.do',0),('4028fe81429270e2014292b8b5710040','2013-11-26 12:44:07',NULL,'4C','收款单',NULL,NULL,'4','启用','menu','budget/projectBudgetDocument/listPage.do',0),('4028fe81429270e2014292b8b5720041','2013-11-26 12:44:07',NULL,'2','预算管理',NULL,NULL,'root','启用','accordion',NULL,0),('4028fe81429270e2014292b8b5720042','2013-11-26 12:44:07',NULL,'21','参数设置',NULL,NULL,'2','启用','menu',NULL,0),('4028fe81429270e2014292b8b5720043','2013-11-26 12:44:07',NULL,'211','部门管理',NULL,NULL,'21','启用','menu','sys/department/addDepartmentPage.do',0),('4028fe81429270e2014292b8b5730044','2013-11-26 12:44:07',NULL,'212','预算指标管理',NULL,NULL,'21','启用','menu','budget/budgetTargetController/budgetTargetList.do',0),('4028fe81429270e2014292b8b5740045','2013-11-26 12:44:07',NULL,'213','费用项管理',NULL,NULL,'21','启用','menu','budget/costItemController/costItemList.do',0),('4028fe81429270e2014292b8b5750046','2013-11-26 12:44:07',NULL,'214','会计科目管理',NULL,NULL,'21','启用','menu','budget/accountingSubject/listPage.do',0),('4028fe81429270e2014292b8b5760047','2013-11-26 12:44:07',NULL,'215','现金流量管理',NULL,NULL,'21','启用','menu','budget/flowsController/flowsList.do',0),('4028fe81429270e2014292b8b5760048','2013-11-26 12:44:07',NULL,'216','客户档案管理',NULL,NULL,'21','启用','menu','budget/clientRecordController/clientRecordList.do',0),('4028fe81429270e2014292b8b5770049','2013-11-26 12:44:07',NULL,'217','供应商管理',NULL,NULL,'21','启用','menu','budget/supplierRecordController/supplierRecordList.do',0),('4028fe81429270e2014292b8b578004a','2013-11-26 12:44:07',NULL,'21A','项目档案',NULL,NULL,'21','启用','menu','budget/itemArchives/listPage.do',0),('4028fe81429270e2014292b8b579004b','2013-11-26 12:44:07',NULL,'21B','项目状态',NULL,NULL,'21','启用','menu','budget/itemState/listPage.do',0),('4028fe81429270e2014292b8b57a004c','2013-11-26 12:44:07',NULL,'218','收款',NULL,NULL,'21','启用','menu','budget/collection1/listPage.do',0),('4028fe81429270e2014292b8b57b004d','2013-11-26 12:44:07',NULL,'219','支出',NULL,NULL,'21','启用','menu','budget/spending/listPage.do',0),('4028fe81429270e2014292b8b57c004e','2013-11-26 12:44:07',NULL,'3','个人自助',NULL,NULL,'root','启用','accordion',NULL,0),('4028fe81429270e2014292b8b57c004f','2013-11-26 12:44:07',NULL,'31','项目预算单',NULL,NULL,'3','启用','menu','budget/projectBudgetDocument/personalListPage.do',0),('4028fe81429270e2014292b8b57d0050','2013-11-26 12:44:07',NULL,'32','项目支出预算单',NULL,NULL,'3','启用','menu','budget/projectBudgetDocument/listPage.do',0),('4028fe81429270e2014292b8b57e0051','2013-11-26 12:44:07',NULL,'33','费用年度预算单',NULL,NULL,'3','启用','menu','budget/projectBudgetDocument/listPage.do',0),('4028fe81429270e2014292b8b57f0052','2013-11-26 12:44:07',NULL,'34','年度支出预算单',NULL,NULL,'3','启用','menu','budget/projectBudgetDocument/listPage.do',0),('4028fe81429270e2014292b8b57f0053','2013-11-26 12:44:07',NULL,'35','季度或月度支出预算单',NULL,NULL,'3','启用','menu','budget/projectBudgetDocument/listPage.do',0),('4028fe81429270e2014292b8b5800054','2013-11-26 12:44:07',NULL,'36','报销单',NULL,NULL,'3','启用','menu','budget/projectBudgetDocument/listPage.do',0),('4028fe81429270e2014292b8b5810055','2013-11-26 12:44:07',NULL,'37','付款申请单',NULL,NULL,'3','启用','menu','budget/projectBudgetDocument/listPage.do',0),('4028fe81429270e2014292b8b5820056','2013-11-26 12:44:07',NULL,'38','借款申请单',NULL,NULL,'3','启用','menu','budget/projectBudgetDocument/listPage.do',0),('4028fe81429270e2014292b8b5830057','2013-11-26 12:44:07',NULL,'39','核销单',NULL,NULL,'3','启用','menu','budget/projectBudgetDocument/listPage.do',0),('4028fe81429270e2014292b8b5840058','2013-11-26 12:44:07',NULL,'3A','付款单',NULL,NULL,'3','启用','menu','budget/projectBudgetDocument/listPage.do',0),('4028fe81429270e2014292b8b5850059','2013-11-26 12:44:07',NULL,'3B','其他费用确认单',NULL,NULL,'3','启用','menu','budget/projectBudgetDocument/listPage.do',0),('4028fe81429270e2014292b8b585005a','2013-11-26 12:44:07',NULL,'3C','收款单',NULL,NULL,'3','启用','menu','budget/projectBudgetDocument/listPage.do',0),('4028fe81429270e2014292b8b588005b','2013-11-26 12:44:07',NULL,'1','系统管理',NULL,NULL,'root','启用','accordion',NULL,0),('4028fe81429270e2014292b8b588005c','2013-11-26 12:44:07',NULL,'11','权限管理',NULL,NULL,'1','启用','menu',NULL,0),('4028fe81429270e2014292b8b588005d','2013-11-26 12:44:07',NULL,'111','部门管理',NULL,NULL,'11','启用','menu','sys/department/addDepartmentPage.do',0),('4028fe81429270e2014292b8b58a005e','2013-11-26 12:44:07',NULL,'112','用户管理',NULL,NULL,'11','启用','menu','sys/user/addPage.do',0),('4028fe81429270e2014292b8b58b005f','2013-11-26 12:44:07',NULL,'113','角色管理',NULL,NULL,'11','启用','menu','sys/role/listPage.do',0),('4028fe81429270e2014292b8b58c0060','2013-11-26 12:44:07',NULL,'114','资源管理',NULL,NULL,'11','启用','menu','sys/resource/listPage.do',0),('4028fe81429270e2014292b8b58c0061','2013-11-26 12:44:07',NULL,'12','数据备份还原',NULL,NULL,'1','启用','menu',NULL,0),('4028fe81429270e2014292b8b58c0062','2013-11-26 12:44:07',NULL,'121','数据备份与还原',NULL,NULL,'12','启用','menu','sys/sqlbackup/listPage.do',0),('4028fe81429270e2014292b8b58e0063','2013-11-26 12:44:07',NULL,'13','系统信息',NULL,NULL,'1','启用','menu',NULL,0),('4028fe81429270e2014292b8b58e0064','2013-11-26 12:44:07',NULL,'131','日志管理',NULL,NULL,'13','启用','menu','sys/log/listPage.do',0),('4028fe81429270e2014292b8b58f0065','2013-11-26 12:44:07',NULL,'132','系统基本信息',NULL,NULL,'13','启用','menu','sys/info/showInfo.do',0),('4028fe81429270e2014292b8b5900066','2013-11-26 12:44:07',NULL,'14','数据字典',NULL,NULL,'1','启用','menu','sys/dict/listPage.do',0);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `f_id` varchar(32) NOT NULL,
  `f_create_time` varchar(50) default NULL,
  `f_remark` longtext,
  `f_code` varchar(20) NOT NULL,
  `f_name` varchar(100) NOT NULL,
  `f_state` varchar(255) default NULL,
  `f_version` int(11) NOT NULL,
  PRIMARY KEY  (`f_id`),
  UNIQUE KEY `f_code` (`f_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`f_id`,`f_create_time`,`f_remark`,`f_code`,`f_name`,`f_state`,`f_version`) values ('4028fe814256412e01425645113c0001','2013-11-14 07:00:36','','1001','系统管理员','启用',0),('4028fe814287b169014287b38a500000','2013-11-24 09:22:39','制单人','1002','个人用户','启用',0),('4028fe814287b169014287b3c93f0001','2013-11-24 09:22:55',NULL,'1003','部门经理','启用',0),('4028fe814287b169014287b413520002','2013-11-24 09:23:14',NULL,'1004','项目经理','启用',0),('4028fe814287b169014287b44bb50003','2013-11-24 09:23:29',NULL,'1005','财务会计','启用',0),('4028fe814287b169014287b47e360004','2013-11-24 09:23:42',NULL,'1006','总经理','启用',0);

/*Table structure for table `sys_role_resource` */

DROP TABLE IF EXISTS `sys_role_resource`;

CREATE TABLE `sys_role_resource` (
  `f_id` varchar(32) NOT NULL,
  `f_create_time` varchar(50) default NULL,
  `f_remark` longtext,
  `f_parent_resource_code` varchar(255) default NULL,
  `f_resource_code` varchar(255) default NULL,
  `f_resource_name` varchar(255) default NULL,
  `f_resource_type` varchar(255) default NULL,
  `f_role_code` varchar(255) default NULL,
  `f_role_name` varchar(255) default NULL,
  `f_url` varchar(255) default NULL,
  `f_version` int(11) NOT NULL,
  PRIMARY KEY  (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_resource` */

insert  into `sys_role_resource`(`f_id`,`f_create_time`,`f_remark`,`f_parent_resource_code`,`f_resource_code`,`f_resource_name`,`f_resource_type`,`f_role_code`,`f_role_name`,`f_url`,`f_version`) values ('4028fe814287c1dc014287d11627005b','2013-11-24 09:54:56',NULL,'root','3','个人自助','accordion','1006','总经理',NULL,0),('4028fe814287c1dc014287d11629005c','2013-11-24 09:54:56',NULL,'3','3A','付款单','menu','1006','总经理','budget/projectBudgetDocument/listPage.do',0),('4028fe814287c1dc014287d11629005d','2013-11-24 09:54:56',NULL,'3','37','付款申请单','menu','1006','总经理','budget/projectBudgetDocument/listPage.do',0),('4028fe814287c1dc014287d11629005e','2013-11-24 09:54:56',NULL,'3','38','借款申请单','menu','1006','总经理','budget/projectBudgetDocument/listPage.do',0),('4028fe814287c1dc014287d11629005f','2013-11-24 09:54:56',NULL,'3','3B','其他费用确认单','menu','1006','总经理','budget/projectBudgetDocument/listPage.do',0),('4028fe814287c1dc014287d116290060','2013-11-24 09:54:56',NULL,'3','35','季度或月度支出预算单','menu','1006','总经理','budget/projectBudgetDocument/listPage.do',0),('4028fe814287c1dc014287d116290061','2013-11-24 09:54:56',NULL,'3','34','年度支出预算单','menu','1006','总经理','budget/projectBudgetDocument/listPage.do',0),('4028fe814287c1dc014287d116290062','2013-11-24 09:54:56',NULL,'3','36','报销单','menu','1006','总经理','budget/projectBudgetDocument/listPage.do',0),('4028fe814287c1dc014287d116290063','2013-11-24 09:54:56',NULL,'3','3C','收款单','menu','1006','总经理','budget/projectBudgetDocument/listPage.do',0),('4028fe814287c1dc014287d116290064','2013-11-24 09:54:56',NULL,'3','39','核销单','menu','1006','总经理','budget/projectBudgetDocument/listPage.do',0),('4028fe814287c1dc014287d116290065','2013-11-24 09:54:56',NULL,'3','33','费用年度预算单','menu','1006','总经理','budget/projectBudgetDocument/listPage.do',0),('4028fe814287c1dc014287d116290066','2013-11-24 09:54:56',NULL,'3','32','项目支出预算单','menu','1006','总经理','budget/projectBudgetDocument/listPage.do',0),('4028fe814287c1dc014287d116290067','2013-11-24 09:54:56',NULL,'3','31','项目预算单','menu','1006','总经理','budget/projectBudgetDocument/listPage.do',0),('4028fe814287c1dc014287d116290068','2013-11-24 09:54:56',NULL,'root','4','事务处理','accordion','1006','总经理',NULL,0),('4028fe814287c1dc014287d116290069','2013-11-24 09:54:56',NULL,'4','4A','付款单','menu','1006','总经理','budget/projectBudgetDocument/listPage.do',0),('4028fe814287c1dc014287d11629006a','2013-11-24 09:54:56',NULL,'4','47','付款申请单','menu','1006','总经理','budget/projectBudgetDocument/listPage.do',0),('4028fe814287c1dc014287d11629006b','2013-11-24 09:54:56',NULL,'4','48','借款申请单','menu','1006','总经理','budget/projectBudgetDocument/listPage.do',0),('4028fe814287c1dc014287d11629006c','2013-11-24 09:54:56',NULL,'4','4B','其他费用确认单','menu','1006','总经理','budget/projectBudgetDocument/listPage.do',0),('4028fe814287c1dc014287d11629006d','2013-11-24 09:54:56',NULL,'4','45','季度或月度支出预算单','menu','1006','总经理','budget/projectBudgetDocument/listPage.do',0),('4028fe814287c1dc014287d11629006e','2013-11-24 09:54:56',NULL,'4','44','年度支出预算单','menu','1006','总经理','budget/projectBudgetDocument/listPage.do',0),('4028fe814287c1dc014287d11629006f','2013-11-24 09:54:56',NULL,'4','46','报销单','menu','1006','总经理','budget/projectBudgetDocument/listPage.do',0),('4028fe814287c1dc014287d1162b0070','2013-11-24 09:54:56',NULL,'4','4C','收款单','menu','1006','总经理','budget/projectBudgetDocument/listPage.do',0),('4028fe814287c1dc014287d1162b0071','2013-11-24 09:54:56',NULL,'4','49','核销单','menu','1006','总经理','budget/projectBudgetDocument/listPage.do',0),('4028fe814287c1dc014287d1162b0072','2013-11-24 09:54:56',NULL,'4','43','费用年度预算单','menu','1006','总经理','budget/projectBudgetDocument/listPage.do',0),('4028fe814287c1dc014287d1162b0073','2013-11-24 09:54:56',NULL,'4','42','项目支出预算单','menu','1006','总经理','budget/projectBudgetDocument/listPage.do',0),('4028fe814287c1dc014287d1162b0074','2013-11-24 09:54:56',NULL,'4','41','项目预算单','menu','1006','总经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c61610065','2013-11-24 08:18:56',NULL,'root','3','个人自助','accordion','1002','个人用户',NULL,0),('4028fe81428a0b1701428a0c61620066','2013-11-24 08:18:56',NULL,'3','3A','付款单','menu','1002','个人用户','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c61620067','2013-11-24 08:18:56',NULL,'3','37','付款申请单','menu','1002','个人用户','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c61620068','2013-11-24 08:18:56',NULL,'3','38','借款申请单','menu','1002','个人用户','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c61620069','2013-11-24 08:18:56',NULL,'3','3B','其他费用确认单','menu','1002','个人用户','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c6162006a','2013-11-24 08:18:56',NULL,'3','35','季度或月度支出预算单','menu','1002','个人用户','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c6162006b','2013-11-24 08:18:56',NULL,'3','34','年度支出预算单','menu','1002','个人用户','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c6162006c','2013-11-24 08:18:56',NULL,'3','36','报销单','menu','1002','个人用户','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c6162006d','2013-11-24 08:18:56',NULL,'3','3C','收款单','menu','1002','个人用户','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c6162006e','2013-11-24 08:18:56',NULL,'3','39','核销单','menu','1002','个人用户','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c6162006f','2013-11-24 08:18:56',NULL,'3','33','费用年度预算单','menu','1002','个人用户','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c61620070','2013-11-24 08:18:56',NULL,'3','32','项目支出预算单','menu','1002','个人用户','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c61620071','2013-11-24 08:18:56',NULL,'3','31','项目预算单','menu','1002','个人用户','budget/projectBudgetDocument/personalListPage.do',0),('4028fe81428a0b1701428a0c80500072','2013-11-24 08:19:04',NULL,'root','3','个人自助','accordion','1003','部门经理',NULL,0),('4028fe81428a0b1701428a0c80520073','2013-11-24 08:19:04',NULL,'3','3A','付款单','menu','1003','部门经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c80520074','2013-11-24 08:19:04',NULL,'3','37','付款申请单','menu','1003','部门经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c80520075','2013-11-24 08:19:04',NULL,'3','38','借款申请单','menu','1003','部门经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c80520076','2013-11-24 08:19:04',NULL,'3','3B','其他费用确认单','menu','1003','部门经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c80520077','2013-11-24 08:19:04',NULL,'3','35','季度或月度支出预算单','menu','1003','部门经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c80520078','2013-11-24 08:19:04',NULL,'3','34','年度支出预算单','menu','1003','部门经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c80520079','2013-11-24 08:19:04',NULL,'3','36','报销单','menu','1003','部门经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c8052007a','2013-11-24 08:19:04',NULL,'3','3C','收款单','menu','1003','部门经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c8052007b','2013-11-24 08:19:04',NULL,'3','39','核销单','menu','1003','部门经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c8052007c','2013-11-24 08:19:04',NULL,'3','33','费用年度预算单','menu','1003','部门经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c8053007d','2013-11-24 08:19:04',NULL,'3','32','项目支出预算单','menu','1003','部门经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c8053007e','2013-11-24 08:19:04',NULL,'3','31','项目预算单','menu','1003','部门经理','budget/projectBudgetDocument/personalListPage.do',0),('4028fe81428a0b1701428a0c8053007f','2013-11-24 08:19:04',NULL,'root','4','事务处理','accordion','1003','部门经理',NULL,0),('4028fe81428a0b1701428a0c80530080','2013-11-24 08:19:04',NULL,'4','4A','付款单','menu','1003','部门经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c80530081','2013-11-24 08:19:04',NULL,'4','47','付款申请单','menu','1003','部门经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c80530082','2013-11-24 08:19:04',NULL,'4','48','借款申请单','menu','1003','部门经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c80530083','2013-11-24 08:19:04',NULL,'4','4B','其他费用确认单','menu','1003','部门经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c80530084','2013-11-24 08:19:04',NULL,'4','45','季度或月度支出预算单','menu','1003','部门经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c80530085','2013-11-24 08:19:04',NULL,'4','44','年度支出预算单','menu','1003','部门经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c80530086','2013-11-24 08:19:04',NULL,'4','46','报销单','menu','1003','部门经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c80560087','2013-11-24 08:19:04',NULL,'4','4C','收款单','menu','1003','部门经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c80560088','2013-11-24 08:19:04',NULL,'4','49','核销单','menu','1003','部门经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c80560089','2013-11-24 08:19:04',NULL,'4','43','费用年度预算单','menu','1003','部门经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c8056008a','2013-11-24 08:19:04',NULL,'4','42','项目支出预算单','menu','1003','部门经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c8056008b','2013-11-24 08:19:04',NULL,'4','41','项目预算单','menu','1003','部门经理','budget/projectBudgetDocument/auditListPage.do',0),('4028fe81428a0b1701428a0c9d47008c','2013-11-24 08:19:11',NULL,'root','3','个人自助','accordion','1004','项目经理',NULL,0),('4028fe81428a0b1701428a0c9d50008d','2013-11-24 08:19:11',NULL,'3','3A','付款单','menu','1004','项目经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c9d50008e','2013-11-24 08:19:11',NULL,'3','37','付款申请单','menu','1004','项目经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c9d50008f','2013-11-24 08:19:11',NULL,'3','38','借款申请单','menu','1004','项目经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c9d500090','2013-11-24 08:19:11',NULL,'3','3B','其他费用确认单','menu','1004','项目经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c9d500091','2013-11-24 08:19:11',NULL,'3','35','季度或月度支出预算单','menu','1004','项目经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c9d500092','2013-11-24 08:19:11',NULL,'3','34','年度支出预算单','menu','1004','项目经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c9d510093','2013-11-24 08:19:11',NULL,'3','36','报销单','menu','1004','项目经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c9d510094','2013-11-24 08:19:11',NULL,'3','3C','收款单','menu','1004','项目经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c9d510095','2013-11-24 08:19:11',NULL,'3','39','核销单','menu','1004','项目经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c9d510096','2013-11-24 08:19:11',NULL,'3','33','费用年度预算单','menu','1004','项目经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c9d510097','2013-11-24 08:19:11',NULL,'3','32','项目支出预算单','menu','1004','项目经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c9d510098','2013-11-24 08:19:11',NULL,'3','31','项目预算单','menu','1004','项目经理','budget/projectBudgetDocument/personalListPage.do',0),('4028fe81428a0b1701428a0c9d510099','2013-11-24 08:19:11',NULL,'root','4','事务处理','accordion','1004','项目经理',NULL,0),('4028fe81428a0b1701428a0c9d51009a','2013-11-24 08:19:11',NULL,'4','4A','付款单','menu','1004','项目经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c9d51009b','2013-11-24 08:19:11',NULL,'4','47','付款申请单','menu','1004','项目经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c9d51009c','2013-11-24 08:19:11',NULL,'4','48','借款申请单','menu','1004','项目经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c9d51009d','2013-11-24 08:19:11',NULL,'4','4B','其他费用确认单','menu','1004','项目经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c9d51009e','2013-11-24 08:19:11',NULL,'4','45','季度或月度支出预算单','menu','1004','项目经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c9d51009f','2013-11-24 08:19:11',NULL,'4','44','年度支出预算单','menu','1004','项目经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c9d5100a0','2013-11-24 08:19:11',NULL,'4','46','报销单','menu','1004','项目经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c9d5300a1','2013-11-24 08:19:11',NULL,'4','4C','收款单','menu','1004','项目经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c9d5300a2','2013-11-24 08:19:11',NULL,'4','49','核销单','menu','1004','项目经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c9d5300a3','2013-11-24 08:19:11',NULL,'4','43','费用年度预算单','menu','1004','项目经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c9d5300a4','2013-11-24 08:19:11',NULL,'4','42','项目支出预算单','menu','1004','项目经理','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0c9d5300a5','2013-11-24 08:19:11',NULL,'4','41','项目预算单','menu','1004','项目经理','budget/projectBudgetDocument/auditListPage.do',0),('4028fe81428a0b1701428a0cb5df00a6','2013-11-24 08:19:18',NULL,'root','3','个人自助','accordion','1005','财务会计',NULL,0),('4028fe81428a0b1701428a0cb5e100a7','2013-11-24 08:19:18',NULL,'3','3A','付款单','menu','1005','财务会计','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0cb5e100a8','2013-11-24 08:19:18',NULL,'3','37','付款申请单','menu','1005','财务会计','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0cb5e100a9','2013-11-24 08:19:18',NULL,'3','38','借款申请单','menu','1005','财务会计','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0cb5e100aa','2013-11-24 08:19:18',NULL,'3','3B','其他费用确认单','menu','1005','财务会计','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0cb5e100ab','2013-11-24 08:19:18',NULL,'3','35','季度或月度支出预算单','menu','1005','财务会计','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0cb5e100ac','2013-11-24 08:19:18',NULL,'3','34','年度支出预算单','menu','1005','财务会计','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0cb5e100ad','2013-11-24 08:19:18',NULL,'3','36','报销单','menu','1005','财务会计','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0cb5e100ae','2013-11-24 08:19:18',NULL,'3','3C','收款单','menu','1005','财务会计','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0cb5e100af','2013-11-24 08:19:18',NULL,'3','39','核销单','menu','1005','财务会计','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0cb5e100b0','2013-11-24 08:19:18',NULL,'3','33','费用年度预算单','menu','1005','财务会计','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0cb5e100b1','2013-11-24 08:19:18',NULL,'3','32','项目支出预算单','menu','1005','财务会计','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0cb5e100b2','2013-11-24 08:19:18',NULL,'3','31','项目预算单','menu','1005','财务会计','budget/projectBudgetDocument/personalListPage.do',0),('4028fe81428a0b1701428a0cb5e100b3','2013-11-24 08:19:18',NULL,'root','4','事务处理','accordion','1005','财务会计',NULL,0),('4028fe81428a0b1701428a0cb5e100b4','2013-11-24 08:19:18',NULL,'4','4A','付款单','menu','1005','财务会计','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0cb5e100b5','2013-11-24 08:19:18',NULL,'4','47','付款申请单','menu','1005','财务会计','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0cb5e100b6','2013-11-24 08:19:18',NULL,'4','48','借款申请单','menu','1005','财务会计','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0cb5e100b7','2013-11-24 08:19:18',NULL,'4','4B','其他费用确认单','menu','1005','财务会计','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0cb5e200b8','2013-11-24 08:19:18',NULL,'4','45','季度或月度支出预算单','menu','1005','财务会计','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0cb5e200b9','2013-11-24 08:19:18',NULL,'4','44','年度支出预算单','menu','1005','财务会计','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0cb5e200ba','2013-11-24 08:19:18',NULL,'4','46','报销单','menu','1005','财务会计','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0cb5e400bb','2013-11-24 08:19:18',NULL,'4','4C','收款单','menu','1005','财务会计','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0cb5e400bc','2013-11-24 08:19:18',NULL,'4','49','核销单','menu','1005','财务会计','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0cb5e400bd','2013-11-24 08:19:18',NULL,'4','43','费用年度预算单','menu','1005','财务会计','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0cb5e400be','2013-11-24 08:19:18',NULL,'4','42','项目支出预算单','menu','1005','财务会计','budget/projectBudgetDocument/listPage.do',0),('4028fe81428a0b1701428a0cb5e400bf','2013-11-24 08:19:18',NULL,'4','41','项目预算单','menu','1005','财务会计','budget/projectBudgetDocument/auditListPage.do',0),('4028fe81429c202201429c2fc5b40000','2013-11-28 08:50:45',NULL,'root','3','个人自助','accordion','1001','系统管理员',NULL,0),('4028fe81429c202201429c2fc5b60001','2013-11-28 08:50:45',NULL,'3','3A','付款单','menu','1001','系统管理员','budget/projectBudgetDocument/listPage.do',0),('4028fe81429c202201429c2fc5b60002','2013-11-28 08:50:45',NULL,'3','37','付款申请单','menu','1001','系统管理员','budget/projectBudgetDocument/listPage.do',0),('4028fe81429c202201429c2fc5b60003','2013-11-28 08:50:45',NULL,'3','38','借款申请单','menu','1001','系统管理员','budget/projectBudgetDocument/listPage.do',0),('4028fe81429c202201429c2fc5b60004','2013-11-28 08:50:45',NULL,'3','3B','其他费用确认单','menu','1001','系统管理员','budget/projectBudgetDocument/listPage.do',0),('4028fe81429c202201429c2fc5b60005','2013-11-28 08:50:45',NULL,'3','35','季度或月度支出预算单','menu','1001','系统管理员','budget/projectBudgetDocument/listPage.do',0),('4028fe81429c202201429c2fc5b60006','2013-11-28 08:50:45',NULL,'3','34','年度支出预算单','menu','1001','系统管理员','budget/projectBudgetDocument/listPage.do',0),('4028fe81429c202201429c2fc5b60007','2013-11-28 08:50:45',NULL,'3','36','报销单','menu','1001','系统管理员','budget/projectBudgetDocument/listPage.do',0),('4028fe81429c202201429c2fc5b60008','2013-11-28 08:50:45',NULL,'3','3C','收款单','menu','1001','系统管理员','budget/projectBudgetDocument/listPage.do',0),('4028fe81429c202201429c2fc5b60009','2013-11-28 08:50:45',NULL,'3','39','核销单','menu','1001','系统管理员','budget/projectBudgetDocument/listPage.do',0),('4028fe81429c202201429c2fc5b6000a','2013-11-28 08:50:45',NULL,'3','33','费用年度预算单','menu','1001','系统管理员','budget/projectBudgetDocument/listPage.do',0),('4028fe81429c202201429c2fc5b6000b','2013-11-28 08:50:45',NULL,'3','32','项目支出预算单','menu','1001','系统管理员','budget/projectBudgetDocument/listPage.do',0),('4028fe81429c202201429c2fc5b6000c','2013-11-28 08:50:45',NULL,'3','31','项目预算单','menu','1001','系统管理员','budget/projectBudgetDocument/personalListPage.do',0),('4028fe81429c202201429c2fc5b7000d','2013-11-28 08:50:45',NULL,'root','4','事务处理','accordion','1001','系统管理员',NULL,0),('4028fe81429c202201429c2fc5b7000e','2013-11-28 08:50:45',NULL,'4','4A','付款单','menu','1001','系统管理员','budget/projectBudgetDocument/listPage.do',0),('4028fe81429c202201429c2fc5b7000f','2013-11-28 08:50:45',NULL,'4','47','付款申请单','menu','1001','系统管理员','budget/projectBudgetDocument/listPage.do',0),('4028fe81429c202201429c2fc5b70010','2013-11-28 08:50:45',NULL,'4','48','借款申请单','menu','1001','系统管理员','budget/projectBudgetDocument/listPage.do',0),('4028fe81429c202201429c2fc5b70011','2013-11-28 08:50:45',NULL,'4','4B','其他费用确认单','menu','1001','系统管理员','budget/projectBudgetDocument/listPage.do',0),('4028fe81429c202201429c2fc5b70012','2013-11-28 08:50:45',NULL,'4','45','季度或月度支出预算单','menu','1001','系统管理员','budget/projectBudgetDocument/listPage.do',0),('4028fe81429c202201429c2fc5b70013','2013-11-28 08:50:45',NULL,'4','44','年度支出预算单','menu','1001','系统管理员','budget/projectBudgetDocument/listPage.do',0),('4028fe81429c202201429c2fc5b70014','2013-11-28 08:50:45',NULL,'4','46','报销单','menu','1001','系统管理员','budget/projectBudgetDocument/listPage.do',0),('4028fe81429c202201429c2fc5ba0015','2013-11-28 08:50:45',NULL,'4','4C','收款单','menu','1001','系统管理员','budget/projectBudgetDocument/listPage.do',0),('4028fe81429c202201429c2fc5ba0016','2013-11-28 08:50:45',NULL,'4','49','核销单','menu','1001','系统管理员','budget/projectBudgetDocument/listPage.do',0),('4028fe81429c202201429c2fc5ba0017','2013-11-28 08:50:45',NULL,'4','43','费用年度预算单','menu','1001','系统管理员','budget/projectBudgetDocument/listPage.do',0),('4028fe81429c202201429c2fc5ba0018','2013-11-28 08:50:45',NULL,'4','42','项目支出预算单','menu','1001','系统管理员','budget/projectBudgetDocument/listPage.do',0),('4028fe81429c202201429c2fc5ba0019','2013-11-28 08:50:45',NULL,'4','41','项目预算单','menu','1001','系统管理员','budget/projectBudgetDocument/auditListPage.do',0),('4028fe81429c202201429c2fc5ba001a','2013-11-28 08:50:45',NULL,'root','1','系统管理','accordion','1001','系统管理员',NULL,0),('4028fe81429c202201429c2fc5ba001b','2013-11-28 08:50:45',NULL,'1','12','数据备份还原','menu','1001','系统管理员',NULL,0),('4028fe81429c202201429c2fc5ba001c','2013-11-28 08:50:45',NULL,'12','121','数据备份与还原','menu','1001','系统管理员','sys/sqlbackup/listPage.do',0),('4028fe81429c202201429c2fc5ba001d','2013-11-28 08:50:45',NULL,'1','14','数据字典','menu','1001','系统管理员','sys/dict/listPage.do',0),('4028fe81429c202201429c2fc5ba001e','2013-11-28 08:50:45',NULL,'1','11','权限管理','menu','1001','系统管理员',NULL,0),('4028fe81429c202201429c2fc5ba001f','2013-11-28 08:50:45',NULL,'11','112','用户管理','menu','1001','系统管理员','sys/user/addPage.do',0),('4028fe81429c202201429c2fc5ba0020','2013-11-28 08:50:45',NULL,'11','113','角色管理','menu','1001','系统管理员','sys/role/listPage.do',0),('4028fe81429c202201429c2fc5bb0021','2013-11-28 08:50:45',NULL,'11','114','资源管理','menu','1001','系统管理员','sys/resource/listPage.do',0),('4028fe81429c202201429c2fc5bb0022','2013-11-28 08:50:45',NULL,'11','111','部门管理','menu','1001','系统管理员','sys/department/addDepartmentPage.do',0),('4028fe81429c202201429c2fc5bb0023','2013-11-28 08:50:45',NULL,'1','13','系统信息','menu','1001','系统管理员',NULL,0),('4028fe81429c202201429c2fc5bb0024','2013-11-28 08:50:45',NULL,'13','131','日志管理','menu','1001','系统管理员','sys/log/listPage.do',0),('4028fe81429c202201429c2fc5bb0025','2013-11-28 08:50:45',NULL,'13','132','系统基本信息','menu','1001','系统管理员','sys/info/showInfo.do',0),('4028fe81429c202201429c2fc5bb0026','2013-11-28 08:50:45',NULL,'root','2','预算管理','accordion','1001','系统管理员',NULL,0),('4028fe81429c202201429c2fc5bb0027','2013-11-28 08:50:45',NULL,'2','21','参数设置','menu','1001','系统管理员',NULL,0),('4028fe81429c202201429c2fc5bb0028','2013-11-28 08:50:45',NULL,'21','214','会计科目管理','menu','1001','系统管理员','budget/accountingSubject/listPage.do',0),('4028fe81429c202201429c2fc5bd0029','2013-11-28 08:50:45',NULL,'21','217','供应商管理','menu','1001','系统管理员','budget/supplierRecordController/supplierRecordList.do',0),('4028fe81429c202201429c2fc5bd002a','2013-11-28 08:50:45',NULL,'21','216','客户档案管理','menu','1001','系统管理员','budget/clientRecordController/clientRecordList.do',0),('4028fe81429c202201429c2fc5bd002b','2013-11-28 08:50:45',NULL,'21','219','支出','menu','1001','系统管理员','budget/spending/listPage.do',0),('4028fe81429c202201429c2fc5bd002c','2013-11-28 08:50:45',NULL,'21','218','收款','menu','1001','系统管理员','budget/collection1/listPage.do',0),('4028fe81429c202201429c2fc5bd002d','2013-11-28 08:50:45',NULL,'21','215','现金流量管理','menu','1001','系统管理员','budget/flowsController/flowsList.do',0),('4028fe81429c202201429c2fc5bd002e','2013-11-28 08:50:45',NULL,'21','213','费用项管理','menu','1001','系统管理员','budget/costItemController/costItemList.do',0),('4028fe81429c202201429c2fc5bd002f','2013-11-28 08:50:45',NULL,'21','211','部门管理','menu','1001','系统管理员','sys/department/addDepartmentPage.do',0),('4028fe81429c202201429c2fc5bd0030','2013-11-28 08:50:45',NULL,'21','21A','项目档案','menu','1001','系统管理员','budget/itemArchives/listPage.do',0),('4028fe81429c202201429c2fc5bd0031','2013-11-28 08:50:45',NULL,'21','21B','项目状态','menu','1001','系统管理员','budget/itemState/listPage.do',0),('4028fe81429c202201429c2fc5be0032','2013-11-28 08:50:45',NULL,'21','212','预算指标管理','menu','1001','系统管理员','budget/budgetTargetController/budgetTargetList.do',0);

/*Table structure for table `sys_sql_backup` */

DROP TABLE IF EXISTS `sys_sql_backup`;

CREATE TABLE `sys_sql_backup` (
  `f_id` varchar(32) NOT NULL,
  `f_create_time` varchar(50) default NULL,
  `f_remark` longtext,
  `f_code` varchar(20) NOT NULL,
  `f_name` varchar(100) NOT NULL,
  `f_file_name` varchar(255) default NULL,
  `f_version` int(11) NOT NULL,
  PRIMARY KEY  (`f_id`),
  UNIQUE KEY `f_code` (`f_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_sql_backup` */

insert  into `sys_sql_backup`(`f_id`,`f_create_time`,`f_remark`,`f_code`,`f_name`,`f_file_name`,`f_version`) values ('4028fe81425693ba014256b2970c0000','2013-11-14 09:00:13',NULL,'1','手动备份','db_budget_2013_11_14_21_00_13.sql',0),('4028fe81426e650101426e65a9a80000','2013-11-19 11:27:05',NULL,'2','手动备份','db_budget_2013_11_19_11_27_05.sql',0);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `f_id` varchar(32) NOT NULL,
  `f_create_time` varchar(50) default NULL,
  `f_remark` longtext,
  `f_code` varchar(20) NOT NULL,
  `f_name` varchar(100) NOT NULL,
  `f_department_code` varchar(255) default NULL,
  `f_department_name` varchar(255) default NULL,
  `f_password` varchar(255) default NULL,
  `f_role_code` varchar(255) default NULL,
  `f_role_name` varchar(255) default NULL,
  `f_state` varchar(255) default NULL,
  `f_user_name` varchar(255) default NULL,
  `f_version` int(11) NOT NULL,
  PRIMARY KEY  (`f_id`),
  UNIQUE KEY `f_code` (`f_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`f_id`,`f_create_time`,`f_remark`,`f_code`,`f_name`,`f_department_code`,`f_department_name`,`f_password`,`f_role_code`,`f_role_name`,`f_state`,`f_user_name`,`f_version`) values ('4028fe814287b169014287b656110005','2013-11-24 09:25:43','','100101','张三','1001','阿里巴巴','506184b9ae916191a9e829536808ddc1','1002','个人用户','启用','100101',0),('4028fe814287b169014287b6fd420006','2013-11-24 09:26:25','','100102','部门经理','1001','阿里巴巴','2beb6d92618f0aa12635339d9a6a4412','1003','部门经理','启用','100102',0),('4028fe814287b169014287b770e50007','2013-11-24 09:26:55','','100103','项目经理','1001','阿里巴巴','01043235c54be3d5eee3ebf3ce5be804','1004','项目经理','启用','100103',0),('4028fe814287b169014287b7f3ba0008','2013-11-24 09:27:28','','100201','会计','1002','支付宝','4c76a9809ef0f33a1f6558402c3005f8','1005','财务会计','启用','100201',0),('4028fe814287b169014287b8803f0009','2013-11-24 09:28:04','','100104','总经理','1001','阿里巴巴','294394f986f17198456941ebc14c7f8c','1006','总经理','启用','100104',0),('4028fe81429270e20142929287ac0033','2013-11-26 12:02:25','','1','开发者','1001','阿里巴巴','ceb4f32325eda6142bd65215f4c0f371','1001','系统管理员','启用','admin',0);

/* Function  structure for function  `rand_string` */

/*!50003 DROP FUNCTION IF EXISTS `rand_string` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `rand_string`(n INT) RETURNS varchar(255) CHARSET utf8
BEGIN 
 DECLARE chars_str VARCHAR(100) DEFAULT
   'abcdefghijklmnopqrstuvwxyzABCDEFJHIJKLMNOPQRSTUVWXYZ';
 DECLARE return_str VARCHAR(255) DEFAULT '';
 DECLARE i INT DEFAULT 0;
 WHILE i < n DO 
   SET return_str =CONCAT(return_str,SUBSTRING(chars_str,FLOOR(1+RAND()*52),1));
   SET i = i + 1;
   END WHILE;
  RETURN return_str;
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
