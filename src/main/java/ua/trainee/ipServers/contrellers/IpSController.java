package ua.trainee.ipServers.contrellers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.trainee.ipServers.services.IpSService;

@Controller
@RequestMapping("/ipSs")
public class IpSController {
    private final IpSService ipSService;

@Autowired
    public IpSController(IpSService ipSService) {
        this.ipSService = ipSService;
    }

    @GetMapping
    public String ipIndex(Model model){
        model.addAttribute("ipSs", ipSService.index());
        return "ipSs/index";
    }

    @GetMapping("/{id}")
    public String ipShow(@PathVariable ("id") Long id, Model model){
    model.addAttribute("ipS", ipSService.findById(id));
    return "ipSs/show";
    }

    @DeleteMapping("/{id}")
    public String ipDelete(@PathVariable("id")Long id){
        ipSService.delete(id);
        return "redirect:/servers";
    }
}
