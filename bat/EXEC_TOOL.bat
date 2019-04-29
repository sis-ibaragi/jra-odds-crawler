@ECHO OFF
SETLOCAL
REM ----------------------------------------------------------------------------
REM �c�[���F JraOddsCrawler �����s���܂��B
REM ----------------------------------------------------------------------------

REM ���s���t�H���_�Ɋւ�����ϐ���ݒ�
REM -- �z�[���t�H���_
CD ..
SET TOOL_HOME=%CD%
REM -- �p�����[�^�t�H���_
SET CONF_DIR=%TOOL_HOME%\conf
REM -- ���C�u�����[�t�H���_
SET LIB_DIR=%TOOL_HOME%\lib
REM -- ���O�t�H���_
SET LOG_DIR=%TOOL_HOME%\log
REM -- �p�����[�^
SET KAISAI_DT=%DATE:/=-%
SET ODDS_TIME_NO=%1
REM -- Jar �t�@�C��
FOR /F "usebackq" %%i in (`DIR /B %LIB_DIR%\jra-odds-crawler*.jar`) do @SET JAR_FILE=%%i

REM PATH �Ɋւ�����ϐ���ݒ�
rem SET JAVA_HOME=D:\apps\Java\jdk1.8.0_192
IF "%JAVA_HOME%" == "" (
	ECHO JAVA_HOME ��ݒ肵�Ă��������B
rem PAUSE
	GOTO :EOF
)
REM -- PATH
SET PATH=%PATH%;%JAVA_HOME%\bin;

REM -- JVM ����
SET JVM_ARG=-Xrs -Xms128m -Xmx256m

REM �m�F���b�Z�[�W��\��
ECHO +-------------------------------------------------------------------------+
ECHO  �c�[���F JraOddsCrawler �����s���܂��B
ECHO  - TOOL_HOME     : %TOOL_HOME%
ECHO  - KAISAI_DT     : %KAISAI_DT%
ECHO  - ODDS_TIME_NO  : %ODDS_TIME_NO%
ECHO +-------------------------------------------------------------------------+
rem PAUSE

REM ���O�t�H���_���쐬
IF NOT EXIST %LOG_DIR% (
	MKDIR %LOG_DIR%
)

REM �c�[�������s
java %JVM_ARG% -Dtool.root.dir=%TOOL_HOME% -Dlog.dir=%LOG_DIR% -Dkaisai.date=%KAISAI_DT% -Dodds.time.no=%ODDS_TIME_NO% -jar %LIB_DIR%\%JAR_FILE%
rem PAUSE

REM ���C�������I��
GOTO :EOF
ENDLOCAL
