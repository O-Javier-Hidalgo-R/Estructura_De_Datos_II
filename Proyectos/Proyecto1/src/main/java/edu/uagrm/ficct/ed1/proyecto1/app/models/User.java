package edu.uagrm.ficct.ed1.proyecto1.app.models;

public class User {
    private String name;
    private String fistLastName;
    private String secondLastName;
    private String addres;
    private String phone;
    private int sanctions;
    private int sanc_money;

    public User() {
    }

    public User(String name, String fistLastName, String secondLastName, String addres, String phone, int sanctions, int sanc_money) {
        this.name = name;
        this.fistLastName = fistLastName;
        this.secondLastName = secondLastName;
        this.addres = addres;
        this.phone = phone;
        this.sanctions = sanctions;
        this.sanc_money = sanc_money;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFistLastName() {
        return fistLastName;
    }

    public void setFistLastName(String fistLastName) {
        this.fistLastName = fistLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSanctions() {
        return sanctions;
    }

    public void setSanctions(int sanctions) {
        this.sanctions = sanctions;
    }

    public int getSanc_money() {
        return sanc_money;
    }

    public void setSanc_money(int sanc_money) {
        this.sanc_money = sanc_money;
    }
    
    
}
