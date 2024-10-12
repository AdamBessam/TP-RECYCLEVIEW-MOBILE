package com.ensa.tp4.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ensa.tp4.R;
import com.ensa.tp4.beans.Player;
import com.ensa.tp4.service.PlayerService;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PlayerAdater extends RecyclerView.Adapter<PlayerAdater.PlayerViewHolder> implements Filterable {
    private  static final String TAG="PlayerAdater";
    private List<Player> players;
    private List<Player> playersFilter;
    private NewFilter mfilter;
    private Context context;
    public PlayerAdater(Context context,List<Player> players){
        this.players=players;
        this.context=context;
        playersFilter=new ArrayList<>();
        playersFilter.addAll(players);
        this.mfilter = new NewFilter(this);
    }
    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,int i){
        View v= LayoutInflater.from(this.context).inflate(R.layout.player_item,viewGroup,false);
        final PlayerViewHolder holder = new PlayerViewHolder(v);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popup=LayoutInflater.from(context).inflate(R.layout.star_edit_item,null,false);
                final ImageView img=popup.findViewById(R.id.img);
                final RatingBar bar = popup.findViewById(R.id.ratingBar);
                final TextView idss = popup.findViewById(R.id.idss);
                Bitmap bitmap =
                        ((BitmapDrawable)((ImageView) v.findViewById(R.id.img)).getDrawable()).getBitmap();
                img.setImageBitmap(bitmap);
                bar.setRating(((RatingBar)v.findViewById(R.id.stars)).getRating());
                idss.setText(((TextView)v.findViewById(R.id.ids)).getText().toString());
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setView(popup)
                        .setPositiveButton("Validate", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                float s = bar.getRating();
                                int ids = Integer.parseInt(idss.getText().toString());
                                Player player = PlayerService.getInstance().findById(ids);
                                player.setStar(s); PlayerService.getInstance().update(player);
                            notifyItemChanged(holder.getBindingAdapterPosition());
                        }})
                        .setNegativeButton("Annuller",null)
                        .create();
                dialog.show();

            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder playerViewHolder, int i) {
        Log.d(TAG,"onBindView call !"+i);
        Glide.with(context)
                .asBitmap()
                .load(playersFilter.get(i).getImg())
                .apply(new RequestOptions().override(100,100))
                .into(playerViewHolder.img);
        playerViewHolder.name.setText(playersFilter.get(i).getNom().toUpperCase());
        playerViewHolder.stars.setRating(playersFilter.get(i).getStar());
        playerViewHolder.idss.setText(playersFilter.get(i).getId()+"");

    }

    @Override
    public int getItemCount() {
        return playersFilter.size();
    }

    @Override
    public Filter getFilter() {
        return mfilter;
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder{
        TextView idss;
        ImageView img;
        TextView name;
        RatingBar stars;
        RelativeLayout parent;
        public PlayerViewHolder(@NonNull View itemView){
            super(itemView);
            idss=itemView.findViewById(R.id.ids);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.nom);
            stars = itemView.findViewById(R.id.stars);
            parent = itemView.findViewById(R.id.parent);
        }
    }
    public class NewFilter extends Filter{
        public RecyclerView.Adapter mAdapter;
        public NewFilter(RecyclerView.Adapter mAdapter){
            super();
            this.mAdapter=mAdapter;
        }


        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            playersFilter.clear();
            final FilterResults results=new FilterResults();
            if(constraint.length()==0){
                playersFilter.addAll(players);
            }else{
                final String filterPattern =constraint.toString().toLowerCase().trim();
                for(Player p:players){
                    if(p.getNom().toLowerCase().startsWith(filterPattern)){
                        playersFilter.add(p);
                    }
                }
            }
            results.values=playersFilter;
            results.count=playersFilter.size();
            return results;


        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            playersFilter=(List<Player>) results.values;
            this.mAdapter.notifyDataSetChanged();

        }
    }
}
