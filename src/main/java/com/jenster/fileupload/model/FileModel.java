package com.jenster.fileupload.model;

public class FileModel {
    String fileId;
    String fileName;
    byte [] fileContents;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileContents() {
        return fileContents;
    }

    public void setFileContents(byte[] fileContents) {
        this.fileContents = fileContents;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("FileModel{");
        sb.append("fileId='").append(fileId).append('\'');
        sb.append(", fileName='").append(fileName).append('\'');
        sb.append(", fileContents=");
        if (fileContents == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < fileContents.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(fileContents[i]);
            sb.append(']');
        }
        sb.append('}');
        return sb.toString();
    }
}
