package android.zaharova.ru.projectpiano;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    public List<Note> noteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String value = intent.getStringExtra("key");

        View v = findViewById(R.id.game_view);

        setContentView(R.layout.activity_game);
    }

}
