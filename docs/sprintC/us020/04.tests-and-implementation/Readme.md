# US020 - Register a green space

## 4. Tests 

### Class GreenSpaceTest

**Test 1:** Check if the method testGreenSpaceNull is working.

    @Test
    public void testGreenSpaceNull() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            GreenSpace greenSpace = new GreenSpace(GreenSpace.TypeOfGreenSpace.GARDEN, "Ovar", 40.5, "Ovar", null);
        });
    }

**Test 2:** Check if the method testGreenSpaceTypeNull is working.

    public void testGreenSpaceTypeNull() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            GreenSpace greenSpace = new GreenSpace(null, "Ovar", 40.5, "Ovar", gsm);
        });
    }

**Test 3:** Check if the method testGreenSpaceAddressNull is working.

    public void testGreenSpaceAddressNull() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            GreenSpace greenSpace = new GreenSpace(GreenSpace.TypeOfGreenSpace.GARDEN, "Ovar", 40.5, null, gsm);
        });
    }

**Test 4:** Check if the method testGreenSpaceDimensionNegative is working.

    public void testGreenSpaceDimensionNegative() {
        assertThrows(InvalidGreenSpaceDataException.class, () -> {
            GreenSpace greenSpace = new GreenSpace(GreenSpace.TypeOfGreenSpace.GARDEN, "Ovar", -40.5, "Ovar", gsm);
        });
    }

### Class GreenSpaceRepositoryTest

**Test 1:** Check if addGreenSpace adds a new green space to the repository.

    @Test
    void testAddGreenSpace() {
        GreenSpace newGreenSpace = mock(GreenSpace.class);
        repository.addGreenSpace(newGreenSpace);
        assertTrue(repository.getGreenSpaceList().contains(newGreenSpace));
    }

## 5. Construction (Implementation)

### Class RegisterGreenSpaceController 

```java
    public boolean registerGreenSpace(String parkName, double dimension, String address, GreenSpace.TypeOfGreenSpace type) {
    try {
        Collaborator collaborator = getCollaboratorFromSession();
        GreenSpace greenSpace = new GreenSpace(type, parkName, dimension, address, collaborator);


        greenSpaceRepository.addGreenSpace(greenSpace);

        return true;
    } catch (InvalidGreenSpaceDataException e) {

        System.err.println("Error registering green space: " + e.getMessage());
        return false;
    }

}
```

### Class GreenSpaceRepository

```java
public void addGreenSpace(GreenSpace greenSpace) {
    greenSpaceList.add(greenSpace);
    saveGreenSpaceRepositoryToFile();
}
```

## 6. Integration and Demo 

* A new option on the Green Space Manager menu options was added, so he can register a new green space .

