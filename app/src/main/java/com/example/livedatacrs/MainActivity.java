package com.example.livedatacrs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // Creating EditText.
    EditText FirstName, LastName, Email ;

    // Creating button;
    Button InsertButton;

    // Necesario de Volley para las consultas
    RequestQueue rq;

    // Create string variable to hold the EditText Value.
    String FirstNameHolder, LastNameHolder, EmailHolder ;

    // Creating Progress dialog.
    ProgressDialog progressDialog;

    // Storing server url into String variable.
    String HttpUrl = "https://www.simcrs.org.sv/ochoa/viewData.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assigning ID's to EditText.
        FirstName = (EditText) findViewById(R.id.editTextFirstName);
        LastName = (EditText) findViewById(R.id.editTextLastName);
        Email = (EditText) findViewById(R.id.editTextEmail);

        // Assigning ID's to Button.
        InsertButton = (Button) findViewById(R.id.ButtonInsert);
        Button GetMapButton = findViewById(R.id.MapButton);

        //Evento click botón mapas
        GetMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });

        // Iniciamos el Volley
        rq = Volley.newRequestQueue(MainActivity.this);

        progressDialog = new ProgressDialog(MainActivity.this);

        // Adding click listener to button
        InsertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Showing progress dialog at user registration time.
                progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
                progressDialog.show();

                // Calling method to get value from EditText.
                GetValueFromEditText();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {
                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();

                                // Showing response message coming from server.
                                // Toast.makeText(MainActivity.this, ServerResponse, Toast.LENGTH_LONG).show();

                                Toast.makeText(MainActivity.this, "Informacion Guardada con Exito !!!", Toast.LENGTH_LONG).show();

                                FirstName.setText("");
                                LastName.setText("");
                                Email.setText("");

                                FirstName.setFocusable(true);
                                FirstName.requestFocus();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();

                                // Showing error message if something goes wrong.
                                // Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                                Toast.makeText(MainActivity.this, "No fue posible Guardar los Datos...", Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        // Creating Map String Params.
                        Map<String, String> params = new HashMap<String, String>();

                        // Adding All values to Params.
                        params.put("first_name", FirstNameHolder);
                        params.put("last_name", LastNameHolder);
                        params.put("email", EmailHolder);

                        return params;
                    }
                };

                // Creating RequestQueue.
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

                // Adding the StringRequest object into requestQueue.
                requestQueue.add(stringRequest);
            }
        });
    }

    // Creating method to get value from EditText.
    public void GetValueFromEditText(){
        FirstNameHolder = FirstName.getText().toString().trim();
        LastNameHolder = LastName.getText().toString().trim();
        EmailHolder = Email.getText().toString().trim();
    }
}
