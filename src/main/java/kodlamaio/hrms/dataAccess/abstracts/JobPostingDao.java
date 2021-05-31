package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.JobPosting;

public interface JobPostingDao extends JpaRepository<JobPosting, Integer> {

	// Sistemdeki tüm aktif iş ilanlarını listele
	@Query("From JobPosting where isOpen = true")
	List<JobPosting> getAllOpenJobPostingList();

	// Sistemdeki tüm aktif iş ilanlarını tarihe göre al
	List<JobPosting> getByisOpenTrueOrderByCreatedDateDesc(); // tarihe göre tersten sıralıyor

	// Sistemde bir firmaya ait tüm aktif iş ilanları listele
	List<JobPosting> getByisOpenTrueAndEmployer_Id(int id);
}
