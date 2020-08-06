package com.civilservice.entity;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	private long id;
	private String first_name;
	private String last_name;
	private String email;
	private String ip_address;
	private double latitude;
	private double longitude;
	private String city;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, email, first_name, id, ip_address, last_name, latitude, longitude);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(city, other.city) && Objects.equals(email, other.email)
				&& Objects.equals(first_name, other.first_name) && id == other.id
				&& Objects.equals(ip_address, other.ip_address) && Objects.equals(last_name, other.last_name)
				&& Double.doubleToLongBits(latitude) == Double.doubleToLongBits(other.latitude)
				&& Double.doubleToLongBits(longitude) == Double.doubleToLongBits(other.longitude);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ ", ip_address=" + ip_address + ", latitude=" + latitude + ", longitude=" + longitude + ", city="
				+ city + "]";
	}

}
