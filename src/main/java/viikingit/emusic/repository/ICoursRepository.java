package viikingit.emusic.repository;

import org.springframework.data.repository.CrudRepository;

import viikingit.emusic.models.Cours;

public interface ICoursRepository extends CrudRepository<Cours, Integer> {
}
