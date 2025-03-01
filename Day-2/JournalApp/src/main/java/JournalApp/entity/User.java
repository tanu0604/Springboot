package JournalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


//this one annotation @Data has all the annotations- @Getter, @Setter, @NoargsConstructor... etc
@Data
@Document(collection = "users")
public class User {

    @Id  // MongoDB ObjectId as primary key
    private ObjectId id;

    @Indexed(unique = true)  //this will make the username unique
    @NonNull
    private String username;
    @NonNull
    private String password;

    //basically the id of the JournalEntry entity will behave as a foreign key in the Users entity.
    // the @DBRef will take the id of the journal entry as a reference for a particular use.
    // it is linking the JournalEntries and the Users entity.
    @DBRef
    private List<JournalEntry> journalEntries=new ArrayList<>();
}
