package com.lh.wanandroid

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * des
 *
 * @author hellobrothers
 * @data 2020/12/23
 */
open class MyRuler: TestRule {
    override fun apply(base: Statement, description: Description): Statement = object :
        Statement() {
        override fun evaluate() {
            var methodName = description.methodName
            println("$methodName 测试开始！")

            if (base == null){
                println("请求参数为空")
                return
            }else{

                base?.evaluate()
            }

            println("$methodName 测试结束！")

        }

    }
}