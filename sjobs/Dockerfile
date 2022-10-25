FROM mhart/alpine-node:latest

MAINTAINER Eranga Bandara (erangaeb@gmail.com)

ARG VCS_REF
ARG BUILD_DATE

RUN npm i http-server -g

RUN mkdir /app
WORKDIR /app
VOLUME ["/app"]

# add
#   1. skunk-assembly.jar
#   2. docker-entrypoint.sh
ADD target/scala-2.12/sjobs-assembly-1.0.jar sjobs.jar
ADD docker-entrypoint.sh /app/docker-entrypoint.sh

EXPOSE 8000
ENTRYPOINT ["sh", "/app/docker-entrypoint.sh"]
