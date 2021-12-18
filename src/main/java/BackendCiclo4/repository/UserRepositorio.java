package BackendCiclo4.repository;

import BackendCiclo4.model.Order;
import BackendCiclo4.model.User;
import java.util.List;
import java.util.Optional;

import BackendCiclo4.repository.crud.InterfaceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositorio {
    @Autowired
    private InterfaceUser userCrudRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getAll() {
        return (List<User>) userCrudRepository.findAll();
    }

    public Optional<User> getUser(int id) {
        return userCrudRepository.findById(id);
    }

    public User create(User user) {
        return userCrudRepository.save(user);
    }
    
    public void update(User user) {
        userCrudRepository.save(user);
    }
    
    public void delete(User user) {
        userCrudRepository.delete(user);
    }

    public boolean emailExists(String email) {
        Optional<User> usuario = userCrudRepository.findByEmail(email);
        
        return !usuario.isEmpty();
    }
    
    public Optional<User> authenticateUser(String email, String password) {
        return userCrudRepository.findByEmailAndPassword(email, password);
    }

    public Optional<User> lastUserId(){
        return userCrudRepository.findTopByOrderByIdDesc();
    }

    public List<User> usersBirthday(String birthday) {
        Query query = new Query();
        Criteria criterio = Criteria.where("monthBirthtDay").is(birthday);

        query.addCriteria(criterio);

        List<User> users = mongoTemplate.find(query,User.class);

        return users;
    }
     
}
