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

	@NotNull
	private String name;

	@NotNull
	private String surname;

	@NotNull
	@Column(nullable=false,unique=true)
	private String citizenIdNumber;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "registeredSportsmans")
	private Set<Event> events = new HashSet<Event>();

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "competitors")
	private Set<Sport> competesInSports = new HashSet<Sport>();

	/********************
	 *** CONSTRUCTORS ***
	 ********************/

	public Sportsman() {
	}

	/*************************
	 *** GETTERS & SETTERS ***
	 *************************/

	public Long getIdSportsman() {
		return idSportsman;
	}

	public void setIdSportsman(Long id) {
		this.idSportsman = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCitizenIdNumber() {
		return citizenIdNumber;
	}

	public void setCitizenIdNumber(String citizenIdNumber) {
		this.citizenIdNumber = citizenIdNumber;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) { this.events = events; }

	public Set<Sport> getCompetesInSports() {
		return competesInSports;
	}

	public void setCompetesInSports(Set<Sport> competesInSports) {
		this.competesInSports = competesInSports;
	}

	/***************************
	 *** METHODS & FUNCTIONS ***
	 ***************************/

	/**
	 * Prida sport, ktery tento sportovec umi.
	 * @param sport
	 */
	public void addSport(Sport sport) {
		competesInSports.add(sport);
	}

	/**
	 * Odebere sport, ktery tento sportovec umi.
	 * @param sport
	 */
	public void removeSport(Sport sport) {
		competesInSports.remove(sport);
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 67 * hash + Objects.hashCode(getCitizenIdNumber());
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
		if (!Objects.equals(getCitizenIdNumber(), other.getCitizenIdNumber())) {
			return false;
		}
		return true;
	}

}
