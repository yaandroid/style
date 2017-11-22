package yang.com.style.main;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import yang.com.style.R;
import yang.com.style.utils.ListClass;

/**
 * Created by Admin on 31-07-2017.
 */
public class StyleListAdapter extends RecyclerView.Adapter<StyleListAdapter.ViewHolder> {

    private ArrayList<Integer> list;
    private OnImageClick onImageClick;

    public  StyleListAdapter( ArrayList<Integer> list, OnImageClick onclick){
        this.onImageClick=onclick;
        this.list=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.listview_items, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

//        holder.Football_txt.set
        holder.mainicon.setImageResource(list.get(position));
        holder.mainicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onImageClick.onclickView(list,(position));
            }
        });

    }

    public interface OnImageClick {
       void onclickView(ArrayList<Integer> listName, Integer pos);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView Football_txt;
        private final ImageView mainicon;

        public ViewHolder(View itemView) {
            super(itemView);
            Football_txt = (TextView) itemView.findViewById(R.id.football_txt);
            mainicon= (ImageView)itemView.findViewById(R.id.icon);
        }
    }
}
