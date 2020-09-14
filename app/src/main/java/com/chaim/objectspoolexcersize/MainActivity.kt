package com.chaim.objectspoolexcersize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chaim.objectspoolexcersize.myobjectspool.MyExpensiveObjectsPool
import com.chaim.objectspoolexcersize.myobjectspool.myexpensiveobject.IMyExpensiveObject
import com.chaim.objectspoolexcersize.myobjectspool.myexpensiveobject.MyExpensiveObjectImp

class MainActivity : AppCompatActivity() {

    lateinit var myExpensiveObject:IMyExpensiveObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myExpensiveObject = MyExpensiveObjectsPool.pull()
        myExpensiveObject.foo()

        repeat(5) {
            MyExpensiveObjectsPool.pull().foo()
            System.gc()
        }
    }

}
