CREATE TABLE `eloanregistrationpage` (
  `UserName` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL,
  `MobileNumber` varchar(30) NOT NULL,
  `EmailID` varchar(45) NOT NULL,
  PRIMARY KEY (`MobileNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

UPDATE `eloan_jeevangajawada`.`eloanregistrationpage`
SET
`UserName` = <{UserName: }>,
`Password` = <{Password: }>,
`MobileNumber` = <{MobileNumber: }>,
`EmailID` = <{EmailID: }>
WHERE `MobileNumber` = <{expr}>;

INSERT INTO `eloan_jeevangajawada`.`eloanregistrationpage`
(`UserName`,
`Password`,
`MobileNumber`,
`EmailID`)
VALUES
(<{UserName: }>,
<{Password: }>,
<{MobileNumber: }>,
<{EmailID: }>);

DELETE FROM `eloan_jeevangajawada`.`eloanregistrationpage`
WHERE <{where_expression}>;
