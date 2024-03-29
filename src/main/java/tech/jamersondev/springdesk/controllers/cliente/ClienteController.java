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

    @GetMapping("cadastro-cliente")
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

    @GetMapping("list-clientes")
    public ModelAndView listClientes() {
        ModelAndView mv = new ModelAndView("cliente/list-clientes");
        mv.addObject("clientes", clienteRepo.findAll());
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("cliente/editar");
        mv.addObject("perfils", Perfil.values());
        mv.addObject("usuario", clienteRepo.findById(id));
        return mv;
    }

    @PostMapping("editar-cliente")
    public ModelAndView editar(@ModelAttribute Cliente cliente, @RequestParam(name = "file", required = false) MultipartFile imagem) {
        ModelAndView mv = new ModelAndView("cliente/editar");
        mv.addObject("usuario", cliente);

        try {
            if (imagem != null && !imagem.isEmpty()) {
                if (UploadUtil.fazerUploadImagem(imagem)) {
                    cliente.setImagem(imagem.getOriginalFilename());
                }
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

    @GetMapping("excluir/{id}")
    public ModelAndView deletar(@PathVariable("id") Integer id) {
        clienteRepo.deleteById(id);
        return home();
    }

    @GetMapping("home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home/index");

        return mv;
    }


}
