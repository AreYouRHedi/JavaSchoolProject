package main;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import domaine.EcoleFactory;

public class Main {

  public static void main(String[] args) {
    // Création de GestionEcole
	  String configPath="dependencies.properties";
	  Properties properties= new Properties();
	  
	  Path p = FileSystems.getDefault().getPath(configPath);
	  try(InputStream in = Files.newInputStream(p)) {
		  properties.load(in);
	  }catch(IOException e) {
		  e.printStackTrace();
	  }
	  Class classe;
	try {
		classe = Class.forName(properties.getProperty("domaine.EcoleFactory"));
		EcoleFactory factory;
		try {
			factory = (EcoleFactory) classe.newInstance();
			GestionEcole gestionEcole = new GestionEcole(factory);
			  gestionEcole.gererEcole();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    // Utilisation de gestionEcole
	  
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
    
  }

}
