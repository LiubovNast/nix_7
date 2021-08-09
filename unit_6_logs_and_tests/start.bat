echo off
call mvn clean package
call java -jar target/library.jar