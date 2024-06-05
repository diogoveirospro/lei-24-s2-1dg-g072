package pt.ipp.isep.dei.esoft.project.domain.externalModules;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.Arrays;
import java.util.List;

public class SortSmalestToBigestByStatus implements SortExternalModule {

    @Override
    public String getReferenceValue(String sortingOption) {
        if (sortingOption.equalsIgnoreCase("type")) {
            return "Sort by Type";
        }
        return "None";
    }

    @Override
    public void sortList(List<GreenSpace> greenSpacesManagedByGSM) {

        int n = greenSpacesManagedByGSM.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (greenSpacesManagedByGSM.get(j).getType().compareTo(greenSpacesManagedByGSM.get(j+1).getType()) > 0 || greenSpacesManagedByGSM.get(j).getDimension() > greenSpacesManagedByGSM.get(j+1).getDimension()) {
                    GreenSpace temp = greenSpacesManagedByGSM.get(j);
                    greenSpacesManagedByGSM.set(j, greenSpacesManagedByGSM.get(j+1));
                    greenSpacesManagedByGSM.set(j+1, temp);
                }
            }
        }
    }
}

