package com.example.frank.eventapp.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.frank.eventapp.R;
import com.example.frank.eventapp.api.ApiClient;
import com.example.frank.eventapp.api.UserInterface;
import com.example.frank.eventapp.data.Response;
import com.example.frank.eventapp.data.User;
import com.example.frank.eventapp.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.idbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserInterface reto = ApiClient.getClient().create(UserInterface.class);
                User user = new User();
                user.setName(binding.inputName.getEditText().getText().toString());
                user.setLastName(binding.inputLastName.getEditText().getText().toString());
                user.setUsername(binding.inputUsername.getEditText().getText().toString());
                user.setPassword(binding.inputPassword.getEditText().getText().toString());

                Call<Response> call = reto.createNewUser(user);
                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        Response res = response.body();
                        Toast.makeText(MainActivity.this,res.toString(),Toast.LENGTH_SHORT).show();
                        binding.inputName.getEditText().setText("");
                        binding.inputLastName.getEditText().setText("");
                        binding.inputUsername.getEditText().setText("");
                        binding.inputPassword.getEditText().setText("");

                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
