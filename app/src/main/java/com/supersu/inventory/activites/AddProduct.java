package com.supersu.inventory.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.supersu.inventory.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class AddProduct extends AppCompatActivity implements View.OnClickListener{
    EditText etTpNumber,itemPacking,etBillNumber,dpRecieved,dpInvoice,dpTP,etBatchNumber,etCaseNumber,etBottle,etExciseRate,etPurchaseRate,etAmountTotal,etUnitMatch;
    String arr[] = {"etTpNumber","itemPacking","etBillNumber","dpRecieved","dpInvoice","dpTP","etBatchNumber","etCaseNumber","etBottle","etExciseRate","etPurchaseRate","etAmountTotal","etUnitMatch"};
    AutoCompleteTextView etItemName,etVendorName;
    Button btnFullAddProduct,btnShowProductList;
    private int mYear, mMonth, mDay, mHour, mMinute,yearGet,monthGet,daGet;
    SQLiteDatabase sqLiteDatabase;
    Spinner itemDept,itemType;
    RequestQueue requestQueue;
    ProgressDialog progressDoalog;
    String insertURL = "http://192.168.0.105/invento/addDailyProduct.php";
    int bottlesperCase ;

    String[] itemDepartments={"Select Department","Bottal","Full","QUARTLT","HOME","1.5LTR","650ML","2000ML","NIP",
            "500ML","PEG_L","Liquior","1500ML","OTHER","MIN37","330ML","PEG_S","QUART","MIN9","MIN6",
            "NIP 1/2","LTR","Kitchen","Half","Food","Full","Kitchen","SOUTH INDIAN","Bottal","Half",
            "IMFL","Beer","MILD BEER","WINE","275","COLD DRINK"};

    String[] unitMeasure ={"2000","1500","1000","750","375","60","180","90","500","330","650","30"};

    String[] typeMaker = {"IMFL" ,"Beer", "WINE" ,"MILD BEER"};

    FirebaseDatabase mfirebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseApp firebaseApp;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        //inits

                etVendorName = findViewById(R.id.etVendorName);
                etTpNumber= findViewById(R.id.etTpNumber);
                etBillNumber=findViewById(R.id.etBillNumber);
                dpRecieved=findViewById(R.id.dpRecieved);
                dpInvoice=findViewById(R.id.dpInvoice);
                dpTP=findViewById(R.id.dpTP);
                etItemName=findViewById(R.id.etItemName);
                etBatchNumber=findViewById(R.id.etBatchNumber);
                etCaseNumber=findViewById(R.id.etCaseNumber);
                etBottle=findViewById(R.id.etBottle);
                etExciseRate=findViewById(R.id.etExciseRate);
                etPurchaseRate=findViewById(R.id.etPurchaseRate);
                etAmountTotal=findViewById(R.id.etAmountTotal);
                itemDept=findViewById(R.id.spinDepartment);
                etUnitMatch = findViewById(R.id.etUnitMatch);
                itemPacking=findViewById(R.id.spinPacking);
                itemType=findViewById(R.id.spinType);
                requestQueue = Volley.newRequestQueue(getApplicationContext());

                //dbHelper = new DBHelper(this);

        etVendorName.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                etVendorName.showDropDown();
        });

         //buttons
        btnFullAddProduct = findViewById(R.id.btnFullAddProduct);
        btnShowProductList = findViewById(R.id.btnShowProductList);

        //listners
        btnShowProductList.setOnClickListener(this);
        btnFullAddProduct.setOnClickListener(this);
        dpRecieved.setOnClickListener(this);
        dpInvoice.setOnClickListener(this);
        dpTP.setOnClickListener(this);
        setSpinners();

        try {
            vendorListMaker();
            itemListMaker();
        } catch (IOException e) {
            e.printStackTrace();
        }

        validityCracker();
    }
    @SuppressLint("SetTextI18n")
     @Override
    public void onClick(View view) {
         final Calendar c = Calendar.getInstance();
         mYear = c.get(Calendar.YEAR);
         mMonth = c.get(Calendar.MONTH);
         mDay = c.get(Calendar.DAY_OF_MONTH);
        switch (view.getId()){
            case R.id.dpRecieved :

                DatePickerDialog datePickerDialogRecieved = new DatePickerDialog(this,
                        (view12, year, monthOfYear, dayOfMonth) -> dpRecieved.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year), mYear, mMonth, mDay);
                datePickerDialogRecieved.show();
                break;

            case R.id.dpInvoice :

                DatePickerDialog datePickerDialogInvoice = new DatePickerDialog(this,
                        (view1, year, monthOfYear, dayOfMonth) -> dpInvoice.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year), mYear, mMonth, mDay);
                datePickerDialogInvoice.show();
                break;


            case R.id.dpTP :

                DatePickerDialog datePickerDialogTp = new DatePickerDialog(this,
                        (view13, year, monthOfYear, dayOfMonth) -> dpTP.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year), mYear, mMonth, mDay);
                datePickerDialogTp.show();
                break;

            case R.id.btnFullAddProduct :dataSenderStore();
                break;

            case R.id.btnShowProductList :
                Intent showAddedProductList = new Intent(AddProduct.this,ShowAddedProductList.class);
                startActivity(showAddedProductList);

        }

    }



    public void setSpinners(){
        ArrayAdapter<String> deptAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, itemDepartments);
        deptAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemDept.setAdapter(deptAdapter);


        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, typeMaker);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemType.setAdapter(typeAdapter);


    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("item.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;



    }

    public void itemListMaker(){
        loadJSONFromAsset();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("itemList");
            List<String> itemNameList = new ArrayList<String>();
            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String formula_value = jo_inside.getString("item");
                itemNameList.add(formula_value);
                ArrayAdapter<String> adapter = new
                        ArrayAdapter<>(this,android.R.layout.simple_list_item_1, itemNameList);
                etItemName.setAdapter(adapter);
                etItemName.setThreshold(0);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void vendorListMaker() throws IOException {

        String[] vendorNameList = {"vendor1","vendor2","vendor3"};

                ArrayAdapter<String> adapter = new
                        ArrayAdapter<>(this,android.R.layout.simple_list_item_1, vendorNameList);
                etVendorName.setAdapter(adapter);
                etVendorName.setThreshold(0);

    }
    @SuppressLint("SetTextI18n")
    public void validityCracker(){

          etItemName.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
            etItemName.showDropDown();
        });
        etItemName.setOnItemClickListener((adapterView, view, i, l) -> {
            if(etItemName.getText().toString().contains("2000ML")){
                bottlesperCase = 4;
                itemPacking.setText("2000ML");
                etUnitMatch.setText("2000");
            }else if(etItemName.getText().toString().contains("1500ML")) {
                bottlesperCase =9;
                itemPacking.setText("1500ML");
                etUnitMatch.setText("1500");
            }else if(etItemName.getText().toString().contains("QUARTLT")) {
                bottlesperCase=9;
                itemPacking.setText("QUARTLT");
                etUnitMatch.setText("1000");
            }else if(etItemName.getText().toString().contains("QUART")) {
                bottlesperCase=12;
                itemPacking.setText("QUART");
                etUnitMatch.setText("750");
            }else if(etItemName.getText().toString().contains("MIN37")) {
                bottlesperCase=24;
                itemPacking.setText("MIN37");
                etUnitMatch.setText("375");
            }else if(etItemName.getText().toString().contains("MIN6")) {
                bottlesperCase=150;
                itemPacking.setText("MIN6");
                etUnitMatch.setText("60");
            }else if(etItemName.getText().toString().contains("MIN9")) {
                bottlesperCase=96;
                itemPacking.setText("MIN9");
                etUnitMatch.setText("90");
            }else if(etItemName.getText().toString().contains("NIP")) {
                bottlesperCase=48;

                itemPacking.setText("NIP");
                etUnitMatch.setText("180");
            }else if(etItemName.getText().toString().contains("LTR")){
                bottlesperCase=9;
                itemPacking.setText("LTR");
                etUnitMatch.setText("1000");
            }else if(etItemName.getText().toString().contains("1.5LTR")) {
                bottlesperCase=9;
                itemPacking.setText("1.5LTR");
                etUnitMatch.setText("1500");
            }else if(etItemName.getText().toString().contains("500ML")) {
                bottlesperCase=24;
                itemPacking.setText("500ML");
                etUnitMatch.setText("500");
            }else if(etItemName.getText().toString().contains("330ML")) {
                bottlesperCase=24;
                itemPacking.setText("330ML");
                etUnitMatch.setText("330");
            }else if(etItemName.getText().toString().contains("650ML")) {
                bottlesperCase=12;
                itemPacking.setText("650ML");
                etUnitMatch.setText("650");
            }else if(etItemName.getText().toString().contains("NIP 1/2")) {

                itemPacking.setText("NIP 1/2");
                etUnitMatch.setText("90");
            }else if(etItemName.getText().toString().contains("PEG_L")) {

                itemPacking.setText("PEG_L");
                etUnitMatch.setText("60");
            }else if(etItemName.getText().toString().contains("PEG_S")) {

                itemPacking.setText("PEG_S");
                etUnitMatch.setText("30");
            }else {
                itemPacking.setText("Wrong Item Selection");
                etUnitMatch.setText("Wrong Item Selection");
            }


        });

        etBottle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(etBottle.getText().toString().trim().equals("")||etItemName.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"wrong Bottles",Toast.LENGTH_SHORT).show();

                }else {

                    etCaseNumber.setText(String.valueOf(Integer.parseInt(String.valueOf(etBottle.getText()))/bottlesperCase));
                    if(etCaseNumber.getText().toString().equals(String.valueOf(bottlesperCase))){
                        etCaseNumber.setText(String.valueOf(bottlesperCase));
                    }

                }


            }
        });


        etPurchaseRate.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               // doCalculateData();

            }

            @Override
            public void afterTextChanged(Editable editable) {
                doCalculateData();
            }
        });



    }
    public void doCalculateData(){
        double bottelsAtTime = 0;
        int count = 0;
        Toast toast= null;
        if(etBottle.getText().toString().equals("")||etPurchaseRate.getText().toString().equals("")){

          etBottle.setText("0");
        }else {


            bottelsAtTime = Double.parseDouble(etBottle.getText().toString().trim());


            double total_amount = (bottelsAtTime/bottlesperCase)*Double.parseDouble(etPurchaseRate.getText().toString());
            Log.d("Calculation", String.valueOf(total_amount));
            etAmountTotal.setText(String.valueOf(total_amount));}
    }

    public void dataSenderStore(){
       /* $_id= $_POST["_id"];
        $vendorName= $_POST["vendorName"];
        $tpNumber= $_POST["tpNumber"];
        $billNumber= $_POST["billNumber"];
        $recievedDate= $_POST["recievedDate"];
        $invoiceDate= $_POST["invoiceDate"];
        $tpDate= $_POST["tpDate"];
        $itemName= $_POST["itemName"];
        $batchNumber= $_POST["batchNumber"];
        $caseNumber= $_POST["caseNumber"];
        $packing= $_POST["packing"];
        $type= $_POST["type"];
        $bottle= $_POST["bottle"];
        $exciseRate= $_POST["exciseRate"];
        $purchaseRate= $_POST["purchaseRate"];
        $toatlAmount= $_POST["toatlAmount"];*/

       if(etVendorName.getText().toString().equals("") ||
        etTpNumber.getText().toString().equals("") ||
        etBillNumber.getText().toString().equals("") ||
        dpRecieved.getText().toString().equals("") ||
        dpInvoice.getText().toString().equals("") ||
        dpTP.getText().toString().equals("") ||
        etItemName.getText().toString().equals("") ||
        etBatchNumber.getText().toString().equals("") ||
        etCaseNumber.getText().toString().equals("") ||
        etUnitMatch.getText().toString().equals("") ||
        itemPacking.getText().toString().equals("") ||
        etBottle.getText().toString().equals("") ||
        etExciseRate.getText().toString().equals("") ||
        etPurchaseRate.getText().toString().equals("") ||
        etAmountTotal.getText().toString().equals(""))
       {
           AlertDialog.Builder builder;
           builder = new AlertDialog.Builder(this);

           builder.setMessage("Please Check All The Fields To be Filled")
                   .setCancelable(false)
                   .setPositiveButton("Yes", (dialog, id) -> dialog.dismiss());

           AlertDialog alert = builder.create();
           alert.setTitle("Error !");
           alert.show();

       }else {

           ProgressDialog dialog = ProgressDialog.show(this, "Please Wait", "Storing Data...",
                   true);
           dialog.show();
           Log.d("TAG", "clicked");
           Handler handler = new Handler();
           handler.postDelayed(() -> {
               StringRequest stringRequest = new StringRequest(Request.Method.POST, insertURL, response -> {

               }, error -> {


               }) {
                   @Override
                   protected Map<String, String> getParams() {
                       Map<String, String> map = new HashMap<String, String>();
                       map.put("vendorName", etVendorName.getText().toString());
                       map.put("tpNumber", etTpNumber.getText().toString());
                       map.put("billNumber", etBillNumber.getText().toString());
                       map.put("recievedDate", dpRecieved.getText().toString());
                       map.put("invoiceDate", dpInvoice.getText().toString());
                       map.put("tpDate", dpTP.getText().toString());
                       map.put("itemName", etItemName.getText().toString());
                       map.put("batchNumber", etBatchNumber.getText().toString());
                       map.put("caseNumber", etCaseNumber.getText().toString());
                       map.put("unitInt", etUnitMatch.getText().toString());
                       map.put("packing", itemPacking.getText().toString());
                       map.put("prodType", String.valueOf(itemType.getSelectedItem()));
                       map.put("bottle", etBottle.getText().toString());
                       map.put("exciseRate", etExciseRate.getText().toString());
                       map.put("purchaseRate", etPurchaseRate.getText().toString());
                       map.put("toatlAmount", etAmountTotal.getText().toString());

                       return map;

                   }
               };
               requestQueue.add(stringRequest);

               dialog.dismiss();

               etVendorName.setText("");
               etTpNumber.setText("");
               etBillNumber.setText("");
               dpRecieved.setText("");
               dpInvoice.setText("");
               etItemName.setText("");
               etBatchNumber.setText("");
               dpTP.setText("");
               etCaseNumber.setText("");
               etUnitMatch.setText("");
               itemPacking.setText("");
               etBottle.setText("");
               etExciseRate.setText("");
               etPurchaseRate.setText("");
               etAmountTotal.setText("");


           }, 2000);//deliberate delay added for experience

       }


    }

}
