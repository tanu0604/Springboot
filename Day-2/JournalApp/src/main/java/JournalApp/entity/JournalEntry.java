package JournalApp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


//this one annotation @Data has all the annotations- @Getter, @Setter, @NoargsConstructor... etc
@Data
@NoArgsConstructor
@Document(collection = "journal_entry")
public class JournalEntry {

    @Id  // MongoDB ObjectId as primary key
    private ObjectId id;

    @NonNull
    private String title;
    private String content;
    private LocalDate date;

}
