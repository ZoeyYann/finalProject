package nju.zoey.java;

public class TooCrowdedException extends Exception{
    public TooCrowdedException(String message){
        super(message); //将基类用message初始化
    }
}
