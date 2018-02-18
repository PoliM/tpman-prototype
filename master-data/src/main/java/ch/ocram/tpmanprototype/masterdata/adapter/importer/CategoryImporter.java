package ch.ocram.tpmanprototype.masterdata.adapter.importer;

import ch.ocram.tpmanprototype.masterdata.domain.Category;
import ch.ocram.tpmanprototype.masterdata.domain.CategoryRepository;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

@Singleton
@Startup
public class CategoryImporter {

    @Inject
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void startup() {
        loadFromFile("/home/marco/Documents/Projects/EVE_Tpman/tpman-prototype/master-data/import/categoryIDs.yaml");
    }

    public void loadFromFile(String filename) {
        Yaml yaml = new Yaml();

        try (Reader r = new FileReader(filename)) {
            Map<Integer, Object> categoryIds = yaml.load(r);
            processCategoryIds(categoryIds);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processCategoryIds(Map<Integer, Object> categoryIds) {
        for (Map.Entry<Integer, Object> entry: categoryIds.entrySet()) {
            Map<String, Object> categoryData = (Map<String, Object>) entry.getValue();
            if (isPublished(categoryData)) {
                Map<String, Object> names = (Map<String, Object>) categoryData.get("name");
                String name = getEnglishPart(names);
                Category category = new Category(entry.getKey(), name);
                categoryRepository.add(category);
            }
        }
    }

    private Boolean isPublished(Map<String, Object> categoryData) {
        return (Boolean) categoryData.getOrDefault("published", Boolean.FALSE);
    }

    private String getEnglishPart(Map<String, Object> name) {
        return (String) name.get("en");
    }
}
