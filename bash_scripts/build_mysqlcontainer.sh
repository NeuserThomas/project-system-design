#!/bin/bash

#docker network create test_network || true
docker rm mySQLdb || true
docker run --name mySQLdb -p 3306:3306 --env "MYSQL_ROOT_PASSWORD=ThePassword" --env "MYSQL_DATABASE=HallPlanningService" --env "MYSQL_DATABASE=shop" -d mysql:5.7
#This might make it run faster
#bash wait-for-mysql.sh mySQLdb -- docker exec -it mySQLdb mysql -p -e "set global innodb_lru_scan_depth=256;"
