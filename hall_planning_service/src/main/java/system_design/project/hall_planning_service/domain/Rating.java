package system_design.project.hall_planning_service.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Generated from JSON from OMDb API from: http://www.jsonschema2pojo.org/
 * 
 * @author robin
 *
 */
@Document
public class Rating {

	@SerializedName("Source")
	@Expose
	private String source;
	@SerializedName("Value")
	@Expose
	private String value;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}