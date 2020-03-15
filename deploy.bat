@echo off
echo Kopiowanie pliku .war do Xampp
copy e:\workshop\Project_BHP\target\bhp-1.war c:\xampp\tomcat\webapps\bhp.war
echo Kasowanie pliku .war w target
del e:\workshop\Project_BHP\target\bhp-1.war
exit