package Lab0;

class Person {
    private String name;
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
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
    public void sayHello() {
        System.out.println("Hello, my name is " + name );
    }
    public static void compareAge(Person p1, Person p2){
        if(p1.age > p2.age){
            System.out.println(p1.name + "is older than " + p2.name);
        }
        else {
            System.out.println(p1.name + "is older than " + p2.name);
        }
    }
}