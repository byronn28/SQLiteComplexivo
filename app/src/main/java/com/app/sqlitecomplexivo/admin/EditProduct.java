package com.app.sqlitecomplexivo.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sqlitecomplexivo.DBHelper;
import com.app.sqlitecomplexivo.R;

public class EditProduct extends AppCompatActivity {

    DBHelper dbHelper;
    TextView textViewBackProductList;
    EditText editTextStudentName,editTextStudentEmail;
    Button buttonEditStudent;
    String oldemail ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            oldemail = bundle.getString("emailID");
            //Toast.makeText(this, ""+oldemail, Toast.LENGTH_SHORT).show();

            editTextStudentName = findViewById(R.id.txtNombre);
            editTextStudentEmail = findViewById(R.id.txtEditPrecio);
            buttonEditStudent = findViewById(R.id.btnEditarProd);
            textViewBackProductList = findViewById(R.id.textViewBackProductList);

            dbHelper = new DBHelper(EditProduct.this,"ProductsDB.db",null,1);

            Cursor cursor = dbHelper.displayStudentsByEmailEdit(oldemail);
            while (cursor.moveToNext()){
                editTextStudentName.setText(cursor.getString(1));
                editTextStudentEmail.setText(cursor.getString(2));
            }

            buttonEditStudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String Name = editTextStudentName.getText().toString();
                    String newemail = editTextStudentEmail.getText().toString();
                    dbHelper.updateStudentByEmail(Name,newemail,oldemail);
                    Toast.makeText(EditProduct.this, "Update Successfully", Toast.LENGTH_SHORT).show();
                    redirectAdminPanel();
                }
            });

            textViewBackProductList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    redirectAdminPanel();
                }
            });
        }
        else {
            redirectAdminPanel();
        }
    }
    private void redirectAdminPanel(){
        startActivity(new Intent(EditProduct.this, AdminPanel.class));
        finish();
    }
}