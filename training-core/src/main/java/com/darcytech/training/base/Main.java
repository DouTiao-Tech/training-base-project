package com.darcytech.training.base;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        new Main().moveZeroes(new int[]{1,0,2});
    }

    public void moveZeroes(int [] nums){

        int[] nuearray1 =new  int[]{};
        int[] nuearray2 =new  int[]{};

        int a=0;
        int b=0;

        for(int i =0;i<nums.length;i++){
            if(nums[i] == 0){
                nuearray1[a] = nums[i];
                a++;
            }else{
                nuearray2[b] = nums[i];
                b++;
            }


        }

        System.out.println(Arrays.toString(nuearray1));
//        int[] nuearray3 = ArrayUtils.addAll(nuearray1,nuearray2);
//        nums = nuearray3;

    }

}
