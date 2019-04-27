# Orders API
### API using Spring MVC, Hibernate, MySQL for orders app.

#### Requirements:
- Maven 3.6.1
- Java 1.8
- MySQL 8.0.15
- Tomcat v8.5
- Eclipse 2019-03


#### Data model diagram: 

* [diagram] - image png of data model.

**The script db.sql must be run to create the model tables and initialize the available currencies**.

Edit the **HibernateJPA/src/main/resourcespersistence-mysql.properties** file and place the **user and password** corresponding to the local database there.

#### Postman documentation:
* [documentation] - Postman web, API Doc.

### Workflow 

After running the db.sql script and having the app running, You can test the API:

**Base URL:** http://localhost:8080/CoreServices/

**Data creation steps:**

- Create the products (POST /product)
- Create stock for created products (POST /stock)
- Create order (POST /order)
- Assign product with available stock to an existing order (POST /order/product)

In postman are examples of the parameters required by each endpoint and its format.


 [diagram]: <https://drive.google.com/file/d/1AU8FaPivvhCGuhYxES9esblXC5RQH74a/view?usp=sharing>
 [documentation]: <https://documenter.getpostman.com/view/349031/S1LpZrcP>
