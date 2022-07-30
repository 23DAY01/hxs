package com.lypc.hxs.constant;


public interface WebConst {

    interface HEADER {
        String USER_AGENT = "user-agent";
    }

    interface AUTHENTICATION{
        String ADMIN_SESSION ="admin_session";
        String ADMIN_COOKIE="admin_cookie";
        String USER_SESSION="user_session";
        String USER_COOKIE="user_cookie";
    }

    interface SECURITY {
        String IV_KEY = "0000000000000000";
        String ENCODE_KEY = "1234567812345678";
    }

}
