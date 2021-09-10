package br.com.fiap.DevOpsTools.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.DevOpsTools.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
