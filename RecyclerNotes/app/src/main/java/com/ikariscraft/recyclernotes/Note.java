package com.ikariscraft.recyclernotes;

import java.util.Objects;
public class Note {

    int id;
    String title;
    String description;

    public Note(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id && Objects.equals(title, note.title) && Objects.equals(description, note.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description);
    }
}
