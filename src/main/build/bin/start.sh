#!/bin/bash

#  获取 根路径
cd $(dirname $(readlink -f "$0"))/..
ROOT_HOME=$(pwd)
echo "ROOT_HOME==>"$ROOT_HOME
export ROOT_HOME

source ./bin/env.sh
./bin/stop.sh

# java env
if [ -z "$JAVA_HOME" ]; then
  JAVA_BIN=$(which java)
  JAVA_HOME=$(dirname $(dirname $(readlink -f "$JAVA_BIN")))
fi
echo "JAVA_HOME=$JAVA_HOME"
PATH=$JAVA_HOME/bin:$PATH
CLASSPATH=$JAVA_HOME/lib
export  PATH CLASSPATH

LOG_FILE_DIR=./logs
mkdir -p $LOG_FILE_DIR


HEAP_MIN=3072m
HEAP_MAX=3072m
META_MIN=256m
META_MAX=1024m
DIRECT_SIZE=1024m
if [ "$deploy_mode" == "dev" ]; then
  HEAP_MIN=32m
  HEAP_MAX=256m
  META_MIN=32m
  META_MAX=128m
  DIRECT_SIZE=128m
fi

# jvm参数
JAVA_OPTS="-Xms${HEAP_MIN} -Xmx${HEAP_MAX} -XX:MetaspaceSize=${META_MIN} -XX:MaxMetaspaceSize=${META_MAX} -XX:MaxDirectMemorySize=${DIRECT_SIZE} -XX:+UseConcMarkSweepGC -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=75 -XX:+ExplicitGCInvokesConcurrentAndUnloadsClasses -XX:+CMSClassUnloadingEnabled -XX:+ParallelRefProcEnabled -XX:+CMSScavengeBeforeRemark -XX:CMSFullGCsBeforeCompaction=2 -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintTenuringDistribution -XX:+PrintTenuringDistribution -XX:+PrintGCApplicationStoppedTime -XX:+DisableExplicitGC -XX:+HeapDumpOnOutOfMemoryError -XX:ErrorFile=$LOG_FILE_DIR/hs_err_pid%p.log -Xloggc:$LOG_FILE_DIR/gc.log -XX:+UseGCLogFileRotation -XX:GCLogFileSize=10240 -XX:NumberOfGCLogFiles=7"

JAVA_OPTS="$JAVA_OPTS -DlogFile.dir=$LOG_FILE_DIR"


#JAVA_OPTS="$JAVA_OPTS -Xdebug -Xrunjdwp:transport=dt_socket,address=5005,server=y,suspend=y"

export JAVA_OPTS
echo "JAVA_OPTS==>"$JAVA_OPTS

nohup $JAVA_HOME/bin/java $JAVA_OPTS -Dfastjson.parser.safeMode=true -cp "./lib/*:./config"  com.cmic.ydrz.personalized.BootApplication --logging.config=$ROOT_HOME/config/logback-spring.xml > /dev/null 2>&1 &

echo now, process is startting on the backend!