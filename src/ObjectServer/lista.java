package ObjectServer;

public class lista {

    final int maxSize = 100;
    public String[] names = new String[maxSize];
    public String[] hosts = new String[maxSize];
    public int[] onOff = new int[maxSize];
    public int dirSize = 0;

    public int search(Object s) {
        for (int i = 0; i < dirSize; i++) {
            if (names[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }

    public int insert(String s, String hostName, int stan) {
        int oldIndex = search(s);
        if ((oldIndex == -1) && (dirSize < maxSize)) {
            names[dirSize] = s;
            hosts[dirSize] = hostName;
            onOff[dirSize] = stan;
            dirSize++;
            return 1;
        }
        return 0;
    }

    public int getOnOff(int index) {
        return onOff[index];
    }

    public String getHostName(int index) {
        return hosts[index];
    }
}
