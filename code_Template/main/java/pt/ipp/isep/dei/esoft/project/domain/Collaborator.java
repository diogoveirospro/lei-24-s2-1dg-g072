package pt.ipp.isep.dei.esoft.project.domain;

public class Collaborator {
    private String name;
    private String birthday;
    private String admissionDate;
    private String address;
    private int contactInfo;
    private String idDocumentType;
    private int idDocumentNumber;

    public Collaborator(String name, String birthday, String admissionDate, String address, int contactInfo, String idDocumentType, int idDocumentNumber) {
        this.name = name;
        this.birthday = birthday;
        this.admissionDate = admissionDate;
        this.address = address;
        this.contactInfo = contactInfo;
        this.idDocumentType = idDocumentType;
        this.idDocumentNumber = idDocumentNumber;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public String getAddress() {
        return address;
    }

    public int getContactInfo() {
        return contactInfo;
    }
    public String getIdDocumentType(){
        return idDocumentType;
    }

    public int getIdDocumentNumber() {
        return idDocumentNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactInfo(int contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setIdDocumentType(String idDocumentType) {
        this.idDocumentType = idDocumentType;
    }

    public void setIdDocumentNumber(int idDocumentNumber) {
        this.idDocumentNumber = idDocumentNumber;
    }

}
