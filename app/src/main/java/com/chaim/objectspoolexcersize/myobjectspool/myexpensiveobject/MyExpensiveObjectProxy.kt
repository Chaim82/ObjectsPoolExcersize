package com.chaim.objectspoolexcersize.myobjectspool.myexpensiveobject

class MyExpensiveObjectProxy(private val expensiveObjectImp: IMyExpensiveObject): IMyExpensiveObject {

    override fun foo() {
        expensiveObjectImp.foo()
    }

    override fun bar() {
        expensiveObjectImp.bar()
    }
}