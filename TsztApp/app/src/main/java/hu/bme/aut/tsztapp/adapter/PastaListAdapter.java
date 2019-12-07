package hu.bme.aut.tsztapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import hu.bme.aut.tsztapp.R;
import hu.bme.aut.tsztapp.model.Pasta;

public class PastaListAdapter extends RecyclerView.Adapter<PastaListAdapter.MyViewHolder> {
    private Pasta mDataset[];

    private OnPastaSelectedListener listener;

    public interface OnPastaSelectedListener {
        void onPastaSelected(Pasta pasta);
    }

    public PastaListAdapter(Pasta[] myDataset, OnPastaSelectedListener listener) {
        mDataset = myDataset;
        this.listener = listener;
    }

    @Override
    public PastaListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pasta_list_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

     class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        Pasta item;
        public MyViewHolder(TextView v) {
            super(v);
            textView = v;

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onPastaSelected(item);
                    }
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(mDataset[position].getName());
        holder.item = mDataset[position];
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}