package com.movies.repository;
import com.movies.controller.MoviesList;
import com.movies.service.CrudOperation;
import com.ripple.movies.validation.FieldValidation;
import com.ripple.movies.exception.InvalidInputException;
import java.util.Scanner;
import java.util.*;

public class MainClass {
	public static void main(String[] args) {
		CrudOperation crud = new CrudOperation();
		Scanner sc = new Scanner(System.in);
		while(true) {    
			System.out.println("Movie Management System");
			System.out.println("1.Add a Movie");
			System.out.println("2.Update a Movie");
			System.out.println("3.Delete a Movie");
			System.out.println("4.Display All Movies");
			System.out.println("5.Get a Movie by Id");
			System.out.println("6.Exit");
                
			int choice=sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
			case 1:
				String movieName,directorName,amountstr;
				double amount=0.0;
				while(true) {
				System.out.println("Enter the Movie Name: ");
				movieName=sc.nextLine();
				boolean isDuplicate = false;
				    for (MoviesList existingMovie : crud.getAllMovies()) {
				        if (existingMovie.getName().equalsIgnoreCase(movieName)) {
				            isDuplicate = true;
				            break;
				        }
				    }

				    if (isDuplicate) {
				        System.out.println("A movie with the same name already exists.");
				        continue; 
				    }

				    if (!FieldValidation.isValidationString(movieName)) {
				        System.out.println("Name should be a String");
				        continue;
				    } else {
				        break;
				    }
				}
			
				
				while(true) {
				System.out.println("Enter the Director Name: ");
				directorName=sc.nextLine();
				if(!FieldValidation.isValidationString(directorName)) {
					System.out.println("Director Name should be a String");
					continue;
				}else {
					break;
				}
				}
				while(true) {
				System.out.println("Enter the Amount: ");
				amountstr=sc.nextLine();
				try {
					if(!FieldValidation.isValidationInteger(amountstr)) {
						System.out.println("Please enter valid Integer");
						continue;
					}
					 amount=Double.parseDouble(amountstr);
					break;
				}catch(InvalidInputException e) {
					System.out.println(e.getMessage());
					continue;
				}
				}
					MoviesList list= new MoviesList(crud.getAllMovies().size() + 1, movieName,directorName,amount);
					crud.addMovie(list);
					System.out.println("Movie Added Successfully!!!");
					 
                    break;
					

				
			
			case 2:
				System.out.print("Enter the ID of the movie: ");
                int updatedId = sc.nextInt();
                sc.nextLine(); 
                MoviesList existingMovie = crud.getMovieById(updatedId);
                if (existingMovie == null) {
                    System.out.println("Movie not found.");
                } else {
                    System.out.println("Movie ID: " + existingMovie.getId());
                }
                
                System.out.println("Movie Name: " + existingMovie.getName());
				System.out.println("Enter the Updated Movie Name: ");
				String updatedMovieName=sc.nextLine();
				if(!updatedMovieName.isEmpty()) {
					existingMovie.setMovieName(updatedMovieName);
				}
				else {
					 updatedMovieName=existingMovie.getName();
				}
				System.out.println("Director Name: " + existingMovie.getDirectorName());
				System.out.println("Modify the Director Name: ");
				String updatedDirectorName=sc.nextLine();
				if(!updatedDirectorName.isEmpty()) {
					existingMovie.setDirectorName(updatedDirectorName);
				}
				else {
					 updatedDirectorName=existingMovie.getDirectorName();
				}
				System.out.println("Amount: " + existingMovie.getAmount());
				System.out.println("Modify the Amount: ");
				String updatedAmountStr=sc.nextLine();
				double updatedAmount=0.0;
				if(!updatedAmountStr.isEmpty()) {
					if (FieldValidation.isValidationInteger(updatedAmountStr)) {
                         updatedAmount = Double.parseDouble(updatedAmountStr);
                        existingMovie.setAmount(updatedAmount);
                    } else {
                        System.out.println("Invalid input for amount. Movie not updated.");
                    	
                    }
				}
					else {
						updatedAmount=existingMovie.getAmount();
					}
				
				
				crud.updateMovie(updatedId,updatedMovieName,updatedDirectorName,updatedAmount);
				System.out.println("Movie updated Successfully!!");
                break;
				
				
			case 3:
				System.out.println("Enter the Movie Id which has to be deleted: ");
				int deleteId=sc.nextInt();
				crud.deleteById(deleteId);
				System.out.println("Movie Deleted Successfully!!"); 
                break;
				
				
			case 4:
				List<MoviesList> allmovies=crud.getAllMovies();
				for(MoviesList m:allmovies) {
					System.out.println(m);
				}
                break;
				
			
			case 5:
				System.out.println("Enter the Id to get the Movie Details: ");
				int movieId=sc.nextInt();
				MoviesList retrievedMovie=crud.getMovieById(movieId);
				if(retrievedMovie!=null) {
					System.out.println(retrievedMovie);
				}
				else{
					System.out.println("Movie Not Found");
				}
				
                break;
				
				
			case 6:
				 System.out.println("Are you sure you want to exit? (Enter 'yes' to confirm, 'no' to continue)");
                 String confirmExit = sc.nextLine();
                 if (confirmExit.equalsIgnoreCase("yes")) {
                     sc.close();
                     System.out.println("Closed...."); 
                     return;
                 } 
                 else if(confirmExit.equalsIgnoreCase("no")) {
                	 continue;
                 }
                 break;
						
			default:
				System.out.println("Invalid Choice");
				break;
			
			}
			
		}
		
	}

}


