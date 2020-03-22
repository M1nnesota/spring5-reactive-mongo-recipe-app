package guru.springframework.repositories.reactive;


import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryIT {

    @Autowired
    UnitOfMeasureReactiveRepository reactiveRepository;

    @Before
    public void setUp() throws Exception {
        reactiveRepository.deleteAll().block();
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId("test");
        unitOfMeasure.setDescription("test");
        reactiveRepository.save(unitOfMeasure).block();
    }

    @Test
    public void testSave() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId("abc123");
        reactiveRepository.save(unitOfMeasure).block();

        Long actual = reactiveRepository.count().block();
        assertEquals(Long.valueOf(2L), actual);
    }

    @Test
    public void testFindByDescription() {
        UnitOfMeasure unitOfMeasure = reactiveRepository.findByDescription("test").block();
        assertNotNull(unitOfMeasure);
    }
}
