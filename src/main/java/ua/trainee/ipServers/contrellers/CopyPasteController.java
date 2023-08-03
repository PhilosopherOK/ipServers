package ua.trainee.ipServers.contrellers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.trainee.ipServers.services.CopyPasteService;

import java.io.IOException;

@Controller
@RequestMapping("/copyPaste")
public class CopyPasteController {
    private final CopyPasteService copyPasteService;

    public CopyPasteController(CopyPasteService copyPasteService) {
        this.copyPasteService = copyPasteService;
    }


    @PostMapping("/copy")
    public String copyTxtToData() {
        copyPasteService.copyTxtToData();
        return "redirect:/servers";
    }


    @PostMapping("/paste")
    public String pasteInFile() throws IOException {
        copyPasteService.pasteTxtToFile();
        return "redirect:/servers";
    }
}
