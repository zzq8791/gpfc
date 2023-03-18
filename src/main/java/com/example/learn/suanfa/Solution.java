package com.example.learn.suanfa;

import com.alibaba.fastjson.JSON;

import java.util.*;

// 菜鸡解法
public class Solution {
    private ArrayList<ArrayList<Integer>> ansLists;
    private int[] num;
    private int target;


    public static void main(String[] args) {
        int[] num = {10,1,2,7,6,1,5};
        int target = 8;
        Solution solution = new Solution();
        ArrayList<ArrayList<Integer>> arrayLists = solution.combinationSum2(num, target);
        System.out.println(arrayLists);
    }

    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        // 组合要求不重复，需要与排列区分
        ArrayList<ArrayList<Integer>> ansLists = new ArrayList<>();
        if(num==null || num.length==0) return ansLists;

        this.ansLists = ansLists;
        this.num = num;
        this.target = target;
        // 排序方便防重复、剪枝
        Arrays.sort(num);
        System.out.println(JSON.toJSONString(num));
        dfs(new ArrayList<>(), -1, 0);

        return this.ansLists;
    }

    // 组合防止重复只能往后取
    public void dfs(ArrayList<Integer> list, int currIdx, int currSum) {
        if(currIdx==num.length-1 && currSum!=target) return;
        if(currSum==target) {
            ansLists.add(new ArrayList<Integer>(list));
            return;
        }
        System.out.println(list);
        for(int nextIdx=currIdx+1; nextIdx<num.length; ) {
            // 剪枝
            System.err.println("currSum+num[nextIdx]  "+(currSum+num[nextIdx]) +" num[nextIdx] "+num[nextIdx] +"  "+ "nextIdx  :"+nextIdx);
            if(currSum+num[nextIdx]>target) break;

            // 回溯
            list.add(num[nextIdx]);
            System.err.println("递归入参 list  "+(list) +" nextIdx "+nextIdx+"  "+ "currSum+num[nextIdx]  :"+ (currSum+num[nextIdx]));
            dfs(list, nextIdx, currSum+num[nextIdx]);
            list.remove(list.size()-1);
            // 滑动到下一个不同值，防止重复
            nextIdx++;
            while(nextIdx<num.length && num[nextIdx-1]==num[nextIdx]) {
                nextIdx++;
            }
        }
    }
}
