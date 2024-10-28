package com.example.hospital.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Messages")
public class Message {

    // Unique identifier for each message
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ID of the user who sends the message
    private Long senderId;

    // ID of the user who receives the message
    private Long recipientId;

    // Message content, stored as TEXT in the database to allow for longer messages
    @Column(columnDefinition = "TEXT")
    private String content;

    // Timestamp indicating when the message was sent
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    // Getters and Setters for each field

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSenderId() { return senderId; }
    public void setSenderId(Long senderId) { this.senderId = senderId; }

    public Long getRecipientId() { return recipientId; }
    public void setRecipientId(Long recipientId) { this.recipientId = recipientId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
}
