package com.example.anchorbookskvh

import com.example.anchorbookskvh.views.formatChileanPeso
import org.junit.Test

class FormatterTest {

    /*testeo de una extension function que formatea el precio (en entero)
     a un string, separando por miles con un punto, y agregando signo peso ($) al inicio*/
    @Test
    fun formattedPrice_happycase(){
        //given
        val price=10000


        //when
        val formatedPrice=price.formatChileanPeso()

        //then
        assert(formatedPrice.equals("$10.000"))
    }

    @Test
    fun formattedPrice_badcase(){
        //given
        val price=10000


        //when
        val formatedPrice=price.formatChileanPeso()

        //then
        assert(formatedPrice.equals("10000"))
    }

}