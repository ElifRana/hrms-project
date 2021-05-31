package kodlamaio.hrms.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kodlamaio.hrms.business.abstracts.JobPostingService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobPosting;

@RestController
@RequestMapping("/api/job_postings")
public class JobPostingController {

	private JobPostingService jobPostingService;

	@Autowired
	public JobPostingController(JobPostingService jobPostingService) {
		super();
		this.jobPostingService = jobPostingService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<JobPosting>> getAll() {
		return this.jobPostingService.getAll();
	}

	@PostMapping("/add")
	public Result add(@RequestBody JobPosting product) {
		return this.jobPostingService.add(product);
	}
	
	@PostMapping("/ChangeOpenToClose")
	public Result changeOpenToClose(int id) {
		return this.jobPostingService.changeOpenToClose(id);
	}
	
	@GetMapping("/getAllOpenJobPostingList")
	public DataResult<List<JobPosting>> getAllOpenJobPostingList(){
		return this.jobPostingService.getAllOpenJobPostingList();
	}
	
	@GetMapping("/getByisOpenTrueOrderByCreatedDateDesc")
	public DataResult<List<JobPosting>> getByisOpenTrueOrderByCreatedDateDesc() {
	 return this.jobPostingService.getByisOpenTrueOrderByCreatedDateDesc();
	} 
	
	@GetMapping("/getByisOpenTrueAndEmployer_Id")
	public DataResult<List<JobPosting>> getByisOpenTrueAndEmployer_Id(int id) {
		return this.jobPostingService.getByisOpenTrueAndEmployer_Id(id);
	}
}
