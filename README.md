1. Start docker container in the computer system
2. Open terminal and change directory to product-service, where docker-compose.yml file exists
3. Execute command to start mongo service as mentioned in docker-compose.yml file: docker compose up -d
4. To validate mongo service working in docker or not:
   1. Open terminal
   2. Execute command to see all running containers: docker ps -a
   3. copy container id of mongo container
      4. Execute mongosh command on the mongo container:
             docker exec -it <container-id> mongosh
             use admin                                          # Switch to admin database
             db.auth("my_mongo_un", "my_mongo_pwd")             # Authorize with the database
             or
             docker exec -it <container-id> mongosh -u my_mongo_un -p my_mongo_pwd --authenticationDatabase admin
   6. use admin
   7. See all the collections: show collections
   8. See all data within system.users collection: db.system.users.find()
5. Start ProductServiceApplication in debug mode in local
6. Hit GET url: http://localhost:8080/api/product
7. At the end stop ProductServiceApplication and mongo docker container: docker compose down
