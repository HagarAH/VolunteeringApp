// Generated by view binder compiler. Do not edit!
package com.mobil.bizden.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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

public final class ActivityRegisterBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText emailEditText;

  @NonNull
  public final TextView loginNow;

  @NonNull
  public final ImageView logoImageView;

  @NonNull
  public final LinearLayout logoLayout;

  @NonNull
  public final EditText passwordConfirmEditText;

  @NonNull
  public final EditText passwordEditText;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final Button registerButton;

  @NonNull
  public final TextView textViewLabel1;

  @NonNull
  public final TextView textViewLabel2;

  @NonNull
  public final TextView textViewLabel3;

  private ActivityRegisterBinding(@NonNull ConstraintLayout rootView,
      @NonNull EditText emailEditText, @NonNull TextView loginNow, @NonNull ImageView logoImageView,
      @NonNull LinearLayout logoLayout, @NonNull EditText passwordConfirmEditText,
      @NonNull EditText passwordEditText, @NonNull ProgressBar progressBar,
      @NonNull Button registerButton, @NonNull TextView textViewLabel1,
      @NonNull TextView textViewLabel2, @NonNull TextView textViewLabel3) {
    this.rootView = rootView;
    this.emailEditText = emailEditText;
    this.loginNow = loginNow;
    this.logoImageView = logoImageView;
    this.logoLayout = logoLayout;
    this.passwordConfirmEditText = passwordConfirmEditText;
    this.passwordEditText = passwordEditText;
    this.progressBar = progressBar;
    this.registerButton = registerButton;
    this.textViewLabel1 = textViewLabel1;
    this.textViewLabel2 = textViewLabel2;
    this.textViewLabel3 = textViewLabel3;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.emailEditText;
      EditText emailEditText = ViewBindings.findChildViewById(rootView, id);
      if (emailEditText == null) {
        break missingId;
      }

      id = R.id.loginNow;
      TextView loginNow = ViewBindings.findChildViewById(rootView, id);
      if (loginNow == null) {
        break missingId;
      }

      id = R.id.logoImageView;
      ImageView logoImageView = ViewBindings.findChildViewById(rootView, id);
      if (logoImageView == null) {
        break missingId;
      }

      id = R.id.logoLayout;
      LinearLayout logoLayout = ViewBindings.findChildViewById(rootView, id);
      if (logoLayout == null) {
        break missingId;
      }

      id = R.id.passwordConfirmEditText;
      EditText passwordConfirmEditText = ViewBindings.findChildViewById(rootView, id);
      if (passwordConfirmEditText == null) {
        break missingId;
      }

      id = R.id.passwordEditText;
      EditText passwordEditText = ViewBindings.findChildViewById(rootView, id);
      if (passwordEditText == null) {
        break missingId;
      }

      id = R.id.progressBar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.registerButton;
      Button registerButton = ViewBindings.findChildViewById(rootView, id);
      if (registerButton == null) {
        break missingId;
      }

      id = R.id.textViewLabel1;
      TextView textViewLabel1 = ViewBindings.findChildViewById(rootView, id);
      if (textViewLabel1 == null) {
        break missingId;
      }

      id = R.id.textViewLabel2;
      TextView textViewLabel2 = ViewBindings.findChildViewById(rootView, id);
      if (textViewLabel2 == null) {
        break missingId;
      }

      id = R.id.textViewLabel3;
      TextView textViewLabel3 = ViewBindings.findChildViewById(rootView, id);
      if (textViewLabel3 == null) {
        break missingId;
      }

      return new ActivityRegisterBinding((ConstraintLayout) rootView, emailEditText, loginNow,
          logoImageView, logoLayout, passwordConfirmEditText, passwordEditText, progressBar,
          registerButton, textViewLabel1, textViewLabel2, textViewLabel3);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
