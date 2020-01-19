# project-system-design

## System design project: Bioscoop system

This repository contains all the folders/ files related to the Bioscoop system project for the course System Design 2019-2020.
The link to the github repo is: [system-design](https://github.com/NeuserThomas/project-system-design)

## Deploying locally (spring tool suite)
When deploying locally, please change the environment. Change the application.properties file to:
```
spring.profiles.active=dev
```
When deploying on kubernetes, leave it on:
```
spring.profiles.active=prod
```
## Testing remote (Kmaster)
In the directory [kubernetes](kubernetes/), there is a port forward script, to test the remote server. Please use your own certificates and password etc. For the ip adress, use the ip adress of the ingress controller service. We use the cluster ip from the traefik service.
```
rgoussey@kmaster:~$ kubectl get svc --namespace kube-system
NAME                      TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)                       AGE
kube-dns                  ClusterIP   10.96.0.10      <none>        53/UDP,53/TCP,9153/TCP        51d
traefik-ingress-service   NodePort    10.107.202.17   <none>        80:30600/TCP,8080:32726/TCP   3h18m
```

## **Ticket service (Thomas)**

General info

Server port: 2300

Dependencies:
- Zookeeper
- Kafka
- MySQL

link to full file: [Ticket Service](ticket_management_service/README.md)

-----------------------------

## **Parking Service (Thomas)**

This service is used for parking management. When initialized, one parking is available with 200 free spots.

General info

Server port: 2301

Dependencies:
- MySQL

link to full file: [Parking Service](parking_service/README.md)

-----------------------------


## **Hall Planning service (Robin)**
### **General info**
- Server port: `2223`
- Dependencies:
  - Zookeeper
  - Kafka
  - MySQL
  - mongo

link to full file: [hallPlanningService](hall_planning_service/readme.md)

-----------------------------


## **Shop service (Robin)**
### **General info**
- Server port: `2230`
- Dependencies:
  - MySQL

link to full file: [shopService](shop_service/readme.md)
-----------------------------

## Staff Service
- Server Port: `2224`
- Dependencies:
  - Cassandra
  
Link to Staff Service's [Read Me](staff_service/readme.md)


## Databases

### **MySQL**

#### Running locally
To run an appliction locally (in spring tool suite), you might need to set up a local database. We use one server, with multiple databases.
To deploy kafka, you will need to install it, and run the zookeeper, and kafka.
To run our mongo or mysql database, go to the [mysql directory](/mysql) :
```bash
./build_mysqlcontainer.sh
```
This will build the mysql container if not already done, and run it. (port 3306). The reason we use a custom image, is to build our databases.
If we have time, we can try to dynamically create the needed databases.

#### Building docker
To build the database, use the command as written below in the mysql directory:
```
docker build -t mysqldb .
```

### **Mongo**

#### Running locally
To run the mongo db, go to the [bash_scripts directory](/bash_scripts) :
```bash
./build_mongocontainer.sh
```
This will run a standard mongo image, and run it as well.
#### Kubernetes:
At the moment there is an error so our users don't get created. Use the following command:
```
kubectl exec -it <movie-pod-name> mongo
...
db.createUser({ user: "root", pwd: "ThePassword", roles: [ { role : "dbAdmin", db:"movie"}] })
```
### Cassandra DB
Ports:
- `9042` for client connections

For more information on the Cassandra Database, please visit [this link](staff_service/readme.md)


### Errors with the databases:
If for some reason the data gets erases in the mongo databases (the movies), then please also erase all data in the mysql.
Since right now we use the mongo id as string in the mysql database. So if the movies get new ids, the mapping is wrong, and it can cause errors for other applications. 
They won't crash, just return empty lists, as the ids are no longer in the database.
So just doing:
```
docker stop mysqldb moviedb
docker rm mysqldb moviedb
```
And rebuilding both you should be fine (you don't need to manually remove the data).
-----------------------------

## Common errors:
##### Bash: `build_docker.sh` & `run_mysqlcontainer.sh`
###### Permission denied errors 
Make sure to grant execution rights:

```bash
# current directory: project-system-design/bash_scripts
chmod +x build_docker.sh
chmod +x ../hall_planning_service/mvnw

chmod +x ./run_mysqlcontainer.sh
```

- - - -
## **frontend**
To run the frontend, go to the directory /frontend in the terminal, and run:
```bash
$ ng serve --open
```
You can also build the frontend with docker, and use it as an image. Do note, that default, it builds by copying the output of the npm build to the docker file, instead of building it in the container (dockerfile_v2, this because of errors).
So you have to run (in a terminal, in the frontend directory):
```
npm run build
```
Do note: this needs node and angular installed.
And then you can build build with docker.
### Testing locally:
Right now the frontend routes all traffic, to the url it came from, on the same port, so you need the ingress controller to run. Or you have to manually change all urls in the service classes. 
### Running in kubernetes:
Make sure the ingress controller and ingress.yaml is depoyed.


- - - -
## **Deployment on kubernetes! (and docker)**
[Kubernetes](kubernetes/readme.md)

