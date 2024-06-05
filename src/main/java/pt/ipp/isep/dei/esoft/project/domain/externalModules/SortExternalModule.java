package pt.ipp.isep.dei.esoft.project.domain.externalModules;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.List;

public interface SortExternalModule {
    String getReferenceValue(String sortingOption);
    void sortList(List<GreenSpace> greenSpacesManagedByGSM);
}
