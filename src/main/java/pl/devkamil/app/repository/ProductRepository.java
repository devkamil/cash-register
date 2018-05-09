package pl.devkamil.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.devkamil.app.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

        public Product findByBarCodeBarCode(String barCode);
}
