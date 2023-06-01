package viikingit.emusic.repository;

import org.springframework.data.repository.CrudRepository;

import viikingit.emusic.models.Cours;

import java.util.Optional;

public interface ICoursRepository extends CrudRepository<Cours, Integer> {
    public Optional<Cours> findById(int id);
}
