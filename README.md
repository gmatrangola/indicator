# Lab 1

1. Specify and download Spring Initializer

2. Unzip

3. Import into IDE

4. Use annotations to create ResetController

5. Start Web Server & Application

6. Test with HTTP Request, curl or similar

7. Add Email Address to Customer class

8. Add optional email address param to /makeCustomer

   # Cassandra Setup 

1. docker pull cassandra:latest
2. docker run --name spring-cassandra -p 7000-7001:7000-7001 -p 7199:7199 -p 9042:9042 -p 9160:9160 -d cassandra:latest
3. docker start spring-cassandra

## Cassandra cqlsh

docker exec -ti spring-cassandra cqlsh localhost

# Lab 2

1. Create CassandraConfig

2. Add Cassandra Annotations to Customer Class

3. Create CustomerRepository Interface

4. Setup Cassandra database

# Lab 3

1. Create applicaiton.properties

6. CustomerContoller

7. 1. Add @Autowire for CustomerRepository
   2. Run HTTP Get on /makeCustomer
   3. Add getAll() the get all customers with RequestMapping /
   4. Add get(id) to get a customer by ID. hint: customerRepository.findById(id)

8. Verify output in CQL

# Lab 4

1. Implement the code from the Demo in your application.
2. Add an optional age query string parameter to add (/new) that takes a String and uses SimpleDateFormat to convert it to a java.util.Date.
3. Set the Date in the Customer object
4. For now, catch and swallow the exception from SimpleDateFormat.parse(). We’ll cover exception handling soon
5. Create a request mapping to get a customer by ID (DB PK) using a @PathVariable
6. Add another /add that takes a Customer as a @RequestBody parameter and has a RequestType.PUT
7. Add @RequestMapping to the all customers when request is “/customers/”
8. Add a top level / that gets all the customers
9. Add email and zipcode to the Customer domain class bean

## Import

```bash
# Start Web Service NOW!

> docker cp data/scitech.csv spring-cassandra:/tmp/
> docker exec -ti spring-cassandra cqlsh localhost
cqlsh> COPY world.indicator (countryname,countrycode,indicatorname,indicatorcode,year1970,year1980,year1990,year2000,year2010,year2017,year2018) FROM 'tmp/scitech.csv' WITH HEADER = TRUE
cqlsh> select * from world.indicator where countrycode = 'BGR';
```

## 

 