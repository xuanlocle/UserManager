package com.xuanlocle.usermanager.ui.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xuanlocle.usermanager.ui.adapter.item.RecyclerViewItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private List<RecyclerViewItem> mItems;
    private Map<Integer, RecyclerViewItem> mPrototypeItem;


    public RecyclerViewAdapter()
    {
        mItems = new ArrayList<>();
        mPrototypeItem = new HashMap<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return mPrototypeItem.get(viewType).createViewHolder(parent.getContext());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
    {
        mItems.get(position).bind(holder);

    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemViewType(int position)
    {
        return mItems.get(position).getType();
    }

    @Override
    public int getItemCount()
    {
        return mItems.size();
    }

    public void append(RecyclerViewItem item)
    {
        append(mItems.size(), item);
    }

    public void append(int position, RecyclerViewItem item)
    {
        mItems.add(position, item);
        addPrototypeItem(item);
        notifyItemInserted(position);
    }

    public void append(List<RecyclerViewItem> items)
    {
        append(mItems.size(), items);
    }

    public void append(int position, List<RecyclerViewItem> items)
    {
        mItems.addAll(position, items);
        addPrototypeItem(items);
        notifyItemRangeInserted(position, items.size());
    }

    public void remove(int position)
    {
        validPosition(position);

        mItems.remove(position);
        notifyItemRemoved(position);
    }

    public void remove(int position, int length)
    {
        if (length == 1)
        {
            remove(position);
        }
        else
        {
            List<RecyclerViewItem> items = new ArrayList<>();
            for (int i = 0; i < mItems.size(); i++)
            {
                if (i < position || i >= position + length)
                    items.add(mItems.get(i));
            }
            mItems = items;
            notifyItemRangeRemoved(position, length);
        }
    }

    public void replace(int position, RecyclerViewItem item)
    {
        validPosition(position);

        mItems.remove(position);
        mItems.add(position, item);
        addPrototypeItem(item);

        notifyItemChanged(position);
    }

    private void addPrototypeItem(RecyclerViewItem item)
    {
        if (!mPrototypeItem.containsKey(item))
        {
            mPrototypeItem.put(item.getType(), item);
        }
    }

    private void addPrototypeItem(List<RecyclerViewItem> items)
    {
        for (RecyclerViewItem item : items)
        {
            if (!mPrototypeItem.containsKey(item))
            {
                mPrototypeItem.put(item.getType(), item);
            }
        }
    }

    public void validPosition(int position)
    {
        if (position < 0 || position > mItems.size())
        {
            throw new IllegalStateException(String.format("Recycler-Adapter: Out off range: " +
                    "pos %s length ", position, mItems.size()));
        }
    }

}
