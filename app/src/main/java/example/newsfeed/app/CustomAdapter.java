package example.newsfeed.app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
//Adapter class

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ListItemViewHolder>{

    List<NewsData> data;
    public CustomAdapter(List<NewsData>data){
        this.data = data;

    }
    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.carduiview,parent,false);
        return new ListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {
        NewsData positionData = data.get(position);
        holder.sitelink.setText(positionData.getUrl());
        holder.title.setText(positionData.getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public final static class ListItemViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView sitelink;
        TextView cmts;

        public ListItemViewHolder(View itemview){
            super(itemview);
            title = (TextView) itemview.findViewById(R.id.title);
            sitelink = (TextView) itemview.findViewById(R.id.sitelink);


        }
    }

}
