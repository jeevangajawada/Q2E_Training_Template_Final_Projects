CREATE TABLE `loaninformation` (
  `AppNum` int(11) NOT NULL AUTO_INCREMENT,
  `LoanPurpose` varchar(45) DEFAULT NULL,
  `AmountReq` int(11) DEFAULT NULL,
  `DateOfApp` date DEFAULT NULL,
  `BusinessStructure` varchar(45) DEFAULT NULL,
  `BillingIndicator` varchar(45) DEFAULT NULL,
  `TaxIndicator` varchar(45) DEFAULT NULL,
  `Address` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Mobile` varchar(45) DEFAULT NULL,
  `Status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`AppNum`)
) ENGINE=InnoDB AUTO_INCREMENT=800108 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


UPDATE `eloan_jeevangajawada`.`loaninformation`
SET
`AppNum` = <{AppNum: }>,
`LoanPurpose` = <{LoanPurpose: }>,
`AmountReq` = <{AmountReq: }>,
`DateOfApp` = <{DateOfApp: }>,
`BusinessStructure` = <{BusinessStructure: }>,
`BillingIndicator` = <{BillingIndicator: }>,
`TaxIndicator` = <{TaxIndicator: }>,
`Address` = <{Address: }>,
`Email` = <{Email: }>,
`Mobile` = <{Mobile: }>,
`Status` = <{Status: }>
WHERE `AppNum` = <{expr}>;

INSERT INTO `eloan_jeevangajawada`.`loaninformation`
(`AppNum`,
`LoanPurpose`,
`AmountReq`,
`DateOfApp`,
`BusinessStructure`,
`BillingIndicator`,
`TaxIndicator`,
`Address`,
`Email`,
`Mobile`,
`Status`)
VALUES
(<{AppNum: }>,
<{LoanPurpose: }>,
<{AmountReq: }>,
<{DateOfApp: }>,
<{BusinessStructure: }>,
<{BillingIndicator: }>,
<{TaxIndicator: }>,
<{Address: }>,
<{Email: }>,
<{Mobile: }>,
<{Status: }>);


DELETE FROM `eloan_jeevangajawada`.`loaninformation`
WHERE <{where_expression}>;


