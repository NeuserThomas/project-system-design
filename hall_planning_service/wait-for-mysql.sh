#!/bin/bash

#https://stackoverflow.com/questions/1527049/how-can-i-join-elements-of-an-array-in-bash
function join_by { local IFS="$1"; shift; echo "$*"; } # first param is delimter

waitInterval=15;
containerName=mySQLdb;
declare -a command;

while [[ $# -gt 0 ]]
do
key="$1"
case $key in
    -c|--container)
    containerName="$2"
    shift # past argument
    shift # past value
    ;;
    -w|--waitInterval)
    waitInterval="$2"
    shift # past argument
    shift # past value
    ;;
    --)
    command=("${@:2}");
    shift $#;
    ;;
    *)
    shift
    ;;
esac
done

if [[ ! $(docker ps -a | grep $containerName)  ]];
then
	echo "Docker container $containerName is not running!";
	exit 1;
fi

while ! docker exec $containerName mysql --user=root --password=ThePassword -e "SELECT 1" >/dev/null 2>&1; do
    echo "$containerName not ready, sleeping for $waitInterval s";
    sleep $waitInterval;
done;
eval $(join_by ' ' ${command[@]})
