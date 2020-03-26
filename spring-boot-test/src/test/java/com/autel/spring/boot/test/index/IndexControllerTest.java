package com.autel.spring.boot.test.index;

import com.autel.spring.boot.test.controller.IndexController;
import com.autel.spring.boot.test.service.IndexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * https://github.com/keke-nan/spring-boot
 * https://github.com/keke-nan/spring-boot.git
 *
 * @author A20019
 * @date 2020/3/26
 */

@RunWith(SpringRunner.class)
@WebMvcTest(IndexController.class)
public class IndexControllerTest {

    /**
     *  内部类定义配置
     *  @SpringBootApplication
     *
     *  scanBasePackages属性指定了扫描的根路径
     *  确保测试的类在扫描的根路径下
     *  在该路径下的所有bean都会被实例化
     *
     */
   /* @SpringBootApplication(scanBasePackages = "com.autel.spring.boot")
    static  class InnerConfig{}*/


    @Autowired
    private MockMvc mvc;
    @MockBean
    private IndexService service;

    /**
     * 测试service层带参数调用
     *
     */
    @Test
    public void testGetApp() throws Exception {
        String request = "mimi";
        String response = "mock";
        /**
         * 打桩 stub，当service层方法被调用时，会直接返回设置的 response数值
         *
         * 只有mock模拟的bean，才能进行打桩
         *
         *  返回的response数值 ，会与conent()执行返回的数值进行匹配，若匹配成功，则测试成功
         */
        Mockito.when(service.app(request)).thenReturn(response);

        this.mvc.perform(MockMvcRequestBuilders.get("/index/app").param("info",request))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(response));
    }

    /**
     * 测试带参数的方法
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception {
        //由于conent值没有赋值，为null，所以在conent()进行匹配是也要为 null
        this.mvc.perform(MockMvcRequestBuilders.post("/index/save"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("save success,and conent: null"));
        String conent = "keke";  //输入参数的值
        this.mvc.perform(MockMvcRequestBuilders.post("/index/save").param("conent",conent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("save success,and conent: "+conent));
    }

    /**
     * 测试不带参数的方法
     */
    @Test
    public void testdex() throws Exception {
        /**
         * get       测试方法get请求的路径
         * state    设置请求状态正常
         * conent  与返回的结果匹配
         */
        this.mvc.perform(MockMvcRequestBuilders.get("/index/dex"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("this is no param"));
    }

}
