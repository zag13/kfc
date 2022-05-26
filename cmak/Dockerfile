FROM openjdk:14-jdk-alpine


ENV CMAK_VERSION=3.0.0.6

RUN apk add --no-cache bash \
    bash-doc \
    bash-completion \
    && rm -rf /var/cache/apk/* \
    && /bin/bash  \
    && cd /  \
    && wget https://github.com/yahoo/CMAK/releases/download/${CMAK_VERSION}/cmak-${CMAK_VERSION}.zip \
    && unzip  -d / ./cmak-${CMAK_VERSION}.zip  \
    && rm -fr /cmak-${CMAK_VERSION}.zip  \
    && pwd \
    && ls cmak-${CMAK_VERSION}

WORKDIR /cmak-${CMAK_VERSION}

EXPOSE 9000

ENTRYPOINT ["bin/cmak"]
CMD ["-Dconfig.file=conf/application.conf", "-Dhttp.port=9000",  "-Dpidfile.path=/dev/null"]