package com.example.hbapplicationgroupb.validation

import java.util.regex.Matcher
import java.util.regex.Pattern

object ResetPasswordValidationFunctions {
    fun checkIfFieldNotEmpty(string: String):Boolean{
        if (string.isNotEmpty()||string.isNotBlank()){
            return true
        }
        return false
    }
    fun checkIfPasswordMatches(stringOne: String, stringTwo: String):Boolean{
        if (stringOne == stringTwo){
            return true
        }
        return false
    }

     fun isValidPassword(password: String): Boolean {
        val pattern: Pattern
        val specialCharacters = "-@%\\[\\}+'!/#$^?:;,\\(\"\\)~`.*=&\\{>\\]<_"
        val PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$specialCharacters])(?=\\S+$).{8,20}$"
        pattern = Pattern.compile(PASSWORD_REGEX)
        val matcher: Matcher = pattern.matcher(password)
        return matcher.matches()
    }

    fun checkIfPassWordIsValid(string: String):Int{
        if (string.length < 8){
            // one means throw error password less than 8
            return 1
        }
        if (string.length >= 8 && !string.any {it in "0,1,2,3,4,5,6,7,8,9"}){
            //two means throw error that password does not contain number
            return 2
        }
        if (string.length >= 8 && string.any { it in "0,1,2,3,4,5,6,7,8,9"}
            && !string.any { it in "!@#\$&*"}){
            //two means throw error that password does not contain special character
            return 3
        }
        if (string.length >= 8 && string.any { it in "0,1,2,3,4,5,6,7,8,9" } && string.any { it in "!@#\$&*" }
            && !string.any { it in "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z" })
            {
                 return 4
        }

        if (string.length >= 8 && string.any { it in "0,1,2,3,4,5,6,7,8,9" } && string.any { it in "!@#\$&*" }
            && string.any { it in "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z"}
                    && !string.any { it in "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z"}){
            return 5
        }

        return 0
    }
}
