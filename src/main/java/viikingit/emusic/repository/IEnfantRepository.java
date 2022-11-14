package viikingit.emusic.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import viikingit.emusic.models.Enfant;
import viikingit.emusic.models.Parent;

public interface IEnfantRepository extends CrudRepository <Enfant,Integer> {
	public Optional<Enfant> findByParent(int id);
}
