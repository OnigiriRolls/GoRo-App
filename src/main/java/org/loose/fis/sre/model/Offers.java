package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

public class Offers {
    @Id
    private String title;
    private String photo;
    private String details;

    public Offers() {
    }

    public Offers(String title, String photo, String details) {
        this.title = title;
        this.photo = photo;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offers ofr = (Offers) o;

        if (title != null ? !title.equals(ofr.title) : ofr.title != null) return false;
        if (photo != null ? !photo.equals(ofr.photo) : ofr.photo != null) return false;
        return details != null ? !details.equals(ofr.details) : ofr.details != null;

    }
}
