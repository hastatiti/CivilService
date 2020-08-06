package com.civilservice.service;

import org.springframework.stereotype.Service;

//service implementation that serves UsersController class for resolving distance
@Service
public class ResolveDistanceServiceImpl implements ResolveDistanceService {

	@Override
	public boolean isLondon(double lat, double lon) {
//		London's coordinates
		double latitude = 51.5074;
		double longitude = 0.1278;

//		distance between London and given coordinates
		double distance = resolveDistance(latitude, longitude, lat, lon);
		return distance < 50;
	}

//	helper method, formula that returns distance between coordinates
	private double resolveDistance(double lat1, double long1, double lat2, double long2) {
		double earthRadius = 6371;
		double distance = Math.acos(
				Math.sin(lat2 * Math.PI / 180.0) * Math.sin(lat1 * Math.PI / 180.0) + Math.cos(lat2 * Math.PI / 180.0)
						* Math.cos(lat1 * Math.PI / 180.0) * Math.cos((long1 - long2) * Math.PI / 180.0))
				* earthRadius * 0.621;
		return distance;
	}

}
