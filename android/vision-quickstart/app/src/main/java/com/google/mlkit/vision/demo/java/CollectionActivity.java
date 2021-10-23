/*
 * Copyright 2020 Google LLC. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.mlkit.vision.demo.java;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.StrictMode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback;
import androidx.core.content.ContextCompat;
import com.google.mlkit.vision.demo.BuildConfig;
import com.google.mlkit.vision.demo.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Demo app chooser which takes care of runtime permission requesting and allow you pick from all
 * available testing Activities.
 */
public final class CollectionActivity extends AppCompatActivity
    implements OnRequestPermissionsResultCallback, AdapterView.OnItemClickListener {
  private static final String TAG = "CollectionActivity";
  private static final int PERMISSION_REQUESTS = 1;

  private static final Class<?>[] CLASSES = new Class<?>[] {
          Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class,  Nullable.class,
          Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class,  Nullable.class,
          Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class,  Nullable.class,
          Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class,  Nullable.class,
          Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class,  Nullable.class,
          Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class,  Nullable.class,
          Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class,  Nullable.class,
          Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class,  Nullable.class,
          Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class,  Nullable.class,
          Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class, Nullable.class,  Nullable.class,
          Nullable.class,
          };
  private static final int[] CATEGORY_ID = new int []{
          R.string.pref_category_item1, R.string.pref_category_item2, R.string.pref_category_item3, R.string.pref_category_item4, R.string.pref_category_item5, R.string.pref_category_item6, R.string.pref_category_item7, R.string.pref_category_item8, R.string.pref_category_item9, R.string.pref_category_item10,
          R.string.pref_category_item11, R.string.pref_category_item12, R.string.pref_category_item13, R.string.pref_category_item14, R.string.pref_category_item15, R.string.pref_category_item16, R.string.pref_category_item17, R.string.pref_category_item18, R.string.pref_category_item19, R.string.pref_category_item20,
          R.string.pref_category_item21, R.string.pref_category_item22, R.string.pref_category_item23, R.string.pref_category_item24, R.string.pref_category_item25, R.string.pref_category_item26, R.string.pref_category_item27, R.string.pref_category_item28, R.string.pref_category_item29, R.string.pref_category_item30,
          R.string.pref_category_item31, R.string.pref_category_item32, R.string.pref_category_item33, R.string.pref_category_item34, R.string.pref_category_item35, R.string.pref_category_item36, R.string.pref_category_item37, R.string.pref_category_item38, R.string.pref_category_item39, R.string.pref_category_item40,
          R.string.pref_category_item41, R.string.pref_category_item42, R.string.pref_category_item43, R.string.pref_category_item44, R.string.pref_category_item45, R.string.pref_category_item46, R.string.pref_category_item47, R.string.pref_category_item48, R.string.pref_category_item49, R.string.pref_category_item50,
          R.string.pref_category_item51, R.string.pref_category_item52, R.string.pref_category_item53, R.string.pref_category_item54, R.string.pref_category_item55, R.string.pref_category_item56, R.string.pref_category_item57, R.string.pref_category_item58, R.string.pref_category_item59, R.string.pref_category_item60,
          R.string.pref_category_item61, R.string.pref_category_item62, R.string.pref_category_item63, R.string.pref_category_item64, R.string.pref_category_item65, R.string.pref_category_item66, R.string.pref_category_item67, R.string.pref_category_item68, R.string.pref_category_item69, R.string.pref_category_item70,
          R.string.pref_category_item71, R.string.pref_category_item72, R.string.pref_category_item73, R.string.pref_category_item74, R.string.pref_category_item75, R.string.pref_category_item76, R.string.pref_category_item77, R.string.pref_category_item78, R.string.pref_category_item79, R.string.pref_category_item80,
          R.string.pref_category_item81, R.string.pref_category_item82, R.string.pref_category_item83, R.string.pref_category_item84, R.string.pref_category_item85, R.string.pref_category_item86, R.string.pref_category_item87, R.string.pref_category_item88, R.string.pref_category_item89, R.string.pref_category_item90,
          R.string.pref_category_item91, R.string.pref_category_item92, R.string.pref_category_item93, R.string.pref_category_item94, R.string.pref_category_item95, R.string.pref_category_item96, R.string.pref_category_item97, R.string.pref_category_item98, R.string.pref_category_item99, R.string.pref_category_item100,
          R.string.pref_category_item101,
  };

  private static int[] collections_visit_time = new int[]{
          0,0,0,0,0,0,0,0,0,0,
          0,0,0,0,0,0,0,0,0,0,
          0,0,0,0,0,0,0,0,0,0,
          0,0,0,0,0,0,0,0,0,0,
          0,0,0,0,0,0,0,0,0,0,
          0,0,0,0,0,0,0,0,0,0,
          0,0,0,0,0,0,0,0,0,0,
          0,0,0,0,0,0,0,0,0,0,
          0,0,0,0,0,0,0,0,0,0,
          0,0,0,0,0,0,0,0,0,0,
          0,
  };

  public void VisitCollection(int number){
    if(number > 0 && number < 102) {
      collections_visit_time[number-1]++;
    }
  }
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    if (BuildConfig.DEBUG) {
      StrictMode.setThreadPolicy(
          new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
      StrictMode.setVmPolicy(
          new StrictMode.VmPolicy.Builder()
              .detectLeakedSqlLiteObjects()
              .detectLeakedClosableObjects()
              .penaltyLog()
              .build());
    }
    super.onCreate(savedInstanceState);
    Log.d(TAG, "onCreate");

    setContentView(R.layout.activity_collection);

    // Set up ListView and Adapter
    ListView listView = findViewById(R.id.test_activity_list_view);

    MyArrayAdapter adapter = new MyArrayAdapter(this, android.R.layout.simple_list_item_2, CLASSES);
    adapter.setCategoryIds(CATEGORY_ID);
    listView.setAdapter(adapter);
    //listView.setOnItemClickListener(this);

    if (!allPermissionsGranted()) {
      getRuntimePermissions();
    }
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Class<?> clicked = CLASSES[position];
    startActivity(new Intent(this, clicked));
  }

  private String[] getRequiredPermissions() {
    try {
      PackageInfo info =
          this.getPackageManager()
              .getPackageInfo(this.getPackageName(), PackageManager.GET_PERMISSIONS);
      String[] ps = info.requestedPermissions;
      if (ps != null && ps.length > 0) {
        return ps;
      } else {
        return new String[0];
      }
    } catch (Exception e) {
      return new String[0];
    }
  }

  private boolean allPermissionsGranted() {
    for (String permission : getRequiredPermissions()) {
      if (!isPermissionGranted(this, permission)) {
        return false;
      }
    }
    return true;
  }

  private void getRuntimePermissions() {
    List<String> allNeededPermissions = new ArrayList<>();
    for (String permission : getRequiredPermissions()) {
      if (!isPermissionGranted(this, permission)) {
        allNeededPermissions.add(permission);
      }
    }

    if (!allNeededPermissions.isEmpty()) {
      ActivityCompat.requestPermissions(
          this, allNeededPermissions.toArray(new String[0]), PERMISSION_REQUESTS);
    }
  }

  private static boolean isPermissionGranted(Context context, String permission) {
    if (ContextCompat.checkSelfPermission(context, permission)
        == PackageManager.PERMISSION_GRANTED) {
      Log.i(TAG, "Permission granted: " + permission);
      return true;
    }
    Log.i(TAG, "Permission NOT granted: " + permission);
    return false;
  }

  private static class MyArrayAdapter extends ArrayAdapter<Class<?>> {

    private final Context context;
    //private final Class<?>[] classes;
    private int[] descriptionIds;
    private int[] categoryIds;

    MyArrayAdapter(Context context, int resource, Class<?>[] objects) {
      super(context, resource, objects);

      this.context = context;
      //classes = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      View view = convertView;

      if (convertView == null) {
        LayoutInflater inflater =
            (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(android.R.layout.simple_list_item_2, null);
      }

      ((TextView) view.findViewById(android.R.id.text1)).setText(categoryIds[position]);
      if(collections_visit_time[position] == 0) {
        ((TextView) view.findViewById(android.R.id.text2)).setText(R.string.pref_category_not_yet);
      }else{
        ((TextView) view.findViewById(android.R.id.text2)).setText(R.string.pref_category_found);
      }
      //((TextView) view.findViewById(android.R.id.text2)).setText(descriptionIds[position]);

      return view;
    }

    void setDescriptionIds(int[] descriptionIds) {
      this.descriptionIds = descriptionIds;
    }
    void setCategoryIds(int[] categoryIds) {
      this.categoryIds = categoryIds;
    }
  }
}
