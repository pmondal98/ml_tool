package com.bits.ml_tool.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bits.ml_tool.dao.LuLanguageRepository;
import com.bits.ml_tool.dao.LuMessageLangRepository;
import com.bits.ml_tool.dao.LuMessageRepository;
import com.bits.ml_tool.entities.LuLanguage;
import com.bits.ml_tool.entities.LuMessage;
import com.bits.ml_tool.entities.LuMessageLang;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@Component
public class CSVImporter {

    @Autowired
    private LuLanguageRepository luLanguageRepository;

    @Autowired
    private LuMessageRepository luMessageRepository;

    @Autowired
    private LuMessageLangRepository luMessageLangRepository;

    public void importFromCSV(String directoryPath, Boolean updateRecords) {
        String fileName = directoryPath + "\\ImportVendorTranslation.csv";
        List<List<String>> records = new ArrayList<List<String>>();
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            String[] values = null;
            boolean skipHeaders = true;
            while ((values = csvReader.readNext()) != null) {
                if (skipHeaders) {
                    skipHeaders = false; // Skip only the first line
                    continue;
                }
                records.add(Arrays.asList(values));
            }
            System.out.println(records);
            List<LuMessageLang> luMessageLangList = processCsvRecords(records);
            // luMessageLangRepository.saveAll(luMessageLangList);
            for (LuMessageLang luMessageLang : luMessageLangList) {
                saveOrUpdateLuMessageLang(luMessageLang, updateRecords);
            }
        } catch (CsvValidationException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<LuMessageLang> processCsvRecords(List<List<String>> records) {
        List<LuMessageLang> luMessageLangList = new ArrayList<>();

        for (List<String> record : records) {
            // Assuming the structure of each record is [luMessageId, languageId,
            // translatedText]
            String languageCode = record.get(0);
            String luMessageEnglish = record.get(1);
            String translatedText = record.get(2);

            // Create LuMessageLang instance

            // Assuming you have a method to retrieve LuMessage and LuLanguage objects based
            // on their IDs
            List<LuMessage> listOfLuMessages = getLuMessageByEnglishMessage(luMessageEnglish);
            LuLanguage luLanguage = getLuLanguageByCode(languageCode);

            for (LuMessage luMessage : listOfLuMessages) {
                LuMessageLang luMessageLang = new LuMessageLang();
                luMessageLang.setLuLanguage(luLanguage);
                luMessageLang.setTranslatedText(translatedText);
                luMessageLang.setLuMessage(luMessage);
                luMessageLangList.add(luMessageLang);
            }
        }

        return luMessageLangList;
    }

    private List<LuMessage> getLuMessageByEnglishMessage(String luMessageEnglish) {
        List<LuMessage> luMessagesList = luMessageRepository.findByEnglishMessage(luMessageEnglish);

        if (luMessagesList.size() <= 0) {
            System.out.println("No LuMessage found for the given language code :: " + luMessageEnglish);
        }
        return luMessagesList;
    }

    private LuLanguage getLuLanguageByCode(String languageCode) {
        LuLanguage luLanguage = null;
        List<LuLanguage> luLanguagesList = luLanguageRepository.findByLanguageCode(languageCode);

        if (luLanguagesList.size() > 0) {
            luLanguage = luLanguagesList.get(0);
        } else {
            System.out.println("No language found for the given language code :: " + languageCode);
        }
        return luLanguage;
    }

    public void saveOrUpdateLuMessageLang(LuMessageLang luMessageLang, Boolean updateRecords) {
        // Check if a record with the same LuMessage and LuLanguage already exists
        Optional<LuMessageLang> existingRecord = luMessageLangRepository
                .findByLuMessageAndLuLanguage(luMessageLang.getLuMessage(), luMessageLang.getLuLanguage());

        if (updateRecords) {
            if (existingRecord.isPresent()) {
                // Update the existing record if it exists
                LuMessageLang existing = existingRecord.get();
                existing.setTranslatedText(luMessageLang.getTranslatedText());
                luMessageLangRepository.save(existing);
            }
        }

        if (!existingRecord.isPresent()) {
            // Save the new record if it does not exist
            luMessageLangRepository.save(luMessageLang);
        }
    }
}
