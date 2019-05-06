package com.acme.SignalDriver;

        import javax.baja.driver.BDeviceFolder;
        import javax.baja.nre.annotations.NiagaraType;
        import javax.baja.sys.Sys;
        import javax.baja.sys.Type;

/**
 * Created by Derek Otieno on February 17, 2018.
 */

@NiagaraType
public class BSignalDeviceFolder extends BDeviceFolder {
    /*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
    /*@ $com.acme.signalDriver.BSignalDeviceFolder(2979906276)1.0$ @*/
    /* Generated Sat Feb 17 18:12:22 EST 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////

    @Override
    public Type getType() { return TYPE; }
    public static final Type TYPE = Sys.loadType(BSignalDeviceFolder.class);

    /*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
}