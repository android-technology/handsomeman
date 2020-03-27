package com.tt.handsomeman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.databinding.ItemFileNameBinding;
import com.tt.handsomeman.response.BidFileResponse;

import java.util.List;

public class FileNameAdapter extends RecyclerView.Adapter<FileNameAdapter.ViewHolder> {
    private Context context;
    private List<BidFileResponse> bidFileResponses;
    private LayoutInflater inflater;
    private ItemFileNameBinding binding;

    public FileNameAdapter(Context context, List<BidFileResponse> bidFileResponses) {
        this.context = context;
        this.bidFileResponses = bidFileResponses;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemFileNameBinding.inflate(inflater, parent, false);
        View view = binding.getRoot();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BidFileResponse bidFileResponse = bidFileResponses.get(position);

        holder.fileName.setText(bidFileResponse.getFileName());
        holder.btnDownload.setOnClickListener(v -> Toast.makeText(context, bidFileResponse.getDownloadLink(), Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return bidFileResponses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView fileName;
        private final ImageButton btnDownload;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            fileName = binding.fileName;
            btnDownload = binding.downloadButton;
        }
    }
}
