package com.tech.logic;

import java.util.ArrayList;
import java.util.Random;

public class MasterMindLogic {
    private String code;
    private Random random;
    private int lifeLevel = 5;


    public MasterMindLogic(Random random){
        this.random = random;
    }
    public MasterMindLogic(){
        this.random = new Random();
    }

    private String code(){
        ArrayList<Integer> code = new ArrayList<>();
        StringBuilder finalCode = new StringBuilder();
        while (code.size() != 4){
            int digit = random.nextInt(7)+1;
            if(code.contains(digit)==false){
                code.add(digit);
            }
        }
        for(Integer number: code){
            finalCode.append(number);
        }

        return finalCode.toString();
    }

    public String getCode(){
        return this.code;
    }
    public boolean checkCode(String code){
        return this.code.equals(code);
    }
    public void takeLife(){
        if(this.lifeLevel > 0){
            this.lifeLevel--;
        }
    }
    public boolean hasLife(){
        if(this.lifeLevel > 0){
            return true;
        }
        return  false;
    }
    public String startGame(){
        this.code = code();
        return  "gameStarted";
    }
}
