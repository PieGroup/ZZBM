package com.piegroup.zzbm;

import com.piegroup.zzbm.Utils.SpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@MapperScan("com.peigroup.zzbm.Dao")
@Import(SpringUtil.class)   //因为普通类要使用Dao层 和service层所添加这个工具类，该工具类在项目启动类进行引入，否则会报空指针异常。
/*******************驱动缓存机制生效********************************/
//@EnableCaching
/*session共享缓存*/
//@EnableRedisHttpSession
@ServletComponentScan
public class ZzbmApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZzbmApplication.class, args);
    }

}
