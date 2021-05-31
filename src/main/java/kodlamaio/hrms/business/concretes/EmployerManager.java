package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.constants.Message;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService {

	private EmployerDao employerDao;

	@Autowired
	public EmployerManager(EmployerDao employerDao) {
		super();
		this.employerDao = employerDao;
	}

	@Override
	public Result add(Employer employer) {
		var result = BusinessRules.run(checkIfInformation(employer),
				checkIfEmployerEmail(employer.getEMail(), employer.getWebAdress()));
		if (result != null) {
			return result;
		}
		employerDao.save(employer);
		return new SuccessResult(Message.employerAdded);
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), Message.employerGetAll);
	}

	private Result checkIfInformation(Employer employer) {

		if (employer.getEMail() == "" || employer.getCompanyName() == "" || employer.getPassword() == ""
				|| employer.getPhoneNumber() == "" || employer.getWebAdress() == "") {
			return new ErrorResult(Message.employerAreaCheck);
		}
		return new SuccessResult();
	}

	private Result checkIfEmployerEmail(String email, String webSite) {
        Pattern validEmail =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = validEmail.matcher(email);
        
        String[] isEmailCompatible = email.split("@", 2);//İkiye bölüyor  öncesi@sonrası
        String webSiten = webSite.substring(4);//www. den sonrası
        
        if (!matcher.matches()) {
            return new ErrorResult(Message.employerErrorEmail);
            
        }else if(!isEmailCompatible[1].equals(webSiten)) {
        	return new ErrorResult(Message.employerErrorEmailNotCorporate);
        		
        }
        
        return new SuccessResult();
    }
	
//	 private Result checkEmailIsCompatibleWithDomain(String email, String companyName){
//
//	        String[] isEmailCompatible = email.split("@", 2);//İkiye bölüyor  öncesi@sonrası
//	        String webSite = companyName.substring(4);//www. den sonrası
//
//	        if (!isEmailCompatible[1].equals(companyName)){
//	            return new ErrorResult(Message.employerErrorEmail);
//	        }
//
//	        return new SuccessResult();
//	    }//Kurumsal eposta mı?
}
