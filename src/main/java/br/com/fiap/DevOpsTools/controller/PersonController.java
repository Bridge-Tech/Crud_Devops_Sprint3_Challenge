package br.com.fiap.DevOpsTools.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.DevOpsTools.model.Person;
import br.com.fiap.DevOpsTools.repository.PersonRepository;

@Controller
public class PersonController {

	@Autowired
	private PersonRepository repository;
	
	@GetMapping("/people")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("people");
		List<Person> people = repository.findAll();
		modelAndView.addObject("people", people);
		return modelAndView;
	}
	
	@RequestMapping("/person/new")
	public String create(Person person) {
		return "register";
	}
	
	@PostMapping("/person/new")
	public String save(@Valid Person person, BindingResult result) { 
		if(result.hasErrors()) {
			return "register";
		}
		repository.save(person); 
		return "people";
	}
	
	@GetMapping("/person/delete/{id}")
	public ModelAndView personDel(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("personDel");
		Optional<Person>optional = repository.findById(id);
		Person people  = optional.get();
		modelAndView.addObject("people", people);
		return modelAndView;
		
	}
	
	
	@PostMapping("/person/delete/{id}")
	public String destroy(@PathVariable Long id) {
		
		Optional<Person> person = repository.findById(id);
		if (person.isEmpty()) {
			return "personDel";
		}
		repository.deleteById(id);
		return "people";
	}
	
	
	@GetMapping("/person/update/{id}")
	public ModelAndView personupdate(@PathVariable Long id) {
		
		ModelAndView modelAndView = new ModelAndView("personUpdate");
		Optional<Person>optional = repository.findById(id);
		Person peolple  = optional.get();
		modelAndView.addObject("people", peolple);
		return modelAndView;
	}
	
	
	@PostMapping("/person/update/{id}")
	public String update(@Valid Person newPerson, BindingResult result) {
		
		if(result.hasErrors()) {
			return "personUpdate";
		}
		Person person = newPerson;
		person.setAddress(newPerson.getAddress());
		person.setBirthdate(newPerson.getBirthdate());
		person.setName(newPerson.getName());
		person.setPhone(newPerson.getPhone());
		person.setSex(newPerson.getSex());

		repository.save(person); 
		return "people";
	}
	
	
}
