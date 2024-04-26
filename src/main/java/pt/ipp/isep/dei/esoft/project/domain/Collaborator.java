package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;

public class Collaborator {
    private String name;
    private String birthday;
    private String admissionDate;
    private String address;
    private int mobileNumber;
    private String email;
    private String idDocumentType;
    private int idDocumentNumber;
    private ArrayList<Skill> skills;

    public Collaborator(String name, String birthday, String admissionDate, String address, int mobileNumber, String email, String idDocumentType, int idDocumentNumber) {
        this.name = name;
        this.birthday = birthday;
        this.admissionDate = admissionDate;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.email = email;
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

    public int getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
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

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdDocumentType(String idDocumentType) {
        this.idDocumentType = idDocumentType;
    }

    public void setIdDocumentNumber(int idDocumentNumber) {
        this.idDocumentNumber = idDocumentNumber;
    }

    public boolean analyseCollaborator(ArrayList<Skill> skills){
        for (Skill s1 : this.skills){
            if (skills.contains(s1)){
                return true;
            }
        }
        return false;
    }

}

