package pt.ipp.isep.dei.esoft.project.Mapper;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDto;

import java.util.ArrayList;
import java.util.List;

public class GreenSpaceMapper {
    private List<GreenSpaceDto> greenSpaceListDto;
    public GreenSpaceMapper() {
        greenSpaceListDto = new ArrayList<>();
    }

    public List<GreenSpaceDto> getGreenSpaceListDto() {
        return greenSpaceListDto;
    }

    public void setGreenSpaceListDto(List<GreenSpaceDto> greenSpaceListDto) {
        this.greenSpaceListDto = greenSpaceListDto;
    }

    public List<GreenSpaceDto> greenSpaceListToDto(List<GreenSpace> greenSpaceList) {
        for (GreenSpace greenSpace : greenSpaceList){
            GreenSpaceDto greenSpaceDto = new GreenSpaceDto(
                greenSpace.getParkName(),
                greenSpace.getDimension(),
                greenSpace.getGreenSpaceManager(),
                greenSpace.getToDoList(),
                greenSpace.getType(),
                greenSpace.getAddress()
            );
            this.greenSpaceListDto.add(greenSpaceDto);
        }
        return greenSpaceListDto;
    }
}
