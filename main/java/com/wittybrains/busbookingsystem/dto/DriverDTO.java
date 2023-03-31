package com.wittybrains.busbookingsystem.dto;

public class DriverDTO {
    private String name;
    private String licenseNumber;
    private String username;
    private String password;
    
    

    public DriverDTO(String name, String licenseNumber, String username, String password) {
		super();
		this.name = name;
		this.licenseNumber = licenseNumber;
		this.username = username;
		this.password = password;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

