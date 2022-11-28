package viikingit.emusic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import viikingit.emusic.models.Enfant;
import viikingit.emusic.models.Parent;

public interface IEnfantRepository extends CrudRepository<Enfant, Integer> {
	public List<Enfant> findByParent(Parent authUser);

	public Optional<Enfant> findByUsername(String username);
}
