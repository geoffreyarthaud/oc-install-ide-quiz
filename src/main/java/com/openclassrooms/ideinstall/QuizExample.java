package com.openclassrooms.ideinstall;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

import ij.IJ;
import ij.ImagePlus;

public class QuizExample {

	public static void main(String[] args) throws IOException, InterruptedException {
		ArrayList<String> animals = new ArrayList<String>();
		animals.add("hibou");
		animals.add("lama");
		animals.add("lapin");
		animals.add("oiseau");
		animals.add("renard");

		final Path cheminImage = cheminFichier(animals.get(choixSecret()));
		final ImagePlus imp = IJ.openImage(cheminImage.toString());
		imp.show();
		Files.delete(cheminImage);
		Files.delete(cheminImage.getParent());
	}

	public static int choixSecret() {
		return (int) (Math.random() * 4.99); // Mettre 5 aurait pu lancer une Exception, (à très faible probabilité, si
												// Math.RANDOM = 1 .) JAVA tronque le résultat, ce qui garantit
		// un résultat de maximum 4.
	}

	public static Path cheminFichier(String image) throws IOException {
		final String nomImage = image + ".jpg";
		final Path tmpDir = Files.createTempDirectory(image);
		final Path cheminFichier = tmpDir.resolve(nomImage);
		Files.copy(QuizExample.class.getClassLoader().getResourceAsStream(nomImage), cheminFichier);
		return cheminFichier;
	}
}
