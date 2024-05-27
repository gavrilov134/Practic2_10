package com.mirea.kt.practic2_10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {
    private List<Doctor> doctorList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Doctor doctor);
        void onItemLongClick(Doctor doctor);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public static class DoctorViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDoctorName, tvSpecialty, tvCertified;

        public DoctorViewHolder(View view, OnItemClickListener listener) {
            super(view);
            tvDoctorName = view.findViewById(R.id.tvDoctorName);
            tvSpecialty = view.findViewById(R.id.tvSpecialty);
            tvCertified = view.findViewById(R.id.tvCertified);

            view.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick((Doctor) v.getTag());
                    }
                }
            });

            view.setOnLongClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemLongClick((Doctor) v.getTag());
                    }
                }
                return true;
            });
        }
    }

    public DoctorAdapter(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    @Override
    public DoctorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_doctor, parent, false);
        return new DoctorViewHolder(itemView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(DoctorViewHolder holder, int position) {
        Doctor doctor = doctorList.get(position);
        holder.tvDoctorName.setText(doctor.firstName + " " + doctor.lastName);
        holder.tvSpecialty.setText("Specialty: " + doctor.specialty);
        holder.tvCertified.setText("Certified: " + (doctor.certified ? "Yes" : "No"));
        holder.itemView.setTag(doctor);
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public void removeDoctor(Doctor doctor) {
        int position = doctorList.indexOf(doctor);
        if (position != -1) {
            doctorList.remove(position);
            notifyItemRemoved(position);
        }
    }
}

