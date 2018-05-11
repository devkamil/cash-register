package pl.devkamil.app.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.devkamil.app.model.BarCode;
import pl.devkamil.app.model.Product;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindByBarCode_thenReturnProduct(){
        //given
        Product product = new Product("Product test", new BigDecimal("77.77"), new BarCode("777"));
        testEntityManager.persist(product);
        testEntityManager.flush();

        //when
        Product found = productRepository.findByBarCodeBarCode(product.getBarCode().getBarCode());

        //then
        assertThat(found.getBarCode()).isEqualTo(product.getBarCode());
    }

}
