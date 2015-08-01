package com.apps.b3bytes.homefoods.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.apps.b3bytes.homefoods.R;
import com.apps.b3bytes.homefoods.models.OneDishOrder;

import java.util.List;

public class ChefMenuGridViewAdapter extends ArrayAdapter<OneDishOrder> {

    private final List<OneDishOrder> list;
    private final Activity context;
    private final GridView gvChefMenu;

    public ChefMenuGridViewAdapter(Activity context, List<OneDishOrder> list, GridView gvChefMenu) {
        super(context, R.layout.chef_deliver_dish_item, list);
        this.context = context;
        this.list = list;
        this.gvChefMenu = gvChefMenu;
    }

    static class ViewHolder {
        protected TextView tvMenuGridDishName;
        protected TextView tvMenuGridDishQuantity;
        protected TextView tvMenuGridDishPrice;
        protected ImageView ivMenuGridDishImage;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = null;

        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.chef_menu_grid_item, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.tvMenuGridDishName = (TextView) view.findViewById(R.id.tvMenuGridDishName);
            viewHolder.tvMenuGridDishQuantity = (TextView) view.findViewById(R.id.tvMenuGridDishQuantity);
            viewHolder.tvMenuGridDishPrice = (TextView) view.findViewById(R.id.tvMenuGridDishPrice);
            viewHolder.ivMenuGridDishImage = (ImageView) view.findViewById(R.id.ivMenuGridDishImage);

            view.setTag(viewHolder);
        } else {
            view = convertView;
        }

        ViewHolder holder = (ViewHolder) view.getTag();

        holder.tvMenuGridDishName.setText(list.get(position).getDishName());
        holder.tvMenuGridDishQuantity.setText("" + list.get(position).getQuantity());
        holder.tvMenuGridDishPrice.setText(context.getString(R.string.Rs) + " " + (list.get(position).getUnitPrice()));
        holder.ivMenuGridDishImage.setImageResource(R.drawable.south_indian_breakfast_01);

/*        if (position % 2 == 1) {
            view.setBackgroundColor(Color.BLUE);
        } else {
            view.setBackgroundColor(Color.CYAN);
        }*/

        return view;
    }

}


