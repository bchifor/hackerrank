FROM centos:centos7
MAINTAINER Bogdan Chifor

RUN yum install -y wget
RUN yum install -y tar

#java
RUN wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u20-b26/jdk-8u20-linux-x64.tar.gz
RUN tar -zxvf jdk-8u20-linux-x64.tar.gz
RUN mv jdk1.8.0_20/ /usr/
RUN /usr/sbin/alternatives --install /usr/bin/java java /usr/jdk1.8.0_20/bin/java 2
ENV JAVA_HOME /usr/jdk1.8.0_20/
ENV JRE_HOME /usr/jdk1.8.0_20/jre/


