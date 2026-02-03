package gestao_materiais_obra;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/materiais")
public class MaterialController {

    @Autowired
    private MaterialRepository repository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("materiais", repository.findAll());
        return "materiais";
    }

    @GetMapping("/novo")
    public String novo(Material material) {
        return "cadastrar-material";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Material material) {
        repository.save(material);
        return "redirect:/materiais";
    }


    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Material material = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Material inválido: " + id));

        model.addAttribute("material", material);
        return "cadastrar-material";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/materiais";
    }


}
