package com.example.customcheckbox.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customcheckbox.MainActivity;
import com.example.customcheckbox.R;
import com.example.customcheckbox.dataproviders.AllDataProvider;
import com.example.customcheckbox.interfaces.ItemClick;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Anonymous on 1/30/2018.
 */
public class DataAdapters extends RecyclerView.Adapter<DataAdapters.MyViewHolder> {

    private Context context;
    private List<AllDataProvider> dataProviderList;
    public List<String> stringList;

    public DataAdapters(Context context, List<AllDataProvider> dataProviderList) {
        this.context = context;
        this.dataProviderList = dataProviderList;
        stringList = new ArrayList<>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutView = layoutInflater.inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.fullName.setText(dataProviderList.get(position).getFullName());
        holder.uniqueId.setText(dataProviderList.get(position).getActiveId());
        Picasso.with(context)
                .load(dataProviderList.get(position).getImageUrl())
                .into(holder.profileImage);

        final AllDataProvider dataProvider = dataProviderList.get(position);
        if (dataProvider.isSelected()){
            holder.checkBox.setImageResource(R.drawable.ic_check_box_black_24dp);
        } else {
            holder.checkBox.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp);
        }

        holder.setItemClick(new ItemClick() {
            @Override
            public void onItemClick(View view, int position) {

                if (dataProvider.isSelected() && stringList.contains(dataProviderList.get(position).getFullName())){
                    dataProvider.setSelected(false);
                    stringList.remove(dataProviderList.get(position).getFullName());
                } else {
                    dataProvider.setSelected(true);
                    stringList.add(dataProviderList.get(position).getFullName());
                }

                //sending all data to dataProviderList and updating adapter
                dataProviderList.set(position, dataProvider);
                upDateAdapter(dataProviderList);

                //calling setupActionBar to show the number of selected items
                MainActivity mMainActivity = new MainActivity();
                mMainActivity.setupActionBar(stringList.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataProviderList.size();
    }

    public void upDateAdapter(List<AllDataProvider> dataProviderList){
        this.dataProviderList = dataProviderList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ItemClick itemClick;
        TextView fullName, uniqueId;
        ImageView checkBox;
        CircleImageView profileImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            fullName = (TextView) itemView.findViewById(R.id.fullName);
            uniqueId = (TextView) itemView.findViewById(R.id.activeId);
            checkBox = (ImageView) itemView.findViewById(R.id.checkboxImg);
            profileImage = (CircleImageView) itemView.findViewById(R.id.studentProfileImage);
        }

        public void setItemClick(ItemClick itemClick){
            this.itemClick = itemClick;
        }

        @Override
        public void onClick(View v) {
            itemClick.onItemClick(v, getAdapterPosition());
        }
    }
}