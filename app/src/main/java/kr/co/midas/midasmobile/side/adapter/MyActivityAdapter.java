package kr.co.midas.midasmobile.side.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.co.midas.midasmobile.base.domain.Record;
import kr.co.midas.midasmobile.side.viewholder.MyActivityViewHolder;


public class MyActivityAdapter extends RecyclerView.Adapter<MyActivityViewHolder> {

    private Activity mActivity;
    private List<Record> recordList = new ArrayList<>();

    public MyActivityAdapter(Activity activity){
        super();
        mActivity=activity;
    }
    @Override
    public MyActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyActivityViewHolder(mActivity,parent);
    }

    @Override
    public void onBindViewHolder(MyActivityViewHolder holder, int position) {
        int realPosition = recordList.size()-holder.getAdapterPosition()-1; //reverse
        holder.onBind(recordList.get(realPosition));
    }

    @Override
    public int getItemCount() {
        if(recordList==null){
            return 0;
        }
        return recordList.size();
    }

    public void setListData(List<Record> listItem) {
        recordList.clear();
        recordList.addAll(listItem);
        notifyDataSetChanged();
    }

    public void addListData(Record item) {
        recordList.add(item);
        notifyDataSetChanged();
    }
}
