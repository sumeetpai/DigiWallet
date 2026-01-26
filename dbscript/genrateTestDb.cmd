@echo off
REM ============================================
REM DigiWallet - Database Dump & Refresh Script
REM ============================================

REM IMPORTANT:
REM Do NOT put quotes inside the variable value

SET MYSQL_BIN=C:\Program Files\MySQL\MySQL Server 8.0\bin
SET SOURCE_DB=digital_wallet_db
SET TARGET_DB=digitalTest_db
SET SQL_FILE=digiwallet.sql
SET DB_USER=root

echo --------------------------------------------
echo DigiWallet Database Refresh
echo Source DB : %SOURCE_DB%
echo Target DB : %TARGET_DB%
echo --------------------------------------------

REM ------------------------------------------------
REM STEP 1: Dump SOURCE database
REM ------------------------------------------------
echo Dumping database %SOURCE_DB% ...

"%MYSQL_BIN%\mysqldump.exe" -u %DB_USER% -p --routines --triggers --events %SOURCE_DB% > "%SQL_FILE%"

IF ERRORLEVEL 1 (
    echo ERROR: Failed to dump source database
    exit /b 1
)

echo Dump completed successfully.

REM ------------------------------------------------
REM STEP 2: Create TARGET database if not exists
REM ------------------------------------------------
echo Creating database %TARGET_DB% if not exists ...

"%MYSQL_BIN%\mysql.exe" -u %DB_USER% -p -e "CREATE DATABASE IF NOT EXISTS %TARGET_DB%;"

IF ERRORLEVEL 1 (
    echo ERROR: Failed to create target database
    exit /b 1
)

echo Database %TARGET_DB% is ready.

REM ------------------------------------------------
REM STEP 3: Import dump into TARGET database
REM ------------------------------------------------
echo Importing data into %TARGET_DB% ...

"%MYSQL_BIN%\mysql.exe" -u %DB_USER% -p %TARGET_DB% < "%SQL_FILE%"

IF ERRORLEVEL 1 (
    echo ERROR: Failed to import SQL file
    exit /b 1
)

echo --------------------------------------------
echo Database refreshed successfully!
echo --------------------------------------------
pause
