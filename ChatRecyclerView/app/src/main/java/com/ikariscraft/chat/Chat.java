package com.ikariscraft.chat;

import java.util.Objects;

public class Chat {
    private String id;
    private String text;
    private boolean isBot;

    public Chat(String id, String text, boolean isBot) {
        this.id = id;
        this.text = text;
        this.isBot = isBot;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isBot() {
        return isBot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chat)) return false;
        Chat chat = (Chat) o;
        return isBot() == chat.isBot() && Objects.equals(getId(), chat.getId()) && Objects.equals(getText(), chat.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getText(), isBot());
    }
}
