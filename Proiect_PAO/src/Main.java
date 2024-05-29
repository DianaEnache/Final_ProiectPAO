import services.*;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

       DBfunctions db = new DBfunctions();
       Connection conn = db.connect_to_db("ProiectDB","postgres","IOANA1234");
       GameService gameService = new GameServiceImpl(conn);
       ClientService clientService = new ClientServiceImpl(conn);
       StockService stockService  = new StockServiceImpl(conn);
       PurchaseService purchaseService = new PurchaseServiceImpl(conn);
       ReviewService reviewService =new ReviewServiceImpl(conn);

        Scanner scanner = new Scanner(System.in);
        int choice;



        do {
            System.out.println("----- Meniu Principal -----");
            System.out.println("User Type:");
            System.out.println("1. Client ");
            System.out.println("2. Admin  ");
            System.out.println("3. Exit");
            System.out.print("Alegeti optiunea dorita ");


            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("----------Meniu Client-----------");
                    System.out.println("Este nevoie de logare/creare cont");
                    scanner.nextLine();
                    System.out.print("Username:");
                    String clientName = scanner.nextLine();
                    int clientId = clientService.getClientIdByName(clientName);
                    if (clientId != -1) {
                        System.out.println("Clientul " + clientName + " există deja.");
                    } else {
                        System.out.print("Email: ");
                        String clientEmail = scanner.nextLine();
                        clientId = clientService.addClient(clientName, clientEmail);
                        System.out.println("Creare contului realizata!");
                    }

                    int clientChoice;
                    do {
                        System.out.println("----- Meniu Client -----");
                        System.out.println("1. Vezi jocuri disponibile");
                        System.out.println("2. Cumpara joc");
                        System.out.println("3. Istoric achizitii");
                        System.out.println("4. Adauga recenzie");
                        System.out.println("5. Iesire din cont");
                        System.out.print("Alegeti o opțiune: ");
                        clientChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (clientChoice) {
                            case 1:
                                gameService.displayGames();
                                System.out.print("Pentru ce joc doresti sa vezi recenziile? : ");
                                String gameName = scanner.nextLine();
                                reviewService.displayReviewsByGameName(gameName);

                                break;
                            case 2:
                                System.out.print("Introdu ID-ul jocului: ");
                                int gameId = scanner.nextInt();
                                System.out.print("Introdu tipul tranzacției (card/points): ");
                                scanner.nextLine();
                                String transactionType = scanner.nextLine();
                                //adaugare date card sau points/  jocul ar trebui sa aiba si o coloana points care echivaleaza pretul
                                purchaseService.addPurchase(clientId, gameId, transactionType);
                                break;
                            case 3:

                                purchaseService.displayPurchaseByClient(clientId);
                                break;
                            case 4:
                                gameService.displayGames();

                                scanner.nextLine();
                                System.out.println("Adaugare recenzie");

                                System.out.print("Ce ID are jocul: ");
                                Integer idGame = scanner.nextInt();
                                scanner.nextLine();

                                System.out.print("Text recenzie (max 200 caractere): ");
                                String text = scanner.nextLine();

                                System.out.print("Rating (1.0 - 10.0) : ");
                                Double rating = scanner.nextDouble();
                                scanner.nextLine();

                                reviewService.addReview(clientId, idGame, text, rating);

                                break;
                            case 5:
                                System.out.println("Deconectare...");
                                break;
                            default:
                                System.out.println("Optiune invalida");
                                break;
                        }
                    } while (clientChoice != 5);
                    break;
                case 2:
                    System.out.println("--------Meniu Admin---------");
                    // Verificare parola si username
                    int adminChoice;
                    do {
                        System.out.println("----- Meniu Admin -----");
                        System.out.println("1.Lista jocuri");
                        System.out.println("2.Adauga Joc");
                        System.out.println("3.Modifica Detalii Joc");
                        System.out.println("4.Sterge Stock");
                        System.out.println("5.Adauga Stock");
                        System.out.println("6.Modifica Stock");
                        System.out.println("7.Lista stock-uri");
                        System.out.println("8.Lista Clienti");
                        System.out.println("9.Iesire din cont");
                        System.out.print("Alegeți o opțiune: ");
                        adminChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (adminChoice) {
                            case 1:
                                gameService.displayGames();
                                break;
                            case 2:
                                System.out.print("Nume joc: ");
                                String numeJoc = scanner.nextLine();
                                scanner.nextLine();

                                System.out.print("Categorie joc:");
                                String categorieJoc = scanner.nextLine();

                                System.out.print("Developer");
                                String developer = scanner.nextLine();

                                System.out.print("Pret");
                                Double pret = scanner.nextDouble();

                                System.out.print("Pret");
                                String platforma = scanner.nextLine();
                                scanner.nextLine();

                                gameService.addGame(numeJoc, categorieJoc, developer, pret, platforma);

                                break;
                            case 3:
                                //modificare detalii joc
                                System.out.println("Coming soon");
                                break;
                            case 4:

                                System.out.println("Stocul pe care doriti sa-l stergeti:");
                                Integer stocid = scanner.nextInt();
                                scanner.nextLine();
                                stockService.deleteStock(stocid);
                                break;

                            case 5:
                                System.out.print("Exemplare: ");
                                Integer exemplare = scanner.nextInt();
                                scanner.nextLine();

                                System.out.print("ID joc:");
                                Integer idjoc = scanner.nextInt();
                                scanner.nextLine();

                                stockService.addStock(exemplare,idjoc);

                                break;
                            case 6:
                               //modificare stock
                                System.out.println("Coming soon");
                                break;
                            case 7:
                               stockService.displayStocks();
                                break;
                            case 8:
                                clientService.displayClients();
                                break;
                            case 9:
                                System.out.println("Deconectare...");
                                break;
                            default:
                                System.out.println("Optiune invalida");
                                break;
                        }

                    } while (choice != 9);
                    break;


            }
        }while (choice != 3);

        scanner.close();
            // ADD DELETE DISPLAY SI UPDATE BAZA DE DATE
            //gameService.addGame("GTA V", "actiune", "Rockstar Games",87.0, "PC");
            //gameService.deleteGame("GTA V");
            //gameService.displayGames();

            //clientService.addClient("Ion Marar","ion123@gmail.com");
            //clientService.deleteClient("Ion Marar");
            //clientService.displayClients();
            //clientService.updateClient(1, "client_name", "Diana Boloboc");

            //stockService.updateStock(7,"game_id",2);
            //stockService.addStock(200,2);
            //stockService.deleteStock(13);
            //stockService.displayStocks();

            //purchaseService.addPurchase(3,1,"card",50.0);
            //purchaseService.deletePurchase(3);
            //purchaseService.displayPurchase();
            //purchaseService.updatePurchase(5,"transaction_type","points");

            //reviewService.addReview(2,3,"I liked it!",10.0);
            //reviewService.addReview(3,3,"I liked it!",10.0);
            //reviewService.deleteReview(2);
            //reviewService.displayReview();
            //reviewService.updateReview(1,"review_text","YEEEY");


            //creare si inserare in baza de date

            //db.create_card_trans(conn,"CardTransaction");
            //db.create_points_trans(conn,"PointsTransaction");
            //db.create_purchase(conn,"Purchase");
            //db.create_reviews(conn,"Reviews");
            //db.delete_table(conn,"Reviews");
            //db.create_stock(conn,"Stock");
            //db.create_admin(conn,"Admin");
            //db.create_table(conn,"Client");
            //db.insert_row(conn,"Game","Resident Evil 5","actiune","Capcom", 50.0,"PS3");
            //db.insert_col(conn,"client","email");


            //INSERARE
        /*--------------TABEL GAME----------
        db.insert_row(conn,"Game","Resident Evil 1","survival-horror","Capcom", 80.0,"PS3");
        db.insert_row(conn,"Game","Resident Evil 3","survival-horror","Capcom", 90.0,"PS5");
        db.insert_row(conn,"Game","Resident Evil 4","survival-horror","Capcom", 80.0,"PS4");
        db.insert_row(conn,"Game","Red Dead Redemption 1","action-adventure","Rockstar Games", 90.0,"PS5");
        db.insert_row(conn,"Game","Red Dead Redemption 2","action-adventure","Rockstar Games", 130.0,"PS5");


        //--------------TABEL Client----------

        db.insert_client(conn,"Client","Diana Popescu","diana_pop@gamil.com");
        db.insert_client(conn,"Client","Maria Diaconescu","maria_dia@gamil.com");
        db.insert_client(conn,"Client","Sorin Iliescu","sorin_ilie@gamil.com");
        db.insert_client(conn,"Client","Codrin Bradea","codrinel_bra@gamil.com");


        //TABEL ADMIN
        db.insert_admin(conn,"Admin","Mihai Colb","123456");
        db.insert_admin(conn,"Admin","Mihaela Racaru","mihaela23");


        //Tabel Purchase
        db.insert_purchase(conn,"Purchase",1,1,"points",50.0);
        db.insert_purchase(conn,"Purchase",1,2,"card",80.0);
        db.insert_purchase(conn,"Purchase",2,3,"card",90.0);
        db.insert_purchase(conn,"Purchase",3,5,"card",90.0);


        //Tabel STOCK
        db.insert_stock(conn,"Stock",1,100);
        db.insert_stock(conn,"Stock",2,20);
        db.insert_stock(conn,"Stock",3,30);
        db.insert_stock(conn,"Stock",4,40);
        db.insert_stock(conn,"Stock",5,50);
        db.insert_stock(conn,"Stock",6,60);


        db.insert_points(conn,"Pointstransaction",1,200);
        db.insert_card(conn,"Cardtransaction",2,"123345567161514");
        db.insert_card(conn,"Cardtransaction",3,"1233455671678654");
        db.insert_card(conn,"Cardtransaction",4,"1233455671005143");

        db.insert_review(conn,"Reviews",1,1,"Great!");
         */

    }
}