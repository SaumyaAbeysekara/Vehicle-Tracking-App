package com.example.fbasevedio;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class AssignmentAdapter extends FirebaseRecyclerAdapter<Assignment,AssignmentAdapter.myViewHolder> {

    private UnlockVehicleCallback unlockVehicleCallback;
    private DeleteVehicleCallback deleteVehicleCallback;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     * @param lockedVehicles
     */
    public AssignmentAdapter(@NonNull FirebaseRecyclerOptions<Assignment> options, lockedVehicles lockedVehicles) {
        super(options);
        this.unlockVehicleCallback = unlockVehicleCallback;
        this.deleteVehicleCallback = deleteVehicleCallback;
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Assignment model) {
        holder.vehicleAssignTextView.setText(model.getVehicleAssign());
        holder.driverAssignTextView.setText(model.getDriverAssign());

        holder.unlockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.vehicleAssignTextView.getContext());
                builder.setTitle("Do you want to unlock the vehicle ?");
                builder.setMessage("Deleted data can't be Undo");

                builder.setPositiveButton("Unlock", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Assingment")
                                .child(getRef(position).getKey()).removeValue();

                        if (unlockVehicleCallback != null) {
                            unlockVehicleCallback.onUnlockVehicle(model.getVehicleAssign());
                        }

                        if (deleteVehicleCallback != null) {
                            deleteVehicleCallback.onDeleteVehicle(model.getVehicleAssign());
                        }

                    }
                });
                builder.setNegativeButton("Cansel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.vehicleAssignTextView.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.locked_item,parent,false);

        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView vehicleAssignTextView, driverAssignTextView;
        Button unlockBtn,locationBtn;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            vehicleAssignTextView = itemView.findViewById(R.id.tvVehicleNumber);
            driverAssignTextView = itemView.findViewById(R.id.tvDriverCode);

            unlockBtn = itemView.findViewById(R.id.btnUnlock);
            locationBtn = itemView.findViewById(R.id.btnLocation);

            locationBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), MapActivity.class);
                    view.getContext().startActivity(intent);
                }
            });

        }
    }
}
