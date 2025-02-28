package JournalApp.Controller;

import JournalApp.entity.JournalEntry;
import JournalApp.entity.User;
import JournalApp.service.JournalEntryService;
import JournalApp.service.UsersServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UsersServices usersServices;

    // Get all journal entries of a user
    @GetMapping("/{username}")
    public ResponseEntity<?> getAllJournalEntriesByUser(@PathVariable String username) {
        try {
            User user = usersServices.findByUsername(username);
            if (user == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            List<JournalEntry> allJournals = user.getJournalEntries();
            if (allJournals == null || allJournals.isEmpty()) {
                return new ResponseEntity<>("No Journal Entries found", HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(allJournals, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving journal entries: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create a new journal entries for a user
    @PostMapping("/{username}")
    public ResponseEntity<?> createEntriesOfUser(@RequestBody JournalEntry newEntry, @PathVariable String username) {
        try {
            User user = usersServices.findByUsername(username);
            if (user == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            journalEntryService.saveEntry(newEntry, username);
            return new ResponseEntity<>("Journal entry saved successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error saving journal entry: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    //Finding a journal entry by the id
    @GetMapping("id/{id}")
    public ResponseEntity<?> getJournalEntityById(@PathVariable ObjectId id) {
        Optional<JournalEntry> journalEntry = journalEntryService.findById(id);
        if (journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
    }
        return new ResponseEntity<>("No Journal entry of this id is found.",HttpStatus.NOT_FOUND);
    }


   //Updating the values of the journal entry of an user
    //title and username inside a journal entry of the user is changed/updated.
    @PutMapping("id/{username}/{id}")
    public ResponseEntity<?> updateJournalEntry
    (@PathVariable ObjectId id,
     @RequestBody JournalEntry newEntry,
     @PathVariable String username)
    {
        JournalEntry oldEntry=journalEntryService.findById(id).orElse(null);
        if (oldEntry!=null)
        {
            oldEntry.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")?newEntry.getTitle():oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent():oldEntry.getContent());

            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }
        return new ResponseEntity<>("Journal Entry Not found",HttpStatus.NOT_FOUND);
    }

    //Delete a journal entry by an id
    @DeleteMapping("/id/{username}/{id}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId id,@PathVariable String username)
    {
        journalEntryService.deleteById(id,username);
        return new ResponseEntity<>("Journal entry deleted successfully",HttpStatus.ACCEPTED);
    }
}
