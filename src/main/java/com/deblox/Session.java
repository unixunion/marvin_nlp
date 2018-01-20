package com.deblox;

import sx.blah.discord.handle.obj.IUser;

import java.util.List;

/**
 * Created by keghol on 2018-01-19.
 */
public class Session {

    IUser iuser;
    List<Intent> intents;

    public Session(IUser iuser) {
        this.iuser = iuser;
    }

    public Intent getActiveIntent() {
        final Intent[] intent = {null};
        intents.forEach(i -> {
            if (i.isActive()) {
                intent[0] = i;
            }
        });
        return intent[0];
    }

}
