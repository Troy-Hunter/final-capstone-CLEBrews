package com.techelevator.clebrews.model;


import org.hibernate.validator.constraints.NotBlank;

public class Brewery {

	private long id;
	@NotBlank(message="Name cannot be blank")
	private String name;
	@NotBlank(message="required")
	private String address;
	@NotBlank(message="Address is required")
	private String city;
	@NotBlank(message="Zipcode is required")
	private String zipcode;
	@NotBlank(message="required")
	private String phoneNumber;
	@NotBlank(message="required")
	private String description;
	@NotBlank(message="required")
	private String breweryLogoUrl;
	private String imgUrl;
	private String websiteUrl;
	private String businessHours;
	private long userId;
	@NotBlank(message="required")
	private String lat;
	@NotBlank(message="required")
	private String lng;
	@NotBlank(message="required")
	private String googleMapsUrl;
	
	
	public Brewery(){} //default constructor
	
	public Brewery(String name, String location, String description) {
		this.name = name;
		this.address = location;
		this.description = description;
	}

	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}


	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}


	public String getBusinessHours() {
		return businessHours;
	}


	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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


	public String getZipcode() {
		return zipcode;
	}


	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBreweryLogoUrl() {
		return breweryLogoUrl;
	}

	public void setBreweryLogoUrl(String breweryLogoUrl) {
		this.breweryLogoUrl = breweryLogoUrl;
	}
	
	public String getNameById(int id){
		return name;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getGoogleMapsUrl() {
		return googleMapsUrl;
	}

	public void setGoogleMapsUrl(String googleMapsUrl) {
		this.googleMapsUrl = googleMapsUrl;
	}
	
}
