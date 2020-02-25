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

import com.tt.handsomeman.databinding.ItemPayoutBinding;
import com.tt.handsomeman.model.Payout;

import java.util.List;

public class PayoutAdapter extends RecyclerView.Adapter<PayoutAdapter.PayoutViewHolder> {

    private List<Payout> payoutList;
    private LayoutInflater layoutInflater;
    private Context context;
    private ItemPayoutBinding binding;

    public PayoutAdapter(List<Payout> payoutList, Context context) {
        this.payoutList = payoutList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PayoutAdapter.PayoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemPayoutBinding.inflate(layoutInflater, parent, false);
        View item = binding.getRoot();
        return new PayoutAdapter.PayoutViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull PayoutAdapter.PayoutViewHolder holder, int position) {
        Payout payout = payoutList.get(position);
        holder.tvPayoutLastNumbers.setText(payout.getAccountNumber().substring(payout.getAccountNumber().length() - 4));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, payout.getAccountNumber(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnPayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, payout.getAccountNumber(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return payoutList.size();
    }

    class PayoutViewHolder extends RecyclerView.ViewHolder {
        final ImageButton btnPayout;
        final TextView tvPayoutLastNumbers;

        PayoutViewHolder(@NonNull View itemView) {
            super(itemView);
            btnPayout = binding.imageButtonItemPayout;
            tvPayoutLastNumbers = binding.payoutLastNumbers;
        }
    }
}
