GET request för alla activities:

curl --location 'http://localhost:8080/api/activities'

GET specific request för specific activity:

curl --location 'http://localhost:8080/api/activities/1'

POST request för att skapa ny aktivitet:

curl --location 'http://localhost:8080/api/activities' \
--header 'Content-Type: application/json' \
--data '{
"title": "",
"description": "Laga hamburgare",
"done": false
}'

PUT request för att uppdatera aktivitet:

curl --location --request PUT 'http://localhost:8080/api/activities/1' \
--header 'Content-Type: application/json' \
--data '{
"title": "Läs en bok (uppdaterad)",
"description": "Läs kapitel 3 och 4",
"done": false
}'

DELETE request för att ta bort aktivitet:

curl --location --request DELETE 'http://localhost:8080/api/activities/4'