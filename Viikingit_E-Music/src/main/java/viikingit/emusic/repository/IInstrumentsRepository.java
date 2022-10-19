package viikingit.emusic.repository;

import org.springframework.data.repository.CrudRepository;

import viikingit.emusic.models.Instruments;

public interface IInstrumentsRepository extends CrudRepository<Instruments, Integer> {

}
