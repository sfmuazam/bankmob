package com.application.bankmob;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    private TextView saldoTextView, namaTextView, emailTextView, rekeningTextView, hpTextView;
    private Button editProfilButton;
    private static final int EDIT_PROFILE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        saldoTextView = findViewById(R.id.saldoTextView);
        namaTextView = findViewById(R.id.namaTextView);
        emailTextView = findViewById(R.id.emailTextView);
        rekeningTextView = findViewById(R.id.rekeningTextView);
        hpTextView = findViewById(R.id.hpTextView);
        editProfilButton = findViewById(R.id.editProfilButton);

        // Mengisi data saldo dan profil (gantilah dengan data nyata)
        String saldo = "Saldo Anda: Rp 1,000,000";
        String profil = "Nama: John Doe\nEmail: johndoe@example.com\nNo. Rekening: 123456789\nNo. HP: 081234567890";

        saldoTextView.setText(saldo);

        // Memisahkan string "profil" menjadi variabel-variabel individu
        String[] profilData = profil.split("\n");
        String nama = "";
        String email = "";
        String rekening = "";
        String hp = "";

        for (String data : profilData) {
            if (data.startsWith("Nama: ")) {
                nama = data.substring("Nama: ".length());
            } else if (data.startsWith("Email: ")) {
                email = data.substring("Email: ".length());
            } else if (data.startsWith("No. Rekening: ")) {
                rekening = data.substring("No. Rekening: ".length());
            } else if (data.startsWith("No. HP: ")) {
                hp = data.substring("No. HP: ".length());
            }
        }

        namaTextView.setText(nama);
        emailTextView.setText(email);
        rekeningTextView.setText(rekening);
        hpTextView.setText(hp);

        editProfilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ambil data yang terbaru dari tampilan
                String namaTerbaru = namaTextView.getText().toString();
                String emailTerbaru = emailTextView.getText().toString();
                String rekeningTerbaru = rekeningTextView.getText().toString();
                String hpTerbaru = hpTextView.getText().toString();

                Intent intent = new Intent(DashboardActivity.this, EditProfileActivity.class);
                // Kirim data profil terbaru ke EditProfileActivity
                intent.putExtra("nama", namaTerbaru);
                intent.putExtra("email", emailTerbaru);
                intent.putExtra("rekening", rekeningTerbaru);
                intent.putExtra("hp", hpTerbaru);
                startActivityForResult(intent, EDIT_PROFILE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_PROFILE_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                // Terima data profil yang telah diperbarui dari EditProfileActivity
                String namaBaru = data.getStringExtra("nama");
                String emailBaru = data.getStringExtra("email");
                String rekeningBaru = data.getStringExtra("rekening");
                String hpBaru = data.getStringExtra("hp");

                // Perbarui tampilan profil di halaman Dashboard dengan data yang baru
                namaTextView.setText(namaBaru);
                emailTextView.setText(emailBaru);
                rekeningTextView.setText(rekeningBaru);
                hpTextView.setText(hpBaru);
            }
        }
    }
}
