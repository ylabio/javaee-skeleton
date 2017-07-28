# JAVA EE 7 APPLICATION 

## TECHNOLOGIES
EJB, DI, CDI, JPA, JAX-RS, JAX-WS.

## SOFTWARE

- Java JDK 1.8 (Oracle HotSpot)
- Maven 3
- WildFly 10
- MySQL 5.6

## BUILD APPLICATION WITH MAVEN
```bash
mvn clean install
```

## DEPLOY APPLICATION TO WILDFLY
```bash
mvn wildfly:deploy-only
```

## LOCAL REST-JSON SERVICES (JAX-RS)

```bash
wget http://localhost:8080/project/api/ProjectService/ping
```

```bash
wget http://localhost:8080/project/api/TaskService/ping
```

## LOCAL SOAP SERVICES (JAX-WS)

```bash
wget http://localhost:8080/project/ProjectService?wsdl
```

```bash
wget http://localhost:8080/project/TaskService?wsdl
```

## WILFLY DATASOURCE CONFIG
```xml
<datasources>
    <datasource jndi-name="java:jboss/datasources/PROJECT-DS" pool-name="PROJECT-DS" enabled="true" use-java-context="true">
        <connection-url>jdbc:mysql://localhost:3306/project?characterEncoding=utf8</connection-url>
        <driver>mysql</driver>
        <pool>
            <min-pool-size>5</min-pool-size>
            <max-pool-size>15</max-pool-size>
            <prefill>true</prefill>
        </pool>
        <security>
            <user-name>test</user-name>
            <password>test</password>
        </security>
        <validation>
            <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"/>
            <validate-on-match>true</validate-on-match>
            <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter"/>
        </validation>
    </datasource>
</datasources>
<drivers>
    <driver name="mysql" module="com.mysql">
        <driver-class>com.mysql.jdbc.Driver</driver-class>
    </driver>
</drivers>
```


## DEVELOPER

> DENIS VOLNENKO <denis@volnenko.ru>