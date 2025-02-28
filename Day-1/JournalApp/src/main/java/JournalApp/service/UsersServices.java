package JournalApp.service;

import JournalApp.Repository.UsersRepository;
import JournalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServices {

    @Autowired
    private UsersRepository usersRepository;

    // Save User
    public void saveUser(User user) {
        usersRepository.save(user);
    }

    // Get All Users
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    // Find User by ID
    public Optional<User> findById(ObjectId id) {
        return usersRepository.findById(id);
    }

    // Delete User by ID
    public void deleteById(ObjectId id) {
        usersRepository.deleteById(id);
    }

    // Find User by username
    public User findByUsername(String username) {
        return usersRepository.findByUsername(username); // Fixed method call
    }
}
