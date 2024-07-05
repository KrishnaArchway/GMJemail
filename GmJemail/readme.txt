/**
 * 
 * Archway Marketing Services.
 * 7525 Cogswell Rd,
 * Romulus, MI - 48174.
 * Phone - 734.713.2000
 * 
 * Project Name            : gmJemail
 * 
 * Author                  : bhattam0 - Amar Bhatt Email:(AMAR_BHATT@ARCHWAY.COM) Ext:(2019)
 * File Name               : readme.txt 
 * Package Name            : -none-
 * 
 * Date                    : Feb 4, 2006 9:23:56 AM
 * 
 * Change Date             : Mar 17, 2006 7:27:48 AM
 *                           04/04/2006
 *  						 Monday, March 27, 2006
 *                           Tuesday, May 16, 2006
 *                           Thursday, May 18, 2006
 * 							 May 24, 2006 12:58:27 PM
 * 						     May 24, 2006 1:03:30 PM
 * 							 May 24, 2006 1:04:07 PM
 * 							 May 24, 2006 1:04:12 PM
 * 							 May 24, 2006 1:04:17 PM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 * Setting up GMJemail Application ReadMe.
 *
 */




This application send out emails for GM
Types of Emails sent out by this application are 
1. Order Confirmation
2. Shipment Confirmation
3. Bars Billing
4. low water
5. New Items (New Nomenclature records)
6. Over Limit 
7. picklist
8. Inventory Receiving
9. Rush (Order)
10. Inventory Scrap



This application will be on Unix. Paths for each ENV are as follows:
Development :- (/l/gmdev/cron/gmJemail)
QA :- (/l/gmqa/cron/gmJemail)
Production :- (/l/gmprd/cron/gmJemail)
Note : QA and Developemnt will reside on server hawking, Production is on heather.

This application needs to be configured when moving from Environment to Environment.
Following are the steps to Configure :-

1.	build.xml 
	File Location : (../gmJemail)
	Purpose : Compilation 
	Change needed from Env to Env: 
	
	
	this line in build.xml needs to be changed where ever the source code is.
	<property name="build.env"  value="gmdev"/>
	
	For E.g.
	This property needs to be changed when moving from Env to Env.
	Development - gmdev
	QA - gmqa
  	Production - gmprd
	
	
	
2.	log4j.properties
	File Location : (../gmJemail/WEB-INF/classes)
	Purpose : Log Files
	Change needed from ENV to Env:
	
	log4j.appender.R.File=c:/eclipse/workspace/logs/generalEmail.log
	do global find and replace where the log files need to be created
	this application does lot (all emails type) of logging and logs are rotated every day automatically.
	
	For e.g. 
	In Development the log files will be created in /d/gmdev/log 
	
	In QA the log files will be created in /d/gmqa/log 
	
	In Production the log files will be created in /d/gmprd/log 
	
3.	jemail.gm.app.properties
	File Location : (../gmJemail.WEB-INF/classes/com/archway/globalemail/common)
	Purpose : Application Properties
	Change needed from Env to Env:
	
	
	3.1	
	DB_URL_PARM =jdbc:oracle:thin:@hawking:1521:gmdev
	DB_USER_PARM =jemail
	DB_PW_PARM =jemail_oracle
	(Change the Database connection parameters depending on which environment the application needs 
	to fetch data from, DB_USER_PARM & DB_PW_PARM will not chnage as these are common between all the
	environments. only DB_URL_PARM needs tio be changed.)
	
	3.2
	Chnage the following depending on environment (prd,qa,dev)
	
	#PRODUCTION
	#if in Production environment please uncomment this and comment QA and DEVELOPMENT the similar once.
	----------------------------------------------------------------------------------------------------
	#E-STORE=https://dwd.archway.com/dwd/efulfillment/authorize.do?
	#EMAIL=https://dwd.archway.com/dwd/efulfillment/authorize.do?
	#FAX=https://dwd.archway.com/dwd/efulfillment/authorize.do?
	#MAIL=https://dwd.archway.com/dwd/efulfillment/authorize.do?
	#PHONE=https://dwd.archway.com/dwd/efulfillment/authorize.do?
	#SATURN=https://saturn-publications.com/saturn/efulfillment/authorize.do?
	----------------------------------------------------------------------------------------------------

	# QA
	#if in QA environment please uncomment this and comment PRODUCTION and DEVELOPMENT the similar once.
	----------------------------------------------------------------------------------------------------
	#E-STORE=http://dwd-qa.archway.com/dwdqa/efulfillment/authorize.do?
	#EMAIL=http://dwd-qa.archway.com/dwdqa/efulfillment/authorize.do?
	#FAX=http://dwd-qa.archway.com/dwdqa/efulfillment/authorize.do?
	#MAIL=http://dwd-qa.archway.com/dwdqa/efulfillment/authorize.do?
	#PHONE=http://dwd-qa.archway.com/dwdqa/efulfillment/authorize.do?
	#SATURN=http://saturn-publications-qa.archway.com/saturnqa/efulfillment/authorize.do?
	----------------------------------------------------------------------------------------------------

	#DEVELOPMENT
	#if in Production environment please uncomment this and comment QA and PRODUCTION the similar once.
	----------------------------------------------------------------------------------------------------
	E-STORE=http://webgroup:7778/DWDeStore/efulfillment/authorize.do?
	EMAIL=http://webgroup:7778/DWDeStore/efulfillment/authorize.do?
	FAX=http://webgroup:7778/DWDeStore/efulfillment/authorize.do?
	MAIL=http://webgroup:7778/DWDeStore/efulfillment/authorize.do?
	PHONE=http://webgroup:7778/DWDeStore/efulfillment/authorize.do?
	SATURN=http://webgroup:7778/DWDeStore/saturn/efulfillment/authorize.do?
	----------------------------------------------------------------------------------------------------
	
	3.2
	Change the following depending on environment (prd,qa,dev)
	# overlimit url which to go to.:)
	# these property needs to be changed from environment to environment.

	#PRODUCTION
	#if in Production environment please uncomment this and comment QA and DEVELOPMENT the similar once.
	----------------------------------------------------------------------------------------------------
	#OVERLIMIT=https://dwd.archway.com/dwd/overlimit/authorize.do?
	----------------------------------------------------------------------------------------------------


	# QA
	#if in QA environment please uncomment this and comment PRODUCTION and DEVELOPMENT the similar once.
	----------------------------------------------------------------------------------------------------
	#OVERLIMIT=http://dwd-qa.archway.com/dwdqa/overlimit/authorize.do?
	----------------------------------------------------------------------------------------------------


	#DEVELOPMENT
	#if in Production environment please uncomment this and comment QA and PRODUCTION the similar once.
	----------------------------------------------------------------------------------------------------
	OVERLIMIT=http://webgroup.tsdet.archway.com:7778/DWDeStore/overlimit/authorize.do?
	----------------------------------------------------------------------------------------------------
	
	
	
	3.4
	This application is codeed in such a way that depending on which database the application
	is going to connect (refer 3.1) it will send email as mentioned below. Reason for doing this 
	is that the database get refereshed time to time from prd to qa and dev. we do not want to send out 
	production emails from QA or from dev.
	Java program will read the DB_URL_PARM from properties file and determine what database it is connecting 
	and on that will pick email address from below. just an security measure.:)
		
	PROD_EMAIL_BCC_ALWAYS=GM_Sent_Email@archway.com
	QA_EMAIL_BCC_ALWAYS=preetal_modi@archway.com
	DEV_EMAIL_BCC_ALWAYS=amar_bhatt@archway.com
	
