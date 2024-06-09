# US027 - List GreenSpaces 

## 4. Tests

### Class GreenSpaceRepositoryTest

**Test 1:** Check if the sort methods are correctly retrieved.

    @Test
    void testGetSortMethods() {
        Properties properties = mock(Properties.class);
        when(properties.getProperty(anyString())).thenReturn("type,area");
        List<String> sortMethods = repository.getSortMethods();
        assertEquals(2, sortMethods.size());
        assertTrue(sortMethods.contains("type"));
        assertTrue(sortMethods.contains("area"));
    }

**Test 2:** Check if the list of green spaces is correctly retrieved.

    @Test
    void testGetListGreenSpacesManagedByGsm() {
        List<GreenSpace> managedList = repository.getListGreenSpacesManagedByGsm(mockCollaborator);
        assertEquals(2, managedList.size());
        assertTrue(managedList.contains(mockGreenSpace1));
        assertTrue(managedList.contains(mockGreenSpace2));
    }

**Test 3:** Check if the list of green spaces is correctly sorted.

    @Test
    void testGetGreenSpaceListSortedInvalidOption() {
        List<GreenSpace> sortedList = repository.getGreenSpaceListSorted(mockCollaborator, "invalid");
        assertEquals(2, sortedList.size());
        assertTrue(sortedList.contains(mockGreenSpace1));
        assertTrue(sortedList.contains(mockGreenSpace2));
    }

**Test 4:** Check if the list of green spaces is correctly sorted by name.

    @Test
    void testGetGreenSpaceListSortedByType() {
        List<GreenSpace> sortedList = repository.getGreenSpaceListSorted(mockCollaborator, "type");
        assertEquals(2, sortedList.size());
        assertTrue(sortedList.contains(mockGreenSpace1));
        assertTrue(sortedList.contains(mockGreenSpace2));
    }

**Test 5:** Check if the list of green spaces is correctly sorted by area.

    @Test
    void testGetGreenSpaceListSortedByArea() {
        List<GreenSpace> sortedList = repository.getGreenSpaceListSorted(mockCollaborator, "area");
        assertEquals(2, sortedList.size());
        assertTrue(sortedList.contains(mockGreenSpace1));
        assertTrue(sortedList.contains(mockGreenSpace2));
    }

## 5. Construction (Implementation)

### Class ListGreenSpacesController

```java
    public List<GreenSpaceDto> getGreenSpaceList(String sortingOption){
        Collaborator GSM = getCollaboratorFromSession();
        List<GreenSpace> greenSpaceList = greenSpaceRepository.getGreenSpaceListSorted(GSM, sortingOption);
        GreenSpaceMapper mapper = new GreenSpaceMapper();
        return mapper.greenSpaceListToDto(greenSpaceList);
    }

    public List<String> getSortMethods() {
        return greenSpaceRepository.getSortMethods();
    }
```

### Class GreenSpaceRepository

```java
    private void sortBySortOption(String referenceValue, SortExternalModule sorter, List<GreenSpace> greenSpacesManagedByGSM) {
        sorter.sortList(greenSpacesManagedByGSM);
    }

    public GreenSpace getGreenSpaceByParkName(String parkName) {
        for (GreenSpace greenSpace : greenSpaceList) {
            if (greenSpace.getParkName().equals(parkName)) {
                return greenSpace;
            }
        }
        return null;
    }

    public List<GreenSpace> getListGreenSpacesManagedByGsm(Collaborator greenSpaceManager) {
        List<GreenSpace> greenSpacesManagedByGSM = new ArrayList<>();
        for (GreenSpace greenSpace : greenSpaceList) {
            if (greenSpace.getGreenSpaceManager().equals(greenSpaceManager)) {
                greenSpacesManagedByGSM.add(greenSpace);
            }
        }
        return greenSpacesManagedByGSM;
    }
```

## 6. Integration and Demo 

* A new option on the Green Space Manager menu options was added, so he can list green spaces managed by his .
