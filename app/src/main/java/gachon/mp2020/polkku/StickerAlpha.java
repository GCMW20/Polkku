package gachon.mp2020.polkku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class StickerAlpha extends AppCompatActivity {

    ImageButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10,
            btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19, btn20,
            btn21, btn22, btn23, btn24, btn25, btn26, btn27, btn28, btn29, btn30,
            btn31, btn32, btn33, btn34, btn47, btn48, btn49, btn50,
            btn51, btn52, btn53, btn54;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sticker_alphabet);

        btn1 = (ImageButton)findViewById(R.id.A);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 1);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn2 = (ImageButton)findViewById(R.id.B);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 2);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn3 = (ImageButton)findViewById(R.id.C);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 3);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn4 = (ImageButton)findViewById(R.id.D);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 4);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn5 = (ImageButton)findViewById(R.id.E);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 5);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn6 = (ImageButton)findViewById(R.id.F);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 6);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn7 = (ImageButton)findViewById(R.id.G);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 7);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn8 = (ImageButton)findViewById(R.id.H);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 8);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn9 = (ImageButton)findViewById(R.id.I);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 9);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn10 = (ImageButton)findViewById(R.id.J);
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 10);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn11 = (ImageButton)findViewById(R.id.K);
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 11);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn12 = (ImageButton)findViewById(R.id.L);
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 12);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn13 = (ImageButton)findViewById(R.id.M);
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 13);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn14 = (ImageButton)findViewById(R.id.N);
        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 14);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn15 = (ImageButton)findViewById(R.id.O);
        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 15);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn16 = (ImageButton)findViewById(R.id.P);
        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 16);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn17 = (ImageButton)findViewById(R.id.Q);
        btn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 17);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn18 = (ImageButton)findViewById(R.id.R);
        btn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 18);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn19 = (ImageButton)findViewById(R.id.S);
        btn19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 19);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn20 = (ImageButton)findViewById(R.id.T);
        btn20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 20);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn21 = (ImageButton)findViewById(R.id.U);
        btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected",21);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn22 = (ImageButton)findViewById(R.id.V);
        btn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 22);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn23 = (ImageButton)findViewById(R.id.W);
        btn23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 23);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn24 = (ImageButton)findViewById(R.id.X);
        btn24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 24);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn25 = (ImageButton)findViewById(R.id.Y);
        btn25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected",25);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn26 = (ImageButton)findViewById(R.id.Z);
        btn26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 26);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn47 = (ImageButton)findViewById(R.id.food1);
        btn47.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 47);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn48 = (ImageButton)findViewById(R.id.food2);
        btn48.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 48);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn49 = (ImageButton)findViewById(R.id.food3);
        btn49.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 49);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn50 = (ImageButton)findViewById(R.id.food4);
        btn50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 50);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn51= (ImageButton)findViewById(R.id.food5);
        btn51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 51);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn52 = (ImageButton)findViewById(R.id.food6);
        btn52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 52);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn53= (ImageButton)findViewById(R.id.food7);
        btn53.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 53);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn54 = (ImageButton)findViewById(R.id.food8);
        btn54.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 54);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn27 = (ImageButton)findViewById(R.id.fruit1);
        btn27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 27);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn28 = (ImageButton)findViewById(R.id.fruit2);
        btn28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 28);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn29 = (ImageButton)findViewById(R.id.fruit3);
        btn29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 29);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn30 = (ImageButton)findViewById(R.id.fruit4);
        btn30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 30);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn31 = (ImageButton)findViewById(R.id.fruit5);
        btn31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 31);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn32 = (ImageButton)findViewById(R.id.fruit6);
        btn32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 32);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn33 = (ImageButton)findViewById(R.id.fruit7);
        btn33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 33);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn34 = (ImageButton)findViewById(R.id.fruit8);
        btn34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selected", 34);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        Button btn_back = findViewById(R.id.back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                onBackPressed();
            }
        });
    }
}

