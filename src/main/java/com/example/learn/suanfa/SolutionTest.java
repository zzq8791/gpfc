package com.example.learn.suanfa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolutionTest {

    private static List<ArrayList<Integer>> res = new ArrayList<>();

    public static void main(String[] args) {
        int num[] = {1,2,1,5,6,7,10};

        Arrays.sort(num);
        dfs(new ArrayList<>(),num,-1,8,0);
        System.out.println(res);
    };

    private static void dfs(ArrayList<Integer> list, int num[], int currentIdx, int target  , int sum){
        // 如果是8 ，退出递归循环
        if(currentIdx ==( num.length-1) && sum != target){
            return;
        }
        if(sum == target ){
            res.add(new ArrayList<Integer>(list));
            return;
        }
        // 开始递归
        for(int nextIdx = currentIdx + 1;nextIdx < num.length;){
          //  sum = sum +num[nextIdx];
            if(sum +num[nextIdx] > target ){
                break;
            }
            // 如果循环中相加和大于8，退出本次循环
            list.add(num[nextIdx]);
            // 回溯
            dfs(list,num,nextIdx,target,sum +num[nextIdx]) ;
            // 剪枝
            list.remove(list.size() -1);
            nextIdx ++;
            while (nextIdx < num.length && num[nextIdx- 1] == num[nextIdx] ){
                nextIdx ++;
            }

        }
    }

}
