package yang.com.style.main;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.zomato.photofilters.SampleFilters;
import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.imageprocessors.subfilters.VignetteSubfilter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import yang.com.style.R;
import yang.com.style.utils.ListClass;
import yang.com.style.view.BubbleInputDialog;
import yang.com.style.view.BubbleTextView;
import yang.com.style.view.StickerView;

public class MainActivity extends AppCompatActivity {

    private BubbleInputDialog mBubbleInputDialog;

    private StickerView mCurrentView;

    private BubbleTextView mCurrentEditTextView;

    private ArrayList<View> mViews;

    private RelativeLayout mContentRootView;

    private View mAddSticker;
    private static final String IMAGE_DIRECTORY = "/Style";
    private View mAddBubble;
    private RecyclerView CategoryList;
    private RecyclerView SubcategoryList;
    private boolean IsSubCategoryOpen=false;
    private Uri ImageUri;
    private ImageView userimage;
    private AdView mAdView;
    private ImageView backBtn,whatsapp_btn,shareBtn;
    public LinearLayout lis_layuot;
    private String FromAct;
    private ImageView Save_btn;
    private ImageView edit_btn;
    private String NameTxt="";
    private String AppStore_link="https://play.google.com/store/apps/details?id=yang.com.style";
    private Uri SharingUri;
    private ProgressDialog progressDialog;

    private static final float BLUR_RADIUS = 25f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
        mContentRootView = (RelativeLayout) findViewById(R.id.mainlayout);

        CategoryList=(RecyclerView)findViewById(R.id.categoryList);
        SubcategoryList=(RecyclerView)findViewById(R.id.subcategoryList);
         lis_layuot = (LinearLayout) findViewById(R.id.lis_layuot);

        userimage=(ImageView)findViewById(R.id.userimage);
        shareBtn=(ImageView)findViewById(R.id.share);
        Save_btn=(ImageView)findViewById(R.id.Save_btn);
        backBtn=(ImageView)findViewById(R.id.back);
        edit_btn=(ImageView)findViewById(R.id.edit_btn);
        whatsapp_btn=(ImageView)findViewById(R.id.whatsapp_btn);
        mAdView = (AdView) findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

        System.loadLibrary("NativeImageProcessor");

        if (getIntent().getExtras()!=null){
            String imagestring = getIntent().getStringExtra("imageUri");
            FromAct= getIntent().getStringExtra("FromAct");
            ImageUri = Uri.parse(imagestring);

        }

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userimage.setRotation(userimage.getRotation()+90);
            }
        });

        Save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                File fileName = new  File(String.valueOf(ImageUri));
                lis_layuot.setVisibility(View.GONE);
                mCurrentView.setInEdit(false);
                saveFrameLayout(mContentRootView,Create_Path_Name());
                googleTracker("Home page : save button clicked");
                AdScreen ads= new AdScreen();
                ads.AdsCreation(MainActivity.this);

            }
        });


        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,WriteNameActivity.class);
                i.putExtra("EditText_name",NameTxt);
                startActivityForResult(i,22);
                googleTracker("Home page Edit status clicked");

            }
        });

        whatsapp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lis_layuot.setVisibility(View.GONE);
                try {
                    mCurrentView.setInEdit(false);
                }catch (Exception d){}
//                shareOnWhatsApp();
                new SaveImageAsync().execute("whatsapp");

                AdScreen ads= new AdScreen();
                ads.AdsCreation(MainActivity.this);
            }
        });

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lis_layuot.setVisibility(View.GONE);
                try {
                    mCurrentView.setInEdit(false);
                }catch (Exception d){};
