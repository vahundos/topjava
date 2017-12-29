**Get all:** curl http://localhost:8080/topjava/rest/meals

**Get with id 100002:** curl http://localhost:8080/topjava/rest/meals/100002

**Get between 10:00 and 13:00:** curl "http://localhost:8080/topjava/rest/meals/filter?startDate=&endDate=&startTime=10:00&endTime=13:00"

**Update with id 100002:** curl -H "Content-Type: application/json" -X PUT -d '{"id":100002,"dateTime":"2015-05-30T10:00:00","description":"new description for MEAL1","calories":4999}' http://localhost:8080/topjava/rest/meals/100002

**Create:** curl -H "Content-Type: application/json" -X POST -d '{"dateTime":"2017-12-29T13:22:11.005","description":"desc","calories":2500}' http://localhost:8080/topjava/rest/meals

**Delete with id 100002:** curl -X DELETE http://localhost:8080/topjava/rest/meals/100002

