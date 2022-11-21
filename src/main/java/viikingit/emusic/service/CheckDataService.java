package viikingit.emusic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import viikingit.emusic.models.Instruments;
import viikingit.emusic.models.TypeCours;
import viikingit.emusic.repository.IInstrumentsRepository;
import viikingit.emusic.repository.ITypeCoursReposiroty;

@Service
public class CheckDataService {

	private final String[] originalTypes = new String[] { "Individuel", "Collectif" };
	private final String[] OriginalInstru = new String[] { "Violon", "Violoncelle", "Harpe Celtique", "Trombone",
			"Trompette", "Tuba", "Clavier amplifié", "Guitare électrique", "Basse électrique", "orgue", "Piano",
			"Saxophone", "Clarinette", "Flute traversière", "Batterie" };
	@Autowired
	private ITypeCoursReposiroty typeCoursRepo;

	@Autowired
	private IInstrumentsRepository instrumentRepo;

	public Iterable<TypeCours> checkTypeCours() {
		List<TypeCours> types = (List<TypeCours>) typeCoursRepo.findAll();
		for (String ot : originalTypes) {
			if (!types.contains(new TypeCours(ot))) {
				TypeCours tc = new TypeCours(ot);
				types.add(tc);
			}
		}
		return typeCoursRepo.saveAll(types);

	}

	public Iterable<Instruments> checkInstrument() {
		List<Instruments> Instru = (List<Instruments>) instrumentRepo.findAll();
		for (String oi : OriginalInstru) {
			if (!Instru.contains(new Instruments(oi))) {
				Instruments tc = new Instruments(oi);
				Instru.add(tc);
			}
		}
		return instrumentRepo.saveAll(Instru);

	}
}
