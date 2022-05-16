package com.example.app_chat.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_chat.databinding.ItemContainerRecentConversionBinding;
import com.example.app_chat.listeners.ConversionListener;
import com.example.app_chat.models.ChatMessage;
import com.example.app_chat.models.User;

import java.util.List;

public class RecentConversationsAdapter extends RecyclerView.Adapter<RecentConversationsAdapter.ConversationsViewHolder> {

    private final List<ChatMessage> chatMessages;
    private final ConversionListener conversionListener;

    public RecentConversationsAdapter(List<ChatMessage> chatMessages, ConversionListener conversionListener) {
        this.chatMessages = chatMessages;
        this.conversionListener = conversionListener;
    }

    @NonNull
    @Override
    public ConversationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConversationsViewHolder(
                ItemContainerRecentConversionBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationsViewHolder holder, int position) {
        holder.setData(chatMessages.get(position ));
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    class ConversationsViewHolder extends RecyclerView.ViewHolder   {
        ItemContainerRecentConversionBinding binding ;
        ConversationsViewHolder(ItemContainerRecentConversionBinding itemContainerRecentConversionBinding){
            super(itemContainerRecentConversionBinding.getRoot());
            binding = itemContainerRecentConversionBinding;
        }
        void setData(ChatMessage chatMessage ){
            binding.imageProfile.setImageBitmap(getConversionImage(chatMessage.conversionImage));
            binding.txtName.setText(chatMessage.conversionName);
            binding.txtRecentMessage.setText(chatMessage.message);
            binding.getRoot().setOnClickListener(view -> {
                User user = new User();
                user.id = chatMessage.conversionId;
                user.name = chatMessage.conversionName;
                user.image = chatMessage.conversionImage;
                conversionListener.onConversionClicked(user);
            });
        }
    }

    private Bitmap getConversionImage(String encodeImage)   {
        byte[]  bytes = Base64.decode(encodeImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

}
