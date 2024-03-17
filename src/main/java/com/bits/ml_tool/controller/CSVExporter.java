package com.bits.ml_tool.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.opencsv.CSVWriter;

public class CSVExporter {

    public void exportToCsv(Map<String, String> translationMap, String languageCode, String directoryPath) {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "ExportVendorTranslation_" + languageCode + "_" + timeStamp + ".csv";
        String filePath = directoryPath + "/" + fileName;

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Write header
            writer.writeNext(new String[] { "Language Code", "English Message", "Translated Text" });

            // Convert the map to a list of arrays and write to the CSV file
            List<String[]> data = translationMap.entrySet().stream()
                    .map(entry -> new String[] { languageCode, entry.getKey(), entry.getValue() })
                    .collect(Collectors.toList());

            writer.writeAll(data);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
        }
    }

}
