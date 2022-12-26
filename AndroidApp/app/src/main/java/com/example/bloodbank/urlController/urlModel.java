package com.example.bloodbank.urlController;

public class urlModel {

    private static final String domain = "172.20.10.7";
    private static final String port = "5000";


    public final static String get_hospital_numbers_url = "http://" + domain + ":" + port + "/api/hospital/hospitals/";
    public final static String dashboard_data_url = "http://" + domain + ":" + port + "/DashboardData";
    public final static String hospitals_url = "http://" + domain + ":" + port + "/Hospitals";
    public final static String donors_url = "http://" + domain + ":" + port + "/Donors";
    public final static String login_url = "http://" + domain + ":" + port + "/Login";
    public final static String register_url = "http://" + domain + ":" + port + "/NewDonor";
    public final static String states_list_url = "http://" + domain + ":" + port + "/States";
    public final static String delete_donor = "http://" + domain + ":" + port + "/Donor/";
    public final static String update_donor = "http://" + domain + ":" + port + "/Donor/";


}
