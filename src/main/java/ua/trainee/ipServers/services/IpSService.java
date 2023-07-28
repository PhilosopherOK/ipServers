package ua.trainee.ipServers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.trainee.ipServers.models.IpS;
import ua.trainee.ipServers.models.Server;
import ua.trainee.ipServers.repositorieses.IpSRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class IpSService {
    private final IpSRepository ipSRepository;

    @Autowired
    public IpSService(IpSRepository ipSRepository) {
        this.ipSRepository = ipSRepository;
    }

    public List<IpS> index(){
        return ipSRepository.findAll();
    }

    public IpS findById(long id){
        return ipSRepository.findById(id).orElse(null);
    }
    public List<IpS> findByServer(Server server){
        return ipSRepository.findByServer(server);
    }


    public void delete(long id){
        ipSRepository.deleteById(id);
    }
}
