package com.acme.SignalDriver;

import com.acme.SignalDriver.comm.TcpComm;

import javax.baja.driver.BDeviceNetwork;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

/**
 * Created by Derek Otieno on february 17, 2018.
 */
@NiagaraProperty( name = "ipAddress",
        type = "String",
        defaultValue = "127.0.0.1")

@NiagaraProperty( name = "ipPort",
        type = "int",
        defaultValue = "23")
@NiagaraType
public class BSignalDriverNetwork extends BDeviceNetwork {
    /*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
    /*@ $com.acme.signalDriver.BSignalDriverNetwork(2226749828)1.0$ @*/
    /* Generated Sat Feb 17 17:55:34 EST 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "ipAddress"
////////////////////////////////////////////////////////////////

    /**
     * Slot for the {@code ipAddress} property.
     * @see #getIpAddress
     * @see #setIpAddress
     */
    public static final Property ipAddress = newProperty(0, "127.0.0.1", null);

    /**
     * Get the {@code ipAddress} property.
     * @see #ipAddress
     */
    public String getIpAddress() { return getString(ipAddress); }

    /**
     * Set the {@code ipAddress} property.
     * @see #ipAddress
     */
    public void setIpAddress(String v) { setString(ipAddress, v, null); }

////////////////////////////////////////////////////////////////
// Property "ipPort"
////////////////////////////////////////////////////////////////

    /**
     * Slot for the {@code ipPort} property.
     * @see #getIpPort
     * @see #setIpPort
     */
    public static final Property ipPort = newProperty(0, 23, null);

    /**
     * Get the {@code ipPort} property.
     * @see #ipPort
     */
    public int getIpPort() { return getInt(ipPort); }

    /**
     * Set the {@code ipPort} property.
     * @see #ipPort
     */
    public void setIpPort(int v) { setInt(ipPort, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////

    @Override
    public Type getType() { return TYPE; }
    public static final Type TYPE = Sys.loadType(BSignalDriverNetwork.class);

    /*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
    public BSignalDriverNetwork()
    {
        this.comm = new TcpComm(this);
    }

    /**
     * Get the Type of the BDevice which our network can parent
     */
    @Override
    public Type getDeviceType()
    {
        return BSignalDevice.TYPE;
    }

    /**
     * Get the Type of the BDeviceFolder which our network
     * can parent
     */
    @Override
    public Type getDeviceFolderType()
    {
        return BSignalDeviceFolder.TYPE;
    }

    /**
     * Sends a message over the TCP Comm stack and returns
     * the response from the remote network.
     */
    public String sendRequest(String request)
    {
        try { return comm.sendRequest(request); }
        catch (Exception e){ return "Failed " + e.getMessage(); }
    }
    /**
     * Ping the remote network with a protocol specific ping command
     */
    @Override
    public void doPing() throws Exception
    {
        String response = sendRequest("ping");

        if( response.equals("ping ok!"))
            pingOk();
        else
            pingFail(response);
    }

/////////////////////////////////////////////////////////////////
//  Attributes
/////////////////////////////////////////////////////////////////

    private TcpComm comm;
}