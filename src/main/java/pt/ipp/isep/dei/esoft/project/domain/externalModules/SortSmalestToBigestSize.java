package pt.ipp.isep.dei.esoft.project.domain.externalModules;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.List;

public class SortSmalestToBigestSize implements SortExternalModule {

    @Override
    public String getReferenceValue(String sortingOption) {
        if (sortingOption.equalsIgnoreCase("area")) {
            return "Sort by Area";
        }
        return "None";
    }

    @Override
    public void sortList(List<GreenSpace> greenSpacesManagedByGSM) {
        for (int i = 0; i < greenSpacesManagedByGSM.size(); i++) {
            for (int j = i + 1; j < greenSpacesManagedByGSM.size(); j++) {
                if (greenSpacesManagedByGSM.get(i).getDimension() > greenSpacesManagedByGSM.get(j).getDimension()) {
                    GreenSpace temp = greenSpacesManagedByGSM.get(i);
                    greenSpacesManagedByGSM.set(i, greenSpacesManagedByGSM.get(j));
                    greenSpacesManagedByGSM.set(j, temp);
                }
            }
        }
    }
}
