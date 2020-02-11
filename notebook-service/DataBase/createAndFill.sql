DROP DATABASE IF EXISTS test;
CREATE DATABASE test;
USE test;
CREATE TABLE `test`.`test` (
  `id` INT(8) NOT NULL AUTO_INCREMENT,
  `message` VARCHAR(100) NOT NULL,
  `date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `done` BIT(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`))
COLLATE='utf8_general_ci';

INSERT INTO `test`.`test` (`message`,`date`,`done`) VALUES ('Start the moral preparation for the speedy execution of the test task.', '2018-01-30 10:06:22', 0);
INSERT INTO `test`.`test` (`message`,`date`,`done`) VALUES ('Pay attention to China Mieville creativity. Recommended "Rails"', '2018-01-30 12:02:27', 0);
INSERT INTO `test`.`test` (`message`,`date`,`done`) VALUES ('Continue to maintain the physical form in the power loads', '2018-02-01 15:34:10', 1);
INSERT INTO `test`.`test` (`message`,`date`,`done`) VALUES ('Look in the pet shop and buy tablets for the dog', '2018-02-02 13:59:34', 1);
INSERT INTO `test`.`test` (`message`,`date`,`done`) VALUES ('The sister asked to look into the bookstore and check the availability of "1984"', '2018-02-02 17:36:23', 0);
INSERT INTO `test`.`test` (`message`,`date`,`done`) VALUES ('Alas. Absolutely do not understand anything in the set conditions. It is necessary to think', '2018-02-02 20:50:55', 0);
INSERT INTO `test`.`test` (`message`,`date`,`done`) VALUES ('Still do not understand anything. Pretty hard', '2018-02-03 10:40:59', 0);
INSERT INTO `test`.`test` (`message`,`date`,`done`) VALUES ('Maybe pay attention to Spring Boot? Looks interesting', '2018-02-03 19:29:02', 0);
INSERT INTO `test`.`test` (`message`,`date`,`done`) VALUES ('It remains to determine the visualization. Wide choice, very', '2018-02-04 07:26:08', 1);
INSERT INTO `test`.`test` (`message`,`date`,`done`) VALUES ('Explore the details of entry in html', '2018-02-04 12:11:53', 0);
INSERT INTO `test`.`test` (`message`,`date`,`done`) VALUES ('Try to create the first program to display', '2018-02-04 17:12:35', 0);
INSERT INTO `test`.`test` (`message`,`date`,`done`) VALUES ('Error. I continue to understand nothing. Need to learn the documentation', '2018-02-05 23:15:21', 1);
INSERT INTO `test`.`test` (`message`,`date`,`done`) VALUES ('More documentation to the god of documentation! The error does not go away', '2018-02-06 16:18:19', 0);
INSERT INTO `test`.`test` (`message`,`date`,`done`) VALUES ('Begins to work out. Difficult, but it becomes more or less clear', '2018-02-07 20:45:47', 0);
INSERT INTO `test`.`test` (`message`,`date`,`done`) VALUES ('Explore the intricacies of the Thymeleaf syntax for displaying data', '2018-02-07 22:43:53', 0);
INSERT INTO `test`.`test` (`message`,`date`,`done`) VALUES ('Problems with displaying the table. I need to test another idea', '2018-02-08 14:47:45', 0);
INSERT INTO `test`.`test` (`message`,`date`,`done`) VALUES ('It turns out. It''s crooked, but it works. It is necessary to repair', '2018-02-08 16:18:34', 1);
INSERT INTO `test`.`test` (`message`,`date`,`done`) VALUES ('It is necessary to raise information about reading from the database. Important condition', '2018-02-08 21:24:37', 0);
INSERT INTO `test`.`test` (`message`,`date`,`done`) VALUES ('Maybe try to add a stylization?', '2018-02-09 12:53:15', 0);
INSERT INTO `test`.`test` (`message`,`date`,`done`) VALUES ('Yes! Really works. I need to add a filter', '2018-02-09 13:34:12', 0);
INSERT INTO `test`.`test` (`message`,`date`,`done`) VALUES ('Problems with sorting the date. It is necessary to understand', '2018-02-09 18:10:56', 0);
INSERT INTO `test`.`test` (`message`,`date`,`done`) VALUES ('Done. It remains only to check for errors', '2018-02-10 16:39:08', 0);
INSERT INTO `test`.`test` (`message`,`date`,`done`) VALUES ('And again, add a stylized beauty', '2018-02-11 10:43:33', 0);
INSERT INTO `test`.`test` (`message`,`date`,`done`) VALUES ('And, of course, send the completed task for verification', '2018-02-11 20:56:30', 0);
