package service;

import data.Channel;

/**
 * Created by User on 19.02.2017.
 */

public interface WethaerServiceCallback {

    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);
}
