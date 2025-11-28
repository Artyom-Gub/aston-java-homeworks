package www.aston.com.task1.components;

public class UserHashMap{
    private Bucket[] map = new Bucket[16];

    private class Bucket {
        private String key;
        private int value;
        Bucket next;
        Bucket prev;

        Bucket(String key, int value, Bucket next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public boolean put(String key, int value) {
        if(key == null) return false;

        int index = hashCode(key) & (map.length - 1);
        Bucket targetBucket = map[index];

        while(targetBucket != null) {
            if(hashCode(targetBucket.key) == hashCode(key)) {
                if(targetBucket.key.equals(key)) {
                     return false;
                }

                targetBucket.value = value;
                return true;
            }

            targetBucket = targetBucket.next;
        }

        map[index] = new Bucket(key, value, map[index]);
        return true;
    }

    public Boolean remove(String key) {
        if (key == null) return false;

        int index = hashCode(key) & (map.length - 1);
        Bucket targetBucket = map[index];
        Bucket prev = null;

        while (targetBucket != null) {
            if(hashCode(targetBucket.key) == hashCode(key)) {
                if (targetBucket.key.equals(key)) {
                    if (prev == null) {
                        map[index] = targetBucket.next;
                    } else {
                        prev.next = targetBucket.next;
                    }

                    return true;
                }
            }

            prev = targetBucket;
            targetBucket = targetBucket.next;
        }
        return false;
    }

    public Integer get(String key) {
        if(key == null) return null;

        int index = hashCode(key) & (map.length - 1);
        Bucket targetBucket = map[index];

        while(targetBucket != null) {
            if(hashCode(targetBucket.key) == hashCode(key)) {
                if(targetBucket.key.equals(key)) {
                    return targetBucket.value;
                }
            }

            targetBucket = targetBucket.next;
        }

        return null;
    }

    int hashCode(String key) {
        int hash = key.hashCode();

        return hash ^ (hash >>> 16);
    }
}
