import java.util.HashSet;
import java.util.Set;


// more effective than tree method, if we want to find subnet
// then if tree has already created, search will be more effective with tree
public class TestTask {

    public static final String MASK = "%s.%s.%s.%s";
    private static final String SUB_MASK = "XXX";

    private Set<String> ipSet = new HashSet<>();

    public Set<String> getIpSet() {
        return ipSet;
    }

    public void setIpSet(Set<String> ipSet) {
        this.ipSet = ipSet;
    }

    public String getSubNet() {
        if (ipSet.size() < 2) {
            throw new IllegalArgumentException("IPs count must be greater than one");
        }
        String[] ips = ipSet.toArray(new String[0]);
        String octet1, octet3, octet2, octet4;
        String[] firstIp = ips[0].split("\\.");
        octet1 = firstIp[0];
        octet2 = firstIp[1];
        octet3 = firstIp[2];
        octet4 = firstIp[3];
        for (int i = 1; i < ips.length; i++) {
            String[] bytes = ips[i].split("\\.");
            if (!octet1.equals(bytes[0])) {
                octet1 = octet2 = octet3 = octet4 = SUB_MASK;
                break;
            }
            if (!SUB_MASK.equals(octet1)) {
                if (!octet2.equals(bytes[1])) {
                    octet2 = octet3 = octet4 = SUB_MASK;
                    continue;
                }
                if (!SUB_MASK.equals(octet2)) {
                    if (!octet3.equals(bytes[2])) {
                        octet3 = octet4 = SUB_MASK;
                        continue;
                    }
                    if (!SUB_MASK.equals(octet3) && !octet4.equals(bytes[3])) {
                        octet4 = SUB_MASK;
                    }
                }
            }
        }
        return String.format(MASK, octet1, octet2, octet3, octet4);
    }

}
