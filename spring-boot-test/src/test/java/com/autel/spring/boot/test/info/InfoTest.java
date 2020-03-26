package com.autel.spring.boot.test.info;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

/**
 * @author A20019
 * @date 2020/3/25
 *
 * @SpringBootTest(classes = Application.class)
 *
 * webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT  随机端口启动
 *
 * MOCK：提供一个模拟的Servlet环境，内置的Servlet容器没有启动，配合@AutoConfigureMockMvc使用
 * RANDOM_PORT：提供真实的Servlet环境，启动内置容器，随即端口
 * DEFINED_PORT：配置真实Servlet环境，使用默认端口
 * NONE：提供跟Mock一样不真实的Servlet环境
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InfoTest {

    /**
     * 发现实际端口
     */
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setup() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void get(){
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        //assert (response.getBody().equals("is success"));
    }

}
