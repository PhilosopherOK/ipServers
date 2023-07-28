package ua.trainee.ipServers.repositorieses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.trainee.ipServers.models.Server;

import java.util.List;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {
    List<Server> findByNameStartingWith(String startingWith);
}
