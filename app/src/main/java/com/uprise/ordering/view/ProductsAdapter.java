package com.uprise.ordering.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.uprise.ordering.R;
import com.uprise.ordering.model.ProductModel;

import java.util.ArrayList;

/**
 * Created by cicciolina on 10/22/16.
 */

public class ProductsAdapter extends BaseExpandableListAdapter {

    private LayoutInflater inflater;
    private ArrayList<ProductModel> mParent;
    private ExpandableListView accordion;
    public int lastExpandedGroupPosition;
    private Context context;
    private BrandsPagerAdapter.BrandsAdapterListener listener;
    private ViewPager viewPagerBrandList;

    public ProductsAdapter(Context context, ArrayList<ProductModel> parent, ExpandableListView accordion, BrandsPagerAdapter.BrandsAdapterListener listener) {
        this.mParent = parent;
        this.inflater = LayoutInflater.from(context);
        this.accordion = accordion;
        this.context = context;
        this.listener = listener;
    }

    @Override
    //counts the number of group/parent items so the list knows how many times calls getGroupView() method
    public int getGroupCount() {
        return mParent.size();
    }

    @Override
    //counts the number of children items so the list knows how many times calls getChildView() method
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    //gets the title of each parent/group
    public Object getGroup(int i) {
        return mParent.get(i).getName();
    }

    @Override
    //gets the name of each item
    public Object getChild(int i, int i1) {
        return mParent.get(i).getBrands().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.shop_now_list_item_products, viewGroup,false);
        }
        // set category name as tag so view can be found view later
        view.setTag(getGroup(i).toString());

        TextView textView = (TextView) view.findViewById(R.id.list_item_text_product);

        //"i" is the position of the parent/group in the list
        textView.setText(getGroup(i).toString());

//        TextView sub = (TextView) view.findViewById(R.id.list_item_text_subscriptions);

//        if(mParent.get(i).getSelections().size()>0) {
//            sub.setText(mParent.get(i).getSelections().toString());
//        }
//        else {
//            sub.setText("");
//        }

        //return the entire view
        return view;
    }

    @Override
    //in this method you must set the text to see the children on the list
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.shop_now_list_item_brands, viewGroup,false);
        }

        viewPagerBrandList = (ViewPager) view.findViewById(R.id.viewpager_brand_list);
        viewPagerBrandList.setAdapter(new BrandsPagerAdapter(context, mParent.get(i).getBrands(), listener));

        ImageButton leftNav = (ImageButton) view.findViewById(R.id.left_nav);
        ImageButton rightNav = (ImageButton) view.findViewById(R.id.right_nav);

// Images left navigation
        leftNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = viewPagerBrandList.getCurrentItem();
                if (tab > 0) {
                    tab--;
                    viewPagerBrandList.setCurrentItem(tab);
                } else if (tab == 0) {
                    viewPagerBrandList.setCurrentItem(tab);
                }
            }
        });

        // Images right navigatin
        rightNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = viewPagerBrandList.getCurrentItem();
                tab++;
                viewPagerBrandList.setCurrentItem(tab);
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    /**
     * automatically collapse last expanded group
     * @see http://stackoverflow.com/questions/4314777/programmatically-collapse-a-group-in-expandablelistview
     */
    public void onGroupExpanded(int groupPosition) {

        if(groupPosition != lastExpandedGroupPosition){
            accordion.collapseGroup(lastExpandedGroupPosition);
        }

        super.onGroupExpanded(groupPosition);

        lastExpandedGroupPosition = groupPosition;

    }
}
