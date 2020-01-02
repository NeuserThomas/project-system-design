package system_design.project.staff_service.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;


@Table(value="cinema")
public class Cinema {

	@Id
	@PrimaryKey("cinema_id")
	private Long id;

	private String name;

	public Cinema(Long id, String name){
		this.id = id;
		this.name = name;
	}

	public Cinema() {
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(final Object o) {
		if (o == this) return true;
		if (!(o instanceof Cinema)) return false;
		final Cinema other = (Cinema) o;
		if (!other.canEqual((Object) this)) return false;
		final Object this$id = this.getId();
		final Object other$id = other.getId();
		if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
		final Object this$name = this.getName();
		final Object other$name = other.getName();
		if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
		return true;
	}

	protected boolean canEqual(final Object other) {
		return other instanceof Cinema;
	}

	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		final Object $id = this.getId();
		result = result * PRIME + ($id == null ? 43 : $id.hashCode());
		final Object $name = this.getName();
		result = result * PRIME + ($name == null ? 43 : $name.hashCode());
		return result;
	}

	public String toString() {
		return "Cinema(id=" + this.getId() + ", name=" + this.getName() + ")";
	}
}
