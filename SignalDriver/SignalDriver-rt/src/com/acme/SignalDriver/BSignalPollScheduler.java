package com.acme.signalDriver;
import com.acme.SignalDriver.BILightSignalPollable;

import javax.baja.driver.*;
import javax.baja.driver.util.*;
import javax.baja.sys.*;
import javax.baja.nre.annotations.*;

import javax.baja.driver.util.BPollScheduler;

/**
 * Created by Derek Otieno on February 17, 2018.
 */

@NiagaraType
public class BSignalPollScheduler extends BPollScheduler {
    /*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
    /*@ $com.acme.signalDriver.BSignalPollScheduler(2979906276)1.0$ @*/
    /* Generated Sat Feb 17 23:42:53 EST 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////

    @Override
    public Type getType() { return TYPE; }
    public static final Type TYPE = Sys.loadType(BSignalPollScheduler.class);

    /*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    /**
     * This method is called by the Poll Scheduler on components
     * which implement the {@link BIPollable} interface.
     */
    @Override
    public void doPoll(BIPollable p) throws Exception
    {
        //flag indicating whether we should poll
        boolean shouldPoll = true;
        try
        {
            //check the state of our network to determine if we should
            //perform a poll
            BDeviceNetwork net = (BDeviceNetwork)getParent();
            shouldPoll = (!net.isDisabled()) && (!net.isDown())
                    && (!net.isFault());
        }
        catch (Exception e)
        {
            shouldPoll = true;
        }

        //return if we're not performing a poll
        if (!shouldPoll) return;

        //poll the component which is an implementation of our
        //interface
        BILightSignalPollable dev = (BILightSignalPollable)p;
        try { dev.poll(); }
        catch ( NotRunningException e)
        {
            //if we run into a problem, unsubscribe
            unsubscribe(dev);
        }
    }
}