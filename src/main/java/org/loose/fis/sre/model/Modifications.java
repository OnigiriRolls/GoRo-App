package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

public class Modifications {
    @Id
    //for TA
    private String title;
    private String photoTitle;
    private String availability;
    private String description;
    private float price;

    public Modifications() {}

    public Modifications(String title, String photoTitle, String availability, String description, float price) {
        this.title = title;
        this.photoTitle = photoTitle;
        this.availability = availability;
        this.description = description;
        this.price = price;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoTitle() {
        return photoTitle;
    }

    public void setPhotoTitle(String photoTitle) {
        this.photoTitle = photoTitle;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Modifications tatr = (Modifications) o;

        if (title != null ? !title.equals(tatr.title) : tatr.title != null) return false;
        if (photoTitle != null ? !photoTitle.equals(tatr.photoTitle) : tatr.photoTitle != null) return false;
        if (availability != null ? !availability.equals(tatr.availability) : tatr.availability != null) return false;
        if (description != null ? !description.equals(tatr.description) : tatr.description != null) return false;

        return  price == tatr.price ? true:false;
    }
}
