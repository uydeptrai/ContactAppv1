package com.example.contactappv1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Entity;
import androidx.room.Room;

import com.example.contactappv1.AppDatabase;
import com.example.contactappv1.R;

public class NewContactActivity extends AppCompatActivity {

    private EditText editTextName, editTextMobile, editTextEmail;
    private Button buttonSave;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_contact_activity);

        editTextName = findViewById(R.id.editTextName);
        editTextMobile = findViewById(R.id.editTextMobile);
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonSave = findViewById(R.id.buttonSave);

        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "contact-database").build();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String mobile = editTextMobile.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();

                if (!name.isEmpty() && !mobile.isEmpty() && !email.isEmpty()) {
                    Contact contact = new Contact(name, mobile, email);
                    appDatabase.contactDao().insert(contact);
                    finish(); // Đóng màn hình NewContactActivity và quay trở lại màn hình trước đó
                } else {
                    // Hiển thị thông báo lỗi nếu thông tin không đủ
                    Toast.makeText(NewContactActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
