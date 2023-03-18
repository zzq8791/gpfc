package com.example.learn.suanfa;

public class DsJiafa {

    public static void main(String[] args) {
        DsJiafa dd = new DsJiafa();
        String solve = dd.solve("95267", "659");
        System.out.println(solve);
    }

    public String solve (String s, String t) {
        //若是其中一个为空，返回另一个
        //让s为较长的，t为较短的
        if(s.length() < t.length()){
            String temp = s;
            s = t;
            t = temp;
        }
        int carry = 0; //进位标志
        char[] res = new char[s.length()];
        //从后往前遍历较长的字符串
        for(int i = s.length() - 1; i >= 0; i--){
            //转数字加上进位
            int temp = s.charAt(i) - '0' + carry;
            //转较短的字符串相应的从后往前的下标
            int j = i - s.length() + t.length();
            //如果较短字符串还有
            if(j >= 0)
                //转数组相加
                temp += t.charAt(j) - '0';
            //取进位
            carry = temp / 10;
            //去十位
            temp = temp % 10;
            //修改结果
            res[i] = (char)(temp + '0');
        }
        String output = String.valueOf(res);
        //最后的进位
        if(carry == 1)
            output = '1' + output;
        return output;
    }
}
