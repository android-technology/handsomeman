package com.tt.handsomeman.model;

import androidx.annotation.Nullable;

import java.util.Objects;

public class FileRequest {
    private String fileName;
    private String fileDir;
    private String md5;

    public FileRequest(String fileName, String fileDir, String md5) {
        this.fileName = fileName;
        this.fileDir = fileDir;
        this.md5 = md5;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, fileDir, md5);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FileRequest fileRequest = (FileRequest) obj;
        return Objects.equals(fileName, fileRequest.fileName) && Objects.equals(fileDir, fileRequest.fileDir) && Objects.equals(md5, fileRequest.md5);
    }
}
