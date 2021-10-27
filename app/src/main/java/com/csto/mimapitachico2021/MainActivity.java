package com.csto.mimapitachico2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.csto.mimapitachico2021.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    Button BTN;

    EditText CajonLatitud,CajonLongitud,CajonTitulo,CajonFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BTN = (Button)findViewById(R.id.button);

        CajonLatitud = (EditText)findViewById(R.id.cajon_latitud);
        CajonLongitud = (EditText)findViewById(R.id.cajon_longitud);
        CajonTitulo = (EditText)findViewById(R.id.titulo_marcador);

        CajonFecha = (EditText)findViewById(R.id.editTextDate);

        /*binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());*/

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String miTitulo=CajonTitulo.getText().toString();
                double LAT = Double.parseDouble(CajonLatitud.getText().toString());
                double LON = Double.parseDouble(CajonLongitud.getText().toString());
                LatLng Posicion = new LatLng(LAT,LON);
                mMap.addMarker(new MarkerOptions().position(Posicion).title(miTitulo));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(Posicion));

                String fecha = CajonFecha.getText().toString();
                Toast.makeText(getApplicationContext(),"FECHA:"+fecha,Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                CajonLatitud.setText(latLng.latitude+"");
                CajonLongitud.setText(latLng.longitude+"");
            }
        });
        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }

}