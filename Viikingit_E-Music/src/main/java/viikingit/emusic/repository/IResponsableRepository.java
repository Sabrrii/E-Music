package viikingit.emusic.repository;

import org.springframework.data.repository.CrudRepository;

import viikingit.emusic.models.Parent;

public interface IResponsableRepository extends CrudRepository<Parent, Integer> {

}
