#!/bin/bash

docker run -d --name mysqldb -p 3306:3306 mysqldb 
docker start mysqldb
