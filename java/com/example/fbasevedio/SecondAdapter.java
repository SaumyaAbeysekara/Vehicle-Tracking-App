package com.example.fbasevedio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class SecondAdapter extends FirebaseRecyclerAdapter<SecondModel,SecondAdapter.myViewHolder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public SecondAdapter(@NonNull FirebaseRecyclerOptions<SecondModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull SecondAdapter.myViewHolder holder, int position, @NonNull SecondModel model) {
        holder.driverCode.setText(model.getDriverCode());
        holder.driverName.setText(model.getDriverCode());
        holder.driverTP.setText(model.getTP());
        holder.licenceEX.setText(model.getLicenceEX());

        Glide.with(holder.img.getContext()).load(model.getUrl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop().error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);








    }

    @NonNull
    @Override
    public SecondAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.driver_item,parent,false);
        return new SecondAdapter.myViewHolder(view);

    }



    class myViewHolder extends RecyclerView.ViewHolder{
        TextView driverCode,driverName,driverTP,licenceNumber,licenceEX;

        CircleImageView img;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.img1);
            driverCode =(TextView)itemView.findViewById(R.id.driverCode);
            driverName = (TextView) itemView.findViewById(R.id.driverName);
            driverTP = (TextView) itemView.findViewById(R.id.TP);
            licenceEX =(TextView) itemView.findViewById(R.id.licenceEX);



//            updateBtn =(Button)itemView.findViewById(R.id.editBtn);
//            deleteBtn = (Button)itemView.findViewById(R.id.deleteBtn);
//            detailsBtn = (Button) itemView.findViewById((R.id.allDetails));







        }
    }

}
