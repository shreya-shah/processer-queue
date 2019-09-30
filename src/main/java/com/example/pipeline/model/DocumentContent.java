package com.example.pipeline.model;

import org.springframework.stereotype.Component;

@Component
public class DocumentContent {
    String documentUrl;
    String status;

    public DocumentContent() {
    }

    public DocumentContent(String documentUrl, String status) {
        this.documentUrl = documentUrl;
        this.status = status;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
