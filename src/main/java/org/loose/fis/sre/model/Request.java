package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

public class Request {
    @Id
    private String completeName;
    private String titleTA;
    private String email;
    private String address;
    private String phone;

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
