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
        greenSpacesManagedByGSM.sort((gs1, gs2) -> Double.compare(gs1.getDimension(), gs2.getDimension()));
    }
}
