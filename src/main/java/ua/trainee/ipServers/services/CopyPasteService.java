package ua.trainee.ipServers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.trainee.ipServers.models.Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

@Service
public class CopyPasteService {
    private final ServerService serverService;

    @Autowired
    public CopyPasteService(ServerService serverService) {
        this.serverService = serverService;
    }

    public void copyTxtToData(){
        File file = new File("rotmgIN.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String str = scanner.nextLine();
                String[] massiveStr = str.split(";");
                serverService.createIfNotExist(new Server(massiveStr[0]));
                Server server = serverService.findByName(massiveStr[0]);
                for (int i = 1; i < massiveStr.length; i++) {
                    serverService.addIp(server.getId(), massiveStr[i]);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void pasteTxtToFile() throws IOException {
        File file = new File("rotmgOUT.txt");
        if(!file.isFile()){
            file.createNewFile();
        };
        PrintWriter printWriter = new PrintWriter(file);
        List<Server> servers = serverService.index();
        for (int i = 0; i < servers.size(); i++) {
            Server server = servers.get(i);
            StringBuilder sb = new StringBuilder(server.getName());
            for (int j = 0; j < server.getIpSList().size(); j++) {
                sb.append(";");
                sb.append(server.getIpSList().get(j).getName());
            }
            printWriter.println(sb);
        }
        printWriter.close();
    }
}
