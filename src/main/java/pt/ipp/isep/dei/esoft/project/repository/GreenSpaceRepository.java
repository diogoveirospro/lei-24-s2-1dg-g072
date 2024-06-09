package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.externalModules.SortExternalModule;
import pt.ipp.isep.dei.esoft.project.domain.externalModules.SortSmalestToBigestByStatus;
import pt.ipp.isep.dei.esoft.project.domain.externalModules.SortSmalestToBigestSize;
import pt.ipp.isep.dei.esoft.project.repository.data.SerializableRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class GreenSpaceRepository extends SerializableRepository<List<GreenSpace>>  implements Serializable {
    List<GreenSpace> greenSpaceList;
    private static final String CONFIGURATION_FILENAME = "src/main/resources/config/config.properties";
    private static final String SORT_METHODS = "sort.methods";

    public GreenSpaceRepository() {
        super("greenSpaceRepository.ser");
        greenSpaceList = super.load();
        if (greenSpaceList == null){
            greenSpaceList = new ArrayList<>();
        }

    }
    public GreenSpaceRepository(String filename) {
        super(filename);
        greenSpaceList = super.load();
        if (greenSpaceList == null){
            greenSpaceList = new ArrayList<>();
        }
    }

    public List<GreenSpace> getGreenSpaceListSorted(Collaborator greenSpaceManager, String sortingOption) {
        List<GreenSpace> greenSpacesManagedByGSM = new ArrayList<>();
        for (GreenSpace greenSpace : greenSpaceList) {
            if (greenSpace.getGreenSpaceManager().getName().equalsIgnoreCase(greenSpaceManager.getName())) {
                greenSpacesManagedByGSM.add(greenSpace);
            }
        }

        SortExternalModule sorter;
        if (sortingOption.equalsIgnoreCase("type")) {
            sorter = new SortSmalestToBigestByStatus();
        } else if (sortingOption.equalsIgnoreCase("area")) {
            sorter = new SortSmalestToBigestSize();
        } else {
            return greenSpacesManagedByGSM;  // No sorting if the option is unrecognized
        }

        String referenceValue = sorter.getReferenceValue(sortingOption);
        sortBySortOption(referenceValue, sorter, greenSpacesManagedByGSM);
        return greenSpacesManagedByGSM;
    }

    public List<GreenSpace> getListGreenSpacesManagedByGsm(Collaborator greenSpaceManager) {
        List<GreenSpace> greenSpacesManagedByGSM = new ArrayList<>();
        for (GreenSpace greenSpace : greenSpaceList) {
            if (greenSpace.getGreenSpaceManager().getName().equalsIgnoreCase(greenSpaceManager.getName())) {
                greenSpacesManagedByGSM.add(greenSpace);
            }
        }
        return greenSpacesManagedByGSM;
    }

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

    public List<String> getSortMethods() {
        Properties props = getProperties();
        String sortMethodsString = props.getProperty(SORT_METHODS);
        String[] sortMethodsArray = sortMethodsString.split("\\s*,\\s*");
        return new ArrayList<>(Arrays.asList(sortMethodsArray));
    }

    private Properties getProperties() {
        Properties props = new Properties();
        try {
            InputStream in = new FileInputStream(CONFIGURATION_FILENAME);
            props.load(in);
            in.close();
        } catch (IOException ex) {
            System.out.println("Properties were not loaded successfully.");
        }
        return props;
    }

    public void addGreenSpace(GreenSpace greenSpace) {
        greenSpaceList.add(greenSpace);
        saveGreenSpaceRepositoryToFile();
    }

    public void saveGreenSpaceRepositoryToFile() {
        save(greenSpaceList);
    }

    public List<GreenSpace> getGreenSpaceList() {
        return greenSpaceList;
    }
    public void clear() {
        greenSpaceList.clear();
        super.clear();
    }
}
