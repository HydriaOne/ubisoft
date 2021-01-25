/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  fonsito
 * Created: 7 jun. 2020
 */

CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT NUMERIC(38), PRIMARY KEY (SEQ_NAME));
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0);
CREATE TABLE ACCOUNT (ID BIGINT NOT NULL, AGERESTRICTED BOOLEAN, LANGUAGE VARCHAR, LASTPLAYED VARCHAR, LASTPLAYEDVERSION VARCHAR, NAME VARCHAR, VERSION BIGINT, PRIMARY KEY (ID));
