package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job_postings")
public class JobPosting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
//	@Column(name = "job_position_id")
//	private int jobPositionId;
//	
//	@Column(name = "city_id")
//	private int cityId;
//	
//	@Column(name = "employer_id")
//	private int employerId;
	
	@Column(name = "min_salary")
	private int mimSalary;
	
	@Column(name = "max_salary")
	private int maxSalary;
	
	@Column(name = "is_actived")
	@JsonIgnore
	private boolean isActived = true;
	
	@Column(name = "is_open")
	private boolean isOpen;
	
	@Column(name = "is_delete")
	private boolean isDelete;
	
	@Column(name = "created_date")
	@JsonIgnore
	private LocalDate createdDate = LocalDate.now();
	
	@Column(name = "deadline")
	private LocalDate deadline;
	
	@Column(name = "description")
	private String description;
	
	
	@ManyToOne
    @JoinColumn(name= "job_position_id")
	private JobPosition jobPosition;
	
	@ManyToOne
	@JoinColumn(name= "employer_id")
	private Employer employer;
	
	@ManyToOne
	@JoinColumn(name= "city_id")
	private City city;
	
}
