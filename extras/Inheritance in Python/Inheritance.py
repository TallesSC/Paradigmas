# inheritance.java -> python
# Programa de prática 3 em OO/Java feito em Python
# https://github.com/AndreaInfUFSM/elc117-2018a/blob/master/praticas/oo/java3/Inheritance.java

class Person:

    def __init__(self):
        self.name = "Fulano"
        print("Construtor de Person")

    def getName(self):
        return self.name

    def setName(self, n):
        self.name = n


class Student(Person):

    def __init__(self):
        super().__init__()
        self.course = "CC"
        print("Construtor de Student")

def main():
    p =  Person()
    s = Student()
    lis = [p,s]
    s.setName("Beltrano")
    for ref in lis:
        print(ref.getName())

if __name__ == "__main__":
    main()



#inheritance.java
#import java.util.ArrayList;
#class Person {
#  private String name;
#  public Person() {
#    System.out.println("Construtor de Person");
#    name = "Fulano";
#  }
#  public String getName() {
#    return name;
#  }
#  public void setName(String name) {
#    this.name = name;
#  }
#}
#
#class Student extends Person {
#  private String course;
#  public Student() {
#    System.out.println("Construtor de Student");
#    course = "CC";
#  }
#}
#
#class Main {
#  public static void main(String[] args) {
#    Person p = new Person();
#    Student s = new Student();
#    ArrayList<Person> lis = new ArrayList<Person>();
#    lis.add(p);
#    lis.add(s);
#    s.setName("Beltrano");
#    for (Person ref: lis) {
#      System.out.println(ref.getName());
#    }
#  }
#}
