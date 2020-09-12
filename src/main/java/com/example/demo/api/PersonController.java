package com.example.demo.api;
import com.example.demo.service.PersonService;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;


@RequestMapping("/api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@Valid @NotNull @RequestBody Person person){
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    @GetMapping(path="{id}")
    public Person selectPersonById(@PathVariable("id") UUID id){
        return personService.selectPersonById(id)
                .orElse(null);
    }

    @DeleteMapping(path="{id}")
    public int deletePersonById(@PathVariable("id") UUID id){
        return personService.deletePersonById(id);
    }

    @PutMapping(path="{id}")
    public int updatePersonById(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Person personToUpdate){
        return personService.updatePersonById(id,personToUpdate);
    }
}
