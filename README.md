
# Sandwich
## Techs
- Java 11
- Docker
- Apache Kafka
- Mongo
- Spring boot 2
- Reactor 3
- SonarQube
- Maven

## How to run 
First of all, is extremely important to have the docker and docker compose installed
````bash
$ docker-compose -f ./infra/docker-compose.yml up -d
````
wait for te port 9000 (http://localhost:9000), and then run the build command
````bash
$ mvn clean install sonar:sonar
````
after the build a link with all information of the quality of the code will be processed and the link with the report will be available http://localhost:9000/dashboard?id=br.com.vtferrari%3Asandwich

Now you can run the project,
````bash
$ mvn spring-boot:run
````
it will run on port 8080 (http://localhost:8080), and you can execute the commands below to see the results 
-----
### POST /v1/people 
Create a new person on database
````bash 
$ curl -X POST http://localhost:8080/v1/people \
    -H 'Content-Type: application/json' \
    -d '{
  	"category":{
  		"id":"A1",
  		"interest":["Number of barbecue grills","Number of pink Bathroom seats","Estimated number of books read per year"]
  	},
  	"description": "Some description"
  }'
````

### GET /v1/people?group=`<some group>`
Find all people from the some group
````bash
$ curl -X GET 'http://localhost:8080/v1/people?group=A1' \
  -H 'Content-Type: application/json' 
````
### GET /v1/demographics
Find all information from demographic groups
````bash
curl -X GET 'http://localhost:8080/v1/demographics'
````
