package ua.trainee.ipServers.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Server")
public class Server {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "server")
    private List <IpS> ipSList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IpS> getIpSList() {
        return ipSList;
    }

    public void setIpSList(List<IpS> ipSList) {
        this.ipSList = ipSList;
    }

    public Server() {
    }

    public Server(String name) {
        this.name = name;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
