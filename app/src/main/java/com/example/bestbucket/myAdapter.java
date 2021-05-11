package com.example.bestbucket;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class myAdapter extends FirebaseRecyclerAdapter<model,myAdapter.myViewHolder> {


    private DatabaseReference mdatabasereference;
    //private ProgressDialog progressDialog;



    public Context context;
    public myAdapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull model model) {
        mdatabasereference = FirebaseDatabase.getInstance().getReference("products").child("accessories");
        holder.mBrand.setText(model.getBrand());
        holder.mDesc.setText(model.getDesc());
        holder.mName.setText(model.getName());
        holder.mPrice.setText(model.getPrice());
        String l= model.getLink();
        Log.d("LINKDATA"," data : "+l);
        final String pId=getRef(position).getKey();
        Glide.with(holder.mImage.getContext()).load(model.getImg()).into(holder.mImage);

        holder.itemView.setOnClickListener(v -> {
            final String productId=getRef(position).getKey();
            Log.d("productId"," data : "+productId);
            mdatabasereference.child(productId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String finallink = dataSnapshot.child("link").getValue(String.class);
                    Log.d("productLink"," data : "+finallink);
                    if(finallink!=null)
                    {
                        Uri uriUrl = Uri.parse(finallink);
                        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                        context.startActivity(launchBrowser);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_layout,parent,false);
        return new myViewHolder(view);
    }

    static class myViewHolder extends RecyclerView.ViewHolder{
        ImageView mImage;
        TextView mName,mDesc,mPrice,mBrand;
        public myViewHolder(@NonNull View itemView){
            super(itemView);
            mImage=(ImageView)itemView.findViewById(R.id.productImage);
            mName=(TextView)itemView.findViewById(R.id.product_name);
            mDesc=(TextView)itemView.findViewById(R.id.product_desc);
            mPrice=(TextView)itemView.findViewById(R.id.product_price);
            mBrand=(TextView)itemView.findViewById(R.id.product_brand);
        }
    }
}
