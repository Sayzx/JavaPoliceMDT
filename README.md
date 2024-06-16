
# Project Name

## Description

This project is a Java application that manages accounts, citizens, vehicles, and criminal records. It provides functionalities to add, view, and manage these entities using a simple console interface. Data is stored in JSON files for persistence.

## Features

- **Account Management**: Create, login, and logout accounts.
- **Citizen Management**: Add and view citizen details.
- **Vehicle Management**: Add and view vehicle details.
- **Criminal Record Management**: Add and view criminal records associated with citizens.

## Prerequisites

- Java Development Kit (JDK) 22 or higher
- Maven (for managing dependencies)

## Dependencies

The project uses the following dependencies:

- **Gson**: For JSON serialization and deserialization.

## Setup

1. **Clone the repository**:

   ```sh
   git clone https://github.com/Sayzx/JavaPoliceMDT
   cd JavaPoliceMDT
   ```

2. **Set up the project with Maven**:

   Make sure you have Maven installed. You can check by running:

   ```sh
   mvn -v
   ```

   If Maven is not installed, you can download and install it from [here](https://maven.apache.org/install.html).

3. **Add dependencies to your `pom.xml` file**:

   ```xml
   <project xmlns="http://maven.apache.org/POM/4.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
       <modelVersion>4.0.0</modelVersion>

       <groupId>com.sayzx.panel</groupId>
       <artifactId>PanelManager</artifactId>
       <version>1.0-SNAPSHOT</version>

       <dependencies>
           <dependency>
               <groupId>com.google.code.gson</groupId>
               <artifactId>gson</artifactId>
               <version>2.8.9</version>
           </dependency>
       </dependencies>

       <build>
           <plugins>
               <plugin>
                   <groupId>org.apache.maven.plugins</groupId>
                   <artifactId>maven-compiler-plugin</artifactId>
                   <version>3.8.1</version>
                   <configuration>
                       <source>1.8</source>
                       <target>1.8</target>
                   </configuration>
               </plugin>
           </plugins>
       </build>
   </project>
   ```

4. **Build the project**:

   ```sh
   mvn clean install
   ```

## Running the Application

1. **Navigate to the target directory**:

   ```sh
   cd target
   ```

2. **Run the application**:

   ```sh
   java -cp PanelManager-1.0-SNAPSHOT.jar com.sayzx.panel.mdt.Program
   ```

## Usage

### Main Menu

When you run the application, you will be presented with the following options:

```
Select an option:
[1] Account Management
[2] Citizen Management
[3] Vehicle Management
[4] Infraction Management
[0] Exit
```

### Account Management

1. **Create Account**: Create a new user account.
2. **Login**: Log in with an existing account.
3. **Logout**: Log out of the current account.

### Citizen Management

1. **Add Citizen**: Add a new citizen to the system.
2. **View Citizen Details**: View details of a citizen by ID.

### Vehicle Management

1. **Add Vehicle**: Add a new vehicle to the system.
2. **View All Vehicles**: View details of all vehicles.
3. **View Vehicle Details by ID**: View details of a vehicle by ID.

### Infraction Management

1. **Add Infraction on citizen**: Add a new infraction to a citizen's record.
2. **View All Infractions**: View details of all infractions.
3. **View Infractions by Citizen ID**: View details of infractions by citizen ID.

## License

This project is licensed under the MIT License.
