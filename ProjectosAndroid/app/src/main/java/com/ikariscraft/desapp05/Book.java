package com.ikariscraft.desapp05;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Book implements Parcelable {
    private String title;
    private String author;
    private String comment;
    private Float rate;

    public Book(String title, String author, String comment, Float rate) {
        this.title = title;
        this.author = author;
        this.comment = comment;
        this.rate = rate;
    }

    protected Book(Parcel in) {
        title = in.readString();
        author = in.readString();
        comment = in.readString();
        if (in.readByte() == 0) {
            rate = null;
        } else {
            rate = in.readFloat();
        }
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(author);
        parcel.writeString(comment);
        parcel.writeFloat(rate);
    }
}
