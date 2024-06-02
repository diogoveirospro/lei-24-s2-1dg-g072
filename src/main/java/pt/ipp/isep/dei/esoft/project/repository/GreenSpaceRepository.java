package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.ArrayList;
import java.util.List;

public class GreenSpaceRepository {
    List<GreenSpace> greenSpaceList;

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

    }

    public GreenSpace getGreenSpaceByParkName(String parkName) {
        for (GreenSpace greenSpace : greenSpaceList) {
            if (greenSpace.getParkName().equals(parkName)) {
                return greenSpace;
            }
        }
        return null;
    }


}
