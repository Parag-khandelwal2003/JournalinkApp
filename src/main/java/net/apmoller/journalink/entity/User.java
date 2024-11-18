package net.apmoller.journalink.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@Getter
//@Setter
//@ToString                          ===> All of these are equal to @Data
//@NoArgsConstructor
//@AllArgsConstructor

@Entity
@Data
@NoArgsConstructor
@Table(name = "Users",
        indexes = {@Index(columnList = "userName", unique = true)})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uid")
    private Long uid;

    @Column(name = "userName")
    @NonNull
    private String userName;
    @NonNull
    private String password;

    @Column(name = "journal_entries")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JournalEntry> journalEntries = new ArrayList<>();

    private List<String> roles;

}


