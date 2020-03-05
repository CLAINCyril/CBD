# we are extending everything from tomcat:8.0 image ...
From tomcat:8.0.51-jre8-alpine
RUN rm -rf /usr/local/tomcat/webapps/*
# COPY path-to-your-application-war path-to-webapps-in-docker-tomcat

COPY target/CDBwebui.war /usr/local/tomcat/webapps/

CMD [“catalina.sh”, “run”]
