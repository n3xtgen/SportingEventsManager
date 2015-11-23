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

	String passwordHash;

	@NotNull
	private String name;

	@NotNull
	private String surname;
        
        @NotNull
        @Column(nullable=false,unique=true)
	private String email;

	
        

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

         public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
        
	public Long getIdSportsman() {
		return idSportsman;
	}

	public void setIdSportsman(Long id) {
		this.idSportsman = id;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
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
                hash = 7 * hash + Objects.hashCode(getEmail());
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
		
                if (!Objects.equals(getEmail(), other.getEmail())) {
			return false;
		}
		return true;
	}

}