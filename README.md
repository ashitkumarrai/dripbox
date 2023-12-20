# dripbox

## step 1: create docker-compose.yml in any folder and copy this script

 

 

version: '3'
services:
  insurance-apis:
    image: ashitcmd/insurance:insurance-insurance-apis

 

    ports:
      - "8080:8080"  # Map the API port to the host

 

  ldap-server:

 

    image: kadimasolutions/apacheds:latest
    tty: true
    stdin_open: true
    ports:
     - 10389:10389
     - 10636:10636
    volumes:
     - apacheds-data:/opt/apacheds/instances/default

 

  mysql-db:
    image: mysql:latest
    ports:
      - "3306:3306"  # Map the MySQL port to the host
    volumes:
      - mysql_data:/var/lib/mysql  # Volume for MySQL data persistence
    environment:
      MYSQL_ROOT_PASSWORD: 123456789
      MYSQL_DATABASE: insurancedb

 

      MYSQL_HOST: mysql-db 
 

 

  sonarqube:
    image: sonarqube:latest
    ports:
      - "9000:9000"  # Map the SonarQube web interface port to the host
      - "9092:9092"  # Map the SonarQube background tasks port to the host
volumes:
  sonarqube_data:  # Volume definition for SonarQube data persistence
  mysql_data:      # Volume definition for MySQL data persistence
  apacheds-data:

 

 

 

 

 

(make sure all the identation are on correct level)

 

## step 2: open cmd on the folder where docker-compose.yml file is located and exec commands: 
  1) to create images and run all 4 containers in same network: docker-compose up
  2) to stop all the running containers of the docker-compose :   docker-compose down

 

Note: use any apache ds client browser and create this dn: uid=admin,dc=example,dc=com with attribute : userpassword: secret

 

mysql: run on port 8080
apache ds: run on port 10389
sonarqube: run on port 9000
spring boot rest apis: run on port 8080

 

please make sure these ports are availabe in host.(if not try to temporarily close these running ports and then run your containers)
 
