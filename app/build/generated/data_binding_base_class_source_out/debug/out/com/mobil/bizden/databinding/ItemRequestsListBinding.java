// Generated by view binder compiler. Do not edit!
package com.mobil.bizden.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mobil.bizden.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemRequestsListBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView creadtedDate;

  @NonNull
  public final TextView tvAreaLocation;

  @NonNull
  public final TextView tvAreaName;

  @NonNull
  public final TextView tvAreaOrg;

  @NonNull
  public final TextView tvDelete;

  @NonNull
  public final TextView tvSetTime;

  @NonNull
  public final TextView tvStatus;

  private ItemRequestsListBinding(@NonNull LinearLayout rootView, @NonNull TextView creadtedDate,
      @NonNull TextView tvAreaLocation, @NonNull TextView tvAreaName, @NonNull TextView tvAreaOrg,
      @NonNull TextView tvDelete, @NonNull TextView tvSetTime, @NonNull TextView tvStatus) {
    this.rootView = rootView;
    this.creadtedDate = creadtedDate;
    this.tvAreaLocation = tvAreaLocation;
    this.tvAreaName = tvAreaName;
    this.tvAreaOrg = tvAreaOrg;
    this.tvDelete = tvDelete;
    this.tvSetTime = tvSetTime;
    this.tvStatus = tvStatus;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemRequestsListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemRequestsListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_requests_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemRequestsListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.creadtedDate;
      TextView creadtedDate = ViewBindings.findChildViewById(rootView, id);
      if (creadtedDate == null) {
        break missingId;
      }

      id = R.id.tvAreaLocation;
      TextView tvAreaLocation = ViewBindings.findChildViewById(rootView, id);
      if (tvAreaLocation == null) {
        break missingId;
      }

      id = R.id.tvAreaName;
      TextView tvAreaName = ViewBindings.findChildViewById(rootView, id);
      if (tvAreaName == null) {
        break missingId;
      }

      id = R.id.tvAreaOrg;
      TextView tvAreaOrg = ViewBindings.findChildViewById(rootView, id);
      if (tvAreaOrg == null) {
        break missingId;
      }

      id = R.id.tvDelete;
      TextView tvDelete = ViewBindings.findChildViewById(rootView, id);
      if (tvDelete == null) {
        break missingId;
      }

      id = R.id.tvSetTime;
      TextView tvSetTime = ViewBindings.findChildViewById(rootView, id);
      if (tvSetTime == null) {
        break missingId;
      }

      id = R.id.tvStatus;
      TextView tvStatus = ViewBindings.findChildViewById(rootView, id);
      if (tvStatus == null) {
        break missingId;
      }

      return new ItemRequestsListBinding((LinearLayout) rootView, creadtedDate, tvAreaLocation,
          tvAreaName, tvAreaOrg, tvDelete, tvSetTime, tvStatus);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}