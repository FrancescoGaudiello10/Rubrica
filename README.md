# Rubrica 📚

A simple application built in Java 8.

It allows to insert the data of a single user through an interface created in Swing.

<p align="center">
  <img src="img/First.png" width="600" /> </br>
</p>


## Programs used:
- Java 8
- Swing / AWT
- MySQL 

### How does it work? 💻
After successfully installing Java 8 and MySQL (Workbench for convenience), the latter starts, making the connection active.
The connection is possible only after downloading and installing the MySQL Connector **jar** from the official website and pasting it into the project.
A "getConnection( )" function is created to better manage the connection.

Now it's possible to start the application and fill in the various fields:
<p align="center">
  <img src="img/Insert.png" width="600" /> </br>
</p>


The entered contact will be automatically added to the Database.
*The latter has been constructed in order to avoid SQL Injection.*

After entering all the fields you have a result similar to this:
<p align="center">
  <img src="img/MySQL.png" width="900" /> </br>
</p>

Finally, you can return to the application and check all the elements already entered:
<p align="center">
  <img src="img/Search.png" width="600" /> </br>
</p>


*I hope this simple application will help you understand how Java interacts with databases.*
<br>

#### If you find this helpful, please leave a star. 🌟

