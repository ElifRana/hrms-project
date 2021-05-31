package kodlamaio.hrms.entities.concretes;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "employers")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name = "user_id")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobPostings"})
public class Employer extends User {
	
	@Column(name = "company_name")
	private String companyName;

	@Column(name = "web_adress")
	private String webAdress;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "is_verified")
	@JsonIgnore
	private boolean isVerified = false;
	
	@OneToMany(mappedBy = "employer")
	@JsonIgnore
	private List<JobPosting> jobPostings;
}
