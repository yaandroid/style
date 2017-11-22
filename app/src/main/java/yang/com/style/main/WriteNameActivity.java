package yang.com.style.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import yang.com.style.R;

public class WriteNameActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edit_name_grph;
    private TextView text_view_edit_name;
    private Button DoneBtn;
    private String textName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_name);
        initView();
    }

    private void initView() {

        edit_name_grph=(EditText)findViewById(R.id.edit_name_grph);
        text_view_edit_name=(TextView)findViewById(R.id.text_view_edit_name);
        DoneBtn=(Button)findViewById(R.id.done);
        DoneBtn.setOnClickListener(this);

        textName=getIntent().getExtras().getString("EditText_name");
        edit_name_grph.setText(textName.trim());
    }

    @Override
    public void onClick(View view) {
            if (view.getId()==R.id.done){
//                edit_name_grph.getText().toString();
                Intent data = new Intent();
                data.putExtra("EditText_name",edit_name_grph.getText().toString());
                setResult(22,data);
                finish();
            }
    }

    @Override
    protected void onResume() {
        super.onResume();
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
        Tracker trackerId = analytics.newTracker("UA-105139449-1"); // UA-73840163-1  other
//        trackerId.setAppName("ecom_test");
//        trackerId.setScreenName("sandeep");
        trackerId.setScreenName("Activity~ " + "Write status Screen");
        trackerId.send(new HitBuilders.ScreenViewBuilder().build());
        GoogleAnalytics.getInstance(this).reportActivityStart(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
    }
}
