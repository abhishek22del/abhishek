package Company.StockRate;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CompanyStat 
{
   private List<Company> companies;
   private int lastReadIdx=0;

   // Simply read the CSV and return the next set of company data
   private Company getNextCompanyData() 
  {
      if (companies == null) 
      {
         lastReadIdx = 0;
         try 
         {
            loadCompanies();
         } 
         catch (Exception e)
         {
	 }
      }
      if (companies == null)
          return null;
      if (lastReadIdx < companies.size()) 
          return companies.get(lastReadIdx++);
      return null;
   }

   public void loadCompanies() throws Exception
   {
      Scanner s = null;
      try 
      {
         companies = new ArrayList<Company>();
         File f = new File("test.csv");
         s = new Scanner(new FileInputStream(f));
         String[] headers = readLine(s);
         System.out.println("headers: " + Arrays.toString(headers));
         if (headers != null && headers.length >0) 
         {
            String[] data = null;
            while ((data = readLine(s)) != null) 
            {
               System.out.println("data: " + Arrays.toString(data));
               if (data.length != headers.length) 
               {
                  companies = null;
                  throw new Exception("Invalid Data - headers count " + headers.length + " does not match with data count "+data.length);
               }
               String year = data[0];
               String month = data[1];
               for (int x=2; x<data.length; x++) 
               {
                  double price = new Double(data[x]).doubleValue();
                  Company company = new Company(headers[x], year, month, price);
                  companies.add(company);
               }
            }
         }
      } 
      finally
      {
         if (s != null) s.close();
      }
   }

   private String[] readLine(Scanner s)
  {
      if (s.hasNextLine()
         return s.nextLine().trim().split(",");
      
      return null;
   }

   public void processCompanies() 
   {
      Map<String, Company> companies = new TreeMap<String, Company>();
      Company newCompany = null;

      // repeat until all company data processed from CSV file
      while ((newCompany = getNextCompanyData()) != null) 
      {
         Company oldCompany = companies.get(newCompany.getName());
         if (oldCompany == null || newCompany.getPrice() > oldCompany.getPrice())
            companies.put(newCompany.getName(), newCompany);
      }
     
      for (String name : companies.keySet()) 
      {
         Company company = companies.get(name);
         System.out.println(company.getName() + " highest price " + company.getPrice() + " is " + company.getMonth() + " " + company.getYear());
      }
   }

   public static void main(String[] args) 
   {
      CompanyStat loader = new CompanyStat();
      loader.processCompanies();
   }
}
