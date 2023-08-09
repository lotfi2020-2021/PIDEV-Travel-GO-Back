package tn.esprit.TRAVELGO.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class MessageListDto {
    private final Long id;
    private final Long user1Id;
    private final Long user2Id;
    private final String user1Name;
    private final String user2Name;

    private final Date dateCreated;
    
}
