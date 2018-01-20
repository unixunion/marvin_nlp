package com.deblox;

import sx.blah.discord.handle.obj.IUser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by keghol on 2018-01-19.
 */
public class Sessions {

    Map<IUser, Session> sessions;

    public Sessions() {
        this.sessions = new HashMap<>();
    }

    public Session getSession(IUser user) {
        if (sessions.containsKey(user)) {
            return sessions.get(user);
        } else {
            return sessions.put(user, new Session(user));
        }
    }


}
