package pt.ipp.isep.dei.esoft.project.domain;

public class GreenSpace {
    /**
     *
     *
     */
    private String parkName;
    /**
     *
     *
     */
    private double dimension;
    /**
     *
     *
     */
    private String address;
    /**
     *
     *
     */
    public enum TypeOfGreenSpace {
        GARDEN("Garden"),
        MPARK("Medium-sized park"),
        LPARK("Large-sized park"),;
        private final String type;
        TypeOfGreenSpace(String type) {
            this.type = type;
        }
        public String getType() {
            return type;
        }

        @Override
        public String toString() {
            return type;
        }

        public static TypeOfGreenSpace getTypeOfGreenSpace(String type) {
            for (TypeOfGreenSpace typeOfGreenSpace : TypeOfGreenSpace.values()) {
                if (typeOfGreenSpace.getType().equals(type)) {
                    return typeOfGreenSpace;
                }
            }
            throw new IllegalArgumentException("Invalid type of green space: " + type);
        }
    }

    /**
     *
     *
     */
    private TypeOfGreenSpace type;

    /**
     *
     *
     * @param type
     * @param parkName
     * @param dimension
     * @param address
     */
    public GreenSpace(TypeOfGreenSpace type, String parkName, double dimension,String address) {
        this.type =type;
        this.parkName = parkName;
        this.dimension = dimension;
        this.address = address;
    }

    public double getDimension() {
        return dimension;
    }

    public String getAddress() {
        return address;
    }

    public String getParkName() {
        return parkName;
    }

    public TypeOfGreenSpace getType() {
        return type;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDimension(double dimension) {
        this.dimension = dimension;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public void setType(TypeOfGreenSpace type) {
        this.type = type;
    }
}
