package ru.itschool.f;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int hp = 100;
    int dmg = 1;
    int lvl = 0;
    int maxhp = 200;
    int minhp = 100;
    int hard = 0;
    int monei = 0;

    TextView Hp;
    TextView Monei;
    TextView LvL;
    Random random = new Random();
    Random random1 = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Hp = findViewById(R.id.Hp);
        Monei = findViewById(R.id.Monei);
        LvL = findViewById(R.id.LvL);
        updatehp();
        updatemonei();
    }

    public void hit(View view) {
        hp = hp - dmg;
        updatehp();

        if(hp <= 0){
            hp = random.nextInt(maxhp)+minhp;
            lvl = lvl+1;
            hard = 1;
            updateLvL();
            updatehp();
            if(random1.nextFloat() < 0.2){
                monei = monei + 100 * 2;
            }
            else {
                monei = monei + 100;
            }
            updatemonei();
        }
        if(hard == 1){
            maxhp = maxhp + 100;
            minhp = minhp + 100;
            hard = 0;
        }

    }
    void updatehp(){Hp.setText("Hp " +hp);}
    void updatemonei(){Monei.setText("Monei " +monei);}
    void updateLvL(){LvL.setText("lvl " +lvl);}

    public void dmg(View view) {
        if (monei >= 100){
            monei = monei - 100;
            dmg = dmg + 1;
            updatemonei();
        }
    }
}