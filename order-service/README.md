1. Start docker desktop engine in the local computer system
2. Open terminal and change directory to /micro-services/order-service, where docker-compose.yml file exists
3. Execute command to start mysql service as mentioned in docker-compose.yml file: docker compose up -d
4. To validate mysql service working in docker or not, open MySQL workbench and connect with::
       Host: 127.0.0.1    UN: root    PWD: mysql_pwd
5. Execute queries:
       select * from flyway_schema_history;
       select * from t_orders;
6. Start OrderServiceApplication in debug mode in local
7. Hit POST url: http://localhost:8081/api/order
   {
   "skuCode": "SamsungGalaxy3_123",
   "price": 700,
   "quantity": 1
   }
8. At the end stop OrderServiceApplication and mysql docker container: docker compose down
