public class ID {
    protected static int id = 0;

    public static int generateId() {
        return ++id;
    }
}
