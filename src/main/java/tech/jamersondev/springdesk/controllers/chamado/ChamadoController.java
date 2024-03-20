package tech.jamersondev.springdesk.controllers.chamado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import tech.jamersondev.springdesk.Enums.Prioridade;
import tech.jamersondev.springdesk.Enums.Status;
import tech.jamersondev.springdesk.model.Chamado;
import tech.jamersondev.springdesk.repository.ChamadoRepository;


@Controller
@RequestMapping("chamado")
public class ChamadoController {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @GetMapping("cadastro-chamado")
    public ModelAndView cadastro(Chamado chamado) {
        ModelAndView mv = new ModelAndView("chamado/cadastro");
        mv.addObject("status", Status.values());
        mv.addObject("prioridade", Prioridade.values());
        return mv;
    }


    @GetMapping("home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home/index");

        return mv;
    }


}
