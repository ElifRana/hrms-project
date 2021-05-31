package kodlamaio.hrms.business.concretes;

import java.util.List;
import org.springframework.stereotype.Service;
import kodlamaio.hrms.business.abstracts.JobPostingService;
import kodlamaio.hrms.business.constants.Message;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobPostingDao;
import kodlamaio.hrms.entities.concretes.JobPosting;

@Service
public class JobPostingManager implements JobPostingService{

	private JobPostingDao jobPostingDao;

	
	public JobPostingManager(JobPostingDao jobPostingDao) {
		super();
		this.jobPostingDao = jobPostingDao;
	}


	@Override
	public DataResult<List<JobPosting>> getAll() {
		return new SuccessDataResult<List<JobPosting>>(this.jobPostingDao.findAll(), Message.jobPostingGetAll);
	}


	@Override
	public Result add(JobPosting jobPosting) {
		this.jobPostingDao.save(jobPosting);
		return new SuccessResult(Message.jobPostingAdded);
	}
	
	@Override
	public DataResult<JobPosting> getById(int id) {
		return new SuccessDataResult<JobPosting>(this.jobPostingDao.getOne(id));
	}

	@Override
	public Result update(JobPosting jobPosting) {
		this.jobPostingDao.save(jobPosting);
		return new SuccessResult(Message.jobPostingUpdate);
	}
	
	@Override
	public Result changeOpenToClose(int id) {
		
		if(getById(id) == null) {
			return new ErrorResult(Message.jobPostingNoSuchExists);
		}
		if(getById(id).getData().isOpen() == false) {
			return new ErrorResult(Message.jobPostingIsAlreadyClosed);
		}
		
		JobPosting jobPosting = getById(id).getData();
		jobPosting.setOpen(false);
		update(jobPosting);
		return new SuccessResult(Message.jobPostingClose);
	}

	@Override
	public DataResult<List<JobPosting>> getAllOpenJobPostingList() {
		return new SuccessDataResult<List<JobPosting>>(this.jobPostingDao.getAllOpenJobPostingList(), 
				Message.jobPostingGetAllOpenJobPostingList);
	}


	@Override
	public DataResult<List<JobPosting>> getByisOpenTrueOrderByCreatedDateDesc() {
		return new SuccessDataResult<List<JobPosting>>(this.jobPostingDao.getByisOpenTrueOrderByCreatedDateDesc(), 
				Message.jobPostingGetByisOpenTrueOrderByCreatedDateDesc);
		
	}
	

	@Override
	public DataResult<List<JobPosting>> getByisOpenTrueAndEmployer_Id(int id) {
		return new SuccessDataResult<List<JobPosting>>(this.jobPostingDao.getByisOpenTrueAndEmployer_Id(id), 
				Message.jobPostingGetByisOpenTrueAndEmployer_Id);
	}
	
}
