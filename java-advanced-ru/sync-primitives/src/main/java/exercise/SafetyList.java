package exercise;


class SafetyList {

    // BEGIN

    private Object[] list = new Object[0];

    public int getSize() {
        return list.length;
    }

    public Object get(int i) {
        return list[i];
    }

    public synchronized void add(Object o) {
        int newIndex = list.length;
        Object[] newList = new Object[newIndex + 1];
        System.arraycopy(list, 0, newList, 0, newIndex);
        newList[newIndex] = o;
        list = newList;
    }
    // END
}
