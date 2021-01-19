package org.example;

/**
 * Created by mobingsen on 2020/9/29 15:58
 */
public class _14_case {

    public static void main(String[] args) {
        Type t = Type.WEB;
        String devStr = "";
        switch (t) { // switch 可以是int short string(取其hashcode) 等，不能是long
            case IOS: {
                devStr = "ios";
            }
            break;
            case ANDROID: {
                devStr = "android";
            }
            break;
            case WEB: {
                devStr = "web";
            }
            break;
        }
        System.out.println(devStr);
    }

    static enum Type {
        IOS,
        WEB,
        ANDROID;
    }
}
