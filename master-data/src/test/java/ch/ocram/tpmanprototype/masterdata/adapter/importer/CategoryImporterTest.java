package ch.ocram.tpmanprototype.masterdata.adapter.importer;

import ch.ocram.tpmanprototype.masterdata.domain.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CategoryImporterTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryImporter testee;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldLoadAllCategories() {
        testee.loadFromFile("src/test/resources/categoryIDs.yaml");
        verify(categoryRepository, times(1)).add(any());
    }
}