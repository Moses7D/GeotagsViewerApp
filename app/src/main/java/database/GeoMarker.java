package database;

import com.google.firebase.firestore.GeoPoint;

public class GeoMarker {

    public GeoPoint location;
    public int typeCode; //value of the Sensor class constants TYPE_<sensor>
    public float sensorMeasurement;
    public float hue;
    public String title;
    public String description;


    public GeoMarker() {
        typeCode = -1;
        sensorMeasurement = 1.0f;
        hue = -1.0f;
    }

    public GeoMarker(GeoPoint location, int typeCode, float sensorMeasurement, float hue, String title, String description) {
        this.location = location;
        this.typeCode = typeCode;
        this.sensorMeasurement = sensorMeasurement;
        this.hue = hue;
        this.title = title;
        this.description = description;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

    public float getSensorMeasurement() {
        return sensorMeasurement;
    }

    public void setSensorMeasurement(float sensorMeasurement) {
        this.sensorMeasurement = sensorMeasurement;
    }

    public float getHue() {
        return hue;
    }

    public void setHue(float hue) {
        this.hue = hue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void clear(){
        location = null;
        typeCode = -1;
        sensorMeasurement = -1.0f;
        hue = -1.0f;
        title = null;
        description = null;
    }

    @Override
    public String toString() {
        return "GeoMarker{" +
                "location=" + location +
                ", typeCode=" + typeCode +
                ", sensorMeasurement=" + sensorMeasurement +
                ", hue=" + hue +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
