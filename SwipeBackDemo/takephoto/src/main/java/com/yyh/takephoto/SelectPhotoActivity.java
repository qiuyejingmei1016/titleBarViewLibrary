package com.yyh.takephoto;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoActivity;
import org.devio.takephoto.model.CropOptions;
import org.devio.takephoto.model.TResult;

import java.io.File;

public class SelectPhotoActivity extends TakePhotoActivity implements View.OnClickListener {

    private TakePhoto mTakePhoto;

    private RadioGroup mRadioGroupIsCrop;//是否裁剪
    private RadioGroup mRadioGroupCropTool;//裁剪工具
    private RadioGroup mRadioGroupCropSize;//裁剪尺寸/比例
    private EditText mCropWidthSize;//裁剪宽度
    private EditText mCropHeightSize;//裁剪高度

    private RadioGroup mRadioGroupIsUseOwnTool;//是否使用自带相册
    private EditText mPickMaxLimit;//最大选择张数
    private RadioGroup mRadioGroupSelectFrom;//选择方式(相册|文件)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_photo);
        initView();

        mTakePhoto = getTakePhoto();
    }

    private void initView() {
        this.findViewById(R.id.btnPickByTake).setOnClickListener(this);
        this.findViewById(R.id.btnPickBySelect).setOnClickListener(this);
        //图片裁剪相关
        this.mRadioGroupIsCrop = (RadioGroup) findViewById(R.id.rgCutCrop);
        this.mRadioGroupCropTool = (RadioGroup) findViewById(R.id.rgCropTool);
        this.mRadioGroupCropSize = (RadioGroup) findViewById(R.id.rgCropSize);
        this.mCropWidthSize = (EditText) findViewById(R.id.etCropWidth);
        this.mCropHeightSize = (EditText) findViewById(R.id.etCropHeight);

        //选择图片配置相关
        this.mRadioGroupIsUseOwnTool = (RadioGroup) findViewById(R.id.rgPickTool);
        this.mRadioGroupSelectFrom = (RadioGroup) findViewById(R.id.rgSelectFrom);
        this.mPickMaxLimit = (EditText) findViewById(R.id.etLimit);
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
        Log.e("===takeCancel", " takeCancel");
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        Log.e("===takeSuccess", result.toString());
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("images", result.getImages());
        startActivity(intent);
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        Log.e("===takeFail", msg);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btnPickByTake) {//拍照
            File file = new File(Environment.getExternalStorageDirectory(),
                    "/temp/" + System.currentTimeMillis() + ".jpg");
            if (!file.getParentFile().exists()) {
                boolean mkdirs = file.getParentFile().mkdirs();
                if (!mkdirs) {
                    return;
                }
            }
            startTakePhoto(Uri.fromFile(file));
        } else if (viewId == R.id.btnPickBySelect) {//选择照片
            File file = new File(Environment.getExternalStorageDirectory(),
                    "/temp/" + System.currentTimeMillis() + ".jpg");
            if (!file.getParentFile().exists()) {
                boolean mkdirs = file.getParentFile().mkdirs();
                if (!mkdirs) {
                    return;
                }
            }
            selectPhoto(Uri.fromFile(file));
        }
    }

    /**
     * 选择照片
     */
    private void selectPhoto(Uri imageSaveUri) {
        int limit = Integer.parseInt(mPickMaxLimit.getText().toString());//最多选择张数
        if (limit > 1) {//选择多种图片时会自动切换到TakePhoto自带相册
            //是否裁切
            if (mRadioGroupIsCrop.getCheckedRadioButtonId() == R.id.rbCropYes) {
                mTakePhoto.onPickMultipleWithCrop(limit, getCropImageOptions());
            } else {
                mTakePhoto.onPickMultiple(limit);
            }
            return;
        }
        if (mRadioGroupSelectFrom.getCheckedRadioButtonId() == R.id.rbFile) {//从文件选择
            //是否裁切
            if (mRadioGroupIsCrop.getCheckedRadioButtonId() == R.id.rbCropYes) {
                mTakePhoto.onPickFromDocumentsWithCrop(imageSaveUri, getCropImageOptions());
            } else {
                mTakePhoto.onPickFromDocuments();
            }
        } else {//从相册选择
            //是否裁切
            if (mRadioGroupIsCrop.getCheckedRadioButtonId() == R.id.rbCropYes) {
                mTakePhoto.onPickFromGalleryWithCrop(imageSaveUri, getCropImageOptions());
            } else {
                mTakePhoto.onPickFromGallery();//从相册中获取图片（不裁剪）
            }
        }
    }

    /**
     * 拍照
     */
    private void startTakePhoto(Uri imageSaveUri) {
        if (mRadioGroupIsCrop.getCheckedRadioButtonId() == R.id.rbCropYes) {//是否裁切
            mTakePhoto.onPickFromCaptureWithCrop(imageSaveUri, getCropImageOptions());
        } else {
            mTakePhoto.onPickFromCapture(imageSaveUri);//从相机获取图片(不裁剪)
        }
    }

    /**
     * 获取裁切设置
     */
    private CropOptions getCropImageOptions() {
        if (mRadioGroupIsCrop.getCheckedRadioButtonId() != R.id.rbCropYes) {
            return null;
        }
        int width = Integer.parseInt(mCropWidthSize.getText().toString());
        int height = Integer.parseInt(mCropHeightSize.getText().toString());

        //裁切工具 自带或者Luban
        boolean withOwnCrop;
        if (mRadioGroupCropTool.getCheckedRadioButtonId() == R.id.rbCropOwn) {
            withOwnCrop = true;//TakePhoto自带
        } else {
            withOwnCrop = false;//第三方
        }
        CropOptions.Builder builder = new CropOptions.Builder();
        //按尺寸还是比例进行裁剪
        if (mRadioGroupCropSize.getCheckedRadioButtonId() == R.id.rbAspect) {
            builder.setAspectX(width).setAspectY(height);
        } else {
            builder.setOutputX(width).setOutputY(height);
        }
        builder.setWithOwnCrop(withOwnCrop);
        return builder.create();
    }
}
