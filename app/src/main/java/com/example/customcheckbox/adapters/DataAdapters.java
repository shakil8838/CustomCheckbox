package com.example.customcheckbox.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customcheckbox.R;
import com.example.customcheckbox.dataproviders.AllDataProvider;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Anonymous on 1/30/2018.
 */
public class DataAdapters extends ArrayAdapter<AllDataProvider>{

    private CircleImageView circleImageView;
    private TextView fullName, activeId;
    private CheckedTextView checkedTextView;

    //comment...

    public DataAdapters(@NonNull Context context, List<AllDataProvider> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        AllDataProvider dataProvider = getItem(position);

        if (convertView ==  null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_view, parent, false);
        }

        circleImageView = (CircleImageView) convertView.findViewById(R.id.studentProfileImage);
        fullName = (TextView) convertView.findViewById(R.id.fullName);
        activeId = (TextView) convertView.findViewById(R.id.activeId);
        checkedTextView = (CheckedTextView) convertView.findViewById(R.id.checkboxImg);

        Picasso.with(getContext()).load(dataProvider.getImageUrl()).into(circleImageView);
        fullName.setText(dataProvider.getFullName());
        activeId.setText(dataProvider.getActiveId());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedTextView.toggle();
                checkedTextView.setCheckMarkDrawable(R.drawable.ic_check_box_black_24dp);
                Toast.makeText(getContext(), "Position"+position, Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}