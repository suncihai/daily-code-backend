# springboot
SpringBoot PlayGround

This project is to get used to SpringBoot.

* has successfully connected to mysql

# Note: 
* Setup
1. make sure gradle-wrapper.properties version right to your local gradle version.
2. remember some useful plugins and dependencies

* Connect to mysql
1. "com.mysql.jdbc.Driver" has deprecated, use "com.mysql.cj.jdbc.Driver", or it will
   cause "java.sql.SQLException: Unknown system variable 'query_cache_size'"
   
2. "Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 
   5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. 
   For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. 
   You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for
   server certificate verification."
   
   try jdbcUrl, append "?autoReconnect=true&useSSL=false"
3. "java.sql.SQLException: The server time zone value 'xxxx' is unrecognized or represents more than one time zone. 
   You must configure either the server or JDBC driver (via the serverTimezone configuration property) to use a more 
   specific time zone value if you want to utilize time zone support"
   
   try jdbcUrl, append "?serverTimezone=UTC"
   
4. if table primary key "id" does not choose "Auto increment", when insert data, there will
   be "java.sql.SQLException: Field 'id' doesn't have a default value" 
   
5. jpa inbuilt find method will return "javax.persistence.NonUniqueResultException: query did not return a unique 
   result" if your result is more than one than expected
   
6. when use delete method in jpa, have to add @Modifying and @Transactional, or there will be 
   "javax.persistence.TransactionRequiredException: No EntityManager with actual transaction available for current 
   thread - cannot reliably process 'remove' call"
   
* HttpRequest
1. use RequestContextHolder to solve thread safety problem. It is also used to save users' session.

* Debug
1. build.gradle sometimes has weird bugs, compile group swagger does not work but compile swagger work.