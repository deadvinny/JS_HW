//Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
//        Создать множество ноутбуков.
//        Написать метод, который будет запрашивать у пользователя критерий (или критерии)
//        фильтрации и выведет ноутбуки, отвечающие фильтру. Критерии фильтрации можно хранить в Map.
//        Например:
//        “Введите цифру, соответствующую необходимому критерию:
//        1 - ОЗУ
//        2 - Объем ЖД
//        3 - Операционная система
//        4 - Цвет …
//        Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.
//        Отфильтровать ноутбуки из первоначального множества и вывести проходящие по условиям.
//        Работу сдать как обычно ссылкой на гит репозиторий
//        Частые ошибки:
//        1. Заставляете пользователя вводить все существующие критерии фильтрации
//        2. Невозможно использовать более одного критерия фильтрации одновременно
//        3. При выборке выводятся ноутбуки, которые удовлетворяют только одному фильтру, а не всем введенным пользователем
//        4. Работа выполнена только для каких то конкретных ноутбуков, и если поменять характеристики ноутбуков
//        или добавить еще ноутбук, то программа начинает работать некорректно

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<Integer, String> criteries = new HashMap<>();
        Map<Integer, String> values = new HashMap<>();
        List<Computer> computerList = new ArrayList<>();
        int criteria = 1;
        computerList.add(new Computer("Lenovo ideapad Gaming", 32, "Intel_core_i7", 1000,
                "Windows11", "Black"));
        computerList.add(new Computer("Lenovo deadspace", 8, "Intel_core_i5", 512,
                "Windows10", "White"));
        computerList.add(new Computer("Lenovo death", 4, "Intel_core_i3", 128,
                "Windows7", "Pink"));
        computerList.add(new Computer("Mini PC Mac M2", 8, "Apple_M2", 512,
                "macOS_X", "Chromium"));
        computerList.add(new Computer("Monoblock Apple iMac", 8, "Apple_M1", 256,
                "macOS_X", "Silver"));
        Market market = new Market(computerList);
        Scanner scanner = new Scanner(System.in);
        criteries.put(1, "ОЗУ");
        criteries.put(2, "Объем ЖД");
        criteries.put(3, "Операционная система");
        criteries.put(4, "Цвет");
        criteries.put(5, "Процессор");
        while (true) {

            System.out.println("Введите цифру, соответствующую необходимому критерию или 0, если достаточно:");
            for (int k : criteries.keySet()) {
                if (!values.containsKey(k)) System.out.println(k + " - " + criteries.get(k));
            }
            criteria = scanner.nextInt();
            if (criteria == 0) break;
            System.out.println("Введите минимальное значение для критерия: ");
            String value = scanner.next();
            values.put(criteria, value);
        }
        market.showNotebook(values);

    }

    public static class Computer {
        private String Name;
        private int ram;
        private String cpu;
        private int hdd;
        private String OC;
        private String color;

        public Computer(String name, int ram, String cpu, int hdd, String OC, String color) {
            Name = name;
            this.ram = ram;
            this.cpu = cpu;
            this.hdd = hdd;
            this.OC = OC;
            this.color = color;
        }

        public boolean isMatch(int criteria, String value) {
            boolean isMatch = true;
            switch (criteria) {
                case 1 -> isMatch = ram >= Integer.parseInt(value);
                case 2 -> isMatch = hdd >= Integer.parseInt(value);
                case 3 -> isMatch = OC.equals(value);
                case 4 -> isMatch = color.equals(value);
                case 5 -> isMatch = cpu.equals(value);
            }
            return isMatch;
        }

        @Override
        public String toString() {
            return "Computer{" +
                    "Name='" + Name + '\'' +
                    ", ram=" + ram +
                    ", cpu='" + cpu + '\'' +
                    ", hdd=" + hdd +
                    ", OC='" + OC + '\'' +
                    ", color='" + color + '\'' +
                    '}';
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public int getRam() {
            return ram;
        }

        public void setRam(int ram) {
            this.ram = ram;
        }

        public String getCpu() {
            return cpu;
        }

        public void setCpu(String cpu) {
            this.cpu = cpu;
        }

        public int getHdd() {
            return hdd;
        }

        public void setHdd(int hdd) {
            this.hdd = hdd;
        }

        public String getOC() {
            return OC;
        }

        public void setOC(String OC) {
            this.OC = OC;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    public static class Market {
        List<Computer> array;
        public Market(List<Computer> array) {
            this.array = array;
        }
        public Market() {
            this(new ArrayList<>());
        }

        public void showNotebook(Map<Integer, String> criteries) {
            for (Computer c : array) {
                boolean isMatch = true;
                for (int k : criteries.keySet()) {
                    isMatch = c.isMatch(k, criteries.get(k));
                }
                if (isMatch) System.out.println(c.toString());
            }
        }
        public void showNotebook(int criteria, String value) {
            for (Computer c : array) {
                if (c.isMatch(criteria, value)) System.out.println(c.toString());
            }
        }
        public List<Computer> getArray() {
            return array;
        }
        public void setArray(List<Computer> array) {
            this.array = array;
        }
    }
}
