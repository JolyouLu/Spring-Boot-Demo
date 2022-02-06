FROM java:8
ARG JAR_FILE
COPY ${JAR_FILE} web.jar
#定义java启动参数
ENV JAVA_OPTS="-XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -Xloggc:/gc-log/gc.log -Duser.timezone=Asia/Shanghai"
#暴露端口
EXPOSE 8001

ENTRYPOINT java ${JAVA_OPTS} -jar web.jar