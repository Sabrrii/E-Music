package viikingit.emusic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import viikingit.emusic.models.Parent;
import viikingit.emusic.models.TypeCours;
import viikingit.emusic.repository.IParentRepository;
import viikingit.emusic.repository.ITypeCoursReposiroty;

public class montantService {

	@Autowired
	private ITypeCoursReposiroty typeCoursRepo;
	@Autowired
	private IParentRepository ParentRepo;

	// services pour un montant d'un cours

	@SuppressWarnings("unlikely-arg-type")
	public int determinerMontant(int id) {
		Optional<TypeCours> types = typeCoursRepo.findById(id);
		Optional<Parent> parentQ = ParentRepo.findById(id);
		int montant = 0;
		int Quotient = parentQ.get().getQuotientFamilial();
		if (Quotient < 0) {
			montant = 417;
		} else if (Quotient >= 0 && Quotient <= 250) {
			montant = 60;
		} else if (Quotient >= 251 && Quotient <= 425) {
			montant = 96;
		} else if (Quotient >= 426 && Quotient <= 680) {
			montant = 126;
		} else if (Quotient >= 681 && Quotient <= 935) {
			montant = 192;
		} else if (Quotient >= 936 && Quotient <= 1800) {
			montant = 282;
		} else if (Quotient >= 1801) {
			montant = 330;
		}
		if (types.equals("Collectif")) {
			if (Quotient < 0) {
				montant = (montant + 1) / 2;
			} else {
				montant = montant / 2;
			}
		}
		return montant;
	}
}
