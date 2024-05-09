package com.example.monament.utils;


public interface AppConstants {
    String BEARER_TYPE = "Bearer";
    String AUTH_HEADER = "Authorization";
    String REFRESH_AUTH_HEADER = "RefreshToken";
    String PASSWORD_REGEXP = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#_$%^&+=])(?=\\S+$).{8,}$";
//    String[] GET_METHOD_OPEN_PAGES = {
//            AttachmentController.BASE_PATH + "**"
//    };

    static String QUERY(){
        return "";
    }
}
