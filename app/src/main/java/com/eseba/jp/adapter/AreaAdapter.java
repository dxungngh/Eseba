package com.eseba.jp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.eseba.jp.R;
import com.eseba.jp.ServiceRegistry;
import com.eseba.jp.business.AreaBusiness;
import com.eseba.jp.database.table.Area;
import com.eseba.jp.widget.MyTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by danielnguyen on 8/23/17.
 */

public class AreaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = AreaAdapter.class.getSimpleName();

    private List<Area> itemList;
    private AreaBusiness areaBusiness;

    public AreaAdapter(List<Area> itemList) {
        this.itemList = itemList;
        this.areaBusiness = (AreaBusiness) ServiceRegistry.getService(AreaBusiness.TAG);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);
        return new ItemGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final Area area = this.itemList.get(position);
        ItemGroupViewHolder itemViewHolder = (ItemGroupViewHolder) viewHolder;
        itemViewHolder.setup(area, position);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public class ItemGroupViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.row_item_divider_line)
        protected ImageView lineImageView;
        @BindView(R.id.row_item_title)
        protected MyTextView titleTextView;
        @BindView(R.id.row_item_toggle_button)
        protected ToggleButton itemToggleButton;

        private Area item;

        public ItemGroupViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.row_item_toggle_button)
        public void itemToggleButtonOnClick() {
            this.item.setActive(!this.item.isActive());
            areaBusiness.saveArea(this.item);
        }

        public void setup(Area item, int position) {
            this.item = item;
            this.setupDividerLine(position);
            this.titleTextView.setText(this.item.getAreaName());
            this.itemToggleButton.setChecked(this.item.isActive());
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
