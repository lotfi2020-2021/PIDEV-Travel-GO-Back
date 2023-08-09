package tn.esprit.TRAVELGO.mapper;

import tn.esprit.TRAVELGO.Dto.MessageDto;
import tn.esprit.TRAVELGO.entities.ChatMessage;

public class ChatMessageMapper {
    public static MessageDto toDto(ChatMessage message) {
        return new MessageDto(
                message.getSender().getId(),
                message.getSender().getUsername(),
                message.getContent(),
                message.getDateCreated()
        );
    }
}
