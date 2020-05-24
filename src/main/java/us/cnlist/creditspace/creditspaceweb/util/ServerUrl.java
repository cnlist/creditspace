package us.cnlist.creditspace.creditspaceweb.util;

public class ServerUrl {

    public static final String AUTH = "/auth";

    public static final class PROFILE {
        public static final String GET_BY_EMAIL = "/profile/email/";
        public static final String SAVE = "/profile";
        public static final String CONTACTS = "/contact?citizen=";
        public static final String CONTACT = "/contact";
    }

    public static final class USER {
        public static final String REGISTER = "/user/register";
    }
    private ServerUrl(){}


}
