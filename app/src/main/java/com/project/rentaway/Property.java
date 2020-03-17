package com.project.rentaway;



public class Property {

    private String imageUri;
    private String Location;
    private String balconies, noOfFloors, floorNo;
    private String carpetArea, Furnishing;
    private String Rent;
    private String securityAmount;
    private String BHK;
    private String Address,apartmentName;


    public Property() {
    }

    public Property(String imageUri, String location, String balconies, String noOfFloors, String floorNo, String carpetArea, String Furnishing, String apartmentName,String rent, String securityAmount, String BHK, String address) {
        this.imageUri = imageUri;
        Location = location;
        this.balconies = balconies;
        this.noOfFloors = noOfFloors;
        this.floorNo = floorNo;
        this.carpetArea = carpetArea;
        this.Furnishing = Furnishing;
        this.apartmentName=apartmentName;
        Rent = rent;
        this.securityAmount = securityAmount;
        this.BHK = BHK;
        Address = address;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getBalconies() {
        return balconies;
    }

    public void setBalconies(String balconies) {
        this.balconies = balconies;
    }

    public String getNoOfFloors() {
        return noOfFloors;
    }

    public void setNoOfFloors(String noOfFloors) {
        this.noOfFloors = noOfFloors;
    }

    public String getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }

    public String getCarpetArea() {
        return carpetArea;
    }

    public void setCarpetArea(String carpetArea) {
        this.carpetArea = carpetArea;
    }

    public String getFurnishing() {
        return Furnishing;
    }

    public void setFurnishing(String furnishing) {
        this.Furnishing = furnishing;
    }

    public String getRent() {
        return Rent;
    }

    public void setRent(String rent) {
        Rent = rent;
    }

    public String getSecurityAmount() {
        return securityAmount;
    }

    public void setSecurityAmount(String securityAmount) {
        this.securityAmount = securityAmount;
    }

    public String getBHK() {
        return BHK;
    }

    public void setBHK(String BHK) {
        this.BHK = BHK;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
