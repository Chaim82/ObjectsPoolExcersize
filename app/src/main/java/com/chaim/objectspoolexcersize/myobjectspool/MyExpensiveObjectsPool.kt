package com.chaim.objectspoolexcersize.myobjectspool

import android.util.Log
import com.chaim.objectspoolexcersize.myobjectspool.myexpensiveobject.IMyExpensiveObject
import com.chaim.objectspoolexcersize.myobjectspool.myexpensiveobject.MyExpensiveObjectImp
import com.chaim.objectspoolexcersize.myobjectspool.myexpensiveobject.MyExpensiveObjectProxy
import java.lang.ref.ReferenceQueue
import java.lang.ref.WeakReference

object MyExpensiveObjectsPool {

    class MyExpensiveObjectReference(val expensiveObjectImp: MyExpensiveObjectImp, referent: MyExpensiveObjectProxy?) : WeakReference<MyExpensiveObjectProxy>(referent)

    private val objectsList:ArrayList<MyExpensiveObjectImp> = ArrayList()
    private val inUseList by lazy{
        arrayListOf<MyExpensiveObjectReference>()
    }

    init {
        objectsList.addBatchOfObjects()
    }

    fun pull(): IMyExpensiveObject{
        synchronized(this){
            if(objectsList.size > 0){
                return objectsList[0].let {obj->
                    objectsList.remove(obj)
                    MyExpensiveObjectProxy(obj).also {proxy->
                        inUseList.add(MyExpensiveObjectReference(obj,proxy))
                    }
                }
            }
            else{
                inUseList.filter { objRef-> objRef.get() == null }.also {
                    inUseList.removeAll(it)
                }.map { it.expensiveObjectImp }.let{
                    objectsList.addAll(it)
                }
                if(objectsList.size == 0){
                    objectsList.addBatchOfObjects()
                }
                else{
                    Log.d("chaim", "recycle")
                }
               return pull()
            }
        }
    }

    private fun ArrayList<MyExpensiveObjectImp>.addBatchOfObjects(){
        repeat(2) {
             add(MyExpensiveObjectImp())
        }
    }

}