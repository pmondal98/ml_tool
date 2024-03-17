package com.bits.ml_tool.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class LuMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "english_message")
    String englishMessage;

    @OneToMany(mappedBy = "luMessage", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<LuMessageLang> listLuMessageLang = new ArrayList<>();

    public LuMessage(int id, String englishMessage) {
        this.id = id;
        this.englishMessage = englishMessage;
    }

    public LuMessage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnglishMessage() {
        return englishMessage;
    }

    public void setEnglishMessage(String englishMessage) {
        this.englishMessage = englishMessage;
    }
    
    
}
