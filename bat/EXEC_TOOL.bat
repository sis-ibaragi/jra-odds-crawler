@ECHO OFF
SETLOCAL
REM ----------------------------------------------------------------------------
REM ツール： JraOddsCrawler を実行します。
REM ----------------------------------------------------------------------------

REM 実行環境フォルダに関する環境変数を設定
REM -- ホームフォルダ
CD ..
SET TOOL_HOME=%CD%
REM -- パラメータフォルダ
SET CONF_DIR=%TOOL_HOME%\conf
REM -- ライブラリーフォルダ
SET LIB_DIR=%TOOL_HOME%\lib
REM -- ログフォルダ
SET LOG_DIR=%TOOL_HOME%\log
REM -- パラメータ
SET KAISAI_DT=%DATE:/=-%
SET ODDS_TIME_NO=%1
REM -- Jar ファイル
FOR /F "usebackq" %%i in (`DIR /B %LIB_DIR%\jra-odds-crawler*.jar`) do @SET JAR_FILE=%%i

REM PATH に関する環境変数を設定
rem SET JAVA_HOME=D:\apps\Java\jdk1.8.0_192
IF "%JAVA_HOME%" == "" (
	ECHO JAVA_HOME を設定してください。
rem PAUSE
	GOTO :EOF
)
REM -- PATH
SET PATH=%PATH%;%JAVA_HOME%\bin;

REM -- JVM 引数
SET JVM_ARG=-Xrs -Xms128m -Xmx256m

REM 確認メッセージを表示
ECHO +-------------------------------------------------------------------------+
ECHO  ツール： JraOddsCrawler を実行します。
ECHO  - TOOL_HOME     : %TOOL_HOME%
ECHO  - KAISAI_DT     : %KAISAI_DT%
ECHO  - ODDS_TIME_NO  : %ODDS_TIME_NO%
ECHO +-------------------------------------------------------------------------+
rem PAUSE

REM ログフォルダを作成
IF NOT EXIST %LOG_DIR% (
	MKDIR %LOG_DIR%
)

REM ツールを実行
java %JVM_ARG% -Dtool.root.dir=%TOOL_HOME% -Dlog.dir=%LOG_DIR% -Dkaisai.date=%KAISAI_DT% -Dodds.time.no=%ODDS_TIME_NO% -jar %LIB_DIR%\%JAR_FILE%
rem PAUSE

REM メイン処理終了
GOTO :EOF
ENDLOCAL
