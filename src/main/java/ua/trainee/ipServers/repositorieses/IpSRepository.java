package ua.trainee.ipServers.repositorieses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.trainee.ipServers.models.IpS;
import ua.trainee.ipServers.models.Server;

import java.util.List;

@Repository
public interface IpSRepository extends JpaRepository<IpS, Long> {
    List<IpS> findByServer(Server server);
}
