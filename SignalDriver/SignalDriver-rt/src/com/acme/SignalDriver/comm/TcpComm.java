package com.acme.SignalDriver.comm;

import com.acme.SignalDriver.BSignalDriverNetwork;

import javax.baja.sys.BajaRuntimeException;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Derek Otieno on february 17, 2018.
 */

public class TcpComm {

    /**
     * Constructor
     * @param network
     */
    public TcpComm(BSignalDriverNetwork network)
    {
        this.network = network;
    }

    /**
     * This method writes the request to the socket connection.
     * @param sock
     * @param request
     */
    private void writeMessage(Socket sock, String request)
    {
        PrintWriter out = null;
        try
        {
            //Create an output and input writer and reader
            out = new PrintWriter(sock.getOutputStream(), true);

            //encode the request with the starting and ending chars
            //that are specific to our device's protocol
            StringBuffer msg = new StringBuffer();
            msg.append((char)0);
            msg.append(request);
            msg.append((char)23);

            //Flush our message to the output stream
            out.print(msg.toString());
            out.flush();

        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
    /**
     * Read the message response from our request from the socket
     */
    private String readMessage(Socket sock)
    {
        //Listen to the input stream for the device network response
        BufferedReader in = null;
        String response = null;

        try
        {
            //setup the buffered reader input Stream
            InputStream is = sock.getInputStream();
            in = new BufferedReader(new InputStreamReader(is));

            //read characters into String Buffer
            char val;
            StringBuffer value = new StringBuffer();
            while( (val = (char)in.read()) > -1 )
            {
                //if our character is a null character, keep reading. if
                //we hit ASCII character 23, our message is terminated.
                if( val == (char)0) continue;
                else if( val == (char)23) break;

                value.append(val);
            }

            //return the response we received from the input stream
            response = value.toString();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

        return response;
    }

    /**
     * This method sends the String request to the remote server at
     * the configured network port after formatting the string
     * message with the specific protocol format of the
     * SignalDriver protocol.
     *
     * @param request
     *
     * @return String response from the server.
     */
    public String sendRequest(String request)
    {
        String response = null;
        Socket sock = new Socket();

        try
        {
            //get the IP address and port to which our socket will
            //connect
            String addr = network.getIpAddress();
            int ipPort = network.getIpPort();

            try
            {
                //attempt to connect to our address with a 1 second timeout
                InetSocketAddress insa =
                        new InetSocketAddress(InetAddress.getByName(addr), ipPort);
                sock.connect(insa, 1000);
            }
            catch (Exception e)
            {
                sock = null;
                throw new BajaRuntimeException("Cannot connect to " + addr, e);
            }

            //write the request to our socket
            writeMessage(sock, request);

            //read the response from our request from the socket
            response = readMessage(sock);

        }
        //in our finally block, force close of all streams and sockets.
        finally{
            try
            {
                if( null != sock) sock.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return response;
    }

/////////////////////////////////////////////////////////////////
//  Attributes
///////////////////////////////////////////////////////////////

    private BSignalDriverNetwork network;
}