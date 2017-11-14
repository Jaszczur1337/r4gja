package info.androidhive.cardview;


import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


public class MapFragment extends Fragment implements OnMapReadyCallback{

    Location myLocation;
    List<Marker> markersList;
    GoogleMap mMap;
    Marker myPositionMarker;
    MapView mapView;
    List<MarkerOptions> markerOptionsList;
 FusedLocationProviderClient mFusedLocationClient;

    public MapFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_map, container, false);
        markerOptionsList=new ArrayList<>();
        markersList = new ArrayList<>();
        markerOptionsList= getArguments().getParcelableArrayList("location");
        mapView = (MapView) v.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());
        return v;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap=map;

        LatLngBounds latLngBounds = new LatLngBounds(new LatLng(53.321733,14.443114),new LatLng(53.537660 ,14.806133));
        mMap.setLatLngBoundsForCameraTarget(latLngBounds);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(53.4285438,14.5528116),15));
        myPositionMarker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(0,0))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        for(int i=0;i<markerOptionsList.size();i++) {
            Marker marker = mMap.addMarker(markerOptionsList.get(i));

            markersList.add(marker);
        }

    }

    public void setMyPosition(Location location)
    {
        this.myLocation=location;
        if(markersList!=null) {
            for (int i = 0; i < markersList.size(); i++) {
                Location markersLocation = new Location(this.myLocation);
                markersLocation.setLatitude(markersList.get(i).getPosition().latitude);
                markersLocation.setLongitude(markersList.get(i).getPosition().longitude);
                if (myLocation.distanceTo(markersLocation) < 10) {
                    markersList.get(i).remove();
                }
            }
        }
            if (myPositionMarker != null)
                myPositionMarker.setPosition(new LatLng(location.getLatitude(), location.getLongitude()));

    }
    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
