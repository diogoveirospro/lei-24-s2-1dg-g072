# US002 - Register a Job 

## 4. Tests 

### Class JobRepositoryTest
**Test 1:** Test adding a job successfully.

	@Test
    void testAddJob_Successfully() {
        Job job = new Job("AAA");
        assertDoesNotThrow(() -> jobRepository.addJob(job));
        assertTrue(jobRepository.getJobs().contains(job));
    }
	

**Test 2:** Test adding a job throws an exception on invalid job. 

	@Test
    void testAddJob_ThrowsExceptionOnInvalidJob() {
        Job job = null;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> jobRepository.addJob(job));
        assertEquals("Job cannot be null!", exception.getMessage());
    }

**Test 3:** Test adding a job throws an exception if the job already exists.

    @Test
    void testAddJob_ThrowsExceptionOnAlreadyExistingJob() {
        Job job = new Job("AAA");
        jobRepository.addJob(job);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> jobRepository.addJob(job));
        assertEquals("Job already exists!", exception.getMessage());
    }

## 5. Construction (Implementation)

### Class RegisterJobController 

```java
private String name;

public Job(String name){
    if(name == null || name.trim().isEmpty()){
        throw new IllegalArgumentException("Name cannot be empty or null");
    }
    this.name = name;
}

public void registerJob(String name) {

    Job job = new Job(name);
    jobRepository.addJob(job);
}
```

### Class JobRepository

```java
private final List<Job> jobs;

public JobRepository(){
    jobs = new ArrayList<>();
}

public void addJob(Job newJob){
    if (!validateJob(newJob)) {
        throw new IllegalArgumentException("Job already exists!");
    }
    if (newJob == null) {
        throw new IllegalArgumentException("Job cannot be null!");
    }

    jobs.add(newJob);

}

private boolean validateJob(Job job){
    return !jobs.contains(job);
}
```


## 6. Integration and Demo 

* A new option on the HRM menu options was added.


## 7. Observations

n/a