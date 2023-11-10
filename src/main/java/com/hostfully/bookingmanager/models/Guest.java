package com.hostfully.bookingmanager.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "guests")
public class Guest {
    @Id
    private UUID uid;
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    @Column(name = "birth_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @Column(name = "picture_url", nullable = false)
    private String pictureURL;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false, length = 50)
    private String city;
    @Column(nullable = false, length = 30)
    private String state;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(nullable = false, length = 70)
    private String email;
    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;

    @PrePersist
    public void prePersist() {
        createdTime = LocalDateTime.now();
    }

    public Guest() {
        this.setUid(UUID.randomUUID());
    }

    public Guest(UUID uid, String firstName, String lastName, LocalDate birthDate, String pictureURL, String address, String city, String state, String phoneNumber, String email, LocalDateTime createdTime) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.pictureURL = pictureURL;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.createdTime = createdTime;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}
