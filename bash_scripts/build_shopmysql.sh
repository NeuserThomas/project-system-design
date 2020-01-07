#!/bin/bash

docker stop shop || true
docker rm shop || true
docker run --name shop -d -p 2230:2230 shop
#This might make it run faster
