package org.optaplanner.examples.rocktour.domain;

import java.util.Map;

public class RockLocation {

    protected String cityName;
    protected double latitude;
    protected double longitude;

    // Prefer Map over array or List because shows might be added and removed in real-time planning.
    protected Map<RockLocation, Long> drivingSecondsMap;

    public RockLocation() {
    }

    public RockLocation(String cityName, double latitude, double longitude) {
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * @param location never null
     * @return a positive number, in seconds
     */
    public long getDrivingTimeTo(RockLocation location) {
        if (this == location) {
            return 0L;
        }
        return drivingSecondsMap.get(location);
    }

    public long getAirDistanceTo(RockLocation location) {
        // Euclidean distance (Pythagorean theorem) - not correct when the surface is a sphere
        double latitudeDifference = location.latitude - latitude;
        double longitudeDifference = location.longitude - longitude;
        double distance = Math.sqrt(
                (latitudeDifference * latitudeDifference) + (longitudeDifference * longitudeDifference));
        // Multiplied by 1000 to avoid floating point arithmetic rounding errors
        return (long) (distance * 1000.0 + 0.5);
    }

    @Override
    public String toString() {
        return cityName;
    }

    // ************************************************************************
    // Simple getters and setters
    // ************************************************************************

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public Map<RockLocation, Long> getDrivingSecondsMap() {
        return drivingSecondsMap;
    }

    public void setDrivingSecondsMap(Map<RockLocation, Long> drivingSecondsMap) {
        this.drivingSecondsMap = drivingSecondsMap;
    }

}
