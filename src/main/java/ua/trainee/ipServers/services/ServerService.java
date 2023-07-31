package ua.trainee.ipServers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.trainee.ipServers.models.IpS;
import ua.trainee.ipServers.models.Server;
import ua.trainee.ipServers.repositorieses.IpSRepository;
import ua.trainee.ipServers.repositorieses.ServerRepository;

import java.util.Date;
import java.util.List;

@Service
public class ServerService {
    private final ServerRepository serverRepository;
    private final IpSRepository ipSRepository;

    @Autowired
    public ServerService(ServerRepository serverRepository, IpSRepository ipSRepository) {
        this.serverRepository = serverRepository;
        this.ipSRepository = ipSRepository;
    }

    public List<Server> index() {
        return serverRepository.findAll();
    }

    public Server findById(long id) {
        return serverRepository.findById(id).orElse(null);
    }

    public List<Server> findByNameStartingWith(String startingWith) {
        return serverRepository.findByNameStartingWith(startingWith);
    }

    public void addIp(long ServerId, String ipName) {
        List<IpS> IpList = ipSRepository.findAll();
        for(IpS ip:IpList){
            if(ip.getName().equals(ipName)){
                ip.getServer().getIpSList().remove(ip);
                ipSRepository.deleteById(ip.getId());
            }
        }
        IpS ipS = new IpS(ipName, findById(ServerId));
        ipS.setCreatedAt(new Date());
        ipSRepository.save(ipS);
    }
}
