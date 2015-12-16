package cz.muni.fi.PA165.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author jbouska
 */
@Entity
public class Usr {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	String passwordHash;

	@NotNull
	private String name;

	@NotNull
	private String surname;
        
	@NotNull
	@Column(nullable=false,unique=true)
	private String email;

	/**
	 * Seznam prihlasek sportovce k jednotlivym zavodum a jeho vysledek.
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usr")
	private Set<Entry> entries = new HashSet<Entry>();

	/********************
	 *** CONSTRUCTORS ***
	 ********************/

	public Usr() {
	}

	/*************************
	 *** GETTERS & SETTERS ***
	 *************************/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Entry> getEntries() { return Collections.unmodifiableSet(entries); }

	public void addEntry(Entry entry) {
		entries.add(entry);
	}

	public void removeEntry(Entry entry) {
		entries.remove(entry);
	}

	/***************************
	 *** METHODS & FUNCTIONS ***
	 ***************************/

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
		if (!(obj instanceof Usr)) {
			return false;
		}
		final Usr other = (Usr) obj;
		
                if (!Objects.equals(getEmail(), other.getEmail())) {
			return false;
		}
		return true;
	}

}
