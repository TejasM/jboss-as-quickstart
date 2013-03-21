petclinic-spring: PetClinic Example using Spring 3.1

Level: Advanced Technologies: This example demonstrates the use of JPA 2.0, Junit, JMX, Spring MVC Annotations, Spring Data, Thymeleaf, webjars, and Dandellion in JBoss Enterprise Application Platform 6 or JBoss AS 7.

All you need to build this project is Java 6.0 (Java SDK 1.6) or better, Maven 3.0 or better.

==========================================================================
=== Spring PetClinic Sample Application
==========================================================================

@author Ken Krebs
@author Juergen Hoeller
@author Rob Harrop
@author Costin Leau
@author Sam Brannen
@author Scott Andrews

==========================================================================
=== Data Access Strategies
==========================================================================

PetClinic features alternative DAO implementations and application
configurations for JDBC, JPA, and Spring Data JPA, with HSQLDB and MySQL as
target databases. The default PetClinic configuration is JDBC on HSQLDB.
See "src/main/resources/spring/data-access.properties" as well as web.xml and
business-config.xml in the "src/main/resources/spring" folder for
details. A simple comment change in web.xml switches between the data
access strategies.

The JDBC and JPA versions of PetClinic also demonstrate JMX support
via the use of <context:mbean-export/> for exporting MBeans.
SimpleJdbcClinic exposes the SimpleJdbcClinicMBean management interface
via JMX through the use of the @ManagedResource and @ManagedOperation
annotations; whereas, the HibernateStatistics service is exposed via JMX
through auto-detection of the service MBean. You can start up the JDK's
JConsole to manage the exported bean.

All data access strategies can work with JTA for transaction management by
activating the JtaTransactionManager and a JndiObjectFactoryBean that
refers to a transactional container DataSource. The default for JDBC is
DataSourceTransactionManager and for JPA and Spring Data JPA, JpaTransactionManager. Those local strategies allow for working
with any locally defined DataSource.

Note that the sample configurations for JDBC, JPA, and Spring Data JPA configure
a BasicDataSource from the Apache Commons DBCP project for connection
pooling.

==========================================================================
=== Build and Deployment
==========================================================================

The Spring PetClinic sample application is built using Maven.
When the project is first built, Maven will automatically download all required
dependencies (if these haven't been downloaded before). Thus the initial build
may take a few minutes depending on the speed of your Internet connection,
but subsequent builds will be much faster.

Available build commands:

- mvn clean         --> cleans the project
- mvn clean test    --> cleans the project and runs all tests
- mvn clean package --> cleans the project and builds the WAR

After building the project with "mvn clean package", you will find the
resulting WAR file in the "target/" directory. By default, an
embedded HSQLDB instance in configured. No other steps are necessary to
get the data source up and running: you can simply deploy the built WAR
file directly to your Servlet container.

For MySQL, you'll need to use the corresponding schema and SQL scripts in
the "db/mysql" subdirectory. Follow the steps outlined in
"db/mysql/petclinic_db_setup_mysql.txt" for explicit details.

In you intend to use a local DataSource, the JDBC settings can be adapted
in "src/main/resources/spring/datasource-config.xml". To use a JTA DataSource, you need
to set up corresponding DataSources in your Java EE container.

Notes on enabling Log4J:
 - Log4J is disabled by default due to issues with JBoss.
 - Uncomment the Log4J listener in "WEB-INF/web.xml" to enable logging.
 
Notes on service static resources:
 - Most web containers provide a 'default' servlet for serving static 
 resources; Petclinic relies on it for its images.
 - On containers without such a mapping (ex: GlassFish), uncomment the 
 'default' declaration in "WEB-INF/web.xml".

==========================================================================
=== JPA on Tomcat
==========================================================================

This section provides tips on using the Java Persistence API (JPA) on
Apache Tomcat 4.x or higher with a persistence provider that requires
class instrumentation (such as TopLink Essentials).

To use JPA class instrumentation, Tomcat has to be instructed to use a
custom class loader which supports instrumentation. See the JPA section of
the Spring reference manual for complete details.

The basic steps are:
 - Copy "org.springframework.instrument.tomcat-3.0.0.RELEASE.jar" from the
   Spring distribution to "TOMCAT_HOME/server/lib".
 - If you're running on Tomcat 5.x, modify "TOMCAT_HOME/conf/server.xml"
   and add a new "<Context>" element for 'petclinic' (see below). You can 
   alternatively deploy the WAR including "META-INF/context.xml" from this 
   sample application's "src/main/webapp" directory, in which case you
   will need to uncomment the Loader element in that file to enable the
   use of the TomcatInstrumentableClassLoader.

<Context path="/petclinic" docBase="/petclinic/location" ...>
  <!-- please note that useSystemClassLoaderAsParent is available since Tomcat 5.5.20; remove it if previous versions are being used -->
  <Loader loaderClass="org.springframework.instrument.classloading.tomcat.TomcatInstrumentableClassLoader" useSystemClassLoaderAsParent="false"/>
  ...
</Context>

Run the Quickstart in JBoss Developer Studio or Eclipse

You can also start the server and deploy the quickstarts from Eclipse using JBoss tools. For more information, see Use JBoss Developer Studio or Eclipse to Run the Quickstarts
Debug the Application

Note: Eclipse/JBDS may generate a persistence.xml file in the src/main/resources/META-INF/ directory. In order to avoid errors delete this file.

If you want to debug the source code or look at the Javadocs of any library in the project, run either of the following commands to pull them into your local repository. The IDE should then detect them.

    mvn dependency:sources
    mvn dependency:resolve -Dclassifier=javadoc

