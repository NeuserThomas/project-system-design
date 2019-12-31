#!/bin/bash

#docker network create test_network || true
docker rm shop_mysqldb || true
docker run --name shop_mysqldb -p 3310:3306 --env "MYSQL_ROOT_PASSWORD=ThePassword" --env "MYSQL_DATABASE=Shop" -d mysql:5.7
#This might make it run faster
#bash wait-for-mysql.sh mySQLdb -- docker exec -it mySQLdb mysql -p -e "set global innodb_lru_scan_depth=256;"
