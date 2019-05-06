package com.acme.SignalDriver;

import javax.baja.driver.util.*;
import javax.baja.sys.*;
import javax.baja.nre.annotations.*;

/**
 * Created by R. Derek Otieno on February 17, 2018.
 */

@NiagaraType
public interface BILightSignalPollable extends BIPollable {
    /*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
    /*@ $com.acme.signalDriver.BILightSignalPollable(2979906276)1.0$ @*/
    /* Generated Sat Feb 17 23:34:33 EST 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////

    Type TYPE = Sys.loadType(BILightSignalPollable.class);

    /*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    /**
     * The poll() callback method called from the poll scheduler
     * when it is time to poll this object.
     */
    public void poll();

}