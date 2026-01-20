JDBC Demo
JDBC Without Maven (Plain Java Project)

Below is a simple, practical guide to use JDBC without Maven, which is very common in college labs, interviews, and Core Java projects.

🔹 What does “JDBC without Maven” mean?

It means:

❌ No pom.xml

❌ No dependency manager

✅ You manually download and add the JDBC JAR file to your project

🔹 Step 1: Download JDBC Driver JAR
For MySQL:

Download MySQL Connector/J

File name: mysql-connector-j-8.x.x.jar

(Official MySQL site)

📌 For other DBs:

Oracle → ojdbc8.jar

PostgreSQL → postgresql.jar

🔹 Step 2: Add JAR to Project
In Eclipse / IntelliJ / NetBeans:

Right click project

Build Path / Libraries

Add External JARs

Select JDBC .jar

Apply & OK

📁 Or place jar in a lib/ folder and add it to classpath.

🔹 Step 3: JDBC Code (No Maven)
Example: MySQL + JDBC
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcExample {
    public static void main(String[] args) {

        try {
            // 1. Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Create Connection
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/testdb",
                "root",
                "password"
            );

            // 3. Create Statement
            Statement stmt = con.createStatement();

            // 4. Execute Query
            stmt.executeUpdate(
                "INSERT INTO student VALUES (1, 'Rahul')"
            );

            // 5. Close Connection
            con.close();

            System.out.println("Data Inserted Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

🔹 JDBC Flow (Interview Point ⭐)

Load Driver

Create Connection

Create Statement

Execute Query

Process Result

Close Connection

🔹 Common JDBC Interfaces (Must Know)
Interface	Purpose
DriverManager	Manages drivers
Connection	DB connection
Statement	SQL execution
PreparedStatement	Secure SQL
ResultSet	Fetch data
🔹 PreparedStatement Example (Recommended)
String sql = "INSERT INTO student VALUES (?, ?)";
PreparedStatement ps = con.prepareStatement(sql);
ps.setInt(1, 2);
ps.setString(2, "Amit");
ps.executeUpdate();


✔ Prevents SQL Injection
✔ Faster

🔹 Common Errors & Fixes

❌ ClassNotFoundException
➡ JDBC JAR not added properly

❌ Access denied for user
➡ Wrong DB username/password

❌ Communications link failure
➡ MySQL server not running

🔹 When JDBC Without Maven is Used?

✅ College projects
✅ Core Java training
✅ Interview explanation
❌ Not recommended for production
