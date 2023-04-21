package com.app.sqlitecomplexivo.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sqlitecomplexivo.DBHelper;
import com.app.sqlitecomplexivo.MainActivity;
import com.app.sqlitecomplexivo.R;

public class AddProduct extends AppCompatActivity {

    DBHelper dbHelper;
    TextView textViewBack;
    EditText editTextStudentName,editTextStudentEmail;
    Button buttonAddStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);


        editTextStudentName = findViewById(R.id.txtNombreProducto);
        editTextStudentEmail = findViewById(R.id.txtPrecioProducto);
        buttonAddStudent = findViewById(R.id.btnGuardarProd);
        textViewBack = findViewById(R.id.textViewBack);

        dbHelper = new DBHelper(AddProduct.this,"ProductsDB.db",null,1);

        buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextStudentName.getText().toString();
                String email = editTextStudentEmail.getText().toString();

                try
                {
                    dbHelper.addStudent(name,email);
                    Toast.makeText(getApplicationContext(),"Producto agregado Satisafactoriamente", Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(),ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        textViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddProduct.this, AdminPanel.class));
                finish();
            }
        });
    }
}