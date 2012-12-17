package ch.unibe.mcs.celebfinder.controller;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.FetchGroup;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import ch.unibe.mcs.celebfinder.model.Person;

public class PersonController {

	public static List<Person> getRandomPersons(int num, List<Person> forbidden) {
		List<Person> persons = getAllPersons();
		List<Person> candidates = new ArrayList<Person>();
		for (int i = 0; i < num; i++) {
			int index = (int) (Math.random() * (double) persons.size());
			Person candidate = persons.get(index);
			if (forbidden.contains(candidate) || candidates.contains(candidate)) {
				i--;
			} else {
				candidates.add(candidate);
			}
		}

		return candidates;
	}

	private static List<Person> getAllPersons() {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.getFetchPlan().setGroup(FetchGroup.ALL);
		try {
			Query q = pm.newQuery(Person.class);
			List<Person> persons = (List<Person>) q.execute();
			return persons;
		} finally {
			pm.close();
		}
	}
	
	public static Person getPersonFromName(String name){
		List<Person> persons = getAllPersons();
		for (Person person : persons) {
			if (person.toString().equals(name)) {
				return person;
			}
		}
		return null;
		
	}

	public static Person getPersonFromID(long id) {
		List<Person> persons = getAllPersons();
		for (Person person : persons) {
			if (person.getKey().getId() == id) {
				return person;
			}
		}
		return null;
//		PersistenceManager pm = PMF.get().getPersistenceManager();
//		pm.getFetchPlan().setGroup(FetchGroup.ALL);
//		try {
//			Person person = pm.getObjectById(Person.class, id);
//			return person;
//		} finally {
//			pm.close();
//		}
	}
	
}
