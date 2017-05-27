package kr.co.midas.midasmobile.tabbar.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.co.midas.midasmobile.R;
import kr.co.midas.midasmobile.base.domain.Voluntary;
import kr.co.midas.midasmobile.tabbar.viewholders.VoluntListHolder;

/**
 * Created by user on 2017-05-27.
 */

public class VoluntListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static List<Voluntary> voluntList;
    private Context context;

    public VoluntListAdapter(Context context, List<Voluntary> voluntList){
        this.context = context;
        this.voluntList = new ArrayList<>();
        this.voluntList = voluntList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VoluntListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.volunt_list, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((VoluntListHolder) holder).onBind(context, voluntList.get(position));

    }

    @Override
    public int getItemCount() {
        return voluntList.size();
    }
}
