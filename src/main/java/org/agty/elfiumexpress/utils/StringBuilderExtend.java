package org.agty.elfiumexpress.utils;

public class StringBuilderExtend {
    private final StringBuilder stringBuilder = new StringBuilder();

    public StringBuilderExtend() {}

    public StringBuilderExtend(String string) {
        stringBuilder.append(string);
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    public StringBuilderExtend append(String string) {
        stringBuilder.append(string);
        return this;
    }

    public StringBuilderExtend append(char string) {
        stringBuilder.append(string);
        return this;
    }

    public StringBuilderExtend append(int string) {
        stringBuilder.append(string);
        return this;
    }

    public StringBuilderExtend append(long string) {
        stringBuilder.append(string);
        return this;
    }

    public StringBuilderExtend append(float string) {
        stringBuilder.append(string);
        return this;
    }

    public StringBuilderExtend append(double string) {
        stringBuilder.append(string);
        return this;
    }

    public StringBuilderExtend append(boolean string) {
        stringBuilder.append(string);
        return this;
    }

    public StringBuilderExtend appendAndSpaceRight(String string) {
        stringBuilder.append(string);
        stringBuilder.append(" ");
        return this;
    }

    public StringBuilderExtend appendAndSpaceLeft(String string) {
        stringBuilder.append(" ");
        stringBuilder.append(string);
        return this;
    }

    public StringBuilderExtend appendAndSpaceBoth(String string) {
        stringBuilder.append(" ");
        stringBuilder.append(string);
        stringBuilder.append(" ");
        return this;
    }

    public String toString() {
        return stringBuilder.toString();
    }
}
