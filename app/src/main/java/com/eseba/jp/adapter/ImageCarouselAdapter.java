package com.eseba.jp.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.eseba.jp.R;
import com.eseba.jp.database.table.Banner;
import com.eseba.jp.util.NetworkUtil;
import com.eseba.jp.widget.MyTextView;

import java.util.List;

public class ImageCarouselAdapter extends PagerAdapter {
    private List<Banner> bannerList;
    private Context context;
    private LayoutInflater layoutInflater;

    public ImageCarouselAdapter(Context context, List<Banner> bannerList) {
        this.context = context;
        this.bannerList = bannerList;
    }

    @Override
    public int getCount() {
        return this.bannerList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        this.layoutInflater = (LayoutInflater) this.context.getSystemService(
            Context.LAYOUT_INFLATER_SERVICE);
        View itemView = this.layoutInflater.inflate(
            R.layout.layout_image_carousel,
            container,
            false
        );

        this.drawImage(
            (ImageView) itemView.findViewById(R.id.layout_image_carousel_image_view),
            this.bannerList.get(position)
        );

        MyTextView titleTextView = itemView.findViewById(R.id.layout_image_carousel_title);
        titleTextView.setText(this.bannerList.get(position).getTitle());

        MyTextView commentTextView = itemView.findViewById(R.id.layout_image_carousel_comment);
        commentTextView.setText(this.bannerList.get(position).getComment());

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    private void drawImage(ImageView imageView, Banner banner) {
        NetworkUtil.getPicasso(this.context).load(banner.getImageUrl()).into(imageView);
    }
}

