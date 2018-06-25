package com.samsung.kaliyev.satpreparation;

import java.io.Serializable;
import java.util.Objects;

public class Words implements Serializable {
    String name, type, description, translation;

    public Words(){

    }

    public Words(String name, String type, String description, String translation) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.translation = translation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Words words = (Words) o;
        return Objects.equals(name, words.name) &&
                Objects.equals(type, words.type) &&
                Objects.equals(description, words.description) &&
                Objects.equals(translation, words.translation);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, type, description, translation);
    }

    @Override
    public String toString() {
        return "Words{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", translation='" + translation + '\'' +
                '}';
    }
}
