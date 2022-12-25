import java.util.*;

class Movie {
    String mname;
    String genre;
    String lang;
    String rating;

    public Movie(String mname, String genre, String lang, String rating) {
        this.mname = mname;
        this.genre = genre;
        this.lang = lang;
        this.rating = rating;
    }

    public String getMname() {
        return mname;
    }

    public String getGenre() {
        return genre;
    }

    public String getLang() {
        return lang;
    }

    public String getRating() {
        return rating;
    }

}

class Theatre {
    String tname;

    public Theatre(String tname) {

        this.tname = tname;
    }
}

class Show {
    Movie mov;
    Theatre th;
    String date;
    String type;

    public Show(Movie mov, Theatre th, String date, String type) {
        this.mov = mov;
        this.th = th;
        this.date = date;
        this.type = type;

    }

    public void printShows() {

    }

}

interface SeatingArrangement {
    void showSeat();
}

class Seat extends Show implements SeatingArrangement {
    Show sh;
    boolean[][] seats;

    public Seat(Show sh) {
        super(sh.mov, sh.th, sh.date, sh.type);
        this.sh = sh;
        seats = new boolean[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                seats[i][j] = false;
            }
        }
    }

    @Override
    public void showSeat() {
        System.out.println("_______________________");
        System.out.println("    SCREEN THIS WAY    ");
        System.out.println("                       ");
        System.out.println("_______________________");
        for (int i = 0; i < 10; i++) {
            System.out.print((char) (i + 65) + " | ");
            for (int j = 0; j < 10; j++) {
                if (seats[i][j]) {
                    System.out.print("X ");
                } else {
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
        System.out.println("-----------------------");
        System.out.println("Loading ...");
    }
    // public static void main(String[] args) {
    //
    //
    // }
}

class DoubleBookingException extends Exception {
    DoubleBookingException(String s) {
        super(s);
    }
}

public class MovieBooking extends Thread {
    Show sh;
    // Customer c;
    String seats;
    int total;

    // public Booking(Show sh,Customer c) {
    // this.sh=sh;
    // this.c=c;
    // }
    public static void Menu() {
        System.out.println("*****MENU*****");
        System.out.println("1.Available Shows");
        System.out.println("2.Book tickets");
        System.out.println("3.Add Movie");
        System.out.println("4.Add Theatre");
        System.out.println("5.Add Show");
        System.out.println("6.Exit");

    }

    public static void addMovie(ArrayList<Movie> m) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter movie name : ");
        String mname = sc.nextLine();
        System.out.print("Enter genre : ");
        String genre = sc.nextLine();
        System.out.print("Enter language : ");
        String lang = sc.nextLine();
        System.out.print("Enter rating : ");
        String rating = sc.nextLine();
        m.add(new Movie(mname, genre, lang, rating));

    }

    public static void addTheatre(ArrayList<Theatre> t) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter theatre name : ");
        String tname = sc.nextLine();
        t.add(new Theatre(tname));

    }

    public static void addShow(ArrayList<Movie> m, ArrayList<Theatre> t, ArrayList<Show> sh, ArrayList<Seat> st) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------------------------------------");
        System.out.println("List of movies : ");
        System.out.println("--------------------------------------------");
        int c1 = 1;
        for (Movie mov : m) {
            System.out.println(c1 + " " + mov.mname + " " + mov.genre + " " + mov.lang + " " + mov.rating);
            c1++;
        }
        System.out.println("-------------------------");
        System.out.println("List of theatre : ");
        System.out.println("-------------------------");
        int c2 = 1;
        for (Theatre th : t) {
            System.out.println(c2 + " " + th.tname);
            c2++;
        }

        System.out.println("Select Movie : ");
        int s1 = sc.nextInt();
        sc.nextLine();
        System.out.println("Select Theatre : ");
        int s2 = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter date : ");
        String date = sc.nextLine();
        System.out.println("Enter type of show : ");
        String type = sc.nextLine();
        sh.add(new Show(m.get(s1 - 1), t.get(s2 - 1), date, type));
        st.add(new Seat(sh.get(sh.size() - 1)));
        System.out.println("SHOW ADDED !!");

    }

    public static void bill(Show sh, Seat st, int nooftickets, String ticket, String name) {
        System.out.println("******************************");
        System.out.println("*            BILL            *");
        System.out.println("******************************");
        System.out.println("*customer name : " + name);
        System.out.println("*Movie Booked  : " + sh.mov.mname);
        System.out.println("*Date          : " + sh.date);
        System.out.println("*Show Type     : " + sh.type);
        System.out.println("*No of Seats   : " + nooftickets);
        System.out.println("*Seats Booked  : " + ticket);
        System.out.println("*Total cost    : Rs." + nooftickets * 175);
        System.out.println("******************************");

    }

    public static void printShow(ArrayList<Show> sh) {
        int c = 0;

        System.out.println("------------------------AVAILABLE SHOWS------------------------\n");
        for (Show s : sh) {
            System.out.println(c + 1 + " " + s.mov.mname + " " + s.mov.genre + " " + s.mov.lang + " " + s.type + " "
                    + s.date + " " + s.th.tname);
            c++;
        }
    }

    public static void printShow(Show sh) {
        System.out.println("******************************************************************");
        System.out.println(sh.mov.mname + " " + sh.mov.genre + " " + sh.mov.lang + " " + sh.type + " " + sh.date + " "
                + sh.th.tname);
        System.out.println("******************************************************************");
    }

    public static void book(ArrayList<Seat> st, ArrayList<Show> sh) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name : ");
        String name = sc.nextLine();
        // System.out.print("Enter your pno : ");
        // String pno = sc.nextLine();
        // cust.add(new Customer(name,pno));
        System.out.print("\nEnter a show :");
        int ch = sc.nextInt();
        int nooftickets = 0;
        StringBuffer ticket = new StringBuffer();
        int flag = 0;
        while (true) {
            System.out.println("");

            // shows the seat
            System.out.println("Movie : " + sh.get(ch - 1).mov.mname);
            new Thread() {
                @Override
                public void run() {
                    printShow(sh.get(ch - 1));
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    st.get(ch - 1).showSeat();
                }
            }.start();
            try {

                Thread.sleep(3000);
            } catch (Exception e) {
            }

            System.out.print("\nEnter row no :");
            String rno = sc.next();

            char row = rno.charAt(0);
            System.out.print("\nEnter column no :");
            int cno = sc.nextInt();

            sc.nextLine();
            int ascii = (int) row;
            if (st.get(ch - 1).seats[ascii - 65][cno] == true) {
                try {
                    flag = 1;
                    throw new DoubleBookingException("Seat is already booked!");
                } catch (Exception e) {
                    System.out.println(e);
                }

            } else {
                st.get(ch - 1).seats[ascii - 65][cno] = true;
                ticket.append(rno);
                ticket.append(Integer.toString(cno));
                nooftickets++;

            }

            System.out.print("\nDo you want to select another seat (y/n) : ");
            String redo = sc.nextLine();
            if (redo.equals("n")) {
                bill(sh.get(ch - 1), st.get(ch - 1), nooftickets, ticket.toString(), name);
                break;
            }
            if (flag == 0) {
                ticket.append(" ");
            }
            flag = 0;

        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // define Movies
        ArrayList<Movie> m = new ArrayList<>();
        m.add(new Movie("Avatar", "Thriller", "English", "U"));
        m.add(new Movie("Connect", "Horror", "Tamil", "U/A"));
        m.add(new Movie("Lal Singh Chadda", "Drama", "Hindi", "U/A"));

        // define Theatres
        ArrayList<Theatre> t = new ArrayList<>();
        t.add(new Theatre("Sathiyam"));
        t.add(new Theatre("Palazzo"));

        // define shows
        ArrayList<Show> sh = new ArrayList<>();
        sh.add(new Show(m.get(0), t.get(0), "07/11/2022", "Matnee show"));
        sh.add(new Show(m.get(0), t.get(1), "07/11/2022", "Evening show"));
        sh.add(new Show(m.get(1), t.get(0), "22/08/2022", "Morning show"));
        sh.add(new Show(m.get(1), t.get(1), "22/08/2022", "Night show"));

        // define seats for each show
        ArrayList<Seat> st = new ArrayList<>();
        st.add(new Seat(sh.get(0)));
        st.add(new Seat(sh.get(1)));
        st.add(new Seat(sh.get(2)));
        st.add(new Seat(sh.get(3)));

        // //define customers
        // ArrayList<Customer> cust = new ArrayList<>();

        while (true) {
            Menu();
            System.out.print("\nEnter choice from menu :");
            int ch = sc.nextInt();
            sc.nextLine();// beacause of nextInt
            switch (ch) {
                case 1:
                    printShow(sh);
                    break;
                case 2:
                    book(st, sh);
                    break;
                case 3:
                    addMovie(m);
                    break;
                case 4:
                    addTheatre(t);
                    break;
                case 5:
                    addShow(m, t, sh, st);
                    break;
                case 6:
                    System.exit(0);
                    break;
            }

        }

    }
}
