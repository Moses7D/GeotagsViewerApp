package com.androidexercises.geotagsviewerapp.utils;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.GeoPoint;

import java.util.LinkedHashMap;

import database.GeoMarker;

public class MarkerOptionsFactory {

    /**
     * Produces a {@link MarkerOptions} object with the data from the given {@link GeoMarker}
     * @param geoMarker the object to get data from
     * @param sensorsTypeCodesNamesMap the mapping of the sensor codes to their desired names for GUI purposes,
     *                                 mapping must be code based, since the desired field is the name.
     * @return a MarkerOptions object ready to be used to produce a marker.
     */
    public static MarkerOptions makeMarkerOptions(GeoMarker geoMarker, LinkedHashMap<Integer, String> sensorsTypeCodesNamesMap) {
        MarkerOptions markerOptions = new MarkerOptions()
                .position(toLatLng(geoMarker.location))
                .icon(gerColouredMarker(geoMarker.hue));
        String description = "";
        if (geoMarker.title != null || !geoMarker.title.isEmpty()) {
            //If there is a title add it to the marker's title
            markerOptions = markerOptions.title(geoMarker.title);
            //and in the description add the sensor with the measurement
            description += sensorsTypeCodesNamesMap.get(geoMarker.typeCode) + ": " + geoMarker.sensorMeasurement;
        } else {
            //if there's no title then the sensor name with it's measurement goes to the title
            markerOptions = markerOptions.title(sensorsTypeCodesNamesMap.get(geoMarker.typeCode) + ": " + geoMarker.sensorMeasurement);
        }
        if (geoMarker.description != null || !geoMarker.description.isEmpty()) {
            //if there is a description add it at the description of the marker
            description += " " + geoMarker.description;
        }
        markerOptions = markerOptions.snippet(description);
        return markerOptions;
    }

    private static BitmapDescriptor gerColouredMarker(float hue) {
        return BitmapDescriptorFactory.defaultMarker(hue);
    }

    private static LatLng toLatLng(GeoPoint geoPoint) {
        return new LatLng(geoPoint.getLatitude(), geoPoint.getLongitude());
    }

}
