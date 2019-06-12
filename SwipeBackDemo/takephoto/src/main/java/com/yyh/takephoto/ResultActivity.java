package com.yyh.takephoto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.devio.takephoto.model.TImage;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private ArrayList<TImage> mImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mImages = (ArrayList<TImage>) getIntent().getSerializableExtra("images");

        showImage();
    }

    private void showImage() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llImages);
        int i;
        for (i = 0; i < mImages.size(); i++) {
            ImageView imageView = new ImageView(this);
            String path = mImages.get(i).getCompressPath();
            if (path == null || "".equals(path)) {
                path = mImages.get(i).getOriginalPath();
            }
            Bitmap bitmap = BitmapFactory.decodeFile(path).copy(Bitmap.Config.RGB_565, true);
            imageView.setImageBitmap(bitmap);
            linearLayout.addView(imageView);
        }
    }
}
