package com.example.musicquizapp;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class rankingAdapter extends RecyclerView.Adapter<rankingAdapter.ViewHolder> {
    private List<Player> playerList;


    public rankingAdapter(List<Player> playerList) {
        this.playerList = playerList;
    }

    // Tạo ViewHolder mới khi được gọi
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bxh, parent, false);
        return new ViewHolder(view);
    }

    // Gắn dữ liệu vào ViewHolder khi được gọi
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Collections.sort(playerList, new Comparator<Player>() {
            @Override
            public int compare(Player s1, Player s2) {
                // Sắp xếp giảm dần theo điểm
                int compareScore = Integer.compare(s2.getScore(), s1.getScore());

                // Nếu điểm giống nhau, sắp xếp tăng dần theo tên
                if (compareScore == 0) {
                    return s1.getName().compareTo(s2.getName());
                }

                return compareScore;
            }
        });
        Player player = playerList.get(position);

        holder.rankTextView.setText(String.valueOf(position + 1));
        holder.playerNameTextView.setText(player.getName());
        holder.playerScoreTextView.setText(String.valueOf(player.getScore()));


        if (position == 0) {
            holder.rankTextView.setTextColor(Color.YELLOW);
            holder.linearLayout.setBackgroundColor(Color.YELLOW); // Đặt màu cho LinearLayout
        } else if (position == 1) {
            holder.rankTextView.setTextColor(Color.GRAY);
            holder.linearLayout.setBackgroundColor(Color.GRAY); // Đặt màu cho LinearLayout
        } else if (position == 2) {
            holder.rankTextView.setTextColor(Color.parseColor("#CD7F32"));
            holder.linearLayout.setBackgroundColor(Color.parseColor("#CD7F32")); // Đặt màu cho LinearLayout
        } else {
            holder.rankTextView.setTextColor(Color.BLACK);
            holder.linearLayout.setBackgroundColor(Color.TRANSPARENT); // Đặt màu cho LinearLayout (mặc định)
        }
    }


    @Override
    public int getItemCount() {
        // Kiểm tra xem playerList có null hay không trước khi truy xuất size()
        return playerList != null ? playerList.size() : 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView rankTextView, playerNameTextView, playerScoreTextView;

        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rankTextView = itemView.findViewById(R.id.rankTextView);
            playerNameTextView = itemView.findViewById(R.id.playerNameTextView);
            playerScoreTextView = itemView.findViewById(R.id.playerScoreTextView);
            linearLayout = itemView.findViewById(R.id.linearLayoutItem);
        }
    }
}
