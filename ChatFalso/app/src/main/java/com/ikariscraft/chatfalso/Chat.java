package com.ikariscraft.chatfalso;

import java.util.Objects;

public class Chat {
    private String text;
    private boolean isBot;

    public Chat(String text, boolean isBot) {
        this.text = text;
        this.isBot = isBot;
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
        return isBot() == chat.isBot() && Objects.equals(getText(), chat.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getText(), isBot());
    }
}
