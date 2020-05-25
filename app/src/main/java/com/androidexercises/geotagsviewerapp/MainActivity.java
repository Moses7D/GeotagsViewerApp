package com.androidexercises.geotagsviewerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.os.Bundle;
import android.util.Log;

import com.androidexercises.geotagsviewerapp.utils.MarkerOptionsFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import database.GeoMarker;


/**
 * This class loads the marker data from the database and converts them to {@link MarkerOptions} objects
 * so they can be shown on the map.
 * TODO: Get markers to clean then on app resume
 */
public class MainActivity extends AppCompatActivity {
    private final LinkedHashMap<Integer, String> sensorsTypeCodesNamesMap = new LinkedHashMap<>();

    private GoogleMap gMap;
    private MapView mapView;
    private FirebaseFirestore databaseCloud;
    //this variable is used to sync the two threads(events) that create the map and pull the data from the database
    private boolean mapNotNull = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("app", "onCreate called.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseCloud = FirebaseFirestore.getInstance();
        mapView = findViewById(R.id.main_map);
        mapView.onCreate(savedInstanceState);
        //to get the GoogleMap object when it's ready
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                MainActivity.this.onMapReady(googleMap);
            }
        });
        //maps the names of the sensors using their unique code of the constants from Sensor class
        String[] namesArray = getResources().getStringArray(R.array.sensor_names);
        sensorsTypeCodesNamesMap.put(Sensor.TYPE_LIGHT, namesArray[0]);
        sensorsTypeCodesNamesMap.put(Sensor.TYPE_PRESSURE, namesArray[1]);
        sensorsTypeCodesNamesMap.put(Sensor.TYPE_RELATIVE_HUMIDITY, namesArray[2]);
        sensorsTypeCodesNamesMap.put(Sensor.TYPE_AMBIENT_TEMPERATURE, namesArray[3]);
        //reads data from the Firestore database
        CollectionReference markersCollection = databaseCloud.collection("markers");
        markersCollection.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(final QuerySnapshot queryDocumentSnapshots) {
                Log.i("query", "query successful");
                Log.i("query", "entries found: " + queryDocumentSnapshots.size());
                GeoMarker geoMarker;
                //active wait to make sure the GoogleMap object is not null
                while(!mapNotNull){
                }
                for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                    //map document to an object
                    geoMarker = snapshot.toObject(GeoMarker.class);
                    Log.i("query", "geomarker: " + geoMarker.toString());
                    //creates a MarkerOption object and adds it as a marker to the map
                    gMap.addMarker(MarkerOptionsFactory.makeMarkerOptions(geoMarker, sensorsTypeCodesNamesMap));
                }
            }
        });
    }

    @Override
    protected void onPause() {
        Log.i("app", "onPause called.");
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        Log.i("app", "onResume called.");
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.i("app", "onDestroy called.");
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onStop() {
        Log.i("app", "onStop called.");
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onRestart() {
        Log.i("app", "onRestart called.");
        super.onRestart();
        mapView.onStart();
    }

    /**
     * Gets the map passed from the MapView's getMapAsync method and changes the value to the flag
     * used to create active wait in the database's successful event.
     * @param googleMap
     */
    private void onMapReady(GoogleMap googleMap) {
        Log.i("app", "onMapReady called.");
        gMap = googleMap;
        mapNotNull = true;
        //Find my location button
        gMap.getUiSettings().setMyLocationButtonEnabled(false);
        //My location button "blue dot" on the map
        gMap.setMyLocationEnabled(false);
        MapsInitializer.initialize(this);
    }
}
