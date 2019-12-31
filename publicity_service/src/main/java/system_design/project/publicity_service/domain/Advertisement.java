package system_design.project.publicity_service.domain;

import java.time.Duration;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 
 * @author Jasper Derbaix
 * 
 * An Advertisement is publicity for a company, that can be the cinema itself.
 * 
 */
@Entity
@DiscriminatorValue("A")
public class Advertisement extends AMovie {
	private String company;

	public Advertisement() {
		super(0, null, null, null);
	}
	
	public Advertisement(long id, Duration duration, Category category, String name, String company) {
		super(id, duration, category, name);
		// TODO Auto-generated constructor stub
		this.company = company;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
