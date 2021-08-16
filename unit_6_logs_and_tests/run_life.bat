echo off
call mvn clean install -Plife -DskipTests
call java -jar target/library.jar