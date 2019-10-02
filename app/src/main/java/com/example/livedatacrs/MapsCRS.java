package com.example.livedatacrs;

import androidx.fragment.app.FragmentActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public  class MapsCRS extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_crs);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        Button SendLocation = findViewById(R.id.SendLocationButton);
        SendLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MapsCRS.this, "Enviando Datos al Servdior CRS...", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng location = new LatLng(13.707003, -89.194733);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.addMarker(new MarkerOptions().position(location).title("Cruz Roja Salvadoreña San Salvador")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_maker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 20.0f));
        mMap.setTrafficEnabled(true);

        // Add a marker in Sydney and move the camera
        LatLng location1 = new LatLng(13.677427, -89.280783);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.addMarker(new MarkerOptions().position(location1).title("Cruz Roja Salvadoreña Santa Tecla")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_maker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location1, 20.0f));
        mMap.setTrafficEnabled(true);

        //new marker
        LatLng location2 = new LatLng(13.879136, -89.171251);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.addMarker(new MarkerOptions().position(location2).title("Cruz Roja Salvadoreña Guazapa")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_maker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location2, 20.0f));
        mMap.setTrafficEnabled(true);
    }
}
