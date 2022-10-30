package com.ulp.inmobiliariafpestchanker.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ulp.inmobiliariafpestchanker.MainActivity;
import com.ulp.inmobiliariafpestchanker.R;

public class Login extends AppCompatActivity {

    private LoginViewModel viewModel;
    private TextView tvError;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private DetectarAgitar mDetectarAgitar;
    private Button btnNoRecuerdoPass;

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mDetectarAgitar, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {

        mSensorManager.unregisterListener(mDetectarAgitar);

        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        solicitarPermisoLLamada();
        solicitarPermisoUbicacion();
        btnNoRecuerdoPass = findViewById(R.id.btnNoRecuerdoPass);



        inicializarVista();


        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
                .create(LoginViewModel.class);
        viewModel.getErrorVisibility().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String mensaje) {

                Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG).show();


            }
        });










    }

    public void inicializarVista() {

        Button btLogin = findViewById(R.id.btLogin);
        EditText etUsuario = findViewById(R.id.etUsuario);
        EditText etPassword = findViewById(R.id.etPassword);


        tvError = findViewById(R.id.tvError);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mDetectarAgitar = new DetectarAgitar();



        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.login(
                       etUsuario.getText().toString(),
                        etPassword.getText().toString()
                );
            }
        });


        btnNoRecuerdoPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.noRecueldoPass(
                        etUsuario.getText().toString()
                                        );
            }
        });







        arranqueagitacion();

    }

    private void solicitarPermisoLLamada() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);
        }

    }

        private void solicitarPermisoUbicacion() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
            }



    }
    private void arranqueagitacion() {
        mDetectarAgitar.setOnShakeListener(new DetectarAgitar.OnShakeListener() {
            @Override
            public void onShake(int count) {
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "444444"));
                startActivity(i);
            }
        });
    }


}

