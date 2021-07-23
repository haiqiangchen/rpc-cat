#!/usr/bin/env bash

#  获取 根路径
cd $(dirname $(readlink -f "$0"))/..
ROOT_HOME=$(pwd)
echo "ROOT_HOME==>"$ROOT_HOME
export ROOT_HOME
pidpath=$ROOT_HOME/my.pid
if [ -f "$pidpath" ]; then
  pid=`cat $pidpath`
fi

if [ $pid ]; then
  output=$(ps -p "$pid")
  while [[ $output == *${pid}* ]]
  do
    echo stop process: $pid
    kill $pid
    sleep 0.5s
    output=$(ps -p "$pid")
  done
  echo ''>my.pid

fi

echo now, process is not run!
