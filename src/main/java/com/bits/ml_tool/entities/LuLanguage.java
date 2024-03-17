package com.bits.ml_tool.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table
@Entity(name = "lu_language")
public class LuLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int languageId;
    
    @Column
    String languageCode;

    public LuLanguage() {
    }

    public LuLanguage(int languageId, String languageCode) {
        this.languageId = languageId;
        this.languageCode = languageCode;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    
}
