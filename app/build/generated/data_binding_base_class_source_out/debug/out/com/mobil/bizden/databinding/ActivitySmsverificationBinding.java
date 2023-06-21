// Generated by view binder compiler. Do not edit!
package com.mobil.bizden.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mobil.bizden.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySmsverificationBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonVerifyOtp;

  @NonNull
  public final TextView resendSMSTextView;

  @NonNull
  public final EditText smsArray1;

  @NonNull
  public final EditText smsArray2;

  @NonNull
  public final EditText smsArray3;

  @NonNull
  public final EditText smsArray4;

  @NonNull
  public final EditText smsArray5;

  @NonNull
  public final EditText smsArray6;

  private ActivitySmsverificationBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button buttonVerifyOtp, @NonNull TextView resendSMSTextView,
      @NonNull EditText smsArray1, @NonNull EditText smsArray2, @NonNull EditText smsArray3,
      @NonNull EditText smsArray4, @NonNull EditText smsArray5, @NonNull EditText smsArray6) {
    this.rootView = rootView;
    this.buttonVerifyOtp = buttonVerifyOtp;
    this.resendSMSTextView = resendSMSTextView;
    this.smsArray1 = smsArray1;
    this.smsArray2 = smsArray2;
    this.smsArray3 = smsArray3;
    this.smsArray4 = smsArray4;
    this.smsArray5 = smsArray5;
    this.smsArray6 = smsArray6;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySmsverificationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySmsverificationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_smsverification, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySmsverificationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonVerifyOtp;
      Button buttonVerifyOtp = ViewBindings.findChildViewById(rootView, id);
      if (buttonVerifyOtp == null) {
        break missingId;
      }

      id = R.id.resendSMSTextView;
      TextView resendSMSTextView = ViewBindings.findChildViewById(rootView, id);
      if (resendSMSTextView == null) {
        break missingId;
      }

      id = R.id.smsArray1;
      EditText smsArray1 = ViewBindings.findChildViewById(rootView, id);
      if (smsArray1 == null) {
        break missingId;
      }

      id = R.id.smsArray2;
      EditText smsArray2 = ViewBindings.findChildViewById(rootView, id);
      if (smsArray2 == null) {
        break missingId;
      }

      id = R.id.smsArray3;
      EditText smsArray3 = ViewBindings.findChildViewById(rootView, id);
      if (smsArray3 == null) {
        break missingId;
      }

      id = R.id.smsArray4;
      EditText smsArray4 = ViewBindings.findChildViewById(rootView, id);
      if (smsArray4 == null) {
        break missingId;
      }

      id = R.id.smsArray5;
      EditText smsArray5 = ViewBindings.findChildViewById(rootView, id);
      if (smsArray5 == null) {
        break missingId;
      }

      id = R.id.smsArray6;
      EditText smsArray6 = ViewBindings.findChildViewById(rootView, id);
      if (smsArray6 == null) {
        break missingId;
      }

      return new ActivitySmsverificationBinding((ConstraintLayout) rootView, buttonVerifyOtp,
          resendSMSTextView, smsArray1, smsArray2, smsArray3, smsArray4, smsArray5, smsArray6);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
