package com.devmatech.qrcode;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private IntentIntegrator intentIntegrator;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentIntegrator = new IntentIntegrator(MainActivity.this);
                intentIntegrator.initiateScan();
            }
        });



//
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if(result.getContents() == null){

                Toast.makeText(this,"Hasil tidak ditemukan",Toast.LENGTH_SHORT).show();

            }else{
                    //info
                    AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                    TextView title = new TextView(this);

                    title.setText("Info Qrcode");
                    title.setPadding(10,10,10,10);
                    title.setGravity(Gravity.CENTER);
                    title.setTextSize(20);
                    alertDialog.setCustomTitle(title);


                    TextView message = new TextView(this);
                    message.setGravity(Gravity.CENTER_HORIZONTAL);
                    message.setTextColor(Color.BLACK);
                    message.setText(result.getContents());
                    alertDialog.setView(message);

                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    new Dialog(getApplicationContext());
                    alertDialog.show();

                    final Button okBT = alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
                    LinearLayout.LayoutParams neutralBtnLP = (LinearLayout.LayoutParams) okBT.getLayoutParams();
                    neutralBtnLP.gravity = Gravity.FILL_HORIZONTAL;
                    okBT.setPadding(50, 10, 10, 10);   // Set Position
                    okBT.setTextColor(Color.BLUE);
                    okBT.setLayoutParams(neutralBtnLP);

            }

        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
