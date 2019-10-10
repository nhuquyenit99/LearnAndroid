package com.example.contacts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Contact> {
    private Context context;
    private int resource;
    private List<Contact> arrContact;

    public CustomAdapter(Context context, int resource, ArrayList<Contact> arrContact) {
        super(context, resource, arrContact);
        this.context = context;
        this.resource = resource;
        this.arrContact = arrContact;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_listview, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = convertView.findViewById(R.id.tvName);
            viewHolder.civAvatar = convertView.findViewById(R.id.civAvatar);
            viewHolder.imPhone = convertView.findViewById(R.id.imPhone);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Contact contact = arrContact.get(position);
        viewHolder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Edit.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("contact",contact);
                intent.putExtra("package",bundle);
                context.startActivity(intent);
            }
        });
        viewHolder.imPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = contact.phone;
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",phone,null));
                context.startActivity(intent);
            }
        });
        viewHolder.tvName.setText(contact.getName());
        //viewHolder.tvNumberPhone.setText(contact.getPhone());
        return convertView;
    }

    public class ViewHolder {
        TextView tvName;
        CircularImageView civAvatar;
        ImageButton imPhone;
    }
}