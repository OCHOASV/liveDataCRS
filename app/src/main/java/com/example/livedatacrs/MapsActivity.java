package com.example.livedatacrs;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private int REQUEST_ACCESS_FINE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override


    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        Instanciamos clase LocationManager
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //Verificamos si tiene permiso para ubicación
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Si no tiene permiso, se lo pedimos
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ACCESS_FINE);
        }
        //Obtenemos coordenadas
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        getLocation(location);
    }
    private void getLocation(Location location)
    {
        //Si obtuvo coordenadas
        if(location != null)
        {
            //LLenamos variables Latitud y Longitud
            double lat = location.getLatitude();
            double lon = location.getLongitude();

            LatLng MyLocation = new LatLng(lat, lon);
            //Agregamos marcador
            mMap.addMarker(new MarkerOptions().position(MyLocation).title("Coordenadas: " + lat + " , " + lon));
            //Posicionamos camara
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MyLocation,1));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(20),2000,null);
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        }
    }
}
