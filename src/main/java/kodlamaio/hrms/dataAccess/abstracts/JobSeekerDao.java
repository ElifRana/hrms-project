package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import kodlamaio.hrms.entities.concretes.JobSeeker;

public interface JobSeekerDao extends JpaRepository<JobSeeker,Integer>{

	//Bu metod parametrede aldığı tc numarasına göre jobSeeker getirir
	JobSeeker getByNationalityId(String nationalityId);
	
	//Bu metod parametrede aldığı tc numarasına göre jobSeeker listesi getirir
	List<JobSeeker> findAllByNationalityId(String nationalityId);
}
