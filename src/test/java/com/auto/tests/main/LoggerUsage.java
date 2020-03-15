package com.auto.tests.main;


import org.apache.log4j.Logger;

public class LoggerUsage {

    final static Logger logger = Logger.getLogger(LoggerUsage.class);

    public static void main(String [] args){
        LoggerUsage obj = new LoggerUsage();
        obj.runMe("mkyong");
    }

    private void runMe(String parameter){

        if(logger.isDebugEnabled()){
            logger.debug("This is debug : " + parameter);
        }
        if(logger.isInfoEnabled()){
            logger.info("This is info : " + parameter);
        }
        logger.warn("This is warn : " + parameter);
        logger.error("This is error : " + parameter);
        logger.fatal("This is fatal : " + parameter);
    }
}

//if in log4j.properties : log4j.rootLogger=DEBUG, stdout
/*then:
        2020-03-12 18:56:46,073 DEBUG LoggerUsage - This is debug : mkyong
        2020-03-12 18:56:46,075  INFO LoggerUsage - This is info : mkyong
        2020-03-12 18:56:46,075  WARN LoggerUsage - This is warn : mkyong
        2020-03-12 18:56:46,075 ERROR LoggerUsage - This is error : mkyong
        2020-03-12 18:56:46,075 FATAL LoggerUsage - This is fatal : mkyong*/

//if in log4j.properties : log4j.rootLogger=Error, stdout
//Then:
//        2020-03-12 19:02:35,200 ERROR LoggerUsage - This is error : mkyong
//        2020-03-12 19:02:35,201 FATAL LoggerUsage - This is fatal : mkyong

//log4j’s Priority class. if priority is defined in log4j.properties, only the same or above priority message will be logged.

//    public final static int FATAL_INT = 50000;
//    public final static int ERROR_INT = 40000;
//    public final static int WARN_INT  = 30000;
//    public final static int INFO_INT  = 20000;
//    public final static int DEBUG_INT = 10000;