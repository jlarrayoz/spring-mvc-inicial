package uy.org.curso.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
	
	record Persona(String nombre, String apellido) {}
	
	//Al constuir la lista de esta forma la hacemos inmutable
	private List<Persona> personas = List.of(
			new Persona("Juan", "Larrayoz"), 
			new Persona("Pablo", "Rodriguez"), 
			new Persona("Jose", "Perez"));
	
	@GetMapping("/")
	//Este método ahora recibe el modelo como param
	public String index(Model model) {
		
		//Agregamos al modelo la lista de personas
		model.addAttribute("personas", personas);
		
		//Retornamos el nombre de la vista a renderizar
		return "index";
	}
	
	@PostMapping("/nuevaPersona")
	public String nuevaPersona(@ModelAttribute Persona nuevaPersona) {
		
		//Estos pasos son necesarios porque la lista de personas es inmutable
		List<Persona> aux = new ArrayList<Persona>();
		aux.add(nuevaPersona);
		aux.addAll(this.personas);
		this.personas = List.copyOf(aux);
		
		return "redirect:/";
	}
	
}
