package JournalApp.service;

import JournalApp.Repository.JournalEntryRepo;
import JournalApp.entity.JournalEntry;
import JournalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UsersServices usersServices;

    // Save a Journal Entry
    //Here now @Transactional annotation will be used because we are separately saving in the user db and journal db
    // this annotation will either save in all or show error in all.

//     public void saveEntry(JournalEntry journalEntry, String username) {
//        User user = usersServices.findByUsername(username);
//        journalEntry.setDate(LocalDate.from(LocalDateTime.now()));
//        JournalEntry saved=journalEntryRepo.save(journalEntry);
//        user.getJournalEntries().add(saved);
//        usersServices.saveUser(user);
//    }
    @Transactional public void saveEntry(JournalEntry journalEntry, String username)
    {
        try {
            User user = usersServices.findByUsername(username);
            journalEntry.setDate(LocalDate.from(LocalDateTime.now()));
            JournalEntry saved=journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(saved);
            usersServices.saveUser(user);
        }
        catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving this entry",e);
        }
}

    public void saveEntry(JournalEntry journalEntry)
    {
        journalEntryRepo.save(journalEntry);
    }
    // Get All Entries
    public List<JournalEntry> getAll() {
        return journalEntryRepo.findAll();
    }

    // Find Entry by ID
    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepo.findById(id);
    }

    // Delete Entry by ID
    public void deleteById(ObjectId id, String username)
    {
        User user=usersServices.findByUsername(username);

        //this statement is removing the journal entry from the list of journal entries present in the users db
        user.getJournalEntries().removeIf(x->x.getId().equals(id));
        usersServices.saveUser(user);

        //this is removing from the journal_entry db;
        journalEntryRepo.deleteById(id);
    }
}
