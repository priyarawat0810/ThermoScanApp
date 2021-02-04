package com.example.thermoscanapp;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class GalleryImageAdapter extends RecyclerView.Adapter<GalleryImageAdapter.ViewHolder>  {
    private List<UploadImage> uploadImageList;
    private int lastPosition;

    public GalleryImageAdapter(List<UploadImage> uploadImageList) {
        this.uploadImageList = uploadImageList;
    }

    public void updateList(List<UploadImage> list ){
        uploadImageList= list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public GalleryImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryImageAdapter.ViewHolder holder, int position) {
        String note = uploadImageList.get(position).getName();
        String image = uploadImageList.get(position).getImageUrl();
        String detail= uploadImageList.get(position).getDetails();
        holder.setData(image,note,detail);
        if (lastPosition <position) {
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.fade_in);
            holder.itemView.setAnimation(animation);
            lastPosition=position;
        }
    }

    @Override
    public int getItemCount() {
        return uploadImageList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;
        private TextView imageNote;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image  = itemView.findViewById(R.id.image_view_upload);
            imageNote= itemView.findViewById(R.id.text_view_name);
        }


        private void setData(String mImage, String mImageNote, final String details){

            imageNote.setText(mImageNote);
            imageNote.setVisibility(View.INVISIBLE);
            Glide.with(itemView.getContext()).load(mImage).apply(new RequestOptions().placeholder(R.drawable.square_placeholder)).into(image);

            final ImageView personImage;
            final TextView personName;
            final ImageView dismissProductDetailsDialog;
            final ConstraintLayout backgroundProductDetails;

            final Dialog productDetailsDialog = new Dialog((GalleryActivity) itemView.getContext());
            productDetailsDialog.setContentView(R.layout.record_item);
            productDetailsDialog.setCancelable(true);
            WindowManager.LayoutParams lp=((GalleryActivity) itemView.getContext()).getWindow().getAttributes();
            lp.dimAmount=0.85f;

            productDetailsDialog.getWindow().setAttributes(lp);
            productDetailsDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            productDetailsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            productDetailsDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            personImage=productDetailsDialog.findViewById(R.id.person_img);
            personName=productDetailsDialog.findViewById(R.id.person_name);
            backgroundProductDetails=productDetailsDialog.findViewById(R.id.productDetailsBackground);
            dismissProductDetailsDialog=productDetailsDialog.findViewById(R.id.dismissdialog);

            backgroundProductDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productDetailsDialog.dismiss();
                }
            });
            dismissProductDetailsDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productDetailsDialog.dismiss();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(itemView.getContext(), details
//                            , Toast.LENGTH_SHORT).show();
                    productDetailsDialog.show();
                }
            });

            Glide.with(productDetailsDialog.getContext()).load(mImage).apply(new RequestOptions().placeholder(R.drawable.square_placeholder)).into(personImage);
            personName.setText(mImageNote);
//            productDetailsDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                @Override
//                public void onDismiss(DialogInterface dialog) {
//                    loadingDialog.dismiss();
//                }
//        });
            }
        }
    }



