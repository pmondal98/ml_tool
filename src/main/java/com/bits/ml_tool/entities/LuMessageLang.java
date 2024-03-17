package com.bits.ml_tool.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lu_message_lang")
public class LuMessageLang {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_message_id")
    private LuMessage luMessage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_language_id")
    private LuLanguage luLanguage;
    
    @Column
    String translatedText;

    public LuMessageLang() {
    }

    public LuMessageLang(int id, LuMessage luMessage, LuLanguage luLanguage, String translatedText) {
        this.id = id;
        this.luMessage = luMessage;
        this.luLanguage = luLanguage;
        this.translatedText = translatedText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LuMessage getLuMessage() {
        return luMessage;
    }

    public void setLuMessage(LuMessage luMessage) {
        this.luMessage = luMessage;
    }

    public LuLanguage getLuLanguage() {
        return luLanguage;
    }

    public void setLuLanguage(LuLanguage luLanguage) {
        this.luLanguage = luLanguage;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }
    
}
