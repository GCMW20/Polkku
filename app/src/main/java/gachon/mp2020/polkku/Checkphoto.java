package gachon.mp2020.polkku;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Checkphoto extends AppCompatActivity implements AutoPermissionsListener {

    ImageView imageview;
    ImageView imageview2;
    RelativeLayout capture;
    Uri imageUri = null;
    File file;

    private static final int REQUEST_CAMERA = 100;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkphoto);

        imageview = findViewById(R.id.imageView);
        imageview2 = findViewById(R.id.imageView2);
        capture = findViewById(R.id.frame);

        Button btn_gallery = findViewById(R.id.button2);
        Button btn_camera = findViewById(R.id.button1);
        Button btn_next = findViewById(R.id.button3);
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
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/AnimationCapture";

                File file = new File(path);
                if(!file.exists()){
                    file.mkdirs();
                }*/

                SimpleDateFormat day = new SimpleDateFormat("yyyyMMddHHmmss");
                Date date = new Date();
                capture.buildDrawingCache();
                Bitmap captureview = capture.getDrawingCache();

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                //Bitmap bitmap = ((BitmapDrawable)imageview.getDrawable()).getBitmap();
                float scale = 1024/(float)captureview.getWidth();
                int image_w = (int)(captureview.getWidth() * scale);
                int image_h = (int)(captureview.getHeight() * scale);
                Bitmap resize = Bitmap.createScaledBitmap(captureview,image_w,image_h,true);
                resize.compress(Bitmap.CompressFormat.JPEG,100, stream);
                byte[] byteArray = stream.toByteArray();

                Intent intent = new Intent(getApplicationContext(),Editphoto.class);

                intent.putExtra("image", byteArray);
                startActivity(intent);
                capture.destroyDrawingCache();
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

        /*// 임시로 사용할 파일 생성
        file = new File(Environment.getExternalStorageDirectory(),
                ".camera.jpg");
        imageUri = Uri.fromFile(file);

        // 카메라를 호출합니다.
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        i.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(i, REQUEST_CAMERA);*/

        if(file == null){

            file = createFile();
        }
        imageUri = FileProvider.getUriForFile(this, "gachon.mp2020.polkku.fileprovider", file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent,REQUEST_CAMERA);

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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if(requestCode == REQUEST_CAMERA && resultCode == RESULT_OK)
        {
            try
            {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;

                // 비트맵 이미지로 가져온다
                String imagePath = imageUri.getPath();
                Bitmap image = BitmapFactory.decodeFile(imagePath, options);

                // 이미지를 상황에 맞게 회전시킨다
                ExifInterface exif = new ExifInterface(imagePath);
                int exifOrientation = exif.getAttributeInt(
                        ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                int exifDegree = exifOrientationToDegrees(exifOrientation);
                image = rotate(image, exifDegree);

                // 변환된 이미지 사용
                imageview.setImageBitmap(image);
                setVisible();
            }
            catch(Exception e)
            {
                Toast.makeText(this, "오류발생: " + e.getLocalizedMessage(),
                    Toast.LENGTH_LONG).show();
            }
        }

        /*if(requestCode == 201 && resultCode == RESULT_OK) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);

            imageview.setImageBitmap(bitmap);
            setVisible();
        }*/

    }

    /**
     * EXIF정보를 회전각도로 변환하는 메서드
     *
     * @param exifOrientation EXIF 회전각
     * @return 실제 각도
     */
    public int exifOrientationToDegrees(int exifOrientation)
    {
        if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_90)
        {
            return 90;
        }
        else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_180)
        {
            return 180;
        }
        else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_270)
        {
            return 270;
        }
        return 0;
    }

    public void setVisible(){
        imageview2.setVisibility(View.VISIBLE);
    }

    public Bitmap rotate(Bitmap bitmap, int degrees)
    {
        if(degrees != 0 && bitmap != null)
        {
            Matrix m = new Matrix();
            m.setRotate(degrees, (float) bitmap.getWidth() / 2,
                    (float) bitmap.getHeight() / 2);

            try
            {
                Bitmap converted = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
                if(bitmap != converted)
                {
                    bitmap.recycle();
                    bitmap = converted;
                }
            }
            catch(OutOfMemoryError ex)
            {
                // 메모리가 부족하여 회전을 시키지 못할 경우 그냥 원본을 반환합니다.
            }
        }
        return bitmap;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    @Override
    public void onDenied(int i, String[] strings) {

    }

    @Override
    public void onGranted(int i, String[] strings) {

    }
}


