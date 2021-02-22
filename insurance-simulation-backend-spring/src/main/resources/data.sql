/*
*  保険商品情報格納TABLE
*/
CREATE TABLE IF NOT EXISTS benefit
 (
 benefitid NUMBER(11) NOT NULL,
 benefitname VARCHAR2(100),
 CONSTRAINT pk_benefit PRIMARY KEY( benefitid )
 )
;

CREATE TABLE IF NOT EXISTS product
 (
 productcode VARCHAR2(100) NOT NULL,
 productname VARCHAR2(100),
 benefitid NUMBER(11) NOT NULL,
 CONSTRAINT pk_product PRIMARY KEY( productcode ),
 CONSTRAINT fk1_product FOREIGN KEY( benefitid ) REFERENCES benefit( benefitid )
 );

CREATE TABLE IF NOT EXISTS benefitchoise
 (
 benefitchoisecode VARCHAR2(100) NOT NULL,
 benefitchoisename VARCHAR2(100),
 CONSTRAINT pk_benefitchoise PRIMARY KEY( benefitchoisecode )
 )
;

CREATE TABLE IF NOT EXISTS periodofinsurance
 (
 productinsurancecode VARCHAR2(100) NOT NULL,
 productinsurancename VARCHAR2(100),
 CONSTRAINT pk_periodofinsurance PRIMARY KEY( productinsurancecode )
 )
;

CREATE TABLE IF NOT EXISTS optionlist
 (
 optioncode VARCHAR2(100) NOT NULL,
 optionname VARCHAR2(100),
 CONSTRAINT pk_option PRIMARY KEY( optioncode )
 )
;

CREATE TABLE IF NOT EXISTS benefit_benefitchoise
 (
 id NUMBER(11) NOT NULL,
 benefitid NUMBER(11) NOT NULL,
 benefitchoisecode VARCHAR2(100) NOT NULL,
 CONSTRAINT pk_benefit_benefitchoise PRIMARY KEY( id ),
 CONSTRAINT fk1_benefit_benefitchoise FOREIGN KEY( benefitid ) REFERENCES benefit( benefitid ),
 CONSTRAINT fk2_benefit_benefitchoise FOREIGN KEY( benefitchoisecode ) REFERENCES benefitchoise( benefitchoisecode )
 )
;

CREATE TABLE IF NOT EXISTS product_periodofinsurance
 (
 id NUMBER(11) NOT NULL,
 productcode VARCHAR2(100) NOT NULL,
 productinsurancecode VARCHAR2(100) NOT NULL,
 CONSTRAINT pk_product_periodofinsurance PRIMARY KEY( id ),
 CONSTRAINT fk1_product_periodofinsurance FOREIGN KEY( productcode ) REFERENCES product( productcode ),
 CONSTRAINT fk2_product_periodofinsurance FOREIGN KEY( productinsurancecode ) REFERENCES periodofinsurance( productinsurancecode )
 )
;

CREATE TABLE IF NOT EXISTS product_optionlist
 (
 id NUMBER(11) NOT NULL,
 productcode VARCHAR2(100) NOT NULL,
 optioncode VARCHAR2(100) NOT NULL,
 CONSTRAINT pk_product_option PRIMARY KEY( id ),
 CONSTRAINT fk1_product_optionlist FOREIGN KEY( productcode ) REFERENCES product( productcode ),
 CONSTRAINT fk2_product_optionlist FOREIGN KEY( optioncode ) REFERENCES optionlist( optioncode )
 )
;

/*CUSTOMERSテーブルのデータ*/
INSERT INTO CUSTOMER(NAME,KANA,BIRTHDAY,SEX,ZIPCODE,ADDRESS,TEL_NO,MAIL) VALUES('住友太郎', 'スミトモタロウ','1990-01-01', 1, '123-4567', '東京都中央区', '0120-307-506' ,'test@am.sumitomolife.co.jp');

/*DOCUMENT_REQUESTSテーブルのデータ*/
INSERT INTO DOCUMENT_REQUEST VALUES('1001', '2020-11-16 21:00', 1);

/*SIMULATIONSテーブルのデータ*/
INSERT INTO SIMULATION (PRODUCT_CODE, BENEFIT_CODE, PERIOD_OF_INSULANCE_CODE,INSURANCE_PREMIUM,RECEIPT_NO) VALUES('DeathInsurance-001','5million','10years',1200,'1001');
INSERT INTO SIMULATION (PRODUCT_CODE, BENEFIT_CODE, PERIOD_OF_INSULANCE_CODE,INSURANCE_PREMIUM,RECEIPT_NO) VALUES('MedicalInsurance-001','5000perday','lifetime',1000,'1001');

/*SIMULATION_OPTIONSテーブルのデータ*/
INSERT INTO SIMULATION_OPTION (OPTION_CODE,SIMULATION_ID) VALUES('ThreeMajorIllnessInsurance',1);
INSERT INTO SIMULATION_OPTION (OPTION_CODE,SIMULATION_ID) VALUES('LivingNeeds',1);