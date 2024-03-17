package com.bits.ml_tool.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bits.ml_tool.entities.LuLanguage;
import com.bits.ml_tool.entities.LuMessage;
import com.bits.ml_tool.entities.LuMessageLang;

public interface LuMessageRepository extends CrudRepository<LuMessage, Integer> {

        default Map<String, String> getAllTranslation(int languageId) {
                return findAllTranslations(languageId).stream()
                                .collect(Collectors.toMap(
                                                tuple -> (String) tuple[0],
                                                tuple -> (tuple[1] != null ? (String) tuple[1] : "")));
        }

        @Query("SELECT DISTINCT src.englishMessage, lang.translatedText FROM LuMessage src LEFT JOIN src.listLuMessageLang lang ON lang.luLanguage.languageId = :langId")
        List<Object[]> findAllTranslations(@Param("langId") int languageId);

        public List<LuMessage> findByEnglishMessage(String englishMessage);

        // Query to check if a record with the same LuMessage and LuLanguage already
        // exists
        @Query("SELECT ml FROM LuMessageLang ml WHERE ml.luMessage = :luMessage AND ml.luLanguage = :luLanguage")
        Optional<LuMessageLang> findByLuMessageAndLuLanguage(
                        @Param("luMessage") LuMessage luMessage,
                        @Param("luLanguage") LuLanguage luLanguage);

}
