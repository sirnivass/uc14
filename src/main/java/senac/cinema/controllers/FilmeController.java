/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package senac.cinema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.cinema.models.Filme;
import senac.cinema.models.Analise;
import senac.cinema.repositories.FilmeRepository;
import senac.cinema.repositories.AnaliseRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private AnaliseRepository analiseRepository;

    @GetMapping
    public String listarFilmes(Model model) {
        List<Filme> filmes = filmeRepository.findAll();
        model.addAttribute("filmes", filmes); // nunca será null, será lista vazia se não houver filmes
        return "filmes"; // filmes.html
    }

    @GetMapping("/novo")
    public String novoFilme(Model model) {
        model.addAttribute("filme", new Filme());
        return "novo-filme";
    }

    @PostMapping
    public String salvarFilme(Filme filme) {
        filmeRepository.save(filme);
        return "redirect:/filmes";
    }

    @GetMapping("/{id}")
    public String detalhesFilme(@PathVariable Long id, Model model) {
        Optional<Filme> filme = filmeRepository.findById(id);
        if (filme.isPresent()) {
            model.addAttribute("filme", filme.get());
            return "detalhes-filme";
        } else {
            return "redirect:/filmes";
        }
    }

    @PostMapping("/{id}/analise")
    public String adicionarAnalise(
            @PathVariable Long id,
            @RequestParam("texto") String texto,
            @RequestParam("nota") int nota) {
        Optional<Filme> filmeOpt = filmeRepository.findById(id);
        if (filmeOpt.isPresent()) {
            Filme filme = filmeOpt.get();
            Analise analise = new Analise();
            analise.setTexto(texto);
            analise.setNota(nota);
            analise.setFilme(filme);
            analiseRepository.save(analise);
        }
        return "redirect:/filmes/" + id;
    }

    // Filmes - Atividade 03
    @PutMapping("/{id}")
    @ResponseBody
    public Filme atualizarFilme(@PathVariable Long id, @RequestBody Filme filme) {
        filme.setId(id);
        return filmeRepository.save(filme);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deletarFilme(@PathVariable Long id) {
        if (filmeRepository.existsById(id)) {
            filmeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Análises - Atividade 03
    @PutMapping("/analises/{id}")
    @ResponseBody
    public Analise atualizarAnalise(@PathVariable Long id, @RequestBody Analise analise) {
        analise.setId(id);
        return analiseRepository.save(analise);
    }

    @DeleteMapping("/analises/{id}")
    @ResponseBody
    public void deletarAnalise(@PathVariable Long id) {
        analiseRepository.deleteById(id);
    }

    @PatchMapping("/{id}/titulo")
    @ResponseBody
    public Filme atualizarTitulo(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Filme filme = filmeRepository.findById(id).orElseThrow();
        filme.setTitulo(body.get("titulo"));
        return filmeRepository.save(filme);
    }

    @GetMapping("/{id}/json")
    @ResponseBody
    public Filme getFilmeJson(@PathVariable Long id) {
        return filmeRepository.findById(id).orElse(null);
    }
    
    // Reaproveita o mesmo formulário
    @GetMapping("/{id}/editar")
    public String editarFilme(@PathVariable Long id, Model model) {
        Optional<Filme> filme = filmeRepository.findById(id);
        if (filme.isPresent()) {
            model.addAttribute("filme", filme.get());
            return "novo-filme";
        } else {
            return "redirect:/filmes";
        }
    }

    @PostMapping("/{id}")
    public String atualizarFilmeForm(@PathVariable Long id, Filme filme) {
        filme.setId(id);
        filmeRepository.save(filme);
        return "redirect:/filmes";
    }
}
