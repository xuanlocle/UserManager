package com.xuanlocle.usermanager.ui.adapter;

import com.xuanlocle.usermanager.ui.adapter.RecyclerViewAdapter;
import com.xuanlocle.usermanager.ui.adapter.RenderRange;
import com.xuanlocle.usermanager.ui.adapter.item.RecyclerViewItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerManager<T>
{
    // Manage group type
    private List<T> mClusters;

    // Manage map group range
    private Map<T, RenderRange> mMapRenderRange;

    private RecyclerViewAdapter mAdapter;


    public RecyclerManager()
    {
        mClusters = new ArrayList<>();
        mAdapter = new RecyclerViewAdapter();
        mMapRenderRange = new HashMap<>();
    }

    /*
    *  Public method
    * */
    public void recycle()
    {
        mClusters.clear();
        mMapRenderRange.clear();
    }

    public RecyclerViewAdapter getAdapter()
    {
        return mAdapter;
    }

    public void addCluster(T t)
    {
        mClusters.add(t);
        mMapRenderRange.put(t, new RenderRange(0, 0));
        calculateMapRenderRange();
    }
    public boolean isClusterExist(T t){
        return mClusters.contains(t);
    }

    /*
    *  Method notify change recycle
    *  Position: position item in cluster
    * */
    public void append(T t, RecyclerViewItem item)
    {
        RenderRange renderRange = mMapRenderRange.get(t);
        append(t, renderRange.getLength(), item);
    }

    public void append(T t, int position, RecyclerViewItem item)
    {
        RenderRange renderRange = mMapRenderRange.get(t);

        validPosition(renderRange, position);

        // find position append
        position = position + renderRange.getPosition();

        // update render range
        int length = renderRange.getLength() + 1;
        mMapRenderRange.put(t, new RenderRange(renderRange.getPosition(), length));

        // notify
        mAdapter.append(position, item);

        calculateMapRenderRange();
    }

    public void append(T t, List<RecyclerViewItem> items)
    {
        RenderRange renderRange = mMapRenderRange.get(t);
        append(t, renderRange.getLength(), items);
    }

    public void append(T t, int position, List<RecyclerViewItem> items)
    {
        RenderRange renderRange = mMapRenderRange.get(t);

        validPosition(renderRange, position);

        // find position append
        position = position + renderRange.getPosition();

        // update render range
        int length = renderRange.getLength() + items.size();
        mMapRenderRange.put(t, new RenderRange(renderRange.getPosition(), length));

        // notify
        mAdapter.append(position, items);

        calculateMapRenderRange();
    }

    public void update(T t, int position)
    {
        RenderRange renderRange = mMapRenderRange.get(t);
        position = position + renderRange.getPosition();
        mAdapter.notifyItemChanged(position);
    }

    public void update(T t, int position, RecyclerViewItem item)
    {
        replace(t, position, item);
    }

    public void remove(T t, int position)
    {
        RenderRange renderRange = mMapRenderRange.get(t);

        validPosition(renderRange, position);

        // this delete last item -> just remove cluster t
        int length = renderRange.getLength() - 1;
        if (length < 0)
            length = 0;
        mMapRenderRange.put(t, new RenderRange(renderRange.getPosition(), length));

        position = position + renderRange.getPosition();
        mAdapter.remove(position);

        calculateMapRenderRange();
    }

    public void replace(T t, int position, RecyclerViewItem item)
    {
        // first remove old
        RenderRange renderRange = mMapRenderRange.get(t);
        validPosition(renderRange, position);

        position = position + renderRange.getPosition();
        mAdapter.replace(position, item);
    }

    public void replace(T t, RecyclerViewItem item)
    {
        List<RecyclerViewItem> items = new ArrayList<>();
        items.add(item);
        replace(t, items);
    }

    public void replaceOrUpdateIfExist(T t, RecyclerViewItem item) {
        if (getLenghtOfCluster(t) > 0) {
            update(t, 0, item);
            return;
        }
        replace(t, item);
    }

    public void replace(T t, List<RecyclerViewItem> items)
    {
        // first remove old
        RenderRange renderRange = mMapRenderRange.get(t);
        if (renderRange.getLength() > 0)
            mAdapter.remove(renderRange.getPosition(), renderRange.getLength());

        mMapRenderRange.put(t, new RenderRange(renderRange.getPosition(), items.size()));
        mAdapter.append(renderRange.getPosition(), items);

        calculateMapRenderRange();
    }

    /**/
    private void calculateMapRenderRange()
    {
        int position = 0;
        for (T t : mClusters)
        {
            int length = mMapRenderRange.get(t).getLength();
            mMapRenderRange.put(t, new RenderRange(position, length));

            position += length;
        }
    }

    public void validPosition(RenderRange renderRange, int position)
    {
        if (position < 0 || position > renderRange.getLength())
        {
            throw new IllegalStateException(String.format("Recycler-Manager: Out off range: " +
                    "pos %s length ", position, renderRange.getLength()));
        }
    }

    public int getLenghtOfCluster(Object cluster) {
        return mMapRenderRange.get(cluster).getLength();
    }
}
