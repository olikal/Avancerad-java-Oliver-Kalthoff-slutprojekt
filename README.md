🎓To-Do List Manager
=========================

---

To-Do List Manager is a simple application designed to help users organize daily tasks. Users can add, edit, delete and mark activities as done. The project uses Java and Spring Boot for backend and JavaFX for frontend for user interaction.

---

## 📋Table of Contents

---

- [Getting Started](#getting-started)
- [Running the Application](#running-the-application)
- [API-Requests](#api-requests)
- [Tech Stack](#tech-stack)


## 🚀Getting Started

---

### Prerequisites

To run this project, you will need the following:

- **[JDK 23.0.1](https://www.oracle.com/cis/java/technologies/downloads/)** or higher
- **IDE**:
    - [IntelliJ IDEA](https://lp.jetbrains.com/intellij-idea-promo/?source=google&medium=cpc&campaign=EMEA_en_WEST_IDEA_Branded&term=intellij&content=693349187751&gad_source=1&gclid=CjwKCAiAmMC6BhA6EiwAdN5iLc7kX2T1nr_s36LcjoOVRVosqUNncz02dbJSGmV9f0JKD0-_66j_GBoCKzwQAvD_BwE)
    - [Visual Studio Code](https://code.visualstudio.com/)
    - [Eclipse](https://www.eclipse.org/downloads/packages/release/2022-09/r/eclipse-ide-java-developers)
- **[Apache Maven](https://maven.apache.org/download.cgi) (3.9.x or higher)**

  ([Apache installation instructions](https://maven.apache.org/install.html))

### Installation

##### **Clone the repository:**

```bash
git clone https://github.com/olikal/Avancerad_JAVA24_Oliver_Kalthoff_Uppgift
cd Avancerad_JAVA24_Oliver_Kalthoff_Uppgift
```

---


## 🏃 Running the Application

To run the **To-Do List Manager** application, you'll need to start both the **backend** and **frontend** separately in two terminal windows. Follow these steps:

### 1. Start the Backend (Spring Boot)

1. Open a **new terminal window** (Command Prompt or PowerShell).
2. Navigate to the **backend** project folder (`todoapi`):
   ```bash
   cd path\to\your\project\todoapi
   ```
3. Run backend
    ```bash
    mvn spring-boot:run
    ```
### 1. Start the Frontend (JavaFX)

1. Open another **new terminal window** (Command Prompt or PowerShell).
2. Navigate to the **backend** project folder (`todofe`):
   ```bash
   cd path\to\your\project\todofe
   ```
3. Run frontend
    ```bash
    mvn javafx:run
    ```
   
---

When running the application a window will show up with a list section for the user's activities.
Activites can be added, edited, deleted and marked as done by using the buttons.

Functions:

1. Add activity
    - Lets you add an activity to the list.
2. Edit an activity
    - Allows you to edit a selected activity from the list
3. Delete activity
    - Delete a selected activity from the list
4. Show all activities
    - Shows a list of all activities in the list with all the information.
5. Mark as done
    - Mark a selected activity in the list as done
   
---

## 📡API-Requests

Example API requests that can be tested using Postman:

#### GET all activities:
```
GET http://localhost:8080/api/activities
```

#### GET specific activity:
```
GET http://localhost:8080/api/activities/1
```

#### POST to create a new activity:
```
POST http://localhost:8080/api/activities
Content-Type: application/json

{
  "title": "Laga hamburgare",
  "description": "Köpa ingredienser och laga hamburgare",
  "done": false
}
```

#### PUT to update an existing activity:
```
PUT http://localhost:8080/api/activities/1
Content-Type: application/json

{
  "title": "Läs en bok (uppdaterad)",
  "description": "Läs kapitel 3 och 4",
  "done": false
}
```

#### DELETE an activity:
```
DELETE http://localhost:8080/api/activities/4
```

---

## 🛠️Tech Stack

- **Backend**: Java, Spring Boot
- **Frontend**: JavaFX
- **Data Management**: Java ArrayList
- **API Tools**: Postman
- **Version Control**: Git
