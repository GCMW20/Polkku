package gachon.mp2020.polkku2;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import gachon.mp2020.polkku2.R;

public class Checkphoto extends AppCompatActivity {

    ImageView imageview;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkphoto);

        imageview = findViewById(R.id.imageView);

        Button btn_gallery = findViewById(R.id.button2);
        Button btn_camera = findViewById(R.id.button1);
        Button btn_next=(Button)findViewById(R.id.button3);

        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        btn_camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,101);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),Drawphoto.class);
                startActivity(intent);
            }
        });
    }

    public void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(intent, 101);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                Uri fileUri = data.getData();

                ContentResolver resolver = getContentResolver();

                try {
                    InputStream instream = resolver.openInputStream(fileUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(instream);
                    imageview.setImageBitmap(bitmap);

                    instream.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        if(resultCode==1){
            if (resultCode == RESULT_OK) {
                Uri fileUri = data.getData();

                ContentResolver resolver = getContentResolver();

                try {
                    InputStream instream = resolver.openInputStream(fileUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(instream);
                    imageview.setImageBitmap(bitmap);

                    instream.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }

}


