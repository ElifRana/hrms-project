package kodlamaio.hrms.adapters;

import java.rmi.RemoteException;
import java.time.LocalDate;

import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

public class MernisServiceAdapter implements UserCheckService {

	@Override
	public boolean checkIfRealPerson(String nationalityId, String firstName, String lastName, LocalDate yearOfBirth) {

		KPSPublicSoapProxy client = new KPSPublicSoapProxy();

		boolean result = true;

		try {
			result = client.TCKimlikNoDogrula(Long.parseLong(nationalityId), firstName, lastName,
					yearOfBirth.getYear());
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return result;

	}
}