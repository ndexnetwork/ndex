FROM phusion/baseimage
# start off with standard ubuntu images

# Set local and enable UTF-8
ENV LANG C.UTF-8
ENV LANGUAGE C
ENV LC_ALL C.UTF-8

#java11
RUN apt-get update && apt-get install -y software-properties-common python-software-properties
RUN add-apt-repository ppa:openjdk-r/ppa && \
    apt-get update && \
    apt-get install -y openjdk-11-jdk && \
    apt-get install -y ant && \
    apt-get clean;

# Fix certificate issues
RUN apt-get update && \
    apt-get install ca-certificates-java && \
    apt-get clean && \
    update-ca-certificates -f;

RUN apt-get install gnupg2  -y
RUN gpg2 --keyserver hkp://pool.sks-keyservers.net --recv-keys 75CEBDE82D6BECC940EC0D22B3E38C4A2BBDBA1E


RUN mkdir /ndex
ADD . /ndex


# set ndex to listen on all interfaces
RUN echo 'nxt.allowedBotHosts=*' >> /ndex/conf/nxt.properties
RUN echo 'nxt.apiServerHost=0.0.0.0' >> /ndex/conf/nxt.properties
#RUN chmod +x /docker_start.sh

RUN cd /ndex; ./compile.sh

# both ndex ports get exposed
EXPOSE 6899 6868
CMD ["/ndex/docker_start.sh"]
