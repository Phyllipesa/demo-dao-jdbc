
FROM mysql:8.0.33

ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_USER=developer
ENV MYSQL_PASSWORD=1234567
ENV MYSQL_DATABASE=coursejdbc

COPY ./data.sql /docker-entrypoint-initdb.d/

EXPOSE 3306
