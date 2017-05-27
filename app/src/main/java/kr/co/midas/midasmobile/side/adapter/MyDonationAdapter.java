package kr.co.midas.midasmobile.side.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.co.midas.midasmobile.base.domain.Donation;
import kr.co.midas.midasmobile.side.viewholder.MyDonationViewHolder;

public class MyDonationAdapter extends RecyclerView.Adapter<MyDonationViewHolder> {

    private Activity mActivity;
    private List<Donation> donationList = new ArrayList<>();

    public MyDonationAdapter(Activity activity){
        super();
        mActivity=activity;
    }
    @Override
    public MyDonationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyDonationViewHolder(mActivity,parent);
    }

    @Override
    public void onBindViewHolder(MyDonationViewHolder holder, int position) {
        int realPosition = donationList.size()-holder.getAdapterPosition()-1; //reverse
        holder.onBind(donationList.get(realPosition));
    }

    @Override
    public int getItemCount() {
        if(donationList==null){
            return 0;
        }
        return donationList.size();
    }

    public void setListData(List<Donation> listItem) {
        donationList.clear();
        donationList.addAll(listItem);
        notifyDataSetChanged();
    }

    public void addListData(Donation item) {
        donationList.add(item);
        notifyDataSetChanged();
    }
}
