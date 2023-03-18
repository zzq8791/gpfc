package com.example.learn.suanfa;

import java.util.ArrayList;
/**
 * 描述
 * 输入一个链表的头节点，按链表从尾到头的顺序返回每个节点的值（用数组返回）。
 *
 * 如输入{1,2,3}的链表如下图:
 *
 * 返回一个数组为[3,2,1]
 *
 * 0 <= 链表长度 <= 10000
 * 示例1
 * 输入：
 * {1,2,3}
 * 复制
 * 返回值：
 * [3,2,1]
 */
public class PrintNode {


   /* public static void main(String[] args) {
        ListNode node = new ListNode();

    }*/


    ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if(listNode != null){
            this.printListFromTailToHead(listNode.next);
            list.add(listNode.val);
        }
        return list;
    }

}
