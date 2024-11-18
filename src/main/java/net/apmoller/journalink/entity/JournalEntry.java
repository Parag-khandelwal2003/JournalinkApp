package net.apmoller.journalink.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

//@Getter
//@Setter
//@ToString                          ===> All of these are equal to @Data
//@AllArgsConstructor
@Entity
@Data
@NoArgsConstructor
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "uid")
    @JsonIgnore
    private User user;


}


