package top.year21.utils;

import org.apache.commons.beanutils.BeanUtils;
import java.util.Map;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: TODO
 * @date 2022/3/26 23:03
 */
public class WebUtils {

    /**
     * Description : 把map的值注入到对应的JavaBean的属性中
     * @date 2022/3/26
     * @time 23:31
     * @user hcxs1986
     * @param value
     * @param bean
     * @return Object
     **/
    public static <T>  T copyParamToBean(Map value, T bean){
        try {
            System.out.println("注入之前" + bean);
            /**
             * 把所有请求参数都注入到bean对象中
             */
            BeanUtils.populate(bean,value);
            System.out.println("注入之后" + bean);

        } catch (Exception e) {
//            e.printStackTrace();
        }
        return bean;
    }


    /**
     * Description : 将字符串转换为int类型数据
     * @date 2022/3/27
     * @time 17:43
     * @user hcxs1986
     * @param s 要被转换的String变量
     * @param defaultValue Integer类型的默认值
     * @return int
     **/
    public static int getParseInt(String s,int defaultValue){
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

}
