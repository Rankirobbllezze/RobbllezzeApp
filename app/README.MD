### DATA MANAGEMENT IN COMPOSABLS
- A composable defines the layout and elements in an app screen
- The composables will contain data that will allow our user to interact with and also view
- Data inside a composable unit is called STATE
- To manage this state android-kotlin utilizes the concept of a viewmode

### VIEWMODEL
- Observes the state updates in a composable which determines how a composable is drawn on the android screen
- Apart from observing the data if also encapsulated business login i.e functions working on the data



### ROOM DATABASE
Steps to work with the room persistence database library
1. Add the library dependencies to the build.gradle
   implementation("androidx.room:room-runtime:2.7.1")
   implementation("androidx.room:room-ktx:2.7.1")
2. Create Entity : select the model and annotate it with the @Entity(tableName = "")
3. Create a database access object (DAO) :: THIS WILL PROVIDE METHODS THAT WILL PERFORM OPERATIONS ON A DATABASE TABLE RECORDS
4. CREATE THE database classes : this will be used to track the different database versions i.e. migrations, we can also include sed data in this database versions i.e. seed refers to mock data
5. Create a Repository to work with viewmodel
        UI (composables ie. screens) -> viewmodel -> Repository class
     -> Room Database (DAO)
= THE REPOSITORY ACTS AS :
a. A single source of truth your data
b. An abstraction layer between the data source
c. The component to handle all data operations
6. To provide the viewModel on compilation we will add a dependency manager HILT
To add HILT  to th project :
   a. In the build.gradle project level add this hilt plugin :
   id("com.google.dagger.hilt.android") version "2.56.1" apply false
7. 