package de.nils.google.photos.clone.repository;

import de.nils.google.photos.clone.model.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotoRepository extends CrudRepository<Photo, Integer> {
}
