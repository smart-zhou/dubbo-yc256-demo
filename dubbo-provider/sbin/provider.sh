#!/bin/bash
#
# 程序启动脚本
#
# chkconfig: - 84 16
# description: Spring Boot启动脚本
[ -f "/etc/rc.d/init.d/functions" ] && . /etc/rc.d/init.d/functions
[ -z "$JAVA_HOME" -a -x /etc/profile.d/java.sh ] && . /etc/profile.d/java.sh
#环境变量检察
PATH=$PATH
if [ "$JAVA_HOME" != "" ]; then
echo "JAVA环境变量： $JAVA_HOME "
export PATH=${JAVA_HOME}/bin:$PATH
else
echo "JAVA环境变量未设置或设置有误，请检查，尝试直接启动"
#     exit 1
fi
###############################################################主要配置############################################################
# 项目名称，项目日志位置
PROJECT_NAME=dubbo-provider
# 项目启动入口
PROJECT_MAIN=com.yc256.intra.dubbo.Provider
###################################################################################################################################
# 启动用户
SERVICE_USER=root
# 程序依赖目录
BASE_DIR=$(cd `dirname $0`; pwd)
LIB_PATH="$BASE_DIR/../lib"
export LIB_PATH
# 配置文件目录
ETC_PATH="$BASE_DIR/../etc"
export ETC_PATH

# JAVA路径
SPRINGBOOTAPP_JAVA=${JAVA_HOME}/bin/java

# spring boot log-file
LOG_PATH="$BASE_DIR/../$PROJECT_NAME.log"
PID_PATH="$BASE_DIR/../$PROJECT_NAME.pid"
LOCK_PATH="/var/lock/subsys/$PROJECT_NAME"

RETVAL=0

pid_of_spring_boot() {
    cat ${PID_PATH}
}
command_print(){
    echo "$1"
    sleep 0.01
}

start() {
    [ -e "$LOG_PATH" ] && cnt=`wc -l "$LOG_PATH" | awk '{ print $1 }'` || cnt=1
    last_newline=${cnt}
    echo  $"启动 $PROJECT_NAME: "
    PROJECT_CLASSPATH=${ETC_PATH}
    for f in ${LIB_PATH}/*.jar
    do
        PROJECT_CLASSPATH=${PROJECT_CLASSPATH}:${f}
        command_print "加载$f至CLASSPATH;"
    done
    PROJECT_CLASSPATH=${PROJECT_CLASSPATH}:${ETC_PATH}
    export PROJECT_CLASSPATH

    echo "nohup java -cp \"$PROJECT_CLASSPATH\" $PROJECT_MAIN >> \"$LOG_PATH\" 2>&1 &"
    nohup java -cp "$PROJECT_CLASSPATH" ${PROJECT_MAIN}  >> "$LOG_PATH" 2>&1 &
    echo $! > "$BASE_DIR/../$PROJECT_NAME.pid"
    echo ""
    while { pid_of_spring_boot > /dev/null ; } &&
        ! { tail --lines=+${cnt} "$LOG_PATH" | grep -q '[STARTED]' ; } ; do
        newline=`wc -l "$LOG_PATH" | awk '{ print $1 }'`
	    tail --lines=$(($newline-$last_newline)) "$LOG_PATH"
	    last_newline=${newline}
    done
    tail --lines=$((`wc -l "$LOG_PATH" | awk '{ print $1 }'`-$last_newline)) "$LOG_PATH"
    pid_of_spring_boot > /dev/null
    RETVAL=$?
    [ ${RETVAL} = 0 ] && success $"$STRING" || failure $"$STRING"
    echo

    [ ${RETVAL} = 0 ] && touch "$LOCK_PATH"
}

stop() {
    echo -n "停止$PROJECT_NAME: "

    pid=`pid_of_spring_boot`
    [ -n "$pid" ] && kill ${pid}
    RETVAL=$?
    cnt=10
    echo ""
    while [ ${RETVAL} = 0 -a ${cnt} -gt 0 ] &&
        { pid_of_spring_boot > /dev/null ; } ; do
            sleep 1
            echo -n '.'
            ((cnt--))
    done

    [ ${RETVAL} = 0 ] && rm -f "$LOCK_PATH"
    [ ${RETVAL} = 0 ] && success $"$STRING" || failure $"$STRING"
    rm -f "$BASE_DIR/../$PROJECT_NAME.pid"
    echo
}

status() {
    pid=`pid_of_spring_boot`
    if [ -n "$pid" ]; then
        echo "$PROJECT_NAME (pid $pid) 正在运行......"
        return 0
    fi
    if [ -f "$LOCK_PATH" ]; then
        echo $"${base} 程序已死亡，但SYSLOCK仍存在"
        return 2
    fi
    echo "$PROJECT_NAME已停止"
    return 3
}

# See how we were called.
case "$1" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    status)
        status
        ;;
    restart)
        stop
        start
        ;;
    *)
        echo $"Usage: $0 {start|stop|restart|status}"
        exit 1
esac

exit ${RETVAL}
