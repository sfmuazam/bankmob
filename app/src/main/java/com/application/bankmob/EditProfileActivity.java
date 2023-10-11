package com.application.bankmob;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    private EditText newNameEditText, newEmailEditText, newAccountEditText, newPhoneEditText;
    private Button saveButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        newNameEditText = findViewById(R.id.newNameEditText);
        newEmailEditText = findViewById(R.id.newEmailEditText);
        newAccountEditText = findViewById(R.id.newAccountEditText);
        newPhoneEditText = findViewById(R.id.newPhoneEditText);
        saveButton = findViewById(R.id.saveButton);
        logoutButton = findViewById(R.id.logoutButton);

        // Terima data profil dari Intent
        Intent intent = getIntent();
        if (intent != null) {
            String namaLama = intent.getStringExtra("nama");
            String emailLama = intent.getStringExtra("email");
            String rekeningLama = intent.getStringExtra("rekening");
            String hpLama = intent.getStringExtra("hp");

            // Isi nilai-nilai inputan dengan data profil lama
            newNameEditText.setText(namaLama);
            newEmailEditText.setText(emailLama);
            newAccountEditText.setText(rekeningLama);
            newPhoneEditText.setText(hpLama);
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ambil data yang diubah oleh pengguna
                String newName = newNameEditText.getText().toString();
                String newEmail = newEmailEditText.getText().toString();
                String newAccount = newAccountEditText.getText().toString();
                String newPhone = newPhoneEditText.getText().toString();

                // Kembali ke DashboardActivity setelah menyimpan
                Intent intent = new Intent();
                // Kirim data profil yang telah diperbarui
                intent.putExtra("nama", newName);
                intent.putExtra("email", newEmail);
                intent.putExtra("rekening", newAccount);
                intent.putExtra("hp", newPhone);
                setResult(RESULT_OK, intent); // Atur resultCode menjadi RESULT_OK
                finish(); // Tutup aktivitas EditProfileActivity
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Setelah logout, arahkan pengguna ke halaman login
                Intent intent = new Intent(EditProfileActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
