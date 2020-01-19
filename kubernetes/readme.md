# Kubernetes

## **Deployment on kubernetes!**
In this chapter I shall shortly explain how to deploy kubernetes. We know in real life, you use secrets etc to save the database passwords etc, but for now we left them as is. 
There are 2 directories, a remote, and a local version. There isn't much difference, only that the images are for example are just shop, and the ImagePullPolicy: never.
The other option is for the image for example rgoussey/shop, and the line with ImagePullPolicy left away.
## Deploying
To deploy a file, use :
```
kubectl apply -f <file.yaml>
```
To deploy a directory (like the kafka or ingress one), use the following command in the directory:
```
kubectl apply -f .
```
### Deploy on microk8s. (Using local images).
Right now we use the docker hub registry images. You can use local images by [exporting them]().
To use local images, change the image in the yaml from (example for shop):
```
apiVersion: apps/v1
kind: Deployment
metadata:
  labels:
    app: shop
  name: shop
spec:
  replicas: 1
  selector:
    matchLabels:
      app: shop
  template:
    metadata:
      labels:
        app: shop
    spec:
      containers:
      - image: rgoussey/shop:latest
        name: shop
        ports:
        - containerPort: 2230
```
To:
```
apiVersion: apps/v1
kind: Deployment
metadata:
  labels:
    app: shop
  name: shop
spec:
  replicas: 1
  selector:
  matchLabels:
      app: shop
  template:
    metadata:
      labels:
        app: shop
    spec:
      containers:
      - image: shop
	    imagePullPolicy: Never
        name: shop
        ports:
        - containerPort: 2230
```
## How to build images and export them (also for microk8s):
In this paragraph, there is a short explanation for all settings to work.
### application.properties
Every spring boot app should have multiple applications properties files, like below:
```
application-dev.properties
application-prod.properties
application.properties
```
In the main file, you can either choose dev of or production:
```
spring.profiles.active=prod # or dev
```
An example of the development is below:
```
server.port=2230
spring.jpa.hibernate.ddl-auto=update
#Docker compose:
spring.datasource.url=jdbc:mysql://localhost:3306/Shop #how to reach the Shop database
spring.datasource.username=root
spring.datasource.password=ThePassword
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect

server.servlet.context-path=/shop
```
In production, we replace all localhost links, to the docker (for docker compose), or the kubernetes name (in kubernetes). We chose to take the same name for both:
```
server.port=2230
spring.jpa.hibernate.ddl-auto=update
#Docker compose:
spring.datasource.url=jdbc:mysql://mysqldb:3306/Shop #This means the kubernetes database name is mysqldb
spring.datasource.username=root
spring.datasource.password=ThePassword
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect
server.servlet.context-path=/shop
```
You best use the "/shop" server.servlet.context-path, as this eases routing for your application.

### Docker file
Every application should have a dockerfile, as below:
```
from maven:3.6-jdk-8-alpine AS builder
#from openjdk:8
WORKDIR /app
copy pom.xml .
RUN mvn -e -B dependency:resolve
copy src ./src
RUN mvn -e -B package -DskipTests # maakt de jar

FROM openjdk:8-jre-alpine
COPY --from=builder /app/target/shop_service-0.0.1-SNAPSHOT.jar /
CMD ["java", "-jar", "/shop_service-0.0.1-SNAPSHOT.jar","-Dspring.profiles.active=prod"] #hoe gestart?
```
To build in the directory, do this: 
```
docker build -t <name> .
```
#### Docker names:
Application name  | Docker image name
------------- | -------------
Hall_planning_service  | hall-planning
Shop_service  | shop
Ticket_service  | ticket
Parking_service  | parking
Mysql database  | mysqldb
Mongo db  | moviedb

### Exporting to kubernetes

#### Local images (Microk8s)
If you work with microk8s, it doesn't know where to find local docker images, so you can use the following script, in the images/ directory:
```
./save_image.sh <image_name>
``` 
This wil register the image to microk8s. Only use this when you can't push the images to the online registry.
#### Registring images to docker registry
You can push images to the registry by using: (You have to be authorized by me)
```
docker tag frontend rgoussey/frontend
docker push frontend
```
You have to be logged in by doing docker login (cfr. [stackoverflow topic](https://stackoverflow.com/questions/41984399/denied-requested-access-to-the-resource-is-denied-docker))

### How does kubernetes work kubernetes.
Each app has a deployment and a service: 
```
apiVersion: apps/v1 # definition
kind: Deployment # a deployment: automatically creates pods, independant of the amount of replicas
metadata:
  labels:
    app: shop ## If there is a mismatch in labels, your service will have no endpoints or pods.
  name: shop
spec:
  replicas: 1 # amount of pods/replicas
  selector:
    matchLabels:
      app: shop ## Again, make sure the labels are correct
  template:
    metadata:
      labels:
        app: shop
    spec:
      containers:
      - image: shop # name of the image
        imagePullPolicy: Never # Only when working with microk8s, otherwise use image rgoussey/shop, and remove this line.
        name: shop
        ports:
        - containerPort: 2230 # The port of your container
```
A service is described as below:
```
apiVersion: v1
kind: Service
metadata:
  labels:
    app: shop
  name: shop
spec:
  type: ClusterIP 
  ports:
  - name: "2230" 
    port: 2230 #
    targetPort: 2230
  selector:
    app: shop
```
You can put them in seperate files, or combine them with --- in between. Deploying a yaml goes like: 
```
(microk8s.) kubectl apply -f <file>
```
#### Ingress
To route the traffic, we use an ingress controller, for example:
```
apiVersion: extensions/v1beta1
kind: Ingress
metadata:
  name: gateway-ingress
spec:
  rules:
  - http:
      paths:
      - path: /shop
        backend:
          serviceName: shop
          servicePort: 2230
      - path: / #geen url naar frontend
        backend:
          serviceName: frontend
          servicePort: 80
```
#### Ingress controller:
In microk8s you can use ingress without deploying the controller, and only the ingress yaml:
```
microk8s.enable ingress
```
There are also two custom controllers:
Right now there are 2 ingress controllers, an nginx one, and a treafik one. Deploy the nginx one by deploying everything in the map [local_ingress](/kubernetes/local_ingress).
On the server we use the treafik one, this one can be deployed using the bash script [ingress_controller](ingress_controller).
Then also apply the ingress.yaml.

# ***Resilience test***

To test the resilience of our application, use the file chaos.sh which you can find in this directory.
Eventually make it executable first, then run it.

```
chmod a+x chaos.sh
./chaos.sh
```

