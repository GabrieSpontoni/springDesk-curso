package tech.jamersondev.springdesk.controllers.tecnico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import tech.jamersondev.springdesk.Enums.Perfil;
import tech.jamersondev.springdesk.model.Tecnico;
import tech.jamersondev.springdesk.repository.TecnicoRepository;
import tech.jamersondev.springdesk.util.UploadUtil;

@Controller
@RequestMapping("tecnico")
public class TecnicoController {

    @Autowired
    private TecnicoRepository tecRepo;

    @GetMapping("cadastro")
    public ModelAndView cadastro(Tecnico tecnico) {
        ModelAndView mv = new ModelAndView("tecnico/cadastro");
        mv.addObject("usuario", new Tecnico());
        mv.addObject("perfils", Perfil.values());
        return mv;
    }

    @PostMapping("cadastro-tecnico")
    public ModelAndView cadastro(@ModelAttribute Tecnico tecnico, @RequestParam("file") MultipartFile imagem) {
        ModelAndView mv = new ModelAndView("tecnico/cadastro");
        mv.addObject("usuario", tecnico);

        try {
            if (UploadUtil.fazerUploadImagem(imagem)) {
                tecnico.setImagem(imagem.getOriginalFilename());
            }
            tecRepo.save(tecnico);
            System.out.println("Cliente salvo com sucesso" + tecnico.getNome() + " " + tecnico.getImagem());
            return home();
        } catch (Exception e) {
            mv.addObject("erro", "Erro ao fazer upload da imagem: " + e.getMessage());
            System.out.println("Erro ao fazer upload da imagem: " + e.getMessage());
            return mv;
        }


    }

    @GetMapping("list-tecnicos")
    public ModelAndView listTecnicos() {
        ModelAndView mv = new ModelAndView("tecnico/list-tecnicos");
        mv.addObject("tecnicos", tecRepo.findAll());
        return mv;
    }

    @GetMapping("editar/{id}")
    public ModelAndView editarTecnico(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView("tecnico/editar");
        mv.addObject("usuario", tecRepo.findById(id));
        mv.addObject("perfils", Perfil.values());
        return mv;
    }

    @PostMapping("editar-tecnico")
    public ModelAndView editarTecnico(@ModelAttribute Tecnico tecnico, @RequestParam(name = "file", required = false) MultipartFile imagem) {
        ModelAndView mv = new ModelAndView("tecnico/editar");
        mv.addObject("usuario", tecnico);
        try {
            if (imagem != null && !imagem.isEmpty()) {
                if (UploadUtil.fazerUploadImagem(imagem)) {
                    tecnico.setImagem(imagem.getOriginalFilename());
                }
            }
            tecRepo.save(tecnico);
            System.out.println("Cliente salvo com sucesso" + tecnico.getNome() + " " + tecnico.getImagem());
            return listTecnicos();
        } catch (Exception e) {
            mv.addObject("erro", "Erro ao fazer upload da imagem: " + e.getMessage());
            System.out.println("Erro ao fazer upload da imagem: " + e.getMessage());
            return mv;
        }
    }

    @GetMapping("home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home/index");

        return mv;
    }


}
