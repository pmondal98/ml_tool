package com.bits.ml_tool.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bits.ml_tool.entities.LuLanguage;
import com.bits.ml_tool.entities.LuMessage;
import com.bits.ml_tool.entities.LuMessageLang;

public interface LuMessageLangRepository extends CrudRepository<LuMessageLang, Integer> {

    public List<LuMessageLang> findByLuLanguage(LuLanguage lang);

    public Optional<LuMessageLang> findByLuMessageAndLuLanguage(LuMessage luMessage, LuLanguage luLanguage);
}