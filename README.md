# dripbox

## step 1: copy docker-compose.yml in any folder

 

## step 2: open cmd on the folder where docker-compose.yml file is located and exec commands: 
  1) to create images and run all 4 containers in same network: docker-compose up
  2) to stop all the running containers of the docker-compose :   docker-compose down

 

Note: use any apache ds client browser and create this dn: uid=admin,dc=example,dc=com with attribute : userpassword: secret

 

mysql: run on port 8080
apache ds: run on port 10389
sonarqube: run on port 9000
spring boot rest apis: run on port 8080

 

please make sure these ports are availabe in host.(if not try to temporarily close these running ports and then run your containers)
 
