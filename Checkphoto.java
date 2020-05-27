package gachon.mp2020.polkku;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import gachon.mp2020.polkku.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Checkphoto extends AppCompatActivity implements AutoPermissionsListener{

    ImageView imageview;
    ImageView imageview2;
    File file;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkphoto);

        imageview = findViewById(R.id.imageView);
        imageview2 = findViewById(R.id.imageView2);

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
                openCamera();
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap bitmap = ((BitmapDrawable)imageview.getDrawable()).getBitmap();
                float scale = (float) (1024/(float)bitmap.getWidth());
                int image_w = (int)(bitmap.getWidth() * scale);
                int image_h = (int)(bitmap.getHeight() * scale);
                Bitmap resize = Bitmap.createScaledBitmap(bitmap,image_w,image_h,true);
                resize.compress(Bitmap.CompressFormat.JPEG,100, stream);
                byte[] byteArray = stream.toByteArray();

                Intent intent = new Intent(getApplicationContext(),Editphoto.class);

                intent.putExtra("image", byteArray);
                startActivity(intent);
            }
        });
        AutoPermissions.Companion.loadAllPermissions(this, 201);
    }

    public void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(intent, 101);
    }
    
     public void openCamera(){
        if(file == null){
            file = createFile();
        }
        Uri fileUri = FileProvider.getUriForFile(this, "gachon.mp2020.polkku.fileprovider", file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent,201);
    }
    
    public File createFile(){
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filename = "capture"+timeStamp;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File outFile = new File(storageDir, filename);
        return outFile;
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
                    setVisible();
                } 
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        if(requestCode == 201 && resultCode == RESULT_OK) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
            
            imageview.setImageBitmap(bitmap);
            setVisible();
        }

    }
    
    public void setVisible(){
        imageview2.setVisibility(View.VISIBLE);
    }
    
    @Override
    public void onDenied(int i, String[] strings) {

    }

    @Override
    public void onGranted(int i, String[] strings) {

    }
}