4.	Once the Java Code and all the other files have been moved Compilation is necessary.
	Execute ant. (please see Bryan Hunter if ant is not configured)
	
5.	Execution Scripts
	
	5.1
	exeGMJemailOrderWithOthers.sh
	(do a global find and replace on the file as it contains CLASSPATH (which is required on runtime)
	replace "gmdev" to whatever env this application is in.
	
	5.2
	exeGMJemailShipmentWithOthers.sh
	(do a global find and replace on the file as it contains CLASSPATH (which is required on runtime)
	replace "gmdev" to whatever env this application is in.	
	
	5.3
	exeGMJemailReceiving.sh
	(do a global find and replace on the file as it contains CLASSPATH (which is required on runtime)
	replace "gmdev" to whatever env this application is in.	
	
6.	To Execute the application issue the following command from the command prompt.
	Make Sure that your current directory is ../gmJemail
	
	6.1
	./exeGMJemailOrderWithOthers.sh (make sure that file has execute permission)
	Note : this script will execute 
		1. Order Confirmation
		2. Bars Billing
		3. low water
		4. New Items (New Nomenclature records)
		5. Over Limit 
		6. picklist
		7. Rush (Order)
		8. Inventory Scrap
		
	
		
	6.2
	./exeGMJemailShipmentWithOthers.sh (make sure that file has execute permission)
	Note : this script will execute 
		1. Shipemnt Confirmation
		2. Bars Billing
		3. low water
		4. New Items (New Nomenclature records)
		5. Over Limit 
		6. picklist
		7. Rush (Order)
		8. Inventory Scrap
		
		
	6.3
	./exeGMJemailReceiving.sh (make sure that file has execute permission)		
	Note : this script will execute 
		1. Inventory Receiving
		
	Cron Schedule are as follows (in production)
	6.1 This job runs every hour 24/7
	6.2 This job runs every Hour Starting 9:00 PM till 9:00 AM
	6.3 This job runs Once a day 10:00 pm
	
	
7.	Please truncate Email_History table then create testing records
	This application is automatically populates record in email history for the following
	1. Order Confirmation
	(place an order thru estore,client server)
	2. Shipment Confirmation
	(ship the order just placed,client server)
	3. Bars Billing
	(please contact appropriate programmer or client lead)
	4. low water
	(please contact appropriate programmer or client lead)
	5. New Items (New Nomenclature records)
	(please contact appropriate programmer or client lead, imanager can create it)
	6. Over Limit 
	(please an order with item whose qty ordered is over the max qty allowed)
	7. picklist
	(please contact appropriate programmer or client lead)
	8. Inventory Receiving
	(please contact appropriate programmer or client lead)
	9. Rush (Order)
	(place an order and mark it as rush)
	10. Inventory Scrap
	(this is not live yet.)

8.	File Location Table.
	Please make sure that the file_location tables has correct file_path for the application where to 
	look for email templates.
	
	In Development the column path_name should be /gsm/gmdev/cron/java/gmJemail/jhtml_text/
	In QA the column path_name should be /gsm/gmqa/cron/java/gmJemail/jhtml_text/
	In Production the column path_name should be /gsm/gmprd/cron/java/gmJemail/jhtml_text/
	
9.	Info
	This Java Application only gets data from one database pkg "gm_email_api_pkg" owner is egem_prod.
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		