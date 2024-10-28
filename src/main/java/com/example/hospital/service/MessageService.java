package com.example.hospital.service;

import com.example.hospital.model.Message;
import com.example.hospital.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing messages in the hospital system.
 * Provides methods to send messages and retrieve messages by sender or recipient.
 */
@Service
public class MessageService {
    
    @Autowired
    private MessageRepository messageRepository;

    // Sends a new message by saving it to the database.
    
    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }

    // Retrieves a list of messages sent by a specific sender.
    
    public List<Message> getMessagesBySenderId(Long senderId) {
        // Placeholder for logic to retrieve messages by sender ID
        return null; 
    }

    // Retrieves a list of messages received by a specific recipient.
   
    public List<Message> getMessagesByRecipientId(Long recipientId) {
        // Placeholder for logic to retrieve messages by recipient ID
        return null;
    }
}
