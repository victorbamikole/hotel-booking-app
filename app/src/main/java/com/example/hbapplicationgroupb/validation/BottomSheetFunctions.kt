package com.example.hbapplicationgroupb.validation

import android.widget.RadioButton

fun convertArrayToString(array:ArrayList<String>):String{
    var stringHolder = ""

    if(array.size >0){
        for (i in array){
            stringHolder += "$i "

        }
    }
    return stringHolder
}


fun removerWhereArrayContain(array: ArrayList<String>, string: String){
    for (i in array){
        if (i.contains(string)){
            array.remove(i)
        }
    }
}