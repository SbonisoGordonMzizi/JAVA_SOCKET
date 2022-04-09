package com.tech.utils;



public enum ServerClientConst {
    SERVER_PORT(8080);

    private int portNumber;
    private ServerClientConst(int portNumber){
        this.portNumber = portNumber;
    }
    public int getPortNumber(){
        return this.portNumber;
    }
}
