package ch.ocram.tpmanprototype.masterdata.adapter.storage;

import ch.ocram.tpmanprototype.masterdata.domain.Category;
import ch.ocram.tpmanprototype.masterdata.domain.CategoryRepository;
import com.google.common.collect.ImmutableList;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;
import java.util.TreeMap;

@ApplicationScoped
public class CategoryStorage implements CategoryRepository {

    private Map<Integer, Category> categories = new TreeMap<>();

    @Override
    public void add(Category category) {
        this.categories.put(category.getId(), category);
    }

    @Override
    public ImmutableList<Category> findAll() {
        return ImmutableList.copyOf(categories.values());
    }
}
