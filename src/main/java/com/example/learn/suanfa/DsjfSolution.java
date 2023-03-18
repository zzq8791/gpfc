package com.example.learn.suanfa;

/**
 * Unknown
 *
 * @author : 你的名字
 * @date : 2022-09-28 15:47
 **/
public class DsjfSolution {

    public static void main(String[] args) {
        String str1 = "95267";;
        String str2 = "659";

        DsjfSolution ss = new DsjfSolution();
        System.out.println(ss.addStr(str1,str2));

    }

    private String addStr(String s , String t){

        if(s.length() ==0){
            return t;
        }
        if(t.length() ==0){
            return s;
        }

        if(s.length() < t.length()){
            // change
            String temp = t;
            t = s;
            s = temp;
        }

        System.out.println(s  +"   "+t);

        int carry = 0;
        int tValue = 0;
       // StringBuilder builder = new StringBuilder();
        char[] res = new char[s.length()];
        for(int i = s.length()-1;i>=0;i--){
            char c = s.charAt(i);
            System.out.println(c  +"   "+(c -'0')+"  c1"+c);
            // t 从对应数组最高位开始
            int tindex = i-(s.length() - t.length());
            if(tindex >=0){
                tValue = t.charAt(tindex);
            }else {
                tValue=0;
            }
            System.out.println("cindex "+ i  +"   "+tindex+" :"+ tindex);

            int sum = 0;
            sum = (s.charAt(i)-'0') + carry;
          /*  if(tindex < 0){
                sum = (s.charAt(i)-'0');
                int decimalSystem = sum/10;
                int surplus = sum%10;
                //if(decimalSystem == 0){
             //   builder.append(sum);
            }else {
                sum = (s.charAt(i)-'0') + (tValue-'0') + carry;
                int decimalSystem = sum/10;
                int surplus = sum%10;
                carry = decimalSystem;
                System.out.println(surplus + "  "+decimalSystem);
                //if(decimalSystem == 0){
              //  builder.append(surplus);
            }*/
          if(tindex >=0){
              sum += + (tValue-'0');
          }
            carry = sum/10;
            int temp = sum%10;
            res[i] =  (char)(temp + '0');
          //  }else {
           //     builder.append(decimalSystem);
           // }


        }
        String output = String.valueOf(res);
        if(carry == 1){
            output = "1"+output;
        }
        return output;
    }

}
