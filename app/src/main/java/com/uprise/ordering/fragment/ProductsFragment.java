package com.uprise.ordering.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.uprise.ordering.R;
import com.uprise.ordering.model.BrandModel;
import com.uprise.ordering.model.ProductModel;
import com.uprise.ordering.view.BrandsPagerAdapter;
import com.uprise.ordering.view.ProductsAdapter;

import java.util.ArrayList;

/**
 * Created by cicciolina on 10/22/16.
 */

public class ProductsFragment extends Fragment implements BrandsPagerAdapter.BrandsAdapterListener /**implements ExpandableListView.OnChildClickListener**/ {

    private View fragmentView;
    private ProductsAdapter productsAdapter;
    private ExpandableListView expandableListView;
    private ArrayList<ProductModel> productModels;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.layout_shop_now, container, false);
        expandableListView = (ExpandableListView) fragmentView.findViewById(R.id.el_shop_now_products);
        productModels = generateProductModels();
        productsAdapter = new ProductsAdapter(getContext(), productModels, expandableListView, this);
        expandableListView.setAdapter(productsAdapter);
//        expandableListView.setOnChildClickListener(this);
        return fragmentView;
    }

    // generate some random amount of child objects (1..10)
    private ArrayList<ProductModel> generateProductModels() {
        ArrayList<ProductModel> productModels = new ArrayList<>();
        for(int i=0; i < 11; i++) {
            ProductModel productModel = new ProductModel();
            productModel.setName("Product "+i);
            productModel.setBrands(generateBrands());
            productModels.add(productModel);
        }

        return productModels;
    }

    private ArrayList<BrandModel> generateBrands() {
        ArrayList<BrandModel> brands = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            BrandModel brandModel = new BrandModel();
            brandModel.setBrandName("Brand " + i);
            brandModel.setPrice(10d + brandModel.getPrice() + i);
            brands.add(brandModel);
        }
        return brands;
    }

    @Override
    public void isBrandChecked(int position, BrandModel data) {

    }

    @Override
    public void quantityBrandOrdered(int count) {

    }

//    @Override
//    public boolean onChildClick(ExpandableListView parent, View v,
//                                int groupPosition, int childPosition, long id) {
//        CheckedTextView checkbox = (CheckedTextView)v.findViewById(R.id.list_item_text_child);
//        checkbox.toggle();
//
//
//        // find parent view by tag
//        View parentView = expandableListView.findViewWithTag(productModels.get(groupPosition).getName());
//        if(parentView != null) {
//            TextView sub = (TextView)parentView.findViewById(R.id.list_item_text_subscriptions);
//
//            if(sub != null) {
//                ProductModel productModel = productModels.get(groupPosition);
//                if(checkbox.isChecked()) {
//                    // add child category to parent's selection list
//                    productModel.getSelections().add(checkbox.getText().toString());
//
//                    // sort list in alphabetical order
//                    Collections.sort(productModel.getSelections(), new CustomComparator());
//                }
//                else {
//                    // remove child category from parent's selection list
//                    productModel.getSelections().remove(checkbox.getText().toString());
//                }
//
//                // display selection list
//                sub.setText(productModel.getSelections().toString());
//            }
//        }
//        return true;
//    }
}