//                File fileName = new  File(String.valueOf(ImageUri));
                new SaveImageAsync().execute("all_share");
                AdScreen ads= new AdScreen();
                ads.AdsCreation(MainActivity.this);
            }
        });
        try {
            File imgFile = new  File(String.valueOf(ImageUri));
            if(imgFile.exists()){

                userimage.setImageURI(ImageUri);

                BitmapDrawable background = new BitmapDrawable(new BlurBuilder().blur(MainActivity.this,UriToBitmap(ImageUri)));
                mContentRootView.setBackground(background);

                Filter myFilter = new Filter();
                myFilter.addSubFilter(new VignetteSubfilter(this, 100));
                Bitmap outputImage = myFilter.processFilter(UriToBitmap(ImageUri));
//                mContentRootView.setBackground(bitmapDrawable);
//                if (FromAct.equalsIgnoreCase("camera")){
                    userimage.setRotation(270);
//                }
//                userimage.setScaleType(ImageView.ScaleType.FIT_XY);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        mContentRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mCurrentView!=null ) {
                    mCurrentView.setInEdit(false);
                    SubcategoryList.setVisibility(View.INVISIBLE);
                }
            }
        });



        mViews = new ArrayList<>();
        mBubbleInputDialog = new BubbleInputDialog(this);
        mBubbleInputDialog.setCompleteCallBack(new BubbleInputDialog.CompleteCallBack() {
            @Override
            public void onComplete(View bubbleTextView, String str) {
                ((BubbleTextView) bubbleTextView).setText(str);
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        CategoryList.setItemAnimator(new DefaultItemAnimator());
            CategoryList.setLayoutManager(layoutManager);

        CategoryList.setNestedScrollingEnabled(false);
        SubcategoryList.setNestedScrollingEnabled(false);
        CategoryList.setAdapter(new StyleCategoryList(ListClass.CategoryList(), new StyleCategoryList.OnCategoryClick() {
            @Override
            public void onclickView(String pos) {

                if (pos.equalsIgnoreCase("Glasses")){
                    SetSubCategory("Glasses",ListClass.GlassesLists());
                    googleTracker("Home page Category Glasses");
//                    addStickerView(ListClass.GlassesLists(),0);
                }else if (pos.equalsIgnoreCase("Fun")){
                    SetSubCategory("Fun",ListClass.FunLists());
                    googleTracker("Home page Category Fun");
                }else if (pos.equalsIgnoreCase("Hairs")){
                    SetSubCategory("Hairs",ListClass.hairList());
                    googleTracker("Home page Category Hairs");
                }else if (pos.equalsIgnoreCase("Beards")){
                    SetSubCategory("Beards",ListClass.BeardList());
                    googleTracker("Home page Category Beards");
                }else if (pos.equalsIgnoreCase("Hats")){
                    SetSubCategory("Hats",ListClass.HatLists());
                    googleTracker("Home page Category Hats");
                }
//                CategoryList.setVisibility(View.INVISIBLE);
                SubcategoryList.setVisibility(View.VISIBLE);
            }
        }));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (data!=null){
            if (resultCode == 22) {
                NameTxt= data.getExtras().getString("EditText_name");
                addStickerView(null,null,textAsBitmap(NameTxt,50, Color.CYAN),true);
            }
        }
    }

    public Bitmap textAsBitmap(String text, float textSize, int textColor) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.5f); // round
        int height = (int) (baseline + paint.descent() + 0.5f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);
        return image;
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    private void addStickerView(ArrayList<Integer> listName, Integer image,Bitmap bitmap,Boolean textClicked) {

        final StickerView stickerView = new StickerView(this);
        if (textClicked){
            stickerView.setImageBitmap(bitmap);
        }else {
            stickerView.setImageResource(listName.get(image));
        }
//        stickerView.setImageDrawable(getResources().getDrawable(listName.get(image)));
        stickerView.setY(10);
        stickerView.setX(10);

        stickerView.setOperationListener(new StickerView.OperationListener() {
            @Override
            public void onDeleteClick() {
                mViews.remove(stickerView);
                mContentRootView.removeView(stickerView);
            }

            @Override
            public void onEdit(StickerView stickerView) {
                if (mCurrentEditTextView != null) {
                    mCurrentEditTextView.setInEdit(false);
                }
                mCurrentView.setInEdit(false);
                mCurrentView = stickerView;
                mCurrentView.setInEdit(true);
            }

            @Override
            public void onTop(StickerView stickerView) {
                int position = mViews.indexOf(stickerView);
                if (position == mViews.size() - 1) {
                    return;
                }
                StickerView stickerTemp = (StickerView) mViews.remove(position);
                mViews.add(mViews.size(), stickerTemp);
            }
        });
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mContentRootView.addView(stickerView, lp);
        mViews.add(stickerView);
        setCurrentEdit(stickerView);
    }


    /**
     */
    private void setCurrentEdit(StickerView stickerView) {
        if (mCurrentView != null) {
            mCurrentView.setInEdit(false);
        }
        if (mCurrentEditTextView != null) {
            mCurrentEditTextView.setInEdit(false);
        }
        mCurrentView = stickerView;
        stickerView.setInEdit(true);
    }

    /**
     */
    private void setCurrentEdit(BubbleTextView bubbleTextView) {
        if (mCurrentView != null) {
            mCurrentView.setInEdit(false);
        }
        if (mCurrentEditTextView != null) {
            mCurrentEditTextView.setInEdit(false);
        }
        mCurrentEditTextView = bubbleTextView;
        mCurrentEditTextView.setInEdit(true);
    }

    public String loadJSONFromAsset(String listname) {
        String json = null;
        try {
            InputStream is = MainActivity.this.getAssets().open(listname+".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    public void SetSubCategory(final String ListName , final ArrayList<Integer> list){
            if (SubcategoryList!=null){
                try {
                    SubcategoryList.removeAllViews();
                }catch (Exception d){}
            }
            IsSubCategoryOpen=true;
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        SubcategoryList.setLayoutManager(layoutManager);
        SubcategoryList.setAdapter(new StyleListAdapter(list, new StyleListAdapter.OnImageClick() {
            @Override
            public void onclickView(ArrayList<Integer> listName, Integer pos) {
                addStickerView(listName,pos,null,false);
                googleTracker("Home page "+ListName.toString()+" Category "+list.get(pos).toString());

            }
        }));

    }

    @Override
    public void onBackPressed() {
        if (IsSubCategoryOpen){
            IsSubCategoryOpen=false;
            CategoryList.setVisibility(View.VISIBLE);
            SubcategoryList.setVisibility(View.INVISIBLE);
        }else{
            super.onBackPressed();
        }

    }
    public  void saveFrameLayout(RelativeLayout frameLayout, String path) {
        frameLayout.setDrawingCacheEnabled(true);
        frameLayout.buildDrawingCache();
        Bitmap cache = frameLayout.getDrawingCache();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            cache.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            frameLayout.destroyDrawingCache();
            lis_layuot.setVisibility(View.VISIBLE);
        }
    }

    public Bitmap UriToBitmap(Uri uri){
        File imgFile = new  File(String.valueOf(uri));

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(),bmOptions);
        return (Bitmap.createScaledBitmap(bitmap,400,400,true));
    }
        public  Bitmap ReScaleBitmap(Bitmap originalImage)
        {
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            Bitmap bmp = Bitmap.createScaledBitmap(originalImage,size.x,size.y,true);

//            Bitmap output = Bitmap.createBitmap(wantedWidth, wantedHeight, Bitmap.Config.ARGB_8888);
//            Canvas canvas = new Canvas(output);
//            Matrix m = new Matrix();
//            m.setScale((float)wantedWidth / originalImage.getWidth(), (float)wantedHeight / originalImage.getHeight());
//            canvas.drawBitmap(originalImage, m, new Paint());
            return bmp;
        }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 0, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    public String Create_Path_Name(){
        File filename=null;
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
             filename = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
        }catch (Exception d){}
        return filename.toString();
    }

    @Override
    protected void onResume() {
        super.onResume();
       googleTracker("Home page");

    }

    private void googleTracker(String name) {
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
        Tracker trackerId = analytics.newTracker("UA-105139449-1"); // UA-73840163-1  other
        trackerId.setScreenName("Activity " + name);
        trackerId.send(new HitBuilders.ScreenViewBuilder().build());
        GoogleAnalytics.getInstance(this).reportActivityStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
    }

    public void shareOnWhatsApp(){
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setPackage("com.whatsapp");
        share.setType("image/jpeg");
        share.putExtra(Intent.EXTRA_STREAM,SharingUri );
        share.putExtra(Intent.EXTRA_TEXT, "Download your Style App and Create your Style. \n" + AppStore_link);
        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try{
            startActivity(share);
//            startActivity(Intent.createChooser(share, "Share Your Style"));
        }catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,"Whatsapp have not been installed.",Toast.LENGTH_LONG).show();
        }
    }

    public class SaveImageAsync extends AsyncTask<String,Void,Void>{
        private String Fromwhere="";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(MainActivity.this, "Creating Image", "Wait a moment please", true, false);
        }

        @Override
        protected Void doInBackground(String... value) {
            Fromwhere =value[0];
            SharingUri = getLocalBitmapUri(mContentRootView);
//             SharingUri = Uri.parse(Create_Path_Name());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.cancel();

            if (Fromwhere.equalsIgnoreCase("whatsapp")){
                shareOnWhatsApp();
            }else{
                AllShareMethod();
            }
            lis_layuot.setVisibility(View.VISIBLE);
        }


    }
    public Uri getLocalBitmapUri(RelativeLayout imageView) {
        imageView.buildDrawingCache();
        Bitmap bm = imageView.getDrawingCache();

        OutputStream fOut = null;
        Uri outputFileUri = null;
        try {
            File root = new File(Environment.getExternalStorageDirectory()
                    + File.separator + "folder_name" + File.separator);
            root.mkdirs();
            File imageFile = new File(root, System.currentTimeMillis()+"YourStyle.jpg");
            outputFileUri = Uri.fromFile(imageFile);
            fOut = new FileOutputStream(imageFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            bm.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            return outputFileUri;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void AllShareMethod(){
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("image/jpeg");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"Share Your Style"});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "File Name");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Download your Style App and Create your Style." + AppStore_link);
        emailIntent.putExtra(Intent.EXTRA_STREAM,SharingUri);
        startActivity(Intent.createChooser(emailIntent, "Share Your Style"));
        googleTracker("Home page share btn clicked");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public class BlurBuilder {
        private static final float BITMAP_SCALE = 0.4f;
        private static final float BLUR_RADIUS = 7.5f;

        public  Bitmap blur(Context context, Bitmap image) {
            int width = Math.round(image.getWidth() * BITMAP_SCALE);
            int height = Math.round(image.getHeight() * BITMAP_SCALE);

            Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
            Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

            RenderScript rs = RenderScript.create(context);
            ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
            Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
            theIntrinsic.setRadius(BLUR_RADIUS);
            theIntrinsic.setInput(tmpIn);
            theIntrinsic.forEach(tmpOut);
            tmpOut.copyTo(outputBitmap);

            return outputBitmap;
        }
    }


}
