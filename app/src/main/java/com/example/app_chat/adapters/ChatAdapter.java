package com.example.app_chat.adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_chat.databinding.ItemContainerReceivedMessageBinding;
import com.example.app_chat.databinding.ItemContainerSentMessageBinding;
import com.example.app_chat.models.ChatMessage;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<ChatMessage> chatMessages;
    private Bitmap recyclerProfileImage;
    private final String senderId;


    public ChatAdapter(List<ChatMessage> chatMessages, Bitmap recyclerProfileImage, String senderId) {
        this.chatMessages = chatMessages;
        this.recyclerProfileImage = recyclerProfileImage;
        this.senderId = senderId;
    }

    public static final int VIEW_TYPE_SENT =1;
    public static final int VIEW_TYPE_RECIVED =2;

    public void setRecyclerProfileImage(Bitmap bitmap){
        recyclerProfileImage = bitmap;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SENT){
            return new SendMesageViewHolder(
                    ItemContainerSentMessageBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,
                            false
                    )
            );
        }else {
            return new ReceivedMessageViewHolder(
                    ItemContainerReceivedMessageBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,
                            false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==VIEW_TYPE_SENT){
            ((SendMesageViewHolder) holder ).setData(chatMessages.get(position));
        }else {
            ((ReceivedMessageViewHolder) holder).setData(chatMessages.get(position), recyclerProfileImage);
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (chatMessages.get(position).senderId.equals(senderId)){
            return VIEW_TYPE_SENT;
        }else {
            return VIEW_TYPE_RECIVED;
        }
    }

    static class SendMesageViewHolder extends RecyclerView.ViewHolder {

        private final ItemContainerSentMessageBinding binding ;

        SendMesageViewHolder(ItemContainerSentMessageBinding itemContainerSentMessageBinding){
            super(itemContainerSentMessageBinding.getRoot());
            binding = itemContainerSentMessageBinding;
        }
        void setData(ChatMessage chatMessage){
            binding.txtMessage.setText(chatMessage.message);
            binding.txtDateTime.setText(chatMessage.dateTime);
        }
    }
    static  class ReceivedMessageViewHolder extends  RecyclerView.ViewHolder {
        private  final ItemContainerReceivedMessageBinding binding;
        ReceivedMessageViewHolder(ItemContainerReceivedMessageBinding itemContainerReceivedMessageBinding){
            super(itemContainerReceivedMessageBinding.getRoot());
            binding = itemContainerReceivedMessageBinding;
        }
        void setData(ChatMessage chatMessage , Bitmap receiverProfileImage ){
            binding.txtMessage.setText(chatMessage.message);
            binding.txtDateTime.setText(chatMessage.dateTime);
            if (receiverProfileImage !=null ){
                binding.imageProfile.setImageBitmap(receiverProfileImage);
            }
        }
    }


}
