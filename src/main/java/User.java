public class User {

    final int MAX_AGE = 180;
    static final int MAX_AGE2 = 180;
    static final String HOST = "127.0.0.1";
    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
