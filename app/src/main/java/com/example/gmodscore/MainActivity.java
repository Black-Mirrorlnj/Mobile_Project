package com.example.gmodscore;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;

import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ShapeableImageView profileImageView;
    private ShapeableImageView previewImageView;
    private TextInputEditText emailInput;
    private TextInputEditText nickInput;
    private TextInputEditText steamIdInput;
    private TextView emailPreview;
    private TextView nickPreview;
    private TextView steamIdPreview;

    private final ActivityResultLauncher<String> imagePickerLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), this::updateSelectedImage);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bindViews();
        configureActions();
        updatePlayerPreview();
    }

    private void bindViews() {
        profileImageView = findViewById(R.id.imgPlayerProfile);
        previewImageView = findViewById(R.id.imgPlayerPreview);
        emailInput = findViewById(R.id.inputEmail);
        nickInput = findViewById(R.id.inputNick);
        steamIdInput = findViewById(R.id.inputSteamId);
        emailPreview = findViewById(R.id.txtPreviewEmailValue);
        nickPreview = findViewById(R.id.txtPreviewNickValue);
        steamIdPreview = findViewById(R.id.txtPreviewSteamIdValue);
    }

    private void configureActions() {
        MaterialButton choosePhotoButton = findViewById(R.id.btnChoosePhoto);
        MaterialButton updateButton = findViewById(R.id.btnUpdatePlayer);

        choosePhotoButton.setOnClickListener(v -> imagePickerLauncher.launch("image/*"));
        updateButton.setOnClickListener(v -> {
            updatePlayerPreview();
            Toast.makeText(this, R.string.player_updated_message, Toast.LENGTH_SHORT).show();
        });
    }

    private void updatePlayerPreview() {
        emailPreview.setText(getValueOrFallback(emailInput, R.string.default_email_value));
        nickPreview.setText(getValueOrFallback(nickInput, R.string.default_nick_value));
        steamIdPreview.setText(getValueOrFallback(steamIdInput, R.string.default_steam_id_value));
    }

    private String getValueOrFallback(TextInputEditText input, int fallbackResId) {
        CharSequence text = input.getText();
        if (text == null) {
            return getString(fallbackResId);
        }

        String value = text.toString().trim();
        return value.isEmpty() ? getString(fallbackResId) : value;
    }

    private void updateSelectedImage(Uri imageUri) {
        if (imageUri == null) {
            return;
        }

        showSelectedImage(profileImageView, imageUri);
        showSelectedImage(previewImageView, imageUri);
    }

    private void showSelectedImage(ShapeableImageView imageView, Uri imageUri) {
        imageView.setImageURI(imageUri);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setBackground(null);
    }
}
