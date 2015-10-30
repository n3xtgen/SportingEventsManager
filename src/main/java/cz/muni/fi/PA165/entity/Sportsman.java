package cz.muni.fi.PA165.entity;

import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author jbouska
 */
@Entity
public class Sportsman {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String name;

	@NotNull
	private String Surname;

	@NotNull
	@Column(nullable=false,unique=true)
	private String personID;

	@ManyToOne
	private Event event;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
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
