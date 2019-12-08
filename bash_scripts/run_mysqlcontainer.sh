#!/bin/bash

#docker network create test_network || true
docker rm mySQLdb || true
docker run --name mySQLdb -p 3306:3306 --env "MYSQL_ROOT_PASSWORD=ThePassword" --env "MYSQL_DATABASE=Day" -d mysql:5.7
