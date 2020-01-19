  
FROM mysql:5.7
# Copy our custom SQL file to /docker-entrypoint-initdb.d folder
ENV MYSQL_ROOT_PASSWORD=ThePassword
COPY extra_databases.sql /docker-entrypoint-initdb.d/extra_databases.sql
