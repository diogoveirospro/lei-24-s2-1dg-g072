# US021 - Add a New Entry to the To-Do List

## 4. Tests 

### Class ToDoListTest

**Test 1:** Test if a new entry is added to the to-do list.

    @Test
    public void testAddAndGetToDoListEntry() throws InvalidEntryDataException, InvalidTaskDataException {
        // Arrange
        Task task = new Task("TaskName", "12");
        ToDoListEntry toDoListEntry = new ToDoListEntry(task, null, ToDoListEntry.DegreeOfUrgency.HIGH);

        // Act
        toDoList.addEntry(toDoListEntry);
        ToDoListEntry retrievedEntry = toDoList.getToDoListEntryByTaskName("TaskName");

        // Assert
        assertEquals(toDoListEntry, retrievedEntry);
    }


## 5. Construction (Implementation)

### Class AddToDoListController 

```java
    public void addNewToDoListEntry(String taskName, String duration, String greenSpaceName, String degreeOfUrgency) throws InvalidTaskDataException, InvalidEntryDataException {
        GreenSpace greenSpace = greenSpaceRepository.getGreenSpaceByParkName(greenSpaceName);
        ToDoListEntry.DegreeOfUrgency degree = ToDoListEntry.DegreeOfUrgency.getDegreeOfUrgency(degreeOfUrgency);
        Task task = new Task(taskName, duration);
        ToDoListEntry toDoListEntry = new ToDoListEntry(task, greenSpace, degree);
        toDoList.addEntry(toDoListEntry);
    }
```

### Class ToDoList

```java
    public void addEntry(ToDoListEntry toDoListEntry) {
        this.toDoListEntries.add(toDoListEntry);
        save(toDoListEntries);
    }
```

## 6. Integration and Demo 

* A new option on the Green Space Manager menu options was added, so he can register a new To-Do List Entry .
