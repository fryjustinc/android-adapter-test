package com.example.fryjc.learn;

import android.app.Activity;
import android.content.Intent;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by fryjc on 5/28/2015.
 */
public class PictureFragment extends Fragment
{
    ImageView mImageView;
    Bitmap imageBitmap;
    private View.OnClickListener mOnClickListener =  new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent picMessageIntent = new Intent(Intent.ACTION_SEND);
            picMessageIntent.setType("image/bmp");
            picMessageIntent.putExtra(Intent.EXTRA_STREAM, imageBitmap);
            startActivity(picMessageIntent);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 1);
        }
        Button clickButton = (Button) getActivity().findViewById(R.id.btnSendImage);
        clickButton.setOnClickListener(mOnClickListener);
    }
    public View onCreateView(LayoutInflater inf, ViewGroup parent, Bundle savedInstanceState) {
        View v =  inf.inflate(R.layout.picture_fragment, parent, false);

        return v;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        mImageView = (ImageView) getActivity().findViewById(R.id.imageView1);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }
}
