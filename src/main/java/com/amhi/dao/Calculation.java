package com.amhi.dao;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Calculation {
	
	static Format format =new DecimalFormat("#.###");	

	public static String[] getSingleAns(String[] segment, Double weight, Integer answer){
		String[] returnCalArray = new String[segment.length];
		for(int i=0; i<segment.length; i++)
			returnCalArray[i]= String.valueOf(format.format((Double.parseDouble(segment[i])*answer)));
		return returnCalArray;
	}
	
	public static String[] getMultiAns(List<String[]> segment, Double[] weight, Integer[] answer){
		String[] returnCalArray = new String[segment.get(0).length];
		double totalWeight = 0;
		for(double w:weight)
			totalWeight+=w;
		
		System.out.println("sis list ::::"+segment.size());
		for(int i=0; i<segment.size(); i++){
			String[] segments = segment.get(i);
			for(int j=0; j<segments.length; j++){
				System.out.println(i+"========="+returnCalArray[j] );
				System.out.println("we :::::"+weight[i]);
				System.out.println("ans ::::"+answer[i]);
				if(returnCalArray[j]==null){
					returnCalArray[j]= String.valueOf((Double.parseDouble(segments[j])*weight[i]*answer[i]));
				} else {
					Double total = Double.parseDouble(returnCalArray[j])+(Double.parseDouble(segments[j])*weight[i]*answer[i]);
					returnCalArray[j]= total.toString();
				}
			}
		}
		for(int i=0; i<returnCalArray.length; i++)
			returnCalArray[i] = String.valueOf(format.format((Double.parseDouble(returnCalArray[i])/totalWeight)));
		return returnCalArray;
	}
	public static String[] getFinalResult(List<String[]> list){
		String[] returnCalArray = null;
		double totalSumOfSegment = 0; 
		if(list!=null && !list.isEmpty()){
			returnCalArray = new String[list.get(0).length];
			for(int i=0; i<list.size(); i++){
				if(i==0){
					String[] array = list.get(i);
					for(int j=0;j<array.length;j++)
						returnCalArray[j]= array[j];
				}else{
					String[] array = list.get(i);
					for(int j=0;j<array.length;j++)
						returnCalArray[j]= String.valueOf((Double.parseDouble(returnCalArray[j]) * Double.parseDouble(array[j])));
				}
			}
		}
		if(returnCalArray!=null){
			for(String segment:returnCalArray)
				totalSumOfSegment+=(Double.parseDouble(segment));
			for(int i=0; i<returnCalArray.length; i++)
				returnCalArray[i]=String.valueOf(format.format((Double.parseDouble(returnCalArray[i])/totalSumOfSegment)));
		}
		return returnCalArray;
	}
	
	public static void main(String... string){
		List<String[]> list = new ArrayList<String[]>();
		List<String[]> segment = new LinkedList<String[]>();
		segment.add(new String[]{"0.33","0.21","0.09","0.37"});
		segment.add(new String[]{"0.36","0.18","0.14","0.32"});
		segment.add(new String[]{"0.32","0.19","0.13","0.36"});
		
		Double[] weights= new Double[]{0.18,0.73,0.5};
		Integer[] ans= new Integer[]{1,1,1};
	
		List<String[]> segment1 =  new LinkedList<String[]>();
	
		segment1.add(new String[]{"0.17","0.19","0.40","0.24"});
		segment1.add(new String[]{"0.16","0.30","0.28","0.26"});
		Double[] weights1= new Double[]{0.45,0.37};
		Integer[] ans1= new Integer[]{1,1};
	
	
		
		list.add(getSingleAns(new String[]{"0.175","0.229","0.213","0.383"}, 0.18, 1));
		
		list.add(getMultiAns(segment, weights, ans));
		list.add(getMultiAns(segment1, weights1, ans1));
		
		list.add(getSingleAns(new String[]{"0.124","0.189","0.515","0.172"}, 0.17, 1));
		list.add(getSingleAns(new String[]{"0.371","0.184","0.164","0.280"}, 0.19, 1));
		
		
		for(String str:getFinalResult(list)){
			System.out.println("str :::::::"+str);
		}
	}
}
