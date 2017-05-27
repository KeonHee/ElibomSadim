package kr.co.midas.midasmobile.tabbar.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.co.midas.midasmobile.R;
import kr.co.midas.midasmobile.tabbar.objects.TeamObject;
import kr.co.midas.midasmobile.tabbar.viewholders.TeamListHolder;

/**
 * Created by user on 2017-05-23.
 */

public class TeamListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static List<TeamObject> teamList;
    private Context context;

    public TeamListAdapter(Context context, List<TeamObject> teamList){
        this.teamList = new ArrayList<>();
        this.teamList = teamList;
        this.context = context;
    }

    //ViewHolder 생성
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TeamListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.team_list, parent, false));

    }

    /** ViewHolder에 데이터 넣기 */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((TeamListHolder)holder).onBind(context, teamList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.teamList.size();
    }
}
