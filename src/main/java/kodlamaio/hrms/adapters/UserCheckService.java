package kodlamaio.hrms.adapters;

import java.time.LocalDate;

public interface UserCheckService {

	boolean checkIfRealPerson(String nationalityId, String firstName, String lastName, LocalDate yearOfBirth);
}
