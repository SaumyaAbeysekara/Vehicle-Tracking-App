package com.example.fbasevedio;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel,MainAdapter.myViewHolder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder,final int position, @NonNull MainModel model) {
        holder.vNumber.setText(model.getVehicleNumber());
        holder.oName.setText(model.getOwnerName());

        holder.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.vNumber.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1200).create();
//                dialogPlus.show();

                View view1 = dialogPlus.getHolderView();
                EditText nameOwner= view1.findViewById(R.id.updatedOwner);

                Button btnUpdate = view1.findViewById(R.id.updateBtn);
                nameOwner.setText(model.getOwnerName());
                dialogPlus.show();


                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object>map=new  HashMap<>();
                        map.put("ownerName",nameOwner.getText().toString()); //key should be same as the data base

                        FirebaseDatabase.getInstance().getReference().child("Details").child(Objects.requireNonNull(getRef(position).getKey()))
                                .updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.oName.getContext(), "Updated Successfully.", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                        
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.oName.getContext(), "Error.", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();


                                    }
                                });



                    }
                });




            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder  = new AlertDialog.Builder(holder.vNumber.getContext());
                builder.setTitle("Are you Sure?");
                builder.setMessage("Deleted data can't be Undo");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        FirebaseDatabase.getInstance().getReference().child("Details")
                                .child(getRef(position).getKey()).removeValue();



                    }
                });
                builder.setNegativeButton("Cansel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.vNumber.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();



            }
        });
        holder.detailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.detailsBtn.getContext(), VeiwDetails.class);
                intent.putExtra("vehicleNumber", model.getVehicleNumber());
                intent.putExtra("ownerName", model.getOwnerName());
                intent.putExtra("chassisNumber", model.getChassisNumber());
                intent.putExtra("ownerName", model.getOwnerName());
                intent.putExtra("ownerNIC", model.getOwnerNIC());
                intent.putExtra("ownerTP", model.getOwnerTP());
                intent.putExtra("ownerAddress", model.getOwnerAddress());


                holder.detailsBtn.getContext().startActivity(intent);
            }
        });






    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView vNumber,oName;

        Button updateBtn,deleteBtn,detailsBtn;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            vNumber =(TextView)itemView.findViewById(R.id.vNumber);
            oName = (TextView) itemView.findViewById(R.id.oName);


            updateBtn =(Button)itemView.findViewById(R.id.editBtn);
            deleteBtn = (Button)itemView.findViewById(R.id.deleteBtn);
            detailsBtn = (Button) itemView.findViewById((R.id.allDetails));







        }
    }
}
