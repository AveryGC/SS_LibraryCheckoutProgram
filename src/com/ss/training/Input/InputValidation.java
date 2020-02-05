package com.ss.training.Input;

import java.util.Set;

public class InputValidation {
    public boolean checkInput(int min, int max, String input){
        try {
            int parsed = Integer.parseInt(input);
            if(parsed <= max && parsed >= min)
                return true;
            else if (parsed == 999)
                return true;
            else{
                System.out.println("!!!!Please enter proper input option!!!!");
                return false;
            }
        }
        catch (Exception e){
            System.out.println("!!!Improper input format!!!");
            return false;
        }
    }
    public boolean checkInputMap(Set<Integer> map, String input){
        try{
            int parsed = Integer.parseInt(input);
            if(map.contains(parsed))
                return true;
            else if(parsed==999)
                return true;
            else {
                System.out.println("!!!!Please enter proper input option!!!!");
                return false;
            }
        }
        catch (Exception e){
            System.out.println("!!!Improper input format!!!");
            return false;
        }
    }

//    public static void main(String[] args) {
//        InputValidation valid = new InputValidation();
//        Scanner scanner = new Scanner(System.in);
//        FileToMap filetomap = new FileToMap();
//        Map<Integer, Publisher> publisherMap = new HashMap<Integer,Publisher>();
//        filetomap.readPublisher(publisherMap);
//        String input;
//        int value = 98;
//
//        while (value != 999) {
//            input =  scanner.nextLine();
//            if(valid.checkInputMap(publisherMap.keySet(), input))
//                value = Integer.parseInt(input);
//
//        }
//    }
}
