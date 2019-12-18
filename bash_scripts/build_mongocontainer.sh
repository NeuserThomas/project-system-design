#!/bin/bash
docker rm moviedb || true
docker run --name moviedb -d  -p="27017:27017" --env "MONGO_INITDB_ROOT_USERNAME=root" --env "MONGO_INITDB_ROOT_PASSWORD=ThePassword" --env "MONGO_INITDB_DATABASE=movie" mongo:latest
