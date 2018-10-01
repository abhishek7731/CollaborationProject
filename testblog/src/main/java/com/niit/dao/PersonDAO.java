package com.niit.dao;

import java.util.List;

import com.niit.model.Person;

public interface PersonDAO 
{
List<Person> getAllPersons();
public boolean addPerson(Person person);
void deletePerson(int id);
Person getPerson(int id);
void updatePerson(Person person);
}
