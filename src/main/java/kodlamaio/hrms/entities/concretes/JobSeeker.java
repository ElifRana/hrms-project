package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "job_seekers")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false) 
@PrimaryKeyJoinColumn(name = "user_id")
public class JobSeeker extends User{

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "nationality_id")
	private String nationalityId;
	
	@Column(name = "year_of_birth")
	private LocalDate yearOfBirth;
	
	@Column(name = "is_verified")
	private boolean isVerified = false;
}
