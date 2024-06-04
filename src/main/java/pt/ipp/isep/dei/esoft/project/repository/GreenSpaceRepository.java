package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class GreenSpaceRepository {
    List<GreenSpace> greenSpaceList;
    private static final String CONFIGURATION_FILENAME = "src/main/resources/config/config.properties";
    private static final String SORT_METHODS = "sort.methods";

    public GreenSpaceRepository() {
        this.greenSpaceList = new ArrayList<>();
    }

    public List<GreenSpace> getGreenSpaceListSorted(Collaborator greenSpaceManager, String sortingOption) {
        List<GreenSpace> greenSpacesManagedByGSM = new ArrayList<>();
        for (GreenSpace greenSpace : greenSpaceList) {
            if (greenSpace.getGreenSpaceManager().equals(greenSpaceManager)) {
                greenSpacesManagedByGSM.add(greenSpace);
            }
        }
        sortBySortOption(sortingOption, greenSpacesManagedByGSM);
        return greenSpacesManagedByGSM;
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

    private void sortBySortOption(String sortingOption, List<GreenSpace> greenSpacesManagedByGSM) {
        // Implement sorting logic here
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
            ex.printStackTrace();
        }
        return props;
    }
    public void addGreenSpace(GreenSpace greenSpace) {
        greenSpaceList.add(greenSpace);
    }
}
