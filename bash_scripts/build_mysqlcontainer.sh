#!/bin/bash

#docker network create test_network || true
docker rm mysqldb || true
cd ../mysql/
docker build -t mysqldb .
#This might make it run faster
#bash wait-for-mysql.sh mySQLdb -- docker exec -it mySQLdb mysql -p -e "set global innodb_lru_scan_depth=256;"
