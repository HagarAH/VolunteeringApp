// Generated by view binder compiler. Do not edit!
package com.mobil.bizden.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputLayout;
import com.mobil.bizden.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityUserLocationBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextInputLayout autocompleteDistrict;

  @NonNull
  public final AutoCompleteTextView autocompleteDistrictAuto;

  @NonNull
  public final TextInputLayout autocompleteProvince;

  @NonNull
  public final AutoCompleteTextView autocompleteProvinceAuto;

  @NonNull
  public final EditText editTextAddress;

  @NonNull
  public final Button saveLocationBtn;

  @NonNull
  public final TextView textViewAddress;

  @NonNull
  public final TextView textViewDistrict;

  @NonNull
  public final TextView textViewProvince;

  private ActivityUserLocationBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextInputLayout autocompleteDistrict,
      @NonNull AutoCompleteTextView autocompleteDistrictAuto,
      @NonNull TextInputLayout autocompleteProvince,
      @NonNull AutoCompleteTextView autocompleteProvinceAuto, @NonNull EditText editTextAddress,
      @NonNull Button saveLocationBtn, @NonNull TextView textViewAddress,
      @NonNull TextView textViewDistrict, @NonNull TextView textViewProvince) {
    this.rootView = rootView;
    this.autocompleteDistrict = autocompleteDistrict;
    this.autocompleteDistrictAuto = autocompleteDistrictAuto;
    this.autocompleteProvince = autocompleteProvince;
    this.autocompleteProvinceAuto = autocompleteProvinceAuto;
    this.editTextAddress = editTextAddress;
    this.saveLocationBtn = saveLocationBtn;
    this.textViewAddress = textViewAddress;
    this.textViewDistrict = textViewDistrict;
    this.textViewProvince = textViewProvince;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityUserLocationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityUserLocationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_user_location, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityUserLocationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.autocomplete_district;
      TextInputLayout autocompleteDistrict = ViewBindings.findChildViewById(rootView, id);
      if (autocompleteDistrict == null) {
        break missingId;
      }

      id = R.id.autocomplete_districtAuto;
      AutoCompleteTextView autocompleteDistrictAuto = ViewBindings.findChildViewById(rootView, id);
      if (autocompleteDistrictAuto == null) {
        break missingId;
      }

      id = R.id.autocomplete_province;
      TextInputLayout autocompleteProvince = ViewBindings.findChildViewById(rootView, id);
      if (autocompleteProvince == null) {
        break missingId;
      }

      id = R.id.autocomplete_provinceAuto;
      AutoCompleteTextView autocompleteProvinceAuto = ViewBindings.findChildViewById(rootView, id);
      if (autocompleteProvinceAuto == null) {
        break missingId;
      }

      id = R.id.editTextAddress;
      EditText editTextAddress = ViewBindings.findChildViewById(rootView, id);
      if (editTextAddress == null) {
        break missingId;
      }

      id = R.id.saveLocationBtn;
      Button saveLocationBtn = ViewBindings.findChildViewById(rootView, id);
      if (saveLocationBtn == null) {
        break missingId;
      }

      id = R.id.textViewAddress;
      TextView textViewAddress = ViewBindings.findChildViewById(rootView, id);
      if (textViewAddress == null) {
        break missingId;
      }

      id = R.id.textViewDistrict;
      TextView textViewDistrict = ViewBindings.findChildViewById(rootView, id);
      if (textViewDistrict == null) {
        break missingId;
      }

      id = R.id.textViewProvince;
      TextView textViewProvince = ViewBindings.findChildViewById(rootView, id);
      if (textViewProvince == null) {
        break missingId;
      }

      return new ActivityUserLocationBinding((ConstraintLayout) rootView, autocompleteDistrict,
          autocompleteDistrictAuto, autocompleteProvince, autocompleteProvinceAuto, editTextAddress,
          saveLocationBtn, textViewAddress, textViewDistrict, textViewProvince);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
