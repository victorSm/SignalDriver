package com.acme.SignalDriver;

import javax.baja.driver.BDevice;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.util.IFuture;

/**
 * Created by Derek Otieno on February 17, 2018.
 */

@NiagaraProperty( name = "intersectionId",
        type = "baja:String",
        defaultValue = "A")

@NiagaraType
public class BSignalDevice extends BDevice {
    /*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
    /*@ $com.acme.signalDriver.BSignalDevice(2233431164)1.0$ @*/
    /* Generated Sat Feb 17 18:16:22 EST 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "intersectionId"
////////////////////////////////////////////////////////////////

    /**
     * Slot for the {@code intersectionId} property.
     * @see #getIntersectionId
     * @see #setIntersectionId
     */
    public static final Property intersectionId = newProperty(0, "A", null);

    /**
     * Get the {@code intersectionId} property.
     * @see #intersectionId
     */
    public String getIntersectionId() { return getString(intersectionId); }

    /**
     * Set the {@code intersectionId} property.
     * @see #intersectionId
     */
    public void setIntersectionId(String v) { setString(intersectionId, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////

    @Override
    public Type getType() { return TYPE; }
    public static final Type TYPE = Sys.loadType(BSignalDevice.class);

    /*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    /**
     * Get the Type of the BDeviceNetwork which can parent our device
     */
    @Override
    public Type getNetworkType()
    {
        return BSignalDriverNetwork.TYPE;
    }

    /**
     * Asynchronous ping implementation
     */
    @Override
    protected IFuture postPing()
    {
        try {  doPing(); }

        catch (Exception e) {  e.printStackTrace();  }

        return null;
    }

    /**
     * Ping the specific Light Intersection device represented
     * by our BDevice
     */
    @Override
    public void doPing() throws Exception
    {
        BSignalDriverNetwork network =
                (BSignalDriverNetwork)getNetwork();
        String response =
                network.sendRequest("ping " + getIntersectionId());

        if( response.equals("ping ok!") )
            pingOk();
        else
            pingFail(response);
    }
}