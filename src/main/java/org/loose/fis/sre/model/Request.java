package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

public class Request {
    @Id
    private int requestID;
    private String completeName;
    private String email;
    private String address;
    private String phone;
    private String titleAttraction;
    private String ticketType;

    public static int nr;

    public Request(){}

    public Request(String completeName, String email, String address, String phone, String titleAttraction, String ticketType){
        this.completeName=completeName;
        this.email=email;
        this.address=address;
        this.phone=phone;
        this.titleAttraction=titleAttraction;
        this.ticketType=ticketType;
        nr++;
        requestID=nr;
    }

    public int getRequestID() {
        return requestID;
    }

    public String getTitleAttraction() {
        return titleAttraction;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public void setTitleAttraction(String titleAttraction) {
        this.titleAttraction = titleAttraction;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
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
