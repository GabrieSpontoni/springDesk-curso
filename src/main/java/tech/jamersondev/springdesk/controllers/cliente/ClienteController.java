package tech.jamersondev.springdesk.controllers.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import tech.jamersondev.springdesk.Enums.Perfil;
import tech.jamersondev.springdesk.model.Cliente;
import tech.jamersondev.springdesk.repository.ClienteRepository;
import tech.jamersondev.springdesk.util.UploadUtil;

@Controller
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepo;

    @GetMapping("cadastro")
    public ModelAndView cadastro(Cliente cliente) {
        ModelAndView mv = new ModelAndView("cliente/cadastro");
        mv.addObject("usuario", new Cliente());
        mv.addObject("perfils", Perfil.values());
        return mv;
    }

    @PostMapping("cadastro-cliente")
    public ModelAndView cadastro(@ModelAttribute Cliente cliente, @RequestParam("file") MultipartFile imagem) {
        ModelAndView mv = new ModelAndView("cliente/cadastro");
        mv.addObject("usuario", cliente);

        try {
            if (UploadUtil.fazerUploadImagem(imagem)) {
                cliente.setImagem(imagem.getOriginalFilename());
            }
            clienteRepo.save(cliente);
            System.out.println("Cliente salvo com sucesso" + cliente.getNome() + " " + cliente.getImagem());
            return home();
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