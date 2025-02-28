package JournalApp.Controller;

import JournalApp.entity.User;
import JournalApp.service.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UsersServices usersServices;

    //Performing CRUD Operations


    //C- Create users
    @PostMapping
    public void addUser(@RequestBody  User user)
    {
        usersServices.saveUser(user);
    }


    //R- Read all users
    @GetMapping
    public List<User> getAllUsers()
    {
       return usersServices.getAllUsers();
    }

    //U- Update user credentials
   @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user , @PathVariable String username)
   {
       User userInDb= usersServices.findByUsername(username);
       if (userInDb !=null)
       {
           userInDb.setUsername(user.getUsername());
           userInDb.setPassword(user.getPassword());

           usersServices.saveUser(userInDb);
           return new ResponseEntity<>(HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }

   // D- Delete Users

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody User user)
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
