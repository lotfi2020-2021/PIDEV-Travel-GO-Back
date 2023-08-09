package tn.esprit.TRAVELGO.mapper;



import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import tn.esprit.TRAVELGO.Dto.ConversationDto;
import tn.esprit.TRAVELGO.entities.ChatConversation;
import tn.esprit.TRAVELGO.entities.ChatMessage;

@Component
public class ChatConversationMapper {
    public ConversationDto toDto(ChatConversation conversation) {
        return new ConversationDto(
                conversation.getId(),
                conversation.getUser1().getId(),
                conversation.getUser2().getId(),
                conversation.getUser1().getUsername(),
                conversation.getUser2().getUsername(),
                conversation.getDateCreated(),
                conversation
                        .getMessages()
                        .stream()
                        .sorted(Comparator.comparing(ChatMessage::getDateCreated))
                        .map(ChatMessageMapper::toDto)
                        .collect(Collectors.toList()));
    }

}
