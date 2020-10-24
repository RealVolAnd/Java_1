package Homework_5;

public class Main {
    public static void main(String[] args) {
        Person[] persArray = new Person[5];
        persArray[0] = new Person("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30);
        persArray[1] = new Person("Segeev Sergey", "Engineer", "sergs@mailbox.com", "892312313", 30000, 28);
        persArray[2] = new Person("Alekseev Aleksey", "Chief engeneer", "aleksaleks@mailbox.com", "892312222", 70000, 44);
        persArray[3] = new Person("Ivanova Mariya", "Chief Accountant", "ivanmariya@mailbox.com", "892312333", 60000, 48);
        persArray[4] = new Person("Borisov Boris", "CEO", "barborisov@mailbox.com", "892311111", 100000, 45);


        for (Person pers : persArray) {
            if (pers.getAge() > 40) System.out.println(pers.toString());
        }

        /*
        Дополнительное задание:
         */
        HelixArray array=new HelixArray(10,8);
        System.out.println(array.toString());
    }


}
