package viikingit.emusic.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import viikingit.emusic.models.Parent;

public interface IParentRepository extends CrudRepository<Parent, Integer> {
	public Optional<Parent> findByEmail(String email);
}
