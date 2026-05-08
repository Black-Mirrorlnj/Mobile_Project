package com.example.gmodscore.feature.player;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gmodscore.databinding.FragmentPlayerBinding;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.example.gmodscore.R;

public class PlayerFragment extends Fragment {

    private FragmentPlayerBinding binding;

    private final ActivityResultLauncher<String> imagePickerLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), this::updateSelectedImage);

    public PlayerFragment() {
        super(R.layout.fragment_player);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPlayerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configureActions();
        updatePlayerPreview();
    }

    private void configureActions() {
        binding.btnChoosePhoto.setOnClickListener(v -> imagePickerLauncher.launch("image/*"));
        binding.btnUpdatePlayer.setOnClickListener(v -> {
            updatePlayerPreview();
            Toast.makeText(requireContext(), R.string.player_updated_message, Toast.LENGTH_SHORT).show();
        });
    }

    private void updatePlayerPreview() {
        binding.txtPreviewEmailValue.setText(getValueOrFallback(binding.inputEmail, R.string.default_email_value));
        binding.txtPreviewNickValue.setText(getValueOrFallback(binding.inputNick, R.string.default_nick_value));
        binding.txtPreviewSteamIdValue.setText(getValueOrFallback(binding.inputSteamId, R.string.default_steam_id_value));
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
        if (imageUri == null || binding == null) {
            return;
        }

        showSelectedImage(binding.imgPlayerProfile, imageUri);
        showSelectedImage(binding.imgPlayerPreview, imageUri);
    }

    private void showSelectedImage(ShapeableImageView imageView, Uri imageUri) {
        imageView.setImageURI(imageUri);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setBackground(null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

