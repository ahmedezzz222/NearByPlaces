package com.cognitev.nearbyapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.cognitev.nearbyapp.Model.VenuesExploreModel.Item_;
import com.cognitev.nearbyapp.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;


public class ExploreListAdapter extends ArrayAdapter<Item_> {
	private LayoutInflater layoutInflater_;

	private static class ViewHolder {
		TextView tvName;
		TextView tvadress;
		ImageView image_place;
	}

	private Context ctx;

	public ExploreListAdapter(Context context, int layout, List<Item_> objects) {
		super(context, layout, objects);
		ctx = context;
		layoutInflater_ = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@NotNull
	@Override
	public View getView(int position, View convertView, @NotNull ViewGroup parent) {
		// Get the data item for this position
		Item_ item_ = getItem(position);

		// Check if an existing view is being reused, otherwise inflate the view
		ViewHolder viewHolder; // view lookup cache stored in tag
		if (convertView == null) {
			// If there's no view to re-use, inflate a brand new view for row
			viewHolder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.item_list, parent, false);

			viewHolder.tvName =   convertView.findViewById(R.id.tv_item_name);
			viewHolder.tvadress =  convertView.findViewById(R.id.tvadress);
			viewHolder.image_place =  convertView.findViewById(R.id.image_place);

			// Cache the viewHolder object inside the fresh view
			convertView.setTag(viewHolder);
		} else {
			// View is being recycled, retrieve the viewHolder object from tag
			viewHolder = (ViewHolder) convertView.getTag();
		}


		// Get each data from "item_"
        String name = null;
        if (item_ != null) {
            name = item_.getVenue().getName();
        }

        viewHolder.tvName.setText(name);
		String address="";
        if (item_ != null && item_.getVenue().getLocation().getAddress() != null) {
            address = item_.getVenue().getLocation().getAddress();

        }
        viewHolder.tvadress.setText(String.valueOf(address));

        String prefix = null;
        if (item_ != null) {
            prefix = item_.getVenue().getCategories().get(0).getIcon().getPrefix();
        }
        String suffix = null;
        if (item_ != null) {
            suffix = item_.getVenue().getCategories().get(0).getIcon().getSuffix();
        }

        Picasso.with(ctx).load(prefix + "88" + suffix).placeholder(R.mipmap.img_placeholder).into(viewHolder.image_place);

		// Return the completed view to render on screen
		return convertView;
	}
}