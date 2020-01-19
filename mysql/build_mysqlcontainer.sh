#!/bin/bash

#docker network create test_network || true
docker rm mysqldb || true
#docker run --name mysqldb -p 3306:3306 --env "MYSQL_ROOT_PASSWORD=ThePassword" --env "MYSQL_DATABASE=HallPlanningService" --env "MYSQL_DATABASE=shop" -d mysql:5.7
cd ../mysql
docker build -t mysqldb .
docker run --name mysqldb -p 3306:3306 --env "MYSQL_ROOT_PASSWORD=ThePassword" --env "MYSQL_DATABASE=HallPlanningService" -d mysqldb
#This might make it run faster
#bash wait-for-mysql.sh mySQLdb -- docker exec -it mySQLdb mysql -p -e "set global innodb_lru_scan_depth=256;"
