package BackendCiclo4.repository.crud;

import BackendCiclo4.model.Supplements;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface InterfaceSupplements extends MongoRepository<Supplements, String> {

    public List<Supplements> findByPrice(double price);
    public List<Supplements> findByDescriptionContainingIgnoreCase(String description);
}
