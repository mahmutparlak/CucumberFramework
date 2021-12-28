package com.hrms.utils;

import org.json.JSONObject;

public class APIPayloadCommonMethods {

    public static String createEmployeePayload() {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("emp_firstname", "Apple");
        jsonObject.put("emp_lastname", "Anna");
        jsonObject.put("emp_middle_name", "Red");
        jsonObject.put("emp_gender", "M");
        jsonObject.put("emp_birthday", "1975-01-01");
        jsonObject.put("emp_status", "Self-Employee");
        jsonObject.put("emp_job_title", "Cloud Architect");

        return jsonObject.toString();
    }
}
