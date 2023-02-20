package com.example.roomdatabase;

import static com.example.roomdatabase.AppDatabase.getAppDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.roomdatabase.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    AppDatabase db;
    UserDao userDao;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        db = Room.databaseBuilder(getApplicationContext(),
//                        AppDatabase.class, "database-name")
//                .fallbackToDestructiveMigration() //스키마 버전 변경 가능
//                .allowMainThreadQueries()
//                .build();

        userDao = getAppDatabase(this).userDao();

        binding.inputButton.setOnClickListener(this);
        binding.inquireData.setOnClickListener(this);

    }

    public void Insert() {

        String value_text = binding.inputEditText.getText().toString();

        User user = new User(value_text);

        userDao.insertAll(user);
    }


    public void getAll() {
        List<User> users = userDao.getAll();

        StringBuilder string = new StringBuilder();
        for(int i=0; i<users.size(); i++){
            string.append(users.get(i).firstName).append(" ");
        }

        Log.d("Main", "getAll: " + string);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.input_button:
                Insert();
                break;

            case R.id.inquire_data:
                getAll();
                break;

            default:
                Toast.makeText(this, "notting data", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}