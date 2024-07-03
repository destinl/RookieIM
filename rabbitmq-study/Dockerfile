# 基础镜像
FROM openjdk:8-jre
# 指定路径
WORKDIR /usr/local
# 复制jar文件到路径
COPY target/rabbitmq-study-1.0-SNAPSHOT.jar .
# 启动认证服务
ENTRYPOINT ["java", "-jar", "-Duser.timezone=GMT+08", "rabbitmq-study-1.0-SNAPSHOT.jar"]
#END