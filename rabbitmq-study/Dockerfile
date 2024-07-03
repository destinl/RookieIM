# 基础镜像
FROM  openjdk:8-jre
# 指定路径
WORKDIR /usr/local
# 复制jar文件到路径
COPY Management-system-0.0.1-SNAPSHOT.jar .
# 启动认证服务
ENTRYPOINT ["java","-jar","-Duser.timezone=GMT+08","Management-system-0.0.1-SNAPSHOT.jar"]
#END