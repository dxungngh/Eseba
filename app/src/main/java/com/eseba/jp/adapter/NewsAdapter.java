package com.eseba.jp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.eseba.jp.R;
import com.eseba.jp.bus.data.RowNewsOnClick;
import com.eseba.jp.bus.listener.OnRowNewsClickListener;
import com.eseba.jp.database.table.News;
import com.eseba.jp.util.NetworkUtil;
import com.eseba.jp.widget.MyTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by danielnguyen on 8/23/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = NewsAdapter.class.getSimpleName();

    private static final int TITLE_TYPE = 0;
    private static final int NEWS_TYPE = 1;

    private Context context;
    private List<News> newsList;
    private String newsTypeName;
    private int pagePosition;
    private OnRowNewsClickListener listener;

    public NewsAdapter(Context context,
                       List<News> newsList,
                       String newsTypeName,
                       int pagePosition,
                       OnRowNewsClickListener listener) {
        this.context = context;
        this.newsList = newsList;
        this.newsTypeName = newsTypeName;
        this.pagePosition = pagePosition;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view;
        switch (viewType) {
            case TITLE_TYPE:
                view = LayoutInflater.from(context).inflate(R.layout.row_news_list_type, parent, false);
                return new TitleViewHolder(view);
            case NEWS_TYPE:
                view = LayoutInflater.from(context).inflate(R.layout.row_news, parent, false);
                return new NewsViewHolder(view);
            default:
                view = LayoutInflater.from(context).inflate(R.layout.row_news, parent, false);
                return new NewsViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        int viewType = this.getItemViewType(position);
        if (viewType == NEWS_TYPE) {
            final News news = this.newsList.get(position - 1);
            NewsViewHolder newsViewHolder = (NewsViewHolder) viewHolder;
            newsViewHolder.setup(news, position);
            newsViewHolder.rowNewsLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.rowNewsOnClick(new RowNewsOnClick(news, pagePosition));
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.newsList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TITLE_TYPE;
        }
        return NEWS_TYPE;
    }

    public class TitleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.row_news_list_type_title)
        protected MyTextView titleTextView;

        public TitleViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.titleTextView.setText(newsTypeName);
        }
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.row_news_layout)
        protected RelativeLayout rowNewsLayout;
        @BindView(R.id.row_news_divider_line)
        protected ImageView lineImageView;
        @BindView(R.id.row_news_avatar)
        protected ImageView avatarImageView;
        @BindView(R.id.row_news_title)
        protected MyTextView titleTextView;
        @BindView(R.id.row_news_date)
        protected MyTextView dateTextView;

        private News news;

        public NewsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setup(News news, int position) {
            this.news = news;
            this.setupDividerLine(position);
            this.drawNewsView();
        }

        private void setupDividerLine(int position) {
            if (position == getItemCount() - 1) {
                this.lineImageView.setVisibility(View.GONE);
            } else {
                this.lineImageView.setVisibility(View.VISIBLE);
            }
        }

        private void drawNewsView() {
            if (this.news != null) {
                this.titleTextView.setText(news.getTitle());
                this.dateTextView.setText(news.getDate());
                NetworkUtil.getPicasso(context)
                    .load(news.getImageUrl())
                    .into(this.avatarImageView);
            }
        }
    }
}
