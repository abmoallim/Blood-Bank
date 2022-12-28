package com.example.bloodbank.urlController;

public class urlModel {

    private static final String domain = "172.20.10.7";
    private static final String port = "3030";


    public final static String donation_url = "http://" + domain + ":" + port + "/api/donation/";
    public final static String dashboard_data_url = "http://" + domain + ":" + port + "/Dashboard/";
    public final static String hospitals_url = "http://" + domain + ":" + port + "/api/hospital/";
    public final static String donors_url = "http://" + domain + ":" + port + "/api/donor/";
    public final static String recipients_url = "http://" + domain + ":" + port + "/api/recipient/";
    public final static String login_url = "http://" + domain + ":" + port + "/api/user/login/";
    public final static String register_url = "http://" + domain + ":" + port + "/api/donor/";
    public final static String register_recipient_url = "http://" + domain + ":" + port + "/api/recipient/";
    public final static String states_url = "http://" + domain + ":" + port + "/api/state/";
    public final static String delete_donor = "http://" + domain + ":" + port + "/api/donor/";
    public final static String delete_recipient = "http://" + domain + ":" + port + "/api/recipient/";
    public final static String update_donor = "http://" + domain + ":" + port + "/api/donor/";
    public final static String update_recipient = "http://" + domain + ":" + port + "/api/recipient/";
    public final static String blood_url = "http://" + domain + ":" + port + "/api/blood/";
    public final static String result_url = "http://" + domain + ":" + port + "/api/result/";



}
