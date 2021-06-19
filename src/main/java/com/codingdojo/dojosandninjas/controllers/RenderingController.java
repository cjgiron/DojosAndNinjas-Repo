package com.codingdojo.dojosandninjas.controllers;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codingdojo.dojosandninjas.models.Dojo;
import com.codingdojo.dojosandninjas.models.Ninja;
import com.codingdojo.dojosandninjas.services.DojoService;
import com.codingdojo.dojosandninjas.services.NinjaService;

@Controller
public class RenderingController {
	
	private final DojoService dojoService;
	private final NinjaService ninjaService;
	
	public RenderingController(DojoService dojoService, NinjaService ninjaService) {
		this.ninjaService = ninjaService;
		this.dojoService = dojoService;
	}
	
	@RequestMapping("/dojos")
	public String dojos(Model model) {
		List<Dojo> dojos = dojoService.allDojos();
		model.addAttribute("dojos", dojos);
		return "dojos/allDojos.jsp";
	}
	
	@RequestMapping("/dojos/{id}")
	public String showNinjas(@PathVariable Long id, Model model) {
		Dojo dojo = dojoService.findDojo(id);
		List<Ninja> ninjas = ninjaService.getNinjasByDojo(dojo);
		model.addAttribute("dojo", dojo);
		model.addAttribute("ninjas", ninjas);
		return "dojos/ninjas.jsp";
	}
	
	
	@RequestMapping("/dojos/new")
    public String newDojo(@ModelAttribute("dojo") Dojo dojo) {
        return "/dojos/new.jsp";
    }
	
	@RequestMapping(value="/dojos", method=RequestMethod.POST)
	public String create(@ModelAttribute("dojo") Dojo dojo) {
		dojoService.createDojo(dojo);
		return "redirect:/dojos";
	}
	
	@GetMapping("/ninjas/new")
	public String createNinjaPage(@ModelAttribute("ninja") Ninja ninja, Model model) {
		List<Dojo> dojos = dojoService.allDojos();
		model.addAttribute("dojos", dojos);
		return "/dojos/newNinja.jsp";
	}
	
	@RequestMapping(value="/ninjas", method=RequestMethod.POST)
	public String create(@ModelAttribute("ninja") Ninja ninja) {
		ninjaService.createNinja(ninja);
		return "redirect:/dojos";
	}
}
