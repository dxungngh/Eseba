package com.eseba.jp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.eseba.jp.R;
import com.eseba.jp.bus.listener.OnItemGroupClickListener;
import com.eseba.jp.widget.MyTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by danielnguyen on 8/23/17.
 */

public class ItemGroupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = ItemGroupAdapter.class.getSimpleName();

    private List<String> itemGroupList;
    private OnItemGroupClickListener listener;

    public ItemGroupAdapter(List<String> itemGroupList, OnItemGroupClickListener listener) {
        this.itemGroupList = itemGroupList;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_item_group, parent, false);
        return new ItemGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final String itemGroup = this.itemGroupList.get(position);
        ItemGroupViewHolder newsViewHolder = (ItemGroupViewHolder) viewHolder;
        newsViewHolder.setup(itemGroup, position);
        newsViewHolder.rowItemGroupLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemGroupClick(itemGroup);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.itemGroupList.size();
    }

    public class ItemGroupViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.row_item_group_layout)
        protected RelativeLayout rowItemGroupLayout;
        @BindView(R.id.row_item_group_divider_line)
        protected ImageView lineImageView;
        @BindView(R.id.row_item_group_title)
        protected MyTextView titleTextView;

        private String itemGroup;

        public ItemGroupViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setup(String itemGroup, int position) {
            this.itemGroup = itemGroup;
            this.setupDividerLine(position);
            if (this.itemGroup.equals("event")) {
                this.titleTextView.setText("イベント");
            } else if (this.itemGroup.equals("seminar")) {
                this.titleTextView.setText("セミナー");
            } else {
                this.titleTextView.setText(this.itemGroup);
            }
        }

        private void setupDividerLine(int position) {
            if (position == getItemCount() - 1) {
                this.lineImageView.setVisibility(View.GONE);
            } else {
                this.lineImageView.setVisibility(View.VISIBLE);
            }
        }
    }
}
