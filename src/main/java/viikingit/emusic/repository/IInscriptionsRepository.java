package viikingit.emusic.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import viikingit.emusic.models.Inscriptions;
import viikingit.emusic.models.Parent;

public interface IInscriptionsRepository extends CrudRepository<Inscriptions, Integer> {
	public List<Inscriptions> findByParent(Parent authUser);
}
