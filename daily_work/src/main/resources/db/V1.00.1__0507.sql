DROP TABLE IF EXISTS `dailywork`;
CREATE TABLE `dailywork` (
`id` int(10) NOT NULL AUTO_INCREMENT,
`createDate` datetime DEFAULT NULL,
`userId` int(10) NOT NULL,
`userName` varchar(20) DEFAULT NULL,
`department` varchar(20) DEFAULT NULL,
`position` varchar(20) DEFAULT NULL,
`projectType` int(10) NOT NULL,
`project` varchar(20) DEFAULT NULL,
`workDate` datetime DEFAULT NULL,
`workType` int(10) NOT NULL,
`workTime` double (16,2) NOT NULL,
`overTimeWorkTime` double (16,2) NOT NULL,
`masterId` int(10) NOT NULL,
`masterName` varchar(20) DEFAULT NULL,
`description` varchar(20) DEFAULT NULL,
 PRIMARY KEY (`id`) )
 ENGINE=InnoDB DEFAULT CHARSET=utf8;
