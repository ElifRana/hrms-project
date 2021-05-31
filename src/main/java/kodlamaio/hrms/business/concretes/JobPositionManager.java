package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.business.constants.Message;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.entities.concretes.JobPosition;

@Service
public class JobPositionManager implements JobPositionService {

	private JobPositionDao jobPositionDao;

	@Autowired
	public JobPositionManager(JobPositionDao jobPositionDao) {
		super();
		this.jobPositionDao = jobPositionDao;
	}

	@Override
	public DataResult<List<JobPosition>> getAll() {
		return new SuccessDataResult<List<JobPosition>>(this.jobPositionDao.findAll(),
				Message.jobPositionGetAll);
	}

	@Override
	public Result add(JobPosition jobPosition) {
		var result = BusinessRules.run(checkIfRealPositionExists(jobPosition));
		if (result != null) {
			return result;
		}
		this.jobPositionDao.save(jobPosition);
		return new SuccessResult(Message.jobPositionAdded);
	}

	//verilen position kime ait ise veritabanından o jobPosition getirir
	@Override
	public DataResult<JobPosition> getByPosition(String position) {
		return new SuccessDataResult<JobPosition>(this.jobPositionDao.findByPosition(position));
	}

	public Result checkIfRealPositionExists(JobPosition jobPosition) {
		var result = jobPositionDao.findAllByPosition(jobPosition.getPosition()).stream().count() != 0;
		if (result) {
			return new ErrorResult(Message.jobPositionAviable);
		}
		return new SuccessResult();
	}

}
