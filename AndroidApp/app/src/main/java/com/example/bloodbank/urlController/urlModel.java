package com.example.bloodbank.urlController;

public class urlModel {

    private static final String domain = "172.20.10.7";
    private static final String port = "5000";


    public final static String get_hospital_numbers_url = "http://" + domain + ":" + port + "/api/hospital/hospitals/";
    public final static String dashboard_data = "http://" + domain + ":" + port + "/DashboardData";


}
