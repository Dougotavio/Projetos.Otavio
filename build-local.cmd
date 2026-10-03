@echo off
if not defined JAVA_HOME (
    for /d %%J in ("%USERPROFILE%\.jdk\jdk-25\jdk-25*") do (
        if exist "%%~fJ\bin\java.exe" set "JAVA_HOME=%%~fJ"
    )
)
if not defined JAVA_HOME (
    echo Java 25 JDK not found. Set JAVA_HOME or install it under "%USERPROFILE%\.jdk\jdk-25". 1>&2
    exit /b 1
)
if not exist "%JAVA_HOME%\bin\java.exe" (
    echo JAVA_HOME does not contain a Java runtime: "%JAVA_HOME%". 1>&2
    exit /b 1
)
set "PATH=%JAVA_HOME%\bin;%PATH%"
cd /d "%~dp0"
call mvnw.cmd -B %*
