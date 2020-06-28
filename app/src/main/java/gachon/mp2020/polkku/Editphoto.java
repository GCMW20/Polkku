package gachon.mp2020.polkku;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import yuku.ambilwarna.AmbilWarnaDialog;

public class Editphoto extends AppCompatActivity {

    //  private static String TAG = "EditPhoto";
    private final int REQUEST_IMAGE_STICKER = 5000;
    Set<StickerView> stickers = new HashSet<>();
    MyPaintView view;
    int tColor;
    int n = 0;
    RelativeLayout capture;
    RelativeLayout linearLayout;
    Button text_sticker;
    EditText text_sticker_edit;
    RelativeLayout.LayoutParams params;

    boolean isPaint = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editphoto);

        view = new MyPaintView(this);

        linearLayout = findViewById(R.id.editImage);

        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
        BitmapDrawable ob = new BitmapDrawable(getResources(),bitmap);

        final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 312, getResources().getDisplayMetrics());
        final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 438, getResources().getDisplayMetrics());


        params = new RelativeLayout.LayoutParams(width,height);
        linearLayout.setBackground(ob); //drawble된 이미지를 linearlayout의 배경으로 넣음
        linearLayout.addView(view, params);


        Button btn=findViewById(R.id.colorPickerButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });//색 고르기 버튼

        Button btn2=findViewById(R.id.thickPickerButton);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });//굵기 선택

        Button btn_back = findViewById(R.id.buttonBack);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                onBackPressed();
            }
        });

        Button btn_next = findViewById(R.id.buttonNext);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/AnimationCapture";

                File file = new File(path);
                if(!file.exists()){
                    file.mkdirs();
                }*/

                // SimpleDateFormat day = new SimpleDateFormat("yyyyMMddHHmmss");
                //  Date date = new Date();
                for (Iterator<StickerView> it = stickers.iterator(); it.hasNext(); ) {
                    StickerView sticker = it.next();
                    sticker.setControlItemsHidden(true);
                }
                capture = linearLayout;
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
                capture.destroyDrawingCache();
                Intent intent = new Intent(getApplicationContext(),SavePhoto.class);

                intent.putExtra("final_image", byteArray);
                startActivity(intent);

            }
        });
        final Button eraserBtn = (Button) findViewById(R.id.eraser);
        eraserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPaint){
                    ((Paint)view.mPaint).setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                    view.setStrokeWidth(30);
                    isPaint = false;
                    eraserBtn.setText("펜");
                }else{
                    ((Paint)view.mPaint).setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
                    isPaint = true;
                    eraserBtn.setText("지우개");
                    view.setStrokeWidth(3);
                }
            }
        });
        Button setImageStickerBtn = findViewById(R.id.SetStickerBtn);
        setImageStickerBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Editphoto.this, StickerAlpha.class);
                startActivityForResult(intent, REQUEST_IMAGE_STICKER);
    }
});

        text_sticker_edit = findViewById(R.id.textStickerEdit);
        text_sticker = (Button)findViewById(R.id.textStickerBtn);
        text_sticker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StickerTextView tx_sticker = new StickerTextView(getApplicationContext());
                if(text_sticker_edit.getText().toString().equals("")){
                    tx_sticker.setText("Text");
                }else{
                    tx_sticker.setText(text_sticker_edit.getText().toString());
                    text_sticker_edit.setText("");
                }

                stickers.add(tx_sticker);

                linearLayout.addView(tx_sticker);

            }
        });

    }
    private void show() {
        final EditText editText = new EditText(this);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("굵기 입력");
        builder.setMessage("원하는 크기를 입력해주세요:)");
        builder.setView(editText);
        builder.setPositiveButton("입력",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        view.setStrokeWidth(Integer.parseInt(editText.getText().toString()));
                    }
                });
        builder.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.show();
    }
    private void openColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, tColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
            }
            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                view.setColor(color);
            }
        });
        colorPicker.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_STICKER){
            if(resultCode==RESULT_OK){
                int selected = -1;
                if(data != null){
                    selected =  data.getIntExtra("selected", -1);
                }
                if (selected == 1){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alphaa));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 2){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alphab));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 3){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alphac));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                } else if (selected == 4){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alphad));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 5){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alpha_e));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 6){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alphaf));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 7){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alphag));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                } else if (selected == 8){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alphah));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 9){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alphai));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 10){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alphaj));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 11){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alphak));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 12){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alphal));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 13){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alpham));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 14){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alphan));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 15){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alphao));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 16){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alphap));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 17){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alphaq));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 18){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alphar));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 19){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alphas));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 20){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alphat));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 21){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alphau));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 22){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alphav));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 23){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alphaw));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 24){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alphax));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 25){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alphay));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 26){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.alphaz));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 47){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.food1));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 48){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.food2));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 49){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.food3));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 50){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.food4));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 51){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.food5));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 52){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.food6));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 53){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.food7));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 54) {
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.food8));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 27){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.pngwave1));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 28){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.pngwave2));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 29){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.pngwave3));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 30){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.pngwave4));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 31){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.pngwave5));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 32){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.pngwave6));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 33){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.pngwave9));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }else if (selected == 34){
                    StickerImageView im_sticker = new StickerImageView(getApplicationContext());
                    im_sticker.setImageDrawable(getResources().getDrawable(R.drawable.pngwave8));
                    linearLayout.addView(im_sticker);
                    stickers.add(im_sticker);
                }
            }

        }
    }
}