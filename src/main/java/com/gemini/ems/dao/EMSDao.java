package com.gemini.ems.dao;

import com.gemini.ems.model.Grievance;
import com.gemini.ems.model.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static com.gemini.ems.model.QueryConstants.*;

@Repository
public class EMSDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
