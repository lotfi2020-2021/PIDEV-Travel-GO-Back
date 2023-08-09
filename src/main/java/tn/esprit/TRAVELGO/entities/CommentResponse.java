package tn.esprit.TRAVELGO.entities;

import java.time.LocalDateTime;
import java.util.Set;


public interface CommentResponse {
    Long getId();
    String getContent();
    LocalDateTime getCreatedAt();
    String getCreatedBy();
    LocalDateTime getUpdatedAt();
    String getUpdatedBy();

    Set<CommentResponse> getComments();
}
