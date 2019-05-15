package com.example.maps_android;

import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.InputStream;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String resposta;
    public List<Contato> contatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        DAL dal = new DAL(this);
        Cursor cursor = dal.loadAll();
        if (cursor.getCount()==0) {
            InputStream in;
            String urlJSON = "http://www.mocky.io/v2/5cdb4544300000640068cc7b";
            in = LeJson.leJson(urlJSON);
            resposta = LeJson.convertStreamToString(in);// covert the input stream to a string
            leContatosDeJSONString l = new leContatosDeJSONString();
            contatos = l.transformaContatosDeJSONString(resposta);
            for( Contato ca : contatos){
                String  nome = ca.getNome();
                String  email = ca.getEmail();
                double  longitude = ca.getLongitude();
                double  latitude = ca.getLatitude();
                dal.insert(nome,email,longitude,latitude);
            }
        }else{




        }
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(-34, 151);
        mMap = googleMap;
        DAL dal = new DAL(this);
        Cursor cursor = dal.loadAll();
        if (cursor.getCount()==0) {
            for (Contato ca : contatos) {
                String nome = ca.getNome();
                String email = ca.getEmail();
                double longitude = ca.getLongitude();
                double latitude = ca.getLatitude();
                LatLng br = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(br).title("Nome:" + nome + ", Email: " + email));
            }
        }
        else{

            while(!cursor.isAfterLast()) {
                String nome = cursor.getString(cursor.getColumnIndex(CreateDatabase.NOME));
                String email = cursor.getString(cursor.getColumnIndex(CreateDatabase.EMAIL));
                double longitude =cursor.getDouble(cursor.getColumnIndex(CreateDatabase.LONGITUDE));
                double latitude = cursor.getDouble(cursor.getColumnIndex(CreateDatabase.LATITUDE));
                LatLng br = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(br).title("Nome:" + nome + ", Email: " + email));
                cursor.moveToNext();
            }


        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
