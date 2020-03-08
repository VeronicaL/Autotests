What is done in this framework:
1. mvn clean test -DsuiteXmlFile=TestNGSuiteConfig.xml -Denv=dev
without properties and without some code - tests run, no error.

Run from console:
mvn clean test -DsuiteXmlFile=TestNGSuiteConfig.xml -Denv=dev: run on dev in Chrome - as set in Testng.xml
mvn clean test -DsuiteXmlFile=TestNGSuiteConfig.xml -Denv=dev -Dbrowser=FIREFOX: run on dev and in Firefox

if run using right click on testNG xml - run on qa and Chrome