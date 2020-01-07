#!/bin/bash

#script used to use local images (when already built)
[[ "$1" ]] || ( echo "give docker image name" && exit 1);
docker save $1 > $1.tar
microk8s.ctr --namespace k8s.io image import $1.tar


 




