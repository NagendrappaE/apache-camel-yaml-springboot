@echo off
REM -----------------------------------------------------
REM Copyright 2019 Pham Ngoc Hoai
REM
REM Licensed under the Apache License, Version 2.0 (the "License");
REM you may not use this file except in compliance with the License.
REM You may obtain a copy of the License at
REM
REM http://www.apache.org/licenses/LICENSE-2.0
REM
REM Unless required by applicable law or agreed to in writing, software
REM distributed under the License is distributed on an "AS IS" BASIS,
REM WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
REM See the License for the specific language governing permissions and
REM limitations under the License.
REM
REM Repo: https://github.com/tyrion9/spring-boot-startup-script
REM
REM -----------------------------------------------------

REM PARAMETERS
SET "JAVA_OPT=-Xmx1024m"
FOR /F %%I IN ('dir /B /O:-D *.jar 2^>nul') DO SET "JARFILE=%%I"
SET "PID_FILE=pid.file"
SET "RUNNING=N"
SET "PWD=%CD%"

REM DO NOT MODIFY

IF EXIST "%PID_FILE%" (
    SET /P PID=<"%PID_FILE%"
    IF NOT "%PID%"=="" (
        TASKLIST /FI "PID eq %PID%" 2>NUL | FIND /I /N "%PID%" >NUL
        IF NOT ERRORLEVEL 1 (
            SET "RUNNING=Y"
        )
    )
)

:start
IF "%RUNNING%"=="Y" (
    echo Application already started
) ELSE (
    IF "%JARFILE%"=="" (
        echo ERROR: jar file not found
    ) ELSE (
        start "MyJavaApp" /B java %JAVA_OPT% -Djava.security.egd=file:/dev/./urandom -jar "%PWD%\%JARFILE%" > nohup.out 2>&1
        echo %ERRORLEVEL% > "%PID_FILE%"
        echo Application %JARFILE% starting...
        timeout /T 1 /NOBREAK > NUL
        type nohup.out
    )
)
goto :EOF

:stop
IF "%RUNNING%"=="Y" (
    TASKKILL /F /PID %PID% > NUL
    DEL /F "%PID_FILE%" > NUL
    echo Application stopped
) ELSE (
    echo Application not running
)
goto :EOF

:restart
call :stop
call :start
goto :EOF

:usage
echo Usage: %0 { start | stop | restart }
goto :EOF
