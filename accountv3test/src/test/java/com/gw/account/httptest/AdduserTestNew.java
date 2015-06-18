package com.gw.account.httptest;

import com.gw.account.utils.MyCheckUtil;
import com.gw.account.utils.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * Created by Hihiri on 2015/5/13.
 */
public class AdduserTestNew {
    private static final Log LOG = LogFactory.getLog(AdduserTestNew.class);
    private User user = new User();

    @BeforeClass
    public static void globalInit() {
        MyCheckUtil.initialize();
    }

    @Test
    public void testAdduser() throws InterruptedException, SAXException, IOException {
    }
}
