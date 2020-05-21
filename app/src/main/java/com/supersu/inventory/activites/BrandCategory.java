package com.supersu.inventory.activites;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.supersu.inventory.R;


public class BrandCategory extends AppCompatActivity {
    EditText etAddBrand,etAddCategory;
    Button btnAddBrand,btnAddCategory;

    String[] brandsDefault ={"PAPA 888 WHISKY","100 PIPER","AMERICAN GIN","ANTIQUITY BLUE","BACARDI BLACK","BLACK DOG G","BLENDER PRIDE",
            "BLENDER RES","BLUSHIP  GIN","BP PET","BP WHY","BREZZER","BREZZER MILD","BUDWISER MILD","BUDWISER STRONG","CANNON","CAPTAIN MORGAN RUM",
            "CARLSBURG STRONG","CLASSIC VODKA","DIPLOMAT","DR BRANDY","DSP BLACK","DSP WHY","FOSTER","FRIEN DS WHY","FUEL FLAVOUR","FUEL VODKA","FULE" +
            " APPLE VODKA","FULE ORENGE VODKA","GOA GIN","GRAND M  CRM/ BULE","GRAND M BLUE  VOD","HAYWARDS VODKA","IB","IB RED RUM","ICE VODKA","KANGARO" +
            " MILD","KARAN  DOCTOR BRANDY","KARAN DR.BRANDY","KF MILD","KF STORM  BEER","KF STRONG","KHAJURAO","KING GOA","KNOCKOUT","M H WHISKY","MB WHY","MC " +
            "DIET","MC LUX","MC NO1","MC PLATINUM","MCR RUM","MENSION HOUSE","MGANUM STRONG BEER","MM APPLE VODKA","MM VODKA","OC BLUE","OC PET","OC STAR WHISKY",
            "OC WHY","OLD MONK","PAPA  888 WHISKY","PORT WINE","RC","RC DIET","RIO WINE FLAVOUR 1","ROM VODKA","ROYAL GOLD MALT","RS","RS BAREL","SHOT WHY","SIDUS",
            "SIDUS RED","SIDUS WHITE","SIG  PRE","SIGNATURE","SIM VODKA","SNOW VODKA","SULA WINE","TEACHERS 50","TUBORG","TUBORG CLASSIC BLACK","VAT 69","WMC VODKA",
            "SULA ROSE WINE","MM ORANGE VODKA","DANNY R.BRANDY","DANNY R. BRANDY","CARLSBERG SMOOTH P BEER","CARLSNERG SMOOTH BEER","ROM  VODKA APPLE/ ORENG","ROM VODKA APPLE / ORENG"
            ,"ROM VODKA APPLE","HUNTER S BEER","HUNTER R S BEER","TEACHERS 50  SCOTCH","GRAND M. ORANGE","BECK.\"S ICE BEER","BECK  ICE BEER","BHARAT DR  BRANDY","HAYWARRD 2000  BEER",
            "OLD COIN WHISKY","GOA WHISKY","NOVE PREMIDED DRY GIN","GRAND MASTER GREEN APPLE VODKA","GRAND M GREEN APPLE","DANOVA RUSSIAN VODKA","SAGAR SAMRAT WHISKY","FRIENDS PRE WHISKY",
            "DANOVA RUSSIAN WHISKY","BECKS STRONG","BIRA BOOM","BIRA STRONG","MAGNUM","ELEPHANT","H 2000","H 5000","HUNTER","KF STORM","KANGAROO","LP STRONG","TUB CLASSIC","TUB STRONG","CARLSBERG SMOOTH",
            "HEINKAIN","KF MILD CAN","DIA RED","DIA WHITE","MADERA RED","RIO WINE","SAMARA","SULA SATORI","SULA SECO","SULA CHENIN BLANK","KF ULTRA  MILD","RIO STRONG","TUBORG INTER STRONG","H 5000 CAN 330ML",
            "KNOCK OUT","NOI SPARKLING","SIDUS PREMINUM PORT","KF MAX ULTRA","A"};

    RecyclerView brandIterate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_category);

        etAddBrand = findViewById(R.id.etAddBrand);
        etAddCategory = findViewById(R.id.etAddCategory);
        btnAddBrand = findViewById(R.id.btnAddBrand);
        btnAddCategory = findViewById(R.id.btnAddCategory);




        btnAddBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                brandAdder();

            }
        });

        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });


    }

    public void brandAdder(){
        /*String tx = etAddBrand.getText().toString();
        BrandCategoryRefrence brandCategoryRefrence = new BrandCategoryRefrence(tx);
        databaseReference.child("BrandsList").child(tx);
        Toast.makeText(getApplicationContext(), "Success :)", Toast.LENGTH_SHORT).show();*/




    }
    public void categoryAdder(){

    }
}
