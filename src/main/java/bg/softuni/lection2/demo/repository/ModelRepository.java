package bg.softuni.lection2.demo.repository;

import bg.softuni.lection2.demo.model.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity,Long> {
}
