package com.colobu.zkrecipe.leaderelection;

import com.google.common.base.Optional;

public class OptionalTest {
	
	public static void main(String[] args) {
		 
        Optional<Integer> possible=Optional.of(6);
        Optional<Integer> absentOpt=Optional.absent();
        Optional<Integer> NullableOpt=Optional.fromNullable(null);
        Optional<Integer> NoNullableOpt=Optional.fromNullable(10);
        if(possible.isPresent()){
            System.out.println("possible isPresent:"+possible.isPresent());
            System.out.println("possible value:"+possible.get());
        }
//        if(absentOpt.isPresent()){
            System.out.println("absentOpt isPresent:"+absentOpt.get()); ;
//        }
//        if(NullableOpt.isPresent()){
            System.out.println("fromNullableOpt isPresent:"+NullableOpt.get()); ;
//        }
        if(NoNullableOpt.isPresent()){
            System.out.println("NoNullableOpt isPresent:"+NoNullableOpt.isPresent()); ;
        }
    
	}
    
}