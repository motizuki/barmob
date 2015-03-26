# Barmob
## --- Under development ---

### Details: 

Small app to maintain the administration of orders/menu/table of a small bar, portable for any mobile/pc devices.
- Java 8
- Maven
- Spring
- MongoDB
- Jo framework
- Logback + SLF2J
- JUnit
- AOP
- JQuery

### Requirements
- Java 8
- MongoDB
- Web server (Tomcat 8)

### Instalation

- Checkout the project.
- Install the parent pom.xml file at barmob-parent directory.
- Build the barmob using maven.
- Run the mongodb js file to load some dummy data in the database.
- Deploy the war file inside barmob-service-app/target into your webserver.
- Access the mobile client at {deploy-server}:{port}/{app}/pages/client.html
- Access the dashboard client at {deploy-server}:{port}/{app}/pages/dashboard.html
