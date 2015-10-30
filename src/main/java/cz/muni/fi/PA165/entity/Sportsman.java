package cz.muni.fi.PA165.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author jbouska
 */
@Entity
public class Sportsman {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idSportsman;

	private String name;

	@NotNull
	private String Surname;

	@NotNull
	@Column(nullable=false,unique=true)
	private String personID;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "registeredSportsmans")
	private Set<Event> events = new HashSet<Event>();

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "competitors")
	private Set<Sport> competesInSports = new HashSet<Sport>();

	public Long getIdSportsman() {
		return idSportsman;
	}

	public void setIdSportsman(Long id) {
		this.idSportsman = id;
	}

	public String getSurnamename() {
		return Surname;
	}

	public void setSurnamename(String Surname) {
		this.Surname = Surname;
	}

	public String getPersonID() {
		return personID;
	}

	public void setPersonID(String personID) {
		this.personID = personID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 67 * hash + Objects.hashCode(getPersonID());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Sportsman)) {
			return false;
		}
		final Sportsman other = (Sportsman) obj;
		if (!Objects.equals(getPersonID(), other.getPersonID())) {
			return false;
		}
		return true;
	}




}
