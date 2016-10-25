package com.uprise.ordering.view;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uprise.ordering.R;
import com.uprise.ordering.model.BrandModel;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by cicciolina on 10/24/16.
 */

public class BrandsPagerAdapter extends PagerAdapter {

    private final List<BrandModel> web;
    private View view;
    private Context mContext;
    private final Resources resources;
    private LayoutInflater mLayoutInflater;
    private BrandsPagerAdapter.BrandsAdapterListener listener;

    public BrandsPagerAdapter(Context context, List<BrandModel> web, BrandsPagerAdapter.BrandsAdapterListener listener) {
        this.mContext = context;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listener = listener;
        this.resources = context.getResources();
        this.web = web;
    }

    @Override
    public int getCount() {
        return web.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (LinearLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.custom_brand_list, container, false);
//        RelativeLayout relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl_brand_image);
        TextView tvBrandName =(TextView) itemView.findViewById(R.id.tv_brand_name);
        tvBrandName.setText(web.get(position).getBrandName());
        TextView tvBrandPrice =(TextView) itemView.findViewById(R.id.tv_brand_price);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        tvBrandPrice.setText(decimalFormat.format(web.get(position).getPrice())+" Php");
//        ImageButton btnAddToCart = (ImageButton) itemView.findViewById(R.id.btn_add_to_cart);
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    public interface BrandsAdapterListener {
        void isBrandChecked(int position, BrandModel data);
        void quantityBrandOrdered(int count);

    }
}
