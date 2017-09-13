package com.nanshan.lighteningstorm.pages.events;

import com.squareup.otto.Bus;

/**
 * Created by nanshan on 9/13/2017.
 */

public class OttoBus {

    private static OttoBus ottoBus;
    private static Bus bus;

    public static OttoBus getInstance() {
        if (ottoBus == null){
            ottoBus = new OttoBus();
        }
        return ottoBus;
    }

    public Bus getBusInstance(){
        if (bus == null){
            bus = new Bus();
        }
        return bus;
    }
    
}
