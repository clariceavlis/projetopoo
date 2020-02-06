package br.edu.ifal.agendacontatos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AgendaContatosController {

    @Autowired
    private ContatoRepository repo;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/agendacontatos")
    public ModelAndView listarContatos() {
        ModelAndView mv = new ModelAndView("agendacontatos.html");
        mv.addObject("contato", repo.findAll());
        return mv;
    }

    @RequestMapping("/agendacontatos/adicionar")
    public ModelAndView cadastro(Contato contato) {
        ModelAndView mv = new ModelAndView("redirect:/agendacontatos");
        repo.save(contato);
        return mv;
    }

    @RequestMapping("/agendacontatos/excluir/{id}")
    public ModelAndView excluir(@PathVariable(name = "id") long id) {
        ModelAndView mv = new ModelAndView("redirect:/agendacontatos");
        repo.deleteById(id);
        return mv;
    }

    @RequestMapping("/agendacontatos/atualizar/{id}")
    public ModelAndView atualizar(@PathVariable(name = "id") long id){
        ModelAndView resposta = new ModelAndView("agendacontatos.html");
        resposta.addObject("aluno", repo.findById(id).get());
        return resposta;
    }
}