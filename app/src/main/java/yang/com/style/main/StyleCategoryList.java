package yang.com.style.main;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import yang.com.style.R;

/**
 * Created by Admin on 01-08-2017.
 */
public class StyleCategoryList extends RecyclerView.Adapter<StyleCategoryList.ViewHolder> {

    private final OnCategoryClick onImageClick;
    private final ArrayList<ImageName> list;

    public  StyleCategoryList(ArrayList<ImageName> list, OnCategoryClick onclick){
        this.onImageClick=onclick;
        this.list=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.listview_items, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mainicon.setImageResource(list.get(position).getImagePath());
        holder.Football_txt.setText(list.get(position).getName());
        holder.mainicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onImageClick.onclickView(list.get(position).getName());
                holder.background_layout_icon.setTag(null);
                holder.background_layout_icon.setTag(position);
                notifyDataSetChanged();
            }
        });

        if (holder.background_layout_icon.getTag()!=null){

            int color_pos = (int) holder.background_layout_icon.getTag();
            if (position == color_pos){
                holder.background_layout_icon.setBackgroundColor(Color.parseColor("#f3f3f3"));
            }else{
                holder.background_layout_icon.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }

    }
    public interface OnCategoryClick {
        void onclickView(String pos);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView Football_txt;
        private final ImageView mainicon;
        private final RelativeLayout background_layout_icon;

        public ViewHolder(View itemView) {
            super(itemView);
            Football_txt = (TextView) itemView.findViewById(R.id.football_txt);
            background_layout_icon=(RelativeLayout)itemView.findViewById(R.id.background_layout_icon);
            mainicon= (ImageView)itemView.findViewById(R.id.icon);
        }
    }
}
