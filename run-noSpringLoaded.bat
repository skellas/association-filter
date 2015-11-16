::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::      Dev environment startup script for Alfresco Community     ::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
@echo off

set MAVEN_OPTS=
mvn clean install -Penterprise -Prun -nsu
:: mvn install -Penterprise -Prun
