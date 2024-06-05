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
        List<GreenSpace.TypeOfGreenSpace> typeOfGreenSpaceList = Arrays.asList(GreenSpace.TypeOfGreenSpace.values());
        greenSpacesManagedByGSM.sort((gs1, gs2) -> {
            int typeComparison = Integer.compare(typeOfGreenSpaceList.indexOf(gs1.getType()), typeOfGreenSpaceList.indexOf(gs2.getType()));
            if (typeComparison != 0) {
                return typeComparison;
            }
            return Double.compare(gs1.getDimension(), gs2.getDimension());
        });
    }
}

