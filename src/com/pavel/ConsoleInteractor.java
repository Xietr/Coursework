package com.pavel;

import com.pavel.enums.BreakdownType;
import com.pavel.enums.WorkType;
import com.pavel.models.DiagnosisResponse;
import com.pavel.models.Job;
import com.pavel.workshop.Workshop;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.pavel.utils.Utils.getToday;
import static com.pavel.utils.Utils.parse;

class ConsoleInteractor {

    private final DataBaseManager dataBaseManager = new DataBaseManager();
    private final Scanner scanner = new Scanner(System.in);
    private final Workshop workshop = new Workshop();

    public ConsoleInteractor() {
        init();
    }

    private void init() {
        System.out.print("""
                Enter number what do you want to do:
                1 - Show current database
                2 - Add job manually
                3 - Diagnosis
                4 - Fully clear database 
                5 - Get average repair price by breakdown 
                6 - Get works by date
                7 - Exit
                """);
        int num;
        try {
            num = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Very witty \uD83E\uDD26");
            init();
            return;
        }
        switch (num) {
            case 1:
                dataBaseManager.printDatabase();
                init();
                break;
            case 2:
                dataBaseManager.addJob(getJobByScanner());
                init();
                break;
            case 3:
                initDiagnosis();
                init();
                break;
            case 4:
                dataBaseManager.clearAll();
                init();
                break;
            case 5:
                getAverageRepairPrice();
                init();
                break;
            case 6:
                getWorkBetweenDates();
                init();
                break;
            case 7:
                break;
            default:
                init();
                break;
        }
    }

    private void getWorkBetweenDates() {
        System.out.println("Enter from date");
        Date fromDate;
        while (true) {
            try {
                fromDate = getDate();
                break;
            } catch (ParseException e) {
                System.out.println("Try again");
            }
        }

        System.out.println("Enter to date");
        Date toDate;
        while (true) {
            try {
                toDate = getDate();
                break;
            } catch (ParseException e) {
                System.out.println("Try again");
            }
        }

        dataBaseManager.getWorkBetweenDates(fromDate, toDate);
    }

    private void initDiagnosis() {
        System.out.println("Enter of diagnosis subject name");
        DiagnosisResponse diagnosisResponse = workshop.makeDiagnosis(scanner.next());
        System.out.println("Price for diagnosis: " + diagnosisResponse.price +
                " Type of breakdown " + diagnosisResponse.breakdownType +
                "\nWould you like to repair it for about? y/n " + workshop.getAverageRepairPrice(diagnosisResponse.breakdownType));
        while (true) {
            String scannerStr = scanner.next();
            if (scannerStr.equals("y")) {
                workshop.repair(diagnosisResponse);
                System.out.println(diagnosisResponse.breakdownType + " breakdown fixed");
                break;
            } else if (scannerStr.equals("n")) {
                break;
            } else {
                System.out.println("\nWould you like to repair it for about? y/n ");
            }
        }
    }

    private void getAverageRepairPrice() {
        while (true) {
            try {
                BreakdownType breakdownType = getBreakdownType();
                System.out.println("Average repair price " + workshop.getAverageRepairPrice(breakdownType));
                break;
            } catch (InputMismatchException e) {
                System.out.println("I believe in you! Enter from 1 to 2");
            }
        }
    }

    private BreakdownType getBreakdownType() throws InputMismatchException {
        System.out.println("Enter work type(1 - HARDWARE OR 2 - SOFTWARE)");
        int nextInt = scanner.nextInt();
        if (Arrays.asList(1, 2).contains(nextInt)) {
            return BreakdownType.values()[nextInt - 1];
        } else {
            throw new InputMismatchException();
        }
    }

    private Job getJobByScanner() {
        System.out.println("Enter master name");
        String masterName = scanner.next();

        WorkType workType;
        while (true) {
            try {
                workType = getWorkType();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Try Again!");
            }
        }

        Date date;
        while (true) {
            try {
                date = getDate();
                break;
            } catch (ParseException e) {
                System.out.println("Try Again!");
            }
        }

        return new Job(masterName, workType, date);
    }

    private WorkType getWorkType() throws InputMismatchException {
        System.out.println("Enter work type(1 - DIAGNOSIS OR 2 - REPAIR)");
        int nextInt = scanner.nextInt();
        if (Arrays.asList(1, 2).contains(nextInt)) {
            return WorkType.values()[nextInt - 1];
        } else {
            throw new InputMismatchException();
        }
    }

    private Date getDate() throws ParseException {
        System.out.println("Enter date in format 01/01/2020) or just type today");
        String strDate = scanner.next();
        if (strDate.equals("today")) {
            return getToday();
        } else {
            return parse(strDate);
        }
    }
}