package Lab0;

class Main {
    public static void main(String[] args) {
        Person person1 = new Person("Stephy", 19);
        Person person2 = new Person("Gheorghe", 20);
        person1.sayHello();
        person2.sayHello();
        Person.compareAge(person1, person2);
    }
}
