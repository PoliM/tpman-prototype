package ch.ocram.tpmanprototype.masterdata.domain;

import com.google.common.collect.ImmutableList;

public interface CategoryRepository {
    void add(Category category);

    ImmutableList<Category> findAll();

}
