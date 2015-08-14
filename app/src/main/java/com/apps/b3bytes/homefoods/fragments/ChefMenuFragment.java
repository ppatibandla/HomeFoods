package com.apps.b3bytes.homefoods.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.apps.b3bytes.homefoods.R;
import com.apps.b3bytes.homefoods.adapters.ChefMenuGridViewAdapter;
import com.apps.b3bytes.homefoods.models.OneDishOrder;

import java.util.ArrayList;
import java.util.List;

public class ChefMenuFragment extends Fragment {
    private FragmentActivity mContext;
    GridView gvChefMenu;

    /* TODO: TEST DATA */
    String[] dishNamesArray = {"Roti Paratha", "Curd Rice", "South Indian Breakfast", "Salad", "Chicken Tikka", "Biryani", "Pizza", "Cupcakes", "Sandwhich", "Burger", "PanCake"};
    int[] dishQuantitiesArray = {2, 1, 3, 1, 2, 4, 1, 12, 3, 4, 10};
    double[] dishUnitPriceArray = {75, 120, 175, 90, 125, 150, 250, 25, 75, 80, 40};
    /* TODO: END TEST DATA */

    public ChefMenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_chef_menu, container, false);
        gvChefMenu = (GridView) rootView.findViewById(R.id.gvChefMenu);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        mContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<OneDishOrder> list = new ArrayList<OneDishOrder>();
        int numDishes = dishNamesArray.length;
        for (int i = 0; i < numDishes; i++) {
            list.add(new OneDishOrder(dishNamesArray[i], dishQuantitiesArray[i], dishUnitPriceArray[i]));
        }

        ArrayAdapter<OneDishOrder> aOneDishOrder = new ChefMenuGridViewAdapter(mContext, list, gvChefMenu);
        gvChefMenu.setAdapter(aOneDishOrder);

        aOneDishOrder.notifyDataSetChanged();
    }
}
