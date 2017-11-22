package yang.com.style;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    private FaceOverlayView mFaceOverlayView;
    private int windowwidth;
    private int windowheight;
    private TouchImageView tv1;
    private RelativeLayout.LayoutParams layoutParams2;
    private ImageView tv2;
    private RelativeLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFaceOverlayView = (FaceOverlayView) findViewById( R.id.face_overlay );
//        mainLayout = (RelativeLayout)findViewById(R.id.mainLayout);
        windowwidth = getWindowManager().getDefaultDisplay().getWidth();
        windowheight = getWindowManager().getDefaultDisplay().getHeight();
        DragImage();
        InputStream stream = getResources().openRawResource( R.raw.face );
        Bitmap bitmap = BitmapFactory.decodeStream(stream);

        mFaceOverlayView.setBitmap(bitmap);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                String poss= mFaceOverlayView.ShowEyeImage();
                String cxy[]=poss.split(" ");
                int x = Integer.parseInt(cxy[0]);
                int y = Integer.parseInt(cxy[1]);
                tv1.setX(x);
                tv1.setY(y);
                Toast.makeText(MainActivity.this,"sandeep "+x+" "+y,Toast.LENGTH_SHORT).show();
            }
        }, 300);

    }

    void DragImage() {


        tv1 = (TouchImageView)findViewById(R.id.image);
        tv2 = (ImageView)findViewById(R.id.image1);

//       tv2.setOnTouchListener(new DragImageListener(this,tv2,windowwidth,windowheight));

    }
}
