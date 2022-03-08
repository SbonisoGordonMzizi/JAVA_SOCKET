package com.tech;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public class GetInterfaceInfor {

    public static void main(String[] args)  {
        try {
            Enumeration<NetworkInterface> interfaceEnumeration = NetworkInterface.getNetworkInterfaces();
            for(NetworkInterface element: Collections.list(interfaceEnumeration)){
                System.out.println(element.getName());
                System.out.println(element.getDisplayName());
            }
        }catch (SocketException e){
            System.out.println(e.getStackTrace());
        }
    }
}
