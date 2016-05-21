package android.zaharova.ru.projectpiano;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    public List<Note> noteList = new ArrayList<>();
    public StaffView staffView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String value = intent.getStringExtra("key");

        setContentView(R.layout.activity_game);

        staffView = (StaffView)findViewById(R.id.staff_view);
    }

}
