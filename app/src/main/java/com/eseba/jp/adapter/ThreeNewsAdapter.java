package com.eseba.jp.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.eseba.jp.Config;
import com.eseba.jp.R;
import com.eseba.jp.bus.data.RowNewsOnClick;
import com.eseba.jp.bus.listener.OnRowNewsClickListener;
import com.eseba.jp.database.table.Banner;
import com.eseba.jp.database.table.News;
import com.eseba.jp.database.table.ThreeNews;
import com.eseba.jp.util.NetworkUtil;
import com.eseba.jp.widget.MyTextView;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by danielnguyen on 8/23/17.
 */

public class ThreeNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = ThreeNewsAdapter.class.getSimpleName();

    private static final int CAROUSEL_TYPE = 0;
    private static final int THREE_NEWS_TYPE = 1;

    private Activity activity;
    private List<ThreeNews> threeNewsList;
    private int pagePosition;
    private List<Banner> bannerList;
    private OnRowNewsClickListener listener;

    public ThreeNewsAdapter(Activity activity,
                            List<Banner> bannerList,
                            List<ThreeNews> threeNewsList,
                            int pagePosition,
                            OnRowNewsClickListener listener) {
        this.activity = activity;
        this.bannerList = bannerList;
        this.threeNewsList = threeNewsList;
        this.pagePosition = pagePosition;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view;
        if (viewType == THREE_NEWS_TYPE) {
            view = LayoutInflater.from(context).inflate(R.layout.row_three_news, parent, false);
            return new ThreeNewsViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.row_carousel, parent, false);
            return new CarouselViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        int viewType = this.getItemViewType(position);
        if (viewType == THREE_NEWS_TYPE) {
            ThreeNewsViewHolder threeNewsViewHolder = (ThreeNewsViewHolder) viewHolder;
            ThreeNews threeNews = this.threeNewsList.get(position - 1);
            threeNewsViewHolder.setup(threeNews);
        }
    }

    @Override
    public int getItemCount() {
        return this.threeNewsList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return CAROUSEL_TYPE;
        }
        return THREE_NEWS_TYPE;
    }

    public class ThreeNewsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.row_three_news_group_title)
        protected MyTextView groupTitleTextView;
        @BindViews({R.id.row_three_news_layout_1, R.id.row_three_news_layout_2, R.id.row_three_news_layout_3})
        protected List<RelativeLayout> layoutList;
        @BindViews({R.id.row_three_news_avatar_1, R.id.row_three_news_avatar_2, R.id.row_three_news_avatar_3})
        protected List<ImageView> avatarImageViewList;
        @BindViews({R.id.row_three_news_title_1, R.id.row_three_news_title_2, R.id.row_three_news_title_3})
        protected List<MyTextView> titleTextViewList;
        @BindViews({R.id.row_three_news_date_1, R.id.row_three_news_date_2, R.id.row_three_news_date_3})
        protected List<MyTextView> dateTextViewList;

        private ThreeNews threeNews;

        public ThreeNewsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.row_three_news_layout_1)
        protected void rowLayout1OnClick() {
            this.newsLayoutOnClick(this.threeNews.getNewsList()[0]);
        }

        @OnClick(R.id.row_three_news_layout_2)
        protected void rowLayout2OnClick() {
            this.newsLayoutOnClick(this.threeNews.getNewsList()[1]);
        }

        @OnClick(R.id.row_three_news_layout_3)
        protected void rowLayout3OnClick() {
            this.newsLayoutOnClick(this.threeNews.getNewsList()[2]);
        }

        public void setup(ThreeNews threeNews) {
            this.threeNews = threeNews;
            this.drawComponentViews();
        }

        private void drawComponentViews() {
            this.groupTitleTextView.setText(this.threeNews.getNewsTypeName());
            for (int i = 0; i < this.threeNews.getNewsList().length; i++) {
                News news = this.threeNews.getNewsList()[i];
                this.drawNewsContent(news, i);
            }
        }

        private void drawNewsContent(News news, int position) {
            if (news != null) {
                this.layoutList.get(position).setVisibility(View.VISIBLE);
                this.titleTextViewList.get(position).setText(news.getTitle());
                this.dateTextViewList.get(position).setText(news.getDate());
                NetworkUtil.getPicasso(activity)
                    .load(news.getImageUrl())
                    .into(this.avatarImageViewList.get(position));
            } else {
                this.layoutList.get(position).setVisibility(View.GONE);
            }
        }

        private void newsLayoutOnClick(News news) {
            if (listener != null && news != null) {
                listener.rowNewsOnClick(new RowNewsOnClick(news, pagePosition));
            }
        }
    }

    public class CarouselViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.row_carousel_view_pager)
        protected ViewPager imagesViewPager;
        @BindView(R.id.row_carousel_indicator)
        protected CirclePageIndicator imagesIndicator;

        public CarouselViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.drawImagesViewPager();
        }

        private void drawImagesViewPager() {
            this.setDimensionsForViewPager();
            ImageCarouselAdapter adapter = new ImageCarouselAdapter(activity, bannerList);
            this.imagesViewPager.setAdapter(adapter);
            this.imagesIndicator.setViewPager(this.imagesViewPager);
        }

        private void setDimensionsForViewPager() {
            try {
                WindowManager wm = (WindowManager) activity.getSystemService(
                    Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int screenWidthPixel = size.x;
                int viewPagerHeight = (screenWidthPixel * Config.Screen.VIEWPAGER_IMAGE_HEIGHT_PIXEL)
                    / Config.Screen.VIEWPAGER_IMAGE_WIDTH_PIXEL;
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    viewPagerHeight
                );
                this.imagesViewPager.setLayoutParams(layoutParams);
            } catch (Exception e) {
                Log.e(TAG, "setDimensionsForViewPager", e);
            }
        }
    }
}
