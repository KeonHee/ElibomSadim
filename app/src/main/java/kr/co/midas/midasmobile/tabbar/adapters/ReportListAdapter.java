package kr.co.midas.midasmobile.tabbar.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.co.midas.midasmobile.R;
import kr.co.midas.midasmobile.base.domain.Report;
import kr.co.midas.midasmobile.tabbar.viewholders.ReportListHolder;

/**
 * Created by user on 2017-05-28.
 */

public class ReportListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static List<Report> reportList;
    private Context context;

    public ReportListAdapter(Context context, List<Report> reportList){
        this.context = context;
        this.reportList = new ArrayList<>();
        this.reportList = reportList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReportListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.report_list, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ReportListHolder) holder).onBind(context, reportList.get(position));

    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    public void setReportList(List<Report> reports){
        reportList.clear();
        reportList.addAll(reports);
        notifyDataSetChanged();
    }
}
