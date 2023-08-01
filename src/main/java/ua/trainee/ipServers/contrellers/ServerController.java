package ua.trainee.ipServers.contrellers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.trainee.ipServers.models.IpS;
import ua.trainee.ipServers.models.Server;
import ua.trainee.ipServers.services.IpSService;
import ua.trainee.ipServers.services.ServerService;

@Controller
@RequestMapping("/servers")
public class ServerController {
    private final IpSService ipSService;
    private final ServerService serverService;

    @Autowired
    public ServerController(IpSService ipSService, ServerService serverService) {
        this.ipSService = ipSService;
        this.serverService = serverService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("servers", serverService.index());
        return "servers/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model, @ModelAttribute("ipS") IpS ipS) {
        model.addAttribute("server", serverService.findById(id));
        return "servers/show";
    }

    @PostMapping("/addIp/{idServer}")
    public String addIp(@PathVariable("idServer") Long idServer, @ModelAttribute("ip") IpS ipS) {
        serverService.addIp(idServer, ipS.getName());
        return "redirect:/servers/" + idServer;
    }

    @GetMapping("/createServer")
    public String createServerBlanc(@ModelAttribute("server") Server server, Model model){
        return "servers/createServer";
    }

    @PostMapping("/createServer")
    public String createServer(@ModelAttribute("server") Server server){
        serverService.create(server);
        return "redirect:/servers";
    }

    @DeleteMapping("/{id}")
    public String deleteServer(@PathVariable("id") Long id){
        serverService.deleteById(id);
        return "redirect:/servers";
    }

}
