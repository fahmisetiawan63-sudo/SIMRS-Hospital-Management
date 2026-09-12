package com.otten32.common.constant;

public class AppConstants {
    public static final String API_VERSION = "v1";
    public static final String API_BASE_URL = "/api/" + API_VERSION;
    
    // Common Statuses
    public static final String STATUS_ACTIVE = "ACTIVE";
    public static final String STATUS_INACTIVE = "INACTIVE";
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_COMPLETED = "COMPLETED";
    
    // Patient Status
    public static final String PATIENT_STATUS_ACTIVE = "ACTIVE";
    public static final String PATIENT_STATUS_INACTIVE = "INACTIVE";
    public static final String PATIENT_STATUS_DECEASED = "DECEASED";
    
    // Gender
    public static final String GENDER_MALE = "MALE";
    public static final String GENDER_FEMALE = "FEMALE";
    public static final String GENDER_OTHER = "OTHER";
    
    // Invoice Status
    public static final String INVOICE_STATUS_DRAFT = "DRAFT";
    public static final String INVOICE_STATUS_ISSUED = "ISSUED";
    public static final String INVOICE_STATUS_PAID = "PAID";
    public static final String INVOICE_STATUS_PARTIAL = "PARTIAL";
    public static final String INVOICE_STATUS_CANCELLED = "CANCELLED";
    
    // Lab Status
    public static final String LAB_STATUS_PENDING = "PENDING";
    public static final String LAB_STATUS_PROCESSING = "PROCESSING";
    public static final String LAB_STATUS_COMPLETED = "COMPLETED";
    public static final String LAB_STATUS_CANCELLED = "CANCELLED";
    
    // Radiology Status
    public static final String RADIOLOGY_STATUS_PENDING = "PENDING";
    public static final String RADIOLOGY_STATUS_COMPLETED = "COMPLETED";
    public static final String RADIOLOGY_STATUS_VERIFIED = "VERIFIED";
    
    // Pagination
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int MAX_PAGE_SIZE = 100;
    public static final int DEFAULT_PAGE_NUMBER = 0;
}
