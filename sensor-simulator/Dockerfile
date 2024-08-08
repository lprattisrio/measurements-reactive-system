FROM alpine:latest

COPY script.sh /script.sh

RUN apk update && \
    apk add --no-cache netcat-openbsd

ENTRYPOINT ["/script.sh"]