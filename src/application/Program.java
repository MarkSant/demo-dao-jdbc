package application;
import modelEntities.*;
import java.util.*;

public class Program {
		,public static void main(String[] args) {
				Department department = new Department(1, "Books");
				Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(), 3000.00, department);
				System.out.println(department);
				System.out.println(seller);
				
		}
}
