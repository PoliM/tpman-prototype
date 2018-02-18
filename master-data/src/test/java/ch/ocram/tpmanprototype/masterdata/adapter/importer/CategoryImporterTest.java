package ch.ocram.tpmanprototype.masterdata.adapter.importer;

import ch.ocram.tpmanprototype.masterdata.domain.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CategoryImporterTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryImporter testee;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldLoadAllCategories() {
        testee.loadFromFile("src/test/resources/categoryIDs.yaml");
        verify(categoryRepository, times(1)).add(any());
    }
}