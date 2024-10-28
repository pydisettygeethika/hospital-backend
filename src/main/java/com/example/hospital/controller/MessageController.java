package com.example.hospital.controller;

import com.example.hospital.model.Message;
import com.example.hospital.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")  // Allows cross-origin requests from the React frontend running on localhost:3000
@RestController  // Marks this class as a REST controller for handling HTTP requests
@RequestMapping("/api/messages")  // Defines the base URL for all endpoints in this controller as /api/messages
public class MessageController {

    @Autowired
    private MessageService messageService;  // Injects the MessageService to handle message-related operations

    // Endpoint to send a new message
    @PostMapping("/send")
    public Message sendMessage(@RequestBody Message message) {
        // Calls the service method to send a message and returns the sent message details
        return messageService.sendMessage(message);
    }

    // Endpoint to get messages by a specific sender ID
    @GetMapping("/sender/{senderId}")
    public List<Message> getMessagesBySenderId(@PathVariable Long senderId) {
        // Retrieves and returns a list of messages sent by the specified sender
        return messageService.getMessagesBySenderId(senderId);
    }

    // Endpoint to get messages by a specific recipient ID
    @GetMapping("/recipient/{recipientId}")
    public List<Message> getMessagesByRecipientId(@PathVariable Long recipientId) {
        // Retrieves and returns a list of messages received by the specified recipient
        return messageService.getMessagesByRecipientId(recipientId);
    }
}
