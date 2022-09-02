package com.gemini.ems.model;

public class QueryConstants {
    public static final String ID_PARAM = "Id";
    public static final String NAME_PARAM = "Name";
    public static final String ADDRESS_PARAM = "Address";
    public static final String PINCODE_PARAM = "Pincode";
    public static final String CITY_PARAM = "City";
    public static final String CUSTOMER_ID_PARAM = "CustomerId";
    public static final String SUBJECT_PARAM = "Subject";
    public static final String SUMMARY_PARAM = "Summary";

    public static final String CREATE_USER = "INSERT INTO Customer(ID,Name,Address,City,Pincode) VALUES(" +
            ":" + ID_PARAM + "" +
            ":" + NAME_PARAM + "" +
            ":" + ADDRESS_PARAM + "" +
            ":" + CITY_PARAM + "" +
            ":" + PINCODE_PARAM + ")";
    public static final String REGISTER_GRIEVANCE = "INSERT INTO Grievance(CustomerId,Subject,Summary) VALUES(" +
            ":" + CUSTOMER_ID_PARAM + "" +
            ":" + SUBJECT_PARAM + "" +
            ":" + SUMMARY_PARAM;
}
