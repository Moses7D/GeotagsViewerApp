package database;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class GeoMarker {
    public int colour;
    public String[] sensorData;
    public String description;
    public LatLng location;

    public GeoMarker() {
    }

    public GeoMarker( int colour, String[] sensorData, String description, LatLng location) {
        this.colour = colour;
        this.sensorData = sensorData;
        this.description = description;
        this.location = location;
    }

    public GeoMarker(String[] sensorData, LatLng location) {
        this.sensorData = sensorData;
        this.location = location;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    public String[] getSensorData() {
        return sensorData;
    }

    public void setSensorData(String[] sensorData) {
        this.sensorData = sensorData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }
}
