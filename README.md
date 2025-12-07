# intuit-build-challenge

### Clone and setup

Open Terminal/Command Prompt.\
git clone git@github.com:charita-dotcom/intuit-build-challenge.git

## Assignment 1

This task demonstrates a custom BlockingQueue implemented with wait()/notifyAll(). It consists of a Producer thread that reads from a source container and puts items into the queue and a Consumer thread that takes from the queue and stores items into a destination container. Additionally, there are JUnit tests that verify the FIFO behavior of the blocking queue and the producer–consumer data transfer from source to destination.

### Prerequisites

- Java JDK 8+ installed (`java` and `javac` on your PATH)
- `git` installed
- JUnit 4.13.2 + Hamcrest 1.3 JARs. You can download the JARs from https://jarcasting.com/. Place the downloaded JARs in Assignment 1/.

### 1. Compile and run

cd intuit-build-challenge/Assignment 1\
javac -cp "src" src/*.java\
java -cp "src" ProducerConsumer

### 2. Compile and run tests

javac -cp "src:test:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" src/\*.java test/\*.java\
java -cp "src:test:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore ProducerConsumerTest

### Sample Output
(base) charitachalla@Charitas-MacBook-Air Assignment 1 % java -cp "src" ProducerConsumer\
Produced: 1\
Consumed: 1\
Produced: 2\
Produced: 3\
Produced: 4\
Consumed: 2\
Consumed: 3\
Produced: 5\
Consumed: 4\
Consumed: 5\
Consumer finished after 5 items.\
Destination container: [1, 2, 3, 4, 5]

## Assignment 2

This assignment demonstrates **functional programming** in Java using the **Streams API** on CSV data. The application reads a sales dataset from CSV and performs various aggregation and grouping operations using **streams**, **lambda expressions**, and **collectors**.

### Prerequisites

- Java (configured for JDK 17 in pom.xml, can be changed)
- Maven
- JUnit 5 for tests

### 1. Project Overview

The program:

1. Reads sales data from a CSV file.
2. Parses each row into a `SalesRecord` object.
3. Executes several analytical queries using the Streams API, such as:
   - Total revenue across all sales
   - Revenue aggregated **by region**
   - Revenue aggregated **by product**
   - Revenue aggregated **by salesperson**
   - Identifying the **top salesperson by revenue**

All analytical logic is implemented using **functional-style code** (Streams + lambdas).

### 2. Dataset

I used ChatGPT to generate the synthetic sales data. The csv file is present in the csv folder. You can replace with your own csv file but ensure the data meets the requirements listed below.

Columns \
Region – Geographic region (North, South, East, West)\
Product – Product type (Widget, Gadget, Gizmo)\
SalesPerson – Name of salesperson (Alice, Bob, Charlie)\
Date – Date of sale (yyyy-MM-dd)\
UnitsSold – Integer number of units\
UnitPrice – Price per unit (double, assumed USD)\

Assumptions
All dates are valid ISO-8601 strings (LocalDate.parse).\
No missing fields (no nulls / empty values).\
Currency is USD.\
The dataset is intentionally small so that:\
Total revenue and per-group revenues can be verified manually.\
Focus remains on Streams, not on data cleaning.\

Example derived metrics from this dataset:\
Total revenue: 970.0\
Revenue by region:\
North: 350.0\
South: 260.0\
East: 230.0\
West: 130.0\
Top salesperson by revenue: Alice

### 3. Build and Run
cd intuit-build-challenge/Assignment 2\
mvn clean install test\
java src/main/java/Assignment2/src/Main.java src/main/resources/sales-data.csv

### Sample Output
(base) charitachalla@Charitas-MacBook-Air Assignment 2 % java src/main/java/Assignment2/src/Main.java src/main/resources/sales-data.csv\
Total revenue: 970.0\
Revenue by region: {West=130.0, South=260.0, North=350.0, East=230.0}\
Revenue by product: {Gizmo=150.0, Gadget=240.0, Widget=580.0}\
Top salesperson by revenue: Alice
