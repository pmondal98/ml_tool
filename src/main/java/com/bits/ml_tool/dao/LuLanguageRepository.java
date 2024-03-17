package com.bits.ml_tool.dao;

import org.springframework.data.repository.CrudRepository;

import com.bits.ml_tool.entities.LuLanguage;
import java.util.List;

public interface LuLanguageRepository extends CrudRepository<LuLanguage, Integer> {

    public LuLanguage findByLanguageId(int languageId);

    List<LuLanguage> findByLanguageCode(String languageCode);

}