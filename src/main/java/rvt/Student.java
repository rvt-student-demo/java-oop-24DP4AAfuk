package rvt;

public class Student {
    public String name;
    public String surname;
    public String email;
    public String IDnum;
    public String registrationDate;


    public Student(String name, String surname, String email, String IDnum, String registrationDate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.IDnum = IDnum;
        this.registrationDate = registrationDate;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getIDnum() {
        return IDnum;
    }


}
