#!/bin/bash

docker run --name=mySQLdb -p 3306:3306 -d --env="MYSQL_ROOT_PASSWORD=ThePassword" --env="MYSQL_DATABASE=Day"  mysql:5.7
