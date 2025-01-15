package org.agty.elfiumexpress.storage.types.mime;


import java.util.Arrays;
import java.util.List;

/**
 * Кэшировать частые данные или создать список
 */

//https://ru.wikipedia.org/wiki/%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_MIME-%D1%82%D0%B8%D0%BF%D0%BE%D0%B2
public class MimeList {
    private Mime[] image;
    private Mime[] application;
    private Mime[] chemical;
    private Mime[] xconference;
    private Mime[] model;
    private Mime[] audio;
    private Mime[] video;
    private Mime[] text;
    private Mime[] message;

    public Mime[] getImage() {
        return image;
    }

    public void setImage(Mime[] image) {
        this.image = image;
    }

    public Mime[] getApplication() {
        return application;
    }

    public void setApplication(Mime[] application) {
        this.application = application;
    }

    public Mime[] getChemical() {
        return chemical;
    }

    public void setChemical(Mime[] chemical) {
        this.chemical = chemical;
    }

    public Mime[] getXconference() {
        return xconference;
    }

    public void setXconference(Mime[] xconference) {
        this.xconference = xconference;
    }

    public Mime[] getModel() {
        return model;
    }

    public void setModel(Mime[] model) {
        this.model = model;
    }

    public Mime[] getAudio() {
        return audio;
    }

    public void setAudio(Mime[] audio) {
        this.audio = audio;
    }

    public Mime[] getVideo() {
        return video;
    }

    public void setVideo(Mime[] video) {
        this.video = video;
    }

    public Mime[] getText() {
        return text;
    }

    public void setText(Mime[] text) {
        this.text = text;
    }

    public Mime[] getMessage() {
        return message;
    }

    public void setMessage(Mime[] message) {
        this.message = message;
    }

    public String getFirstExtensionByMimeType(String mimeType) {
        for(Mime[] mimes : getMimeList()) {
            for (Mime mime : mimes) {
                if (mime.equalsMimeType(mimeType)) {
                    return mime.getExtensionsList().getFirst();
                }
            }
        }
        return "";
    }
    public Mime getByMimeType(String mimeType) {
        for(Mime[] mimes : getMimeList()) {
            for (Mime mime : mimes) {
                if (mime.equalsMimeType(mimeType)) {
                    return mime;
                }
            }
        }
        return null;
    }

    private List<Mime[]> getMimeList() {
        return Arrays.asList(
                image, text, audio, video, application,
                message, xconference, model, chemical
        );
    }

    public String getExtensionByMimeType(String mimeType) {
        for(Mime[] mimes : getMimeList()) {
            for (Mime mime : mimes) {
                if (mime.equalsMimeType(mimeType)) {
                    return mime.getExtension();
                }
            }
        }
        return "";
    }

    public List<String> getExtensionsListByMimeType(String mimeType) {
        for(Mime[] mimes : getMimeList()) {
            for (Mime mime : mimes) {
                if (mime.equalsMimeType(mimeType)) {
                    return mime.getExtensionsList();
                }
            }
        }
        return null;
    }

    public String getMimeTypeByExtension(String extension) {
        for(Mime[] mimes : getMimeList()) {
            for (Mime mime : mimes) {
                if (mime.getExtensionsList().contains(extension)) {
                    return mime.getMime();
                }
            }
        }
        return "";
    }

    public Mime getByExtension(String extension) {
        for(Mime[] mimes : getMimeList()) {
            for (Mime mime : mimes) {
                if (mime.getExtensionsList().contains(extension)) {
                    return mime;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "MimeList{" +
                "image=" + Arrays.toString(image) +
                ", application=" + Arrays.toString(application) +
                ", chemical=" + Arrays.toString(chemical) +
                ", xconference=" + Arrays.toString(xconference) +
                ", model=" + Arrays.toString(model) +
                ", audio=" + Arrays.toString(audio) +
                ", video=" + Arrays.toString(video) +
                ", text=" + Arrays.toString(text) +
                ", message=" + Arrays.toString(message) +
                '}';
    }
}
