package tn.esprit.TRAVELGO.mapper;


import org.springframework.stereotype.Component;

import tn.esprit.TRAVELGO.Dto.MessageListDto;
import tn.esprit.TRAVELGO.entities.ChatConversation;

@Component
public class MessageListMapper {

    public MessageListDto toDto(ChatConversation conversation) {
        return new MessageListDto(
                conversation.getId(),
                conversation.getUser1().getId(),
                conversation.getUser2().getId(),
                conversation.getUser1().getUsername(),
                conversation.getUser2().getUsername(),
                conversation.getDateCreated()
        );
    }
}
