FROM quay.io/keycloak/keycloak:latest as builder
ENV KC_METRICS_ENABLED=true
ENV KC_DB=postgres
ENV KC_HTTP_RELATIVE_PATH=/auth
RUN /opt/keycloak/bin/kc.sh build

FROM quay.io/keycloak/keycloak:latest
COPY --from=builder /opt/keycloak/lib/quarkus/ /opt/keycloak/lib/quarkus/
WORKDIR /opt/keycloak
ENV KC_LOG_LEVEL=INFO
ENV KC_PROXY=edge
ENV KC_HTTP_ENABLED=true
ENV KC_HOSTNAME_STRICT=false

COPY ./theme/snc-lara/ /opt/keycloak/themes/snc-lara/

ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start"]