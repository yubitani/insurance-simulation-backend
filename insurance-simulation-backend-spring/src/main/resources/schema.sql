CREATE TABLE IF NOT EXISTS `benefit` (
  `benefitid` INT(11) NOT NULL,
  `benefitname` VARCHAR(128),
  PRIMARY KEY(`benefitid`)
);

CREATE TABLE IF NOT EXISTS `product` (
  `productcode` VARCHAR(128) NOT NULL,
  `productname` VARCHAR(128),
  `benefitid` INT(11) NOT NULL,
  PRIMARY KEY(`productcode`),
  FOREIGN KEY(`benefitid`) REFERENCES `benefit`(`benefitid`)
);

CREATE TABLE IF NOT EXISTS `benefitchoise` (
  `benefitchoisecode` VARCHAR(128) NOT NULL,
  `benefitchoisename` VARCHAR(128),
  PRIMARY KEY(`benefitchoisecode`)
);

CREATE TABLE IF NOT EXISTS `periodofinsurance` (
  `productinsurancecode` VARCHAR(128) NOT NULL,
  `productinsurancename` VARCHAR(128),
  PRIMARY KEY(`productinsurancecode`)
);

CREATE TABLE IF NOT EXISTS `optionlist` (
  `optioncode` VARCHAR(128) NOT NULL,
  `optionname` VARCHAR(128),
  PRIMARY KEY(`optioncode`)
);

CREATE TABLE IF NOT EXISTS `benefit_benefitchoise` (
  `id` INT(11) NOT NULL,
  `benefitid` INT(11) NOT NULL,
  `benefitchoisecode` VARCHAR(128) NOT NULL,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`benefitid`) REFERENCES `benefit`(`benefitid`),
  FOREIGN KEY(`benefitchoisecode`) REFERENCES `benefitchoise`(`benefitchoisecode`)
);

CREATE TABLE IF NOT EXISTS `product_periodofinsurance` (
  `id` INT(11) NOT NULL,
  `productcode` VARCHAR(128) NOT NULL,
  `productinsurancecode` VARCHAR(128) NOT NULL,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`productcode`) REFERENCES `product`(`productcode`),
  FOREIGN KEY(`productinsurancecode`) REFERENCES `periodofinsurance`(`productinsurancecode`)
);

CREATE TABLE IF NOT EXISTS `product_optionlist` (
  `id` INT(11) NOT NULL,
  `productcode` VARCHAR(128) NOT NULL,
  `optioncode` VARCHAR(128) NOT NULL,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`productcode`) REFERENCES `product`(`productcode`),
  FOREIGN KEY(`optioncode`) REFERENCES `optionlist`(`optioncode`)
);