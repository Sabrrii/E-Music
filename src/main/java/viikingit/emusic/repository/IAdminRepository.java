package viikingit.emusic.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import viikingit.emusic.models.Admin;

public interface IAdminRepository extends CrudRepository<Admin, Integer> {
	public Optional<Admin> findByUsername(String username);
}
