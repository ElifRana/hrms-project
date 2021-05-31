package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.constants.Message;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.hrms.entities.concretes.JobSeeker;

@Service
public class JobSeekerManager implements JobSeekerService {

	private JobSeekerDao jobSeekerDao;

	@Autowired
	public JobSeekerManager(JobSeekerDao jobSeekerDao) {
		super();
		this.jobSeekerDao = jobSeekerDao;
	}

	public Result add(JobSeeker jobSeeker) {
		var result = BusinessRules.run(checkIfInformation(jobSeeker)
				,checkIfEmailValid(jobSeeker.getEMail())
				,checkIfJobSeekerNationalIdExists(jobSeeker));
		if (result != null) {
			return result;
		}
		jobSeekerDao.save(jobSeeker);
		return new SuccessResult(Message.employerAdded);
	}

	@Override
	public DataResult<List<JobSeeker>> getAll() {
		return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerDao.findAll(), Message.jobSeekerGetAll);
	}

	//nationalityId kime ait ise veritabanından o jobSeekerı getirir
	@Override
	public DataResult<JobSeeker> getByNationalityId(String nationalityId) {
		return new SuccessDataResult<JobSeeker>(this.jobSeekerDao.getByNationalityId(nationalityId),
				Message.jobSeekerGetByNationalityId);
	}

	private Result checkIfInformation(JobSeeker jobSeeker) {
		if (jobSeeker.getFirstName() == "" || jobSeeker.getLastName() == "" || jobSeeker.getNationalityId() == ""
				|| jobSeeker.getEMail() == "" || jobSeeker.getYearOfBirth() == null) {
			return new ErrorResult(Message.jobSeekerAreaCheck);
		}
		return new SuccessResult();
	}
    
	//Regex kullanarak uygun email getirir
	private Result checkIfEmailValid(String email) {
        Pattern validEmail =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = validEmail.matcher(email);
        if (!matcher.matches()) {
            return new ErrorResult(Message.employerErrorEmail);
        }

        return new SuccessResult();
    }

	private Result checkIfJobSeekerNationalIdExists(JobSeeker jobSeeker) {
		var result = jobSeekerDao.findAllByNationalityId(jobSeeker.getNationalityId()).stream().count() != 0;
		if(result) {
			return new ErrorResult(Message.jobSeekerNationalityIdExists);
		}
		return new SuccessResult();
	}
}
