package yang.com.style.main;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.io.ByteArrayOutputStream;

import yang.com.style.R;

public class PickImageActivity extends AppCompatActivity {


    private static final String IMAGE_DIRECTORY = "/Style";
    private int GALLERY = 1, CAMERA = 2;
    private Button cameraBtn;
    private Button galleryBtn;

    private static final int REQUEST_WRITE_STORAGE = 112;
    private AdView mAdView;

    final int CROP_PIC_REQUEST_CODE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_image);

        galleryBtn=(Button)findViewById(R.id.gallery);
        cameraBtn=(Button)findViewById(R.id.camera);
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission("camera");

            }
        });
        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdScreen ads= new AdScreen();
                ads.AdsCreation(PickImageActivity.this);
                checkPermission("Gallary");
//                choosePhotoFromGallary();
            }
        });
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }


    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
//                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
//                    String path = saveImage(bitmap);
//                    imageview.setImageBitmap(bitmap);
                    Intent intent = new Intent(PickImageActivity.this, MainActivity.class);
                    intent.putExtra("imageUri",getRealPathFromURI(contentURI));
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else if (requestCode == CAMERA) {
//            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
//            Uri uri = (Uri) data.getExtras().get("data");
//            imageview.setImageBitmap(thumbnail);
            Uri duri = data.getData();
//            String path =  saveImage(thumbnail);
            Intent intent = new Intent(PickImageActivity.this, MainActivity.class);
            intent.putExtra("imageUri",getRealPathFromURI(duri));
            startActivity(intent);

        }

    }


    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }



    public void checkPermission(String IsCamera){
        boolean hasPermissionStorage = (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED );
        boolean hasPermissionCamera = (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED );

        if (!hasPermissionStorage && !hasPermissionCamera) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},
                    REQUEST_WRITE_STORAGE);
        }else{
            if (IsCamera.equalsIgnoreCase("camera")){
                takePhotoFromCamera();

//                startActivity(new Intent(PickImageActivity.this, CameraActivity.class));

            }else{
                choosePhotoFromGallary();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_WRITE_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    choosePhotoFromGallary();
                    //reload my activity with permission granted or use the features what required the permission
                } else
                {
//                    Toast.makeText(this, "The app was not allowed to write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }
            }
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
        googleTracker("Home page");

    }

    private void googleTracker(String name) {
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
        Tracker trackerId = analytics.newTracker("UA-105139449-1"); // UA-73840163-1  other
        trackerId.setScreenName("Activity~ " + name);
        trackerId.send(new HitBuilders.ScreenViewBuilder().build());
        GoogleAnalytics.getInstance(this).reportActivityStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
    }
}
