package com.piegroup.zzbm.Utils;

import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

@Slf4j
public class TimeUtilTest {

    @Test
    public void timestampToDate() {
        long time = System.currentTimeMillis();
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(time);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        log.info(res);
    }
}