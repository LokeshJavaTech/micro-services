1. Start docker desktop engine in the local computer system
2. Open terminal and change directory to /micro-services/inventory-service, where docker-compose.yml file exists
3. Execute command to start mysql service as mentioned in docker-compose.yml file: docker compose up -d
4. To validate mysql service working in docker or not, open MySQL workbench and connect with::
       Host: 127.0.0.1    UN: root    PWD: mysql_pwd
5. Execute queries:
       select * from flyway_schema_history;
       select * from t_inventory;
6. Start InventoryServiceApplication in debug mode in local
7. Hit GET url: http://localhost:8082/api/inventory?skuCode=samsung_15g&quantity=50
8. At the end stop InventoryServiceApplication and mysql docker container: docker compose down
