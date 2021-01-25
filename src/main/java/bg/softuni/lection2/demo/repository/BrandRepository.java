package bg.softuni.lection2.demo.repository;

import bg.softuni.lection2.demo.model.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity,Long> {

}
