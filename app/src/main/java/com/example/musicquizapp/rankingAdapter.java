package com.example.musicquizapp;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class rankingAdapter extends RecyclerView.Adapter<rankingAdapter.ViewHolder> {
    private List<Player> playerList;

    // Constructor để truyền vào danh sách người chơi
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
        Player player = playerList.get(position);

        holder.rankTextView.setText(String.valueOf(position + 1));
        holder.playerNameTextView.setText(player.getName());
        holder.playerScoreTextView.setText(String.valueOf(player.getScore()));

        // Thiết lập màu sắc dựa trên hạng
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

    // Trả về số lượng mục trong danh sách
    @Override
    public int getItemCount() {
        return playerList.size();
    }

    // ViewHolder để giữ các thành phần giao diện người dùng cho mỗi item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView rankTextView, playerNameTextView, playerScoreTextView;

        LinearLayout linearLayout; // Thêm LinearLayout vào ViewHolder


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rankTextView = itemView.findViewById(R.id.rankTextView);
            playerNameTextView = itemView.findViewById(R.id.playerNameTextView);
            playerScoreTextView = itemView.findViewById(R.id.playerScoreTextView);
            linearLayout = itemView.findViewById(R.id.linearLayoutItem);
        }
    }
}
