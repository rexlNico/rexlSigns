package de.rexlNico.Signs.Api;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Created by rexlNico on 22.07.2017.
 * Plugin by rexlNico
 * Coded with Eclipse
 */
public class ServerPing {
    String host;
    int port;

    boolean online;
    String motd;
    int maxplayers;
    int onlineplayers;
    boolean ingame = false;

    public ServerPing(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    public ServerPing(int port) {
        this.host = "127.0.0.1";
        this.port = port;
    }

    //SET

    public void setOnline(boolean online) {
        this.online = online;
    }
    
    public void setOnlineplayers(int onlineplayers) {
        this.onlineplayers = onlineplayers;
    }

    public void setMaxplayers(int maxplayers) {
        this.maxplayers = maxplayers;
    }

    public void setMotd(String motd) {
        this.motd = motd;
    }
    
    public void setIngame(boolean ingame) {
    	this.ingame = ingame;
    }

    //GET

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public boolean getOnline() {
        return online;
    }

    public int getOnlineplayers() {
        return onlineplayers;
    }

    public int getMaxplayers() {
        return maxplayers;
    }

    public String getMotd() {
        return motd;
    }

    public void ping() {

        try {
            Socket socket = new Socket();

            socket.setSoTimeout(2500);
            socket.connect(new InetSocketAddress(host, port));

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, Charset.forName("UTF-16BE"));

            dos.write(new byte[] {(byte)0xFE, (byte)0x01});

            int packetID = is.read();

            if(packetID == -1) {
                dos.close();
                is.close();
                isr.close();
                socket.close();
            }
            if(packetID != 0xFF) {
                dos.close();
                is.close();
                isr.close();
                socket.close();
            }

            int packetLenght = isr.read();

            if(packetLenght == -1) {
                dos.close();
                is.close();
                isr.close();
                socket.close();
            }
            if(packetLenght == 0) {
                dos.close();
                is.close();
                isr.close();
                socket.close();
            }

            char[] chars = new char[packetLenght];

            if(isr.read(chars, 0, packetLenght) != packetLenght) {
                dos.close();
                is.close();
                isr.close();
                socket.close();
            }

            String str = new String(chars);
            String[] info = str.split("\0");

            try {
                setMotd(info[3]);
                setOnline(getMotd() != null);
                setOnlineplayers(Integer.parseInt(info[4]));
                setMaxplayers(Integer.parseInt(info[5]));
                if(getMotd().contains("ingame")){
                	setIngame(true);
                }else setIngame(false);
                dos.close();
                is.close();
                isr.close();
                socket.close();
            } catch(Exception ex) {
                setOnline(false);
                setMaxplayers(0);
                setOnlineplayers(0);
                setMotd(null);
                setIngame(false);
                dos.close();
                is.close();
                isr.close();
                socket.close();
            }

            dos.close();
            is.close();
            isr.close();
            socket.close();

        } catch (Exception e) {
            setOnline(false);
            setMaxplayers(0);
            setOnlineplayers(0);
            setMotd(null);
            setIngame(false);
        }

        
        
    }

    public String toString() {
        return host + ":" + port;
    }
}