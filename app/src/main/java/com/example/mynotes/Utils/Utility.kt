package com.example.mynotes.Utility

import java.text.SimpleDateFormat
import java.util.*

object Utility {
    fun getMonthFromNumber(i:Int) =
        when(i){
            0->"Jan"
            1->"Feb"
            2->"Mar"
            3->"Apr"
            4->"May"
            5->"June"
            6->"July"
            7->"Aug"
            8->"Sep"
            9->"Oct"
            10->"Nov"
            11->"Dec"
            else->"error"
        }



    fun getCurrentDateAndTime(timeMilis:Long):String{
        var cal= Calendar.getInstance()
        cal.timeInMillis=timeMilis
        var month=cal.get(Calendar.MONTH)
        var day=cal.get(Calendar.DATE)
        var monthStr= getMonthFromNumber(month)
        var dayStr=day.toString()
        var modificationDate= "$dayStr $monthStr"
        var dateFormatter= SimpleDateFormat("hh.mm aa")
        var modificationTime= dateFormatter.format(cal.time)
        return "$modificationDate $modificationTime"









    }
}