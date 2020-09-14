package com.chaim.objectspoolexcersize.myobjectspool.myexpensiveobject

import android.util.Log
import com.chaim.objectspoolexcersize.myobjectspool.myexpensiveobject.IMyExpensiveObject

class MyExpensiveObjectImp :
    IMyExpensiveObject {

    private val index:Int = count

    init {
        count++
    }

    companion object{
        private var count:Int=0
    }

    override fun foo() {
        Log.d("MyExpensiveObjectImp", "foo method invoked on object index $index" )
    }

    override fun bar() {
        Log.d("MyExpensiveObjectImp", "bar method invoked on object index $index" )
    }
}