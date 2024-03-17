package com.bits.ml_tool.controller;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bits.ml_tool.dao.LuLanguageRepository;
import com.bits.ml_tool.dao.LuMessageRepository;
import com.bits.ml_tool.entities.LuLanguage;

@RestController
public class TranslationController {

    @Autowired
    private LuMessageRepository luMessageRepository;

    @Autowired
    private LuLanguageRepository luLanguageRepository;

    @Autowired
    private CSVImporter csvImporter;

    @GetMapping(value = "/getTranslation/{langId}")
    public String getAllTranslationByLanguage(@PathVariable("langId") int langId) {
        Map<String, String> vendorTranslationMap = luMessageRepository.getAllTranslation(langId);

        LuLanguage luLanguage = luLanguageRepository.findByLanguageId(langId);

        // Construct a JSONObject from a Map.
        JSONObject json = new JSONObject(vendorTranslationMap);

        CSVExporter csvExporter = new CSVExporter();
        csvExporter.exportToCsv(vendorTranslationMap, luLanguage.getLanguageCode(),
                "C:\\Users\\monda\\OneDrive\\Desktop\\ML_TOOL_Translation\\Export");

        return json.toString();
    }

    @GetMapping(value = "/importTranslation/{updateRecords}")
    public String importTranslation(@PathVariable("updateRecords") Boolean updateRecords) {
        csvImporter.importFromCSV("C:\\Users\\monda\\OneDrive\\Desktop\\ML_TOOL_Translation\\Import", updateRecords);
        return "SUCCESS";
    }
}
