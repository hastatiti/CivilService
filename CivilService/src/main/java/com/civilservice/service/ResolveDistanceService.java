package com.civilservice.service;

//service interface , returns true if the distance between these coordinates and manually given 'London's' coordinates are less than 50 miles
public interface ResolveDistanceService {
	 boolean isLondon(double lat,double lon);
}
