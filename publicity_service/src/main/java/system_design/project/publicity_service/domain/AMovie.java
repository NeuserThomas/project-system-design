package system_design.project.publicity_service.domain;

import java.text.MessageFormat;
import java.time.Duration;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * 
 * @author Jasper Derbaix
 * 
 * AMovie is the abstract class of all movie fragments.
 * They have some properties in common. 
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "MovieType", discriminatorType = DiscriminatorType.STRING)
public abstract class AMovie {
	@Id
	@GeneratedValue
	private long id;
	private Duration duration;
	private Category category;
	private String name;
	


	public AMovie() {
	}
	
	/**
	 * @param id
	 * @param duration
	 * @param category
	 */
	public AMovie(long id, Duration duration, Category category, String name) {
		super();
		this.id = id;
		this.duration = duration;
		this.category = category;
		this.name = name;
	}
	
	public Duration getDuration() {
		return duration;
	}
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	public Category getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return MessageFormat.format("Id: {0}\t Name: {1} \t Category: {2} \t Duration: {3}" ,
				this.id, this.name, this.category.name(), this.duration.toString());
	}
}
