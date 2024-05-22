#!/usr/bin/env
source /etc/profile
source ~/.bash_profile
projectName="ttms-expert"
port="8204"
target=$1
health_url="http://127.0.0.1:$port/$projectName/actuator/health"
up_url="http://127.0.0.1:$port/$projectName/actuator/service-registry?status=UP"
down_url="http://127.0.0.1:$port/$projectName/actuator/service-registry?status=DOWN"
current=`date "+%Y-%m-%d-%H%M%S"`

pid=`ps -ef|grep 'java'|grep $projectName|grep $port|awk '{print $2}'`

if [ -n "$pid" ]; then
  curl -s -X POST -H "Content-Type:application/vnd.spring-boot.actuator.v2+json;charset=UTF-8"  $down_url
  sleep 10
  kill $pid
  sleep 5
fi

for((i=0;i<40;i++))
do
  pidA=`ps -ef|grep 'java'|grep $projectName|grep $port|awk '{print $2}'`
  if [ -n "$pidA" ]; then
    echo "Waiting for stop old service ..."
    sleep 3
  else
    echo "old service is stopped"
    sleep 3
    break
  fi
done
pidB=`ps -ef|grep 'java'|grep $projectName|grep $port|awk '{print $2}'`
if [ -n "$pidB" ]; then
  echo "Cannot kill old service $pidB，force kill -9 $pidB"
  kill -9 $pidB
  sleep 3
fi

portLock=`netstat -untlp|awk '{print $4}'|grep ":$port"`
if [ -n "$portLock" ]; then
  echo "port $port is used, please stop use or change port"
  exit 1
fi
JAVA_OPTS=""
JAVA_OPTS="$JAVA_OPTS -Xms2048m -Xmx2048m -Xss256k -Xmn256m -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:SurvivorRatio=8"
JAVA_OPTS="$JAVA_OPTS -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:/app/app/$projectName/logs/gc-$current.log"
JAVA_OPTS="$JAVA_OPTS -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/app/app/$projectName/logs/dump"
JAVA_OPTS="$JAVA_OPTS -Dspring.profiles.active=$target -Dserver.port=$port"
JAVA_OPTS="$JAVA_OPTS -javaagent:/app/app/skywalking-agent/skywalking-agent.jar -Dskywalking.agent.service_name=$projectName-$target -Dskywalking.collector.backend_service=10.4.20.91:11800"

echo "java -jar $JAVA_OPTS $projectName*.jar > /dev/null 2>&1 &"
nohup java -jar $JAVA_OPTS $projectName*.jar > /dev/null 2>&1 &

for ((i=0;i<90;i++))
do
    curl -s -X GET ${health_url} > health
    health=`cat health |grep status |grep UP`
    if [ ${#health} -gt 10 ]; then
        break
    else
        pidNew=`ps -ef |grep 'java' |grep $projectName |grep $port |awk '{print $2}'`
        if [ -n "$pidNew" ]; then
            echo "Waiting for start ..."
            sleep 3
        else
            echo "Deploy Fail, please see log (/home/logs/$projectName-$target)"
            exit 1
        fi
    fi
done

curl -s -X POST -H "Content-Type:application/vnd.spring-boot.actuator.v2+json;charset=UTF-8"  $up_url

rm -rf update

if [ ${#health} -gt 10 ]; then
    echo "Deploy Success"
    exit 0
else
    echo "Deploy Fail"
    exit 1
fi
