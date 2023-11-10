package com.hostfully.bookingmanager.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "properties")
public class Property {
    @Id
    private UUID uid;
    @Column(nullable = false, length = 50)
    private String type;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
    @Column(name = "base_guests", nullable = false)
    private Integer baseGuests;
    @Column(name = "maximum_guests", nullable = false)
    private Integer maximumGuests;
    private Integer bedrooms;
    private Integer bathrooms;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false, length = 50)
    private String city;
    @Column(nullable = false, length = 30)
    private String state;
    @Column(name = "picture_url", nullable = false)
    private String pictureURL;

    public Property(){};
    
    public Property(UUID uid, String type, String name, Boolean isActive, Integer baseGuests, Integer maximumGuests, Integer bedrooms, Integer bathrooms, String address, String city, String state, String pictureURL) {
        this.uid = uid;
        this.type = type;
        this.name = name;
        this.isActive = isActive;
        this.baseGuests = baseGuests;
        this.maximumGuests = maximumGuests;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.address = address;
        this.city = city;
        this.state = state;
        this.pictureURL = pictureURL;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Integer getBaseGuests() {
        return baseGuests;
    }

    public void setBaseGuests(Integer baseGuests) {
        this.baseGuests = baseGuests;
    }

    public Integer getMaximumGuests() {
        return maximumGuests;
    }

    public void setMaximumGuests(Integer maximumGuests) {
        this.maximumGuests = maximumGuests;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }
}
