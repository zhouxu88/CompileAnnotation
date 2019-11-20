package com.zx.processor;

/**
 * @author 周旭
 * @company 伊柯夫
 * @e-mail 374952705@qq.com
 * @time 2019/11/18
 * @descripe
 */


import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import com.zx.annotation.BindView;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Parameterizable;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

/**
 * 生成注解逻辑代码的辅助类
 */
public class ProxyInfo {
    public static final String PROXY = "_ViewBinding";
    /**
     * 注解变量的集合
     */
    public Map<Integer, VariableElement> viewVariableElement = new HashMap<>();
    /**
     * 生成的代理类的名称
     */
    public String proxyClassName;


    /**
     * 生成的代理类的包名
     */
    public String packageName;

    private TypeElement typeElement;

    public ProxyInfo(TypeElement typeElement, String packageName) {
        this.typeElement = typeElement;
        this.packageName = packageName;
        String className1 = getClassName(typeElement, packageName);
        this.proxyClassName = className1 + PROXY;
    }

    /**
     * 获取生成的代理类的类名
     * 之所以用字符串截取、替换而没用clas.getSimpleName()的原因是为了处理内部类注解的情况，比如adapter.ViewHolder
     * 内部类反射之后的类名：例如MyAdapter$ContentViewHolder，中间是$，而不是.
     *
     * @param type
     * @param packageName
     * @return
     */
    private String getClassName(TypeElement type, String packageName) {
        int packageLen = packageName.length() + 1;
        return type.getQualifiedName().toString().substring(packageLen)
                .replace('.', '$');
    }


    /**
     * 通过javapoet API生成代理类
     * @return
     */
    public TypeSpec generateProxyClass() {
        //代理类实现的接口
        ClassName viewInjector = ClassName.get("com.zx.inject_api", "IViewInjector");
        //类
//        ClassName className = ClassName.bestGuess(simpleClassName);
        ClassName className = ClassName.get(typeElement);
        // 类型变量
//        TypeVariableName tTypeVariable = TypeVariableName.get("T");

        //  泛型接口，implements IViewInjector<MainActivity>
        ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.get(viewInjector, className);


        //生成构造方法
        MethodSpec.Builder constructorBuilder = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(className, "target")
                .addStatement("this.target = target");


        //生成接口的实现方法inject()
        MethodSpec.Builder bindBuilder = MethodSpec.methodBuilder("inject")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class) //添加方法注解
                .addParameter(className, "target")
                .addParameter(Object.class, "source");

        for (int id : viewVariableElement.keySet()) {
            VariableElement element = viewVariableElement.get(id);
            String fieldName = element.getSimpleName().toString();
            bindBuilder.addStatement(" if (source instanceof android.app.Activity){target.$L = ((android.app.Activity) source).findViewById( $L);}" +
                    "else{target.$L = ((android.view.View)source).findViewById($L);}", fieldName, id, fieldName, id);
        }

        MethodSpec bindMethodSpec = bindBuilder.build();
//        MethodSpec constructorMethodSpec = constructorBuilder.build();

        //创建类
        TypeSpec typeSpec = TypeSpec.classBuilder(proxyClassName)
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(parameterizedTypeName) //实现接口
//                .addTypeVariable(tTypeVariable)
//                .addMethod(constructorMethodSpec)
                .addMethod(bindMethodSpec) //添加类中的方法
//                .addField(className, "target", Modifier.PRIVATE)
                .build();

        return typeSpec;
    }


}
