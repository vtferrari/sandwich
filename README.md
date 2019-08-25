
````bash
$ docker-compose -f ./infra/docker-compose.yml up -d
````

````bash
$ mvn clean install sonar:sonar
````

````bash
$ mvn spring-boot:run
````


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

````bash
$ curl -X GET 'http://localhost:8080/v1/people?group=A1' \
  -H 'Content-Type: application/json' 
````

````bash
curl -X GET 'http://localhost:8080/v1/demographics'
````
