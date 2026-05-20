@echo off
REM JDK embutido do IntelliJ (CMD / PowerShell)
set "JAVA_HOME=D:\IntelliJ IDEA 2026.1\jbr"
set "PATH=%JAVA_HOME%\bin;%PATH%"

echo JAVA_HOME=%JAVA_HOME%
java -version
javac -version
