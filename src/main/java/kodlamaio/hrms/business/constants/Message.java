package kodlamaio.hrms.business.constants;

public class Message {

	//JobPosition
	public static String jobPositionGetAll = " Job Position Listed ";
	public static String jobPositionAdded = " Job Position Added ";
	public static String jobPositionAviable = " Job position with this name already exists ! ";
	
	//Employer
	public static String employerAdded = " Employer Added ";
	public static String employerGetAll = " Employer Listed ";
	public static String employerAreaCheck = " All fields are required ";
	public static String employerErrorEmail = " Please enter a valid e-mail! ";
	public static String employerErrorEmailNotCorporate = " Your email is not corporate! ";
	
	//JobSeeker
	public static String jobSeekerGetAll = " Job Seeker Listed ";
	public static String jobSeekerGetByNationalityId = " Brought by ID number ";
	public static String jobSeekerAreaCheck = " All fields are required ";
	public static String jobSeekerErrorEmail = " Please enter a valid e-mail ";
	public static String jobSeekerNationalityIdExists = " This ID number has already been used ";
	
	//JobPosting
	public static String jobPostingGetAll = " Job Posting Listed ";
	public static String jobPostingAdded = " Job Posting Added ";
	public static String jobPostingUpdate = " Job Posting Update ";
	public static String jobPostingClose = " Job posting successfully closed ";
	public static String jobPostingGetAllOpenJobPostingList = " All active job postings in the system are listed ";
	public static String jobPostingGetByisOpenTrueOrderByCreatedDateDesc = " All active job postings in the system are listed by date ";
	public static String jobPostingGetByisOpenTrueAndEmployer_Id = " All active job postings of a company are listed in the system ";
	public static String jobPostingNoSuchExists = " No such job posting exists ";
	public static String jobPostingIsAlreadyClosed = " This job posting is already closed ";
	
	//City
	public static String cityGetAll = " City Listed ";
}
