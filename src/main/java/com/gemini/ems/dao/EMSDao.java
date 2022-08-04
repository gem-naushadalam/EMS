package com.gemini.ems.dao;

import com.gemini.ems.model.Grievance;
import com.gemini.ems.model.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class EMSDao {
    private static final String ID_PARAM = "Id";
    private static final String NAME_PARAM = "Name";
    private static final String ADDRESS_PARAM = "Address";
    private static final String PINCODE_PARAM = "Pincode";
    private static final String CITY_PARAM = "City";
    private static final String CUSTOMER_ID_PARAM = "CustomerId";
    private static final String SUBJECT_PARAM = "Subject";
    private static final String SUMMARY_PARAM = "Summary";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String CREATE_USER = "INSERT INTO Customer(ID,Name,Address,City,Pincode) VALUES(" +
            ":" + ID_PARAM + "" +
            ":" + NAME_PARAM + "" +
            ":" + ADDRESS_PARAM + "" +
            ":" + CITY_PARAM + "" +
            ":" + PINCODE_PARAM + ")";
    private static final String REGISTER_GRIEVANCE = "INSERT INTO Grievance(CustomerId,Subject,Summary) VALUES(" +
            ":" + CUSTOMER_ID_PARAM + "" +
            ":" + SUBJECT_PARAM + "" +
            ":" + SUMMARY_PARAM;

    public Boolean createUser(RegisterUser registerUser,UUID customerId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue(ID_PARAM, customerId)
                .addValue(NAME_PARAM, registerUser.getName())
                .addValue(ADDRESS_PARAM, registerUser.getAddress())
                .addValue(CITY_PARAM, registerUser.getCity())
                .addValue(PINCODE_PARAM, registerUser.getPincode());

        try {
            namedParameterJdbcTemplate.update(CREATE_USER, parameterSource);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean registerComplain(Grievance grievance,UUID complainId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue(CUSTOMER_ID_PARAM, grievance.getCustomerId())
                .addValue(SUBJECT_PARAM, grievance.getSubject())
                .addValue(SUMMARY_PARAM, grievance.getSummery());

        try {
            namedParameterJdbcTemplate.update(REGISTER_GRIEVANCE, parameterSource);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
