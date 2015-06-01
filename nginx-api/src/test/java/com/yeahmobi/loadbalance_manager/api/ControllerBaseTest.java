package com.yeahmobi.loadbalance_manager.api;

import org.adclear.dbunit.json.DbUnitRuleMongo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.yeahmobi.loadbalance_manager.api.AbstractController.JsonResult;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test/applicationContext-test.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
public abstract class ControllerBaseTest extends AbstractJUnit4SpringContextTests {

    protected static final String      IP          = "127.0.0.1";

    protected static final int         PORT        = 12345;

    public static final String         MONGODB_URI = "mongodb://localhost:" + PORT + "/loadbalance_manager";

    @Rule
    public DbUnitRuleMongo             dbUnit      = new DbUnitRuleMongo(ControllerBaseTest.class, MONGODB_URI);

    /**
     * please store Starter or RuntimeConfig in a static final field if you want to use artifact store caching (or else
     * disable caching)
     */
    private static final MongodStarter starter     = MongodStarter.getDefaultInstance();

    private static MongodExecutable    _mongodExe;
    private static MongodProcess       _mongod;

    // private static MongoServer server;
    //
    // private static SocketAddress socketAddress = new InetSocketAddress(PORT);

    @BeforeClass
    public static void setUpClass() throws Exception {
        _mongodExe = starter.prepare(new MongodConfigBuilder().version(Version.Main.DEVELOPMENT).net(new Net(
                                                                                                             PORT,
                                                                                                             Network.localhostIsIPv6())).build());
        _mongod = _mongodExe.start();

        // server = new MongoServer(new MemoryBackend());

        // bind on a random local port
        // server.bind(socketAddress);
    }

    @After
    public void setUp() throws Exception {
        // MongoClient mongo = new MongoClient("localhost", PORT);
        // // mongo.getDB("").dropDatabase();
        // DBCollection collection = mongo.getDB("").getCollection("");

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        _mongod.stop();
        _mongodExe.stop();

        // server.shutdownNow();
    }

    protected JsonResult createSuccessJsonResult() {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setErrorCode(JsonResult.CODE_SUCCESS);
        jsonResult.setMsg("success");
        return jsonResult;
    }

    protected JsonResult createFailJsonResult() {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setErrorCode(JsonResult.CODE_PARAM_ERROR);
        return jsonResult;
    }

}
