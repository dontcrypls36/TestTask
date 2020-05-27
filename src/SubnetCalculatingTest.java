import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class SubnetCalculatingTest {

    @Test
    void getSubNetForOneAddress() {
        String ip1 = "192.168.1.1";
        Network tt = new Network();
        tt.setIpSet(new HashSet<>(Arrays.asList(ip1)));
        assertThrows(IllegalArgumentException.class, tt::getSubNet);
    }

    @Test
    void testTaskRandom3rd() {
        Network tt = new Network();
        tt.setIpSet(new HashSet<>(Arrays.asList(generateIps(10000, false, false, true))));
        assertEquals("192.100.XXX.XXX", tt.getSubNet());
    }

    @Test
    void testTaskRandom2nd3rd() {
        Network tt = new Network();
        tt.setIpSet(new HashSet<>(Arrays.asList(generateIps(10000, false, true, true))));
        assertEquals("192.XXX.XXX.XXX", tt.getSubNet());
    }

    @Test
    void testTaskRandomAll() {
        Network tt = new Network();
        tt.setIpSet(new HashSet<>(Arrays.asList(generateIps(10000, true, true, true))));
        assertEquals("XXX.XXX.XXX.XXX", tt.getSubNet());
    }

    @Test
    void testTaskRandom1st3rd() {
        Network tt = new Network();
        tt.setIpSet(new HashSet<>(Arrays.asList(generateIps(10000, true, false, true))));
        assertEquals("XXX.XXX.XXX.XXX", tt.getSubNet());
    }

    @Test
    void testTaskRandom1st2nd() {
        Network tt = new Network();
        tt.setIpSet(new HashSet<>(Arrays.asList(generateIps(10000, true, true, false))));
        assertEquals("XXX.XXX.XXX.XXX", tt.getSubNet());
    }

    @Test
    void testTasRandom2nd() {
        Network tt = new Network();
        tt.setIpSet(new HashSet<>(Arrays.asList(generateIps(10000, false, true, false))));
        assertEquals("192.XXX.XXX.XXX", tt.getSubNet());
    }

    @Test
    void testTaskNoRandom() {
        Network tt = new Network();
        tt.setIpSet(new HashSet<>(Arrays.asList(generateIps(10000, false, false, false))));
        assertEquals("192.100.99.XXX", tt.getSubNet());
    }

    public static String[] generateIps(int count, boolean randomOctet1, boolean randomOctet2, boolean randomOctet3) {
        String[] ips = new String[count];
        for (int i = 0; i < ips.length; i++) {
            String octet1 = randomOctet1 ? "" + Math.round(Math.random() * 255) : "192";
            String octet2 = randomOctet2 ? "" + Math.round(Math.random() * 255) : "100";
            String octet3 = randomOctet3 ? "" + Math.round(Math.random() * 255) : "99";
            String octet4 = "" + Math.round(Math.random() * 255);
            ips[i] = octet1 + "." + octet2 + "." + octet3 + "." + octet4;
        }
        return ips;
    }
}