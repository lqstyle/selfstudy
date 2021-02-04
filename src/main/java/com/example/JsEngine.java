package com.example;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author liangqing
 * @since 2020/12/22 15:29
 */
public class JsEngine {
    public static void main(String[] args) throws ScriptException {
        ScriptEngine engine= new ScriptEngineManager().getEngineByName("Nashorn");
        String fc = "var a={name:'hello'}, b={name:\"hi\"} \n" +
                "    function exchange(a, b){\n" +
                "    var c=b; b=a; a=c;\n" +
                "   a.name=a.name+\"1\"; \n" +
                "   b.name=b.name+'2';\n" +
                "   print(a.name,b.name);\n" +
                "    }\n" +
                "   exchange(a, b); \n" +
                "   print(a.name, b.name);";

        engine.eval(fc);
    }


}
