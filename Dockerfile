FROM java:8
MAINTAINER Stefan Dejanovic <stefan.dejanovic@heig-vd.ch>


# copy the application mockmock to the container
COPY MockMock-1.4.0.one-jar.jar /opt/app/MockMock.jar

# SMTP server will communicate with the port 2525 and port 8080 is for the web site
EXPOSE 2525 8080

# It will run the application
CMD ["java", "-jar", "/opt/app/MockMock.jar"]
