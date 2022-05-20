package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

public class Request {
    @Id
    private int requestID;
    private String completeName;
    private String titleTA;
    private String email;
    private String address;
    private String phone;
    private String titleAttraction;

    public static int nr;

    public Request(String completeName, String titleTA, String email, String address, String phone, String titleAttraction){
        this.completeName=completeName;
        this.titleTA=titleTA;
        this.email=email;
        this.address=address;
        this.phone=phone;
        this.titleAttraction=titleAttraction;
        nr++;
        requestID=nr;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }

    public void setTitleTA(String titleTA) {
        this.titleTA = titleTA;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompleteName() {
        return completeName;
    }

    public String getTitleTA() {
        return titleTA;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
