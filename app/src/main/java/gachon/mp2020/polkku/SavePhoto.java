package gachon.mp2020.polkku;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.graphics.Bitmap.CompressFormat.JPEG;

public class SavePhoto extends AppCompatActivity {

    LinearLayout imageView;
    Button back;
    Button save;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.savephoto);

        imageView = findViewById(R.id.saveImage);
        back=findViewById(R.id.buttonBack);
        save=findViewById(R.id.buttonSave);

        byte[] byteArray = getIntent().getByteArrayExtra("final_image");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
        BitmapDrawable ob = new BitmapDrawable(getResources(),bitmap);
        imageView.setBackground(ob);

        back.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){
                onBackPressed();
            }
        });

        save.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                String path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/POLKKU";
                final LinearLayout capture=findViewById(R.id.saveImage);

                File file=new File(path);
                if(!file.exists()){
                    file.mkdirs();
                }

                java.text.SimpleDateFormat day = new SimpleDateFormat("yyyyMMddHHmmss");
                Date date = new Date();
                capture.buildDrawingCache();
                Bitmap captureview = capture.getDrawingCache();

                FileOutputStream fos=null;

                try{
                    fos=new FileOutputStream(path+"/POLKKU"+day.format(date)+".JPEG");
                    captureview.compress(Bitmap.CompressFormat.JPEG,100,fos);
                    sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"+path+"/POLKKU"+day.format(date)+".JPEG")));
                    Toast.makeText(SavePhoto.this, "저장 완료", Toast.LENGTH_SHORT).show();
                    fos.flush();
                    fos.close();
                }catch(FileNotFoundException e){
                    e.printStackTrace();;
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        });


    }

}