#!/bin/bash

APPLICATION_CONF_DIR=$ROOT_HOME/config/biz.properties
sed -i 's/\r//g'  $APPLICATION_CONF_DIR

JAVA_HOME=`grep "^application.java.home=" $APPLICATION_CONF_DIR|cut -d'=' -f2`
deploy_mode=`grep "^deploy.mode=" $APPLICATION_CONF_DIR|cut -d'=' -f2`

umask 022
LOG_FILE_DIR=`grep "^logs.dir=" $APPLICATION_CONF_DIR|cut -d'=' -f2`