package viikingit.emusic.service;

import org.springframework.beans.factory.annotation.Autowired;

import viikingit.emusic.repository.IParentRepository;
import viikingit.emusic.repository.ITypeCoursReposiroty;

public class montantService {

	@Autowired
	private ITypeCoursReposiroty typeCoursRepo;
	@Autowired
	private IParentRepository ParentRepo;

	// services pour un montant d'un cours

	public int determinerMontant() {
		ParentRepo.findAll();

	}
}
