package test.com.qiniu.qvs;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.qvs.StatsManager;
import com.qiniu.util.Auth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import test.com.qiniu.TestConfig;

public class StatsTest {
    Auth auth = TestConfig.testAuth;
    private StatsManager statsManager;
    private Response res = null;
    private final String namespaceId = "";
    private final String streamId = "";
    private final String tu = "5min";
    private final int start = 20200901;
    private final int end = 20200902;

    @BeforeEach
    public void setUp() throws Exception {
        this.statsManager = new StatsManager(auth);
    }

    @Test
    @Tag("IntegrationTest")
    public void testQueryFlow() {
        try {
            res = statsManager.queryFlow(namespaceId, streamId, tu, start, end);
            assertNotNull(res);
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            e.printStackTrace();
        } finally {
            if (res != null) {
                res.close();
            }
        }
    }

    @Test
    @Tag("IntegrationTest")
    public void testQueryBandwidth() {
        try {
            res = statsManager.queryBandwidth(namespaceId, streamId, tu, start, end);
            assertNotNull(res);
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            e.printStackTrace();
        } finally {
            if (res != null) {
                res.close();
            }
        }
    }
}
