package org.mservice.server;

import org.mservice.server.registration.ServiceRegistrationServer;
import org.mservice.server.service.administration.AdministrationServer;
import org.mservice.server.service.user.UsersServer;

/**
 * Created by oscarimac122 on 15/10/16.
 */
public class Main {
    private static final String REGISTRATION_SERVER = "registration";
    private static final String USERS_SERVER = "users";
    private static final String ADMINISTRATION_SERVER = "administration";

    public static void main(String[] args) {
        String serverName = args[0].toLowerCase();
        if (serverName.equals(REGISTRATION_SERVER)) {
            ServiceRegistrationServer.main(args);
        } else if (serverName.equals(USERS_SERVER)) {
            UsersServer.main(args);
        } else if (serverName.equals(ADMINISTRATION_SERVER)){
            AdministrationServer.main(args);
        } else {
            System.err.println("Not a name of server allowed");
        }
    }
}
