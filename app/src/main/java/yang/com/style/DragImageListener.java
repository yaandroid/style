package yang.com.style;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by Admin on 27-07-2017.
 */
public class DragImageListener implements  View.OnTouchListener{

    private final int windowheight,windowwidth;
    private final Context context;
    ImageView imageView;
    private RelativeLayout.LayoutParams layoutParams1;

    DragImageListener (Context context,ImageView imageView , int windowwidth, int windowheight){
        this.context = context;
        this.imageView=imageView;
        this.windowheight=windowheight;
        this.windowwidth=windowwidth;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
//        Toast.makeText(context,"finger : "+ event.getPointerCount() +"d",Toast.LENGTH_SHORT).show();
        Log.v("sandeep kushwah","finger "+event.getPointerCount());
        layoutParams1 = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        switch(event.getActionMasked())
        {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int x_cord = (int) event.getRawX();
                int y_cord = (int) event.getRawY();
                if (x_cord > windowwidth) {
                    x_cord = windowwidth;
                }
                if (y_cord > windowheight) {
                    y_cord = windowheight;
                }
                layoutParams1.leftMargin = x_cord - 120;
                layoutParams1.topMargin = y_cord - 200;
                imageView.setLayoutParams(layoutParams1);
                break;
            default:
                break;
        }
        return true;

    }
}
